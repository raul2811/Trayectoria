from flask import Flask, render_template, request, redirect, session , flash ,make_response
from flask_paginate import Pagination, get_page_args
import secrets
from pymongo import MongoClient
from datetime import datetime ,timedelta

app = Flask(__name__)
app.secret_key = secrets.token_hex(16)
current_year = datetime.now().year

# Configuración de la base de datos
client = MongoClient('mongodb://localhost:27017')
db = client['Usuarios']
collection = db['users']

# Configuración de la duración de la sesión
app.permanent_session_lifetime = timedelta(minutes=30)

@app.errorhandler(404)
def page_not_found(error):
    # Registra el error en el sistema de registro de errores
    app.logger.error('Página no encontrada: %s', (request.path))
    
    # Renderiza la plantilla de error personalizada
    return render_template('pages/error.html', error_message='Página no encontrada'), 404

@app.errorhandler(500)
def internal_server_error(error):
    # Registra el error en el sistema de registro de errores
    app.logger.error('Error interno del servidor: %s', (error))
    
    # Renderiza la plantilla de error personalizada
    return render_template('pages/error.html', error_message='Error interno del servidor'), 500

# Función para obtener el rango de un usuario
def get_rank(username):
    user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
    if user:
        for u in user['usuarios']:
            if u['username'] == username:
                return u['rank']
    return None

# Verifica si el usuario actual es un administrador
def is_admin():
    if 'username' in session:
        username = session['username']
        user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
        if user:
            for u in user['usuarios']:
                if u['username'] == username:
                    return u['rank'] == 'admin'
    return False

# Elimina un usuario de la base de datos
@app.route('/eliminar_usuario', methods=['GET', 'POST'])
def eliminar_usuario():
    if not is_admin():
        return redirect('/')
    
    usuarios_eliminar = request.form.getlist('usuarios_eliminar')

    # Eliminar usuarios seleccionados
    usuarios_eliminados = []
    for usuario in usuarios_eliminar:
        if usuario != 'admin':
            # Eliminar usuario del array
            collection.update_one({}, {'$pull': {'usuarios': {'username': usuario}}})
            usuarios_eliminados.append(usuario)

    # Verificar si no quedan usuarios en el array
    usuarios_restantes = collection.find_one({}).get('usuarios', [])
    if not usuarios_restantes:
        # Eliminar el documento completo si no hay usuarios restantes
        collection.delete_one({})

    mensaje = None
    if usuarios_eliminados:
        mensaje = "Los siguientes usuarios han sido eliminados exitosamente: {}".format(", ".join(usuarios_eliminados))

    usuarios = get_usuarios()
    return render_template('pages/eliminar_usuario.html', usuarios=usuarios, mensaje=mensaje)

""

# Obtiene la información de un usuario
def get_user_info(username):
    user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
    if user:
        for u in user['usuarios']:
            if u['username'] == username:
                return u
    return None

# Actualiza la información de un usuario
def update_user_info(username, password):
    user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
    if user:
        for u in user['usuarios']:
            if u['username'] == username:
                u['password'] = password
                # Actualizar el usuario en la base de datos
                collection.update_one({'usuarios.username': username}, {'$set': {'usuarios.$': u}})
                break

# Actualiza el rango de un usuario
def update_user_rank(username, rank):
    user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
    if user:
        for u in user['usuarios']:
            if u['username'] == username:
                u['rank'] = rank
                # Actualizar el usuario en la base de datos
                collection.update_one({'usuarios.username': username}, {'$set': {'usuarios.$': u}})
                break
# Verifica si el usuario actual es un administrador
def is_admin_user(username):
    user_info = get_user_info(username)
    if user_info:
        return user_info['rank'] == 'admin'
    return False

# Actualiza la información de un usuario
def update_user(username, rank, password):
    collection.update_one(
        {'usuarios.username': username},
        {'$set': {'usuarios.$.rank': rank, 'usuarios.$.password': password}}
    )

# Crea un nuevo usuario
@app.route('/crear_usuario', methods=['GET', 'POST'])
def crear_usuario():
    if not is_admin():
        return render_template('pages/crear_usuario.html', error_message='No tienes acceso para crear usuarios')

    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        rank = request.form['rank']

        # Verificar si el usuario ya existe en la base de datos
        existing_user = collection.find_one({'usuarios': {'$elemMatch': {'username': username}}})
        if existing_user:
            return render_template('pages/crear_usuario.html', error_message='El usuario ya existe')

        # Crear el nuevo usuario
        new_user = {
            'username': username,
            'password': password,
            'rank': rank
        }

        # Agregar el nuevo usuario a la base de datos
        collection.insert_one({'usuarios': [new_user]})

        return render_template('pages/crear_usuario.html', success_message='Usuario creado exitosamente')

    return render_template('pages/crear_usuario.html', error_message=None, success_message=None)

# Obtiene los usuarios paginados
def get_usuarios(page=1, per_page=10):
    users = collection.find({})
    usuarios = []

    for user in users:
        for usuario in user.get('usuarios', []):
            username = usuario.get('username')
            rank = usuario.get('rank')
            if username and rank:
                usuarios.append({'username': username, 'rank': rank})

    total = len(usuarios)
    pagination_users = usuarios[(page - 1) * per_page: page * per_page]

    return pagination_users, total

# Muestra el gestor de usuarios
@app.route('/gestor_usuarios', methods=['GET', 'POST'])
def gestor_usuarios():
    if not is_admin():
        return redirect('/')

    # Obtener los parámetros de paginación
    page, per_page, offset = get_page_args(page_parameter='page', per_page_parameter='per_page')

    # Obtener los usuarios de acuerdo a la página y cantidad por página
    usuarios, total = get_usuarios(page, per_page)

    # Configurar la paginación
    pagination = Pagination(page=page, per_page=per_page, total=total, css_framework='bootstrap4')

    if request.method == 'POST':
        if 'crear_usuario' in request.form:
            return redirect('/crear_usuario')
        elif 'eliminar_usuario' in request.form:
            return redirect('/eliminar_usuario')

    return render_template('pages/gestor_usuarios.html', usuarios=usuarios, pagination=pagination)



@app.route('/editar_usuario', methods=['GET', 'POST'])
def editar_usuario():
    if not is_admin():
        return redirect('/')

    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        rank = request.form['rank']

        # Realizar la lógica de modificación de usuario aquí
        # Actualizar la información del usuario con los nuevos valores de password y rank
        update_user_info(username, password)
        update_user_rank(username, rank)

        # Almacenar el mensaje en la sesión de Flask
        flash("La modificación se realizó con éxito")

        # Redireccionar a la página de gestor de usuarios después de la modificación
        return redirect('/gestor_usuarios')

    # Si la solicitud es GET, cargar la información del usuario y renderizar la plantilla editar_usuario.html
    username = request.args.get('username')
    user_info = get_user_info(username)
    return render_template('editar_usuario.html', user_info=user_info)

# Inyecta el usuario actual en el contexto de la aplicación
@app.context_processor
def inject_current_user():
    if 'username' in session:
        username = session['username']
        rank = get_rank(username)
    else:
        username = None
        rank = None
    return {'username': username, 'rank': rank, 'current_year': current_year}

# Ruta de inicio de sesión
@app.route('/')
@app.route('/login', methods=['GET', 'POST'])
def login():
    if session.get('authenticated'):
        return redirect('/inicio')

    error_message = None

    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        user = collection.find_one({'usuarios': {'$elemMatch': {'username': username, 'password': password}}})

        if user:
            session['authenticated'] = True
            session['username'] = username

            if request.form.get('remember'):
                # Si se seleccionó "Recordar usuario", se configura la sesión para que dure más tiempo (por ejemplo, 30 días)
                session.permanent = True
                app.permanent_session_lifetime = timedelta(days=30)

            return redirect('/inicio')
        else:
            error_message = 'Credenciales incorrectas'

    return render_template('pages/login.html', error_message=error_message)

# Ruta de cierre de sesión
@app.route('/logout')
def logout():
    session.clear()
    return redirect('/login')

# Ruta de inicio
@app.route('/inicio')
def index():
    if not session.get('authenticated'):
        return redirect('/login')
    return render_template('pages/index.html')

if __name__ == '__main__':
    app.run(debug=True)

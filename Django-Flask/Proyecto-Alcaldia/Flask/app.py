from flask import Flask, render_template, request, redirect, session , flash ,make_response, send_file
from docx2pdf import convert
from flask_paginate import Pagination, get_page_args
import secrets
import uuid
import os
import json
from pymongo import MongoClient
from datetime import datetime ,timedelta


app = Flask(__name__)

app.secret_key = secrets.token_hex(16)
current_year = datetime.now().year


# Configuración de la base de datos
client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
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
# Elimina un usuario de la base de datos
@app.route('/eliminar_usuario', methods=['GET', 'POST'])
def eliminar_usuario():
    if not is_admin():
        return redirect('/')

    if request.method == 'POST':
        confirmacion = request.form.get('confirmacion')
        if confirmacion == 'confirmado':
            usuarios_eliminar = request.form.getlist('usuarios_eliminar')
            usuarios_eliminados = []
            for usuario in usuarios_eliminar:
                if usuario != 'admin':
                    # Eliminar usuario de la base de datos
                    collection.update_one({'usuarios.username': usuario}, {'$pull': {'usuarios': {'username': usuario}}})
                    usuarios_eliminados.append(usuario)

            mensaje = None
            if usuarios_eliminados:
                mensaje = "Los siguientes usuarios han sido eliminados exitosamente: {}".format(", ".join(usuarios_eliminados))

            # Obtener usuarios actualizados después de eliminar
            usuarios = get_usuarios()[0]  # Obtener solo los usuarios sin el total

            return render_template('pages/eliminar_usuario.html', usuarios=usuarios, mensaje=mensaje)

    usuarios = get_usuarios()[0]  # Obtener solo los usuarios sin el total
    return render_template('pages/eliminar_usuario.html', usuarios=usuarios)

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
    #archivos = collection.find()
    # Obtener todos los archivos
    archivos = obtener_archivos()
    if not session.get('authenticated'):
        return redirect('/login')
    return render_template('pages/index.html', archivos=archivos)

def obtener_archivos():
    # Conexión a la base de datos
    client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
    db = client['Archivos']
    collection = db['Archivos']
    
    # Obtener todos los archivos de la base de datos
    archivos = collection.find()
    
    return archivos

def subir_archivo():
    # Conexión a la base de datos
    client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
    db = client['Archivos']
    collection = db['Archivos']

    if request.method == 'POST':
        # Verificar si se ha enviado un archivo
        if 'file' not in request.files:
            return render_template('pages/upload.html', error_message='No se ha seleccionado ningún archivo.')

        file = request.files['file']

        # Verificar si no se ha seleccionado un archivo
        if file.filename == '':
            return render_template('pages/upload.html', error_message='No se ha seleccionado ningún archivo.')

        # Generar un ID único para el archivo
        file_id = str(uuid.uuid4())

        # Obtener la extensión del archivo
        file_extension = os.path.splitext(file.filename)[1]

        # Construir el nombre del archivo con el ID único y la extensión
        filename = file_id + file_extension

        # Guardar el archivo en el servidor
        file_path = os.path.join(app.root_path, 'static/uploads', filename)
        file.save(file_path)

        # Agregar el archivo a la base de datos
        archivo = {
            'file_id': file_id,
            'filename': filename,
            'original_filename': file.filename,
            'timestamp': datetime.now(),
            'size': os.path.getsize(file_path),  # Obtener el tamaño del archivo en bytes
            'extension': file_extension,
            'location': os.path.abspath(file_path)  # Obtener la ubicación absoluta del archivo en el servidor
        }
        collection.insert_one(archivo)

        response_data = {
            'success': True,
            'message': 'Archivo subido exitosamente.'
        }

        return json.dumps(response_data)

    return render_template('pages/upload.html', error_message=None, success_message=None)
# Ruta para subir archivos
@app.route('/subir_archivo', methods=['GET', 'POST'])
def subir_archivo_route():
    return subir_archivo()


def obtener_tamano_archivo(location):
    return os.path.getsize(location)

def obtener_fecha_agregado(location):
    timestamp = os.path.getmtime(location)
    return datetime.fromtimestamp(timestamp).strftime('%Y-%m-%d %H:%M:%S')

def visualizar_archivo(file_id):
    # Conexión a la base de datos
    client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
    db = client['Archivos']
    collection = db['Archivos']

    # Buscar el archivo en la base de datos por su ID
    archivo = collection.find_one({'file_id': file_id})

    if archivo:
        # Obtener la ubicación del archivo en el servidor
        location = archivo['location']
        extension = archivo['extension']
        original_filename = archivo['original_filename']

        # Verificar si el archivo existe en el servidor
        if os.path.exists(location):
            # Convertir archivos de Word (.docx) a PDF si es necesario
            if extension == '.docx':
                pdf_location = location + '.pdf'
                convert(location, pdf_location)
                location = pdf_location

            # Obtener el nombre de archivo sin la extensión
            filename = os.path.splitext(original_filename)[0]

            # Obtener el nombre del ícono según la extensión
            icon_name = extension[1:] + '.png'  # Se asume que hay un ícono PNG para cada extensión

            # Obtener el tamaño del archivo
            tamano = obtener_tamano_archivo(location)

            # Obtener la fecha de agregado del archivo
            fecha_agregado = obtener_fecha_agregado(location)

            # Retornar la plantilla HTML con la información del archivo en formato de lista
            return render_template('pages/visualizar_archivo.html', filename=filename, icon_name=icon_name, file_id=file_id, tamano=tamano, fecha_agregado=fecha_agregado)

    # Si el archivo no se encuentra o no existe, retornar un mensaje de error
    return render_template('pages/error.html', error_message='El archivo solicitado no está disponible.')


# Ruta para visualizar archivos
@app.route('/visualizar_archivo/<file_id>')
def visualizar_archivo_route(file_id):
    return visualizar_archivo(file_id)

def eliminar_archivo(file_id):
    # Conexión a la base de datos
    client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
    db = client['Archivos']
    collection = db['Archivos']

    # Buscar el archivo en la base de datos por su ID
    archivo = collection.find_one({'file_id': file_id})

    if archivo:
        # Obtener la ubicación del archivo en el servidor
        location = archivo['location']

        # Verificar si el archivo existe en el servidor
        if os.path.exists(location):
            # Eliminar el archivo del servidor
            os.remove(location)

        # Eliminar el archivo de la base de datos
        collection.delete_one({'file_id': file_id})

        # Redireccionar a la página de inicio con un mensaje de éxito
        return redirect('/', success_message='El archivo ha sido eliminado exitosamente.')
    else:
        # Redireccionar a la página de inicio con un mensaje de error
        return redirect('/', error_message='No se ha encontrado el archivo.')


# Ruta para eliminar archivos
@app.route('/eliminar_archivo', methods=['POST'])
def eliminar_archivo_route():
    file_ids = request.form.getlist('file_id')
    
    for file_id in file_ids:
        eliminar_archivo(file_id)
    
    # Redireccionar a la página de inicio con un mensaje de éxito
    return redirect('/', success_message='Los archivos seleccionados han sido eliminados exitosamente.')


# Ruta para el formulario de eliminación de archivos
@app.route('/formulario_eliminar_archivos')
def formulario_eliminar_archivos():
    # Obtener la lista de archivos desde la base de datos
    client = MongoClient('mongodb+srv://raulantoni2810:5PAaa1eLS8oNxQaP@cluster0.luklzfn.mongodb.net/')
    db = client['Archivos']
    collection = db['Archivos']
    archivos = collection.find()
    
    # Renderizar el formulario de eliminación de archivos
    return render_template('eliminar_archivos.html', archivos=archivos)


if __name__ == '__main__':
    app.run(debug=True)
# Proyecto-Alcaldia
Propuesta para Visualizar archivos
## Estructura
- los archivos app.py contienen la lógica del backend. El archivo app.py puede ser el punto de entrada principal de la aplicación Flask.
  La carpeta templates contiene las plantillas HTML que serán utilizadas para renderizar las diferentes páginas de la aplicación. Dentro de la carpeta templates, 
  encontramos la carpeta pages, que contiene las plantillas específicas de cada página, como index.html. También tenemos la carpeta partials, que contiene plantillas 
  reutilizables como header.html y footer.html, que pueden ser incluidas en varias páginas.La carpeta static almacena archivos estáticos, como archivos CSS y JavaScript, 
  que serán utilizados por las plantillas HTML. Dentro de la carpeta static, encontramos la carpeta css, que contiene el archivo style.css, la carpeta js, donde se podrían 
  agregar archivos JavaScript adicionales y la carpeta images que contendrá todo el contenido multimedia.

## Funciones
Esta aplicación Flask es un sistema de gestión de usuarios que permite realizar diferentes acciones relacionadas con la administración de usuarios. Algunas características clave de esta aplicación son:

Autenticación de usuarios: Permite a los usuarios iniciar sesión utilizando un nombre de usuario y contraseña. Verifica las credenciales en una base de datos MongoDB y crea una sesión para el usuario autenticado.
```python
def login():
    error_message = None

    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        user = collection.find_one({'usuarios': {'$elemMatch': {'username': username, 'password': password}}})

        if user:
            session['authenticated'] = True
            session['username'] = username
            return redirect('/inicio')
        else:
            error_message = 'Credenciales incorrectas'

    return render_template('pages/login.html', error_message=error_message)
```
Roles de usuario: El sistema de gestión de usuarios incluye la funcionalidad de roles de usuario. Cada usuario tiene asignado un rol, que puede ser "admin" o cualquier otro valor. Los usuarios con rol de "admin" tienen privilegios especiales, como acceder al gestor de usuarios y realizar acciones de administración.

Creación de usuarios: Los usuarios con rol de "admin" pueden crear nuevos usuarios proporcionando un nombre de usuario, contraseña y asignando un rol. La información del nuevo usuario se guarda en la base de datos MongoDB.
```python
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
```
Eliminación de usuarios: Los usuarios con rol de "admin" pueden eliminar usuarios existentes. Sin embargo, el usuario "admin" no se puede eliminar para evitar la pérdida de acceso a la administración del sistema.
```python
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
```
El sistema proporciona una interfaz web que permite a los administradores acceder al gestor de usuarios. En esta interfaz, pueden crear y eliminar usuarios, así como ver una lista de los usuarios existentes.

### <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/python/python-original.svg" alt="python" width="40" height="40"/>  Requisitos
Python 3.9.13 y las siguientes librerias:
```python
import pip
pip.main(["install","Flask"])
pip.main(["install","pymongo"])
```
<h3 align="left">Desarrolladores</h3>
<p><a href="https://www.linkedin.com/in/raul-serrano-a1b79120a?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BkME50HMITUasDmWX9sEExA%3D%3D">Raul Serrano</a>: "Frontend,Backend"<br>
</p>
<h4 align="left">Lenguajes y Herramientas:</h4>
<p align="left"> <a href="https://www.w3schools.com/css/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/> </a> <a href="https://flask.palletsprojects.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/pocoo_flask/pocoo_flask-icon.svg" alt="flask" width="40" height="40"/> </a> <a href="https://www.w3.org/html/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original-wordmark.svg" alt="html5" width="40" height="40"/> </a> <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-original.svg" alt="javascript" width="40" height="40"/> </a> <a href="https://www.python.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/python/python-original.svg" alt="python" width="40" height="40"/> 
<a href="https://www.mongodb.com/" target="_blank" rel="noreferrer"> <img src="https://cdn.worldvectorlogo.com/logos/mongodb-icon-1.svg" alt="MongoDB" width="40" height="40"/> 
 </a></p>






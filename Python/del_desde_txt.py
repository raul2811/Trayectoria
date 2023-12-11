import os

# Ruta del archivo de texto que contiene los nombres de los archivos a eliminar
ruta_archivo_txt = './archivos_a_eliminar.txt'

# Obtener la ruta del directorio actual
ruta_actual = os.getcwd()

# Verificar si el archivo de texto existe
if os.path.exists(ruta_archivo_txt):
    with open(ruta_archivo_txt, 'r') as file:
        # Leer los nombres de los archivos línea por línea
        archivos_a_eliminar = file.readlines()
        archivos_a_eliminar = [archivo.strip() for archivo in archivos_a_eliminar]  # Eliminar espacios en blanco

        # Obtener rutas completas de los archivos a eliminar
        rutas_archivos_a_eliminar = [os.path.join(ruta_actual, archivo) for archivo in archivos_a_eliminar]

        # Eliminar cada archivo de la lista
        for ruta_archivo in rutas_archivos_a_eliminar:
            # Utilizar el comando 'del' para borrar archivos en Windows
            try:
                os.remove(ruta_archivo)
                print(f"Archivo '{ruta_archivo}' eliminado exitosamente.")
            except OSError as e:
                print(f"No se pudo eliminar el archivo '{ruta_archivo}': {e}")
else:
    print(f"No se encontró el archivo '{ruta_archivo_txt}'.")

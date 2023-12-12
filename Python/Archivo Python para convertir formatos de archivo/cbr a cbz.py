import os
import rarfile
import zipfile
import shutil
from concurrent.futures import ThreadPoolExecutor

# Ruta de la carpeta con archivos CBR
ruta_carpeta_cbr = './'

# Ruta de la carpeta donde se guardarán los archivos CBZ
ruta_carpeta_cbz = './cbz'

# Crear la carpeta de destino si no existe
os.makedirs(ruta_carpeta_cbz, exist_ok=True)

def convertir_cbr_a_cbz(archivo_cbr):
    ruta_absoluta_cbr = os.path.join(ruta_carpeta_cbr, archivo_cbr)

    # Nombre del archivo CBZ resultante
    nombre_cbz = os.path.splitext(archivo_cbr)[0] + '.cbz'
    ruta_absoluta_cbz = os.path.join(ruta_carpeta_cbz, nombre_cbz)

    # Extraer archivos del CBR a una carpeta temporal
    temp_folder = f'temp_extracted_{archivo_cbr}'
    os.makedirs(temp_folder, exist_ok=True)

    try:
        with rarfile.RarFile(ruta_absoluta_cbr, 'r') as rf:
            rf.extractall(temp_folder)
    except rarfile.Error as e:
        print(f"Error con el archivo '{archivo_cbr}': {e}")
        shutil.rmtree(temp_folder)
        return f"Error con el archivo '{archivo_cbr}': {e}"

    # Comprimir archivos extraídos en un archivo CBZ
    with zipfile.ZipFile(ruta_absoluta_cbz, 'w') as zf:
        for foldername, _, filenames in os.walk(temp_folder):
            for filename in filenames:
                file_path = os.path.join(foldername, filename)
                zf.write(file_path, os.path.relpath(file_path, temp_folder))

    # Eliminar la carpeta temporal y su contenido
    shutil.rmtree(temp_folder)
    return f"Conversión de '{archivo_cbr}' completada"

# Lista de archivos CBR en la carpeta
archivos_cbr = [archivo for archivo in os.listdir(ruta_carpeta_cbr) if archivo.endswith('.cbr')]

# Procesar la conversión en paralelo
with ThreadPoolExecutor(max_workers=4) as executor:
    resultados = executor.map(convertir_cbr_a_cbz, archivos_cbr)

# Imprimir los resultados
for resultado in resultados:
    print(resultado)

print("¡Conversión completada!")

from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas
from PIL import Image
import os

import re

def images_to_pdf(folder_path, output_pdf):
    # Verificar si la carpeta de entrada existe
    if not os.path.isdir(folder_path):
        print(f"La carpeta de entrada {folder_path} no existe.")
        return

    # Obtener la lista de archivos en la carpeta
    image_files = [os.path.join(folder_path, file) for file in os.listdir(folder_path) if file.endswith(('png', 'jpg', 'jpeg'))]

    # Función para extraer el número de cada archivo
    def extract_number(file_name):
        # Divide el nombre del archivo por el carácter "_"
        parts = file_name.split("_")
        if len(parts) > 1:
            # Elimina la extensión del archivo y convierte el resultado en un número entero
            number = parts[1].split(".")[0]
            return int(number)
        else:
            return 0

# Ordenar la lista de archivos por número
    image_files.sort(key=extract_number)

    # Verificar si la carpeta de entrada contiene imágenes
    if not image_files:
        print(f"La carpeta de entrada {folder_path} no contiene imágenes.")
        return

    # Crear un lienzo PDF
    c = canvas.Canvas(output_pdf, pagesize=letter)

    # Para cada imagen, agregarla al PDF
    for image_file in image_files:
        try:
            img = Image.open(image_file)
            img_width, img_height = img.size
            c.setPageSize((img_width, img_height))
            c.drawImage(image_file, 0, 0, width=img_width, height=img_height)
            c.showPage()
        except Exception as e:
            print(f"Error al procesar la imagen {image_file}: {e}")

    c.save()
    print(f"Se ha creado el archivo PDF: {output_pdf}")

# Ruta de la carpeta que contiene las imágenes
folder_path = 'Archivo Python para convertir formatos de archivo/Samsung Innovation Campus/CAP 2 - MATEMÁTICAS PARA LA CIENCIAS DE DATOSSCORM package'
# Nombre del archivo PDF de salida
output_pdf = 'Archivo Python para convertir formatos de archivo/salida.pdf'

images_to_pdf(folder_path, output_pdf)
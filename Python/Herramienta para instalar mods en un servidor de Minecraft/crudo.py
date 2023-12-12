import os#Manera portátil de utilizar la funcionalidad específica del sistema (según su sistema operativo). Es la elección correcta en la mayoría de los casos, a menos que necesite algo más avanzado.
import sys#Parámetros y funciones específicos del sistema. Este módulo proporciona acceso a las variables y funciones del intérprete. El módulo os interactúa con el sistema operativo y sys interactúa con el intérprete de Python
import pathlib#Uso de ruta avanzado. Le permite representar sistemas de archivos como objetos, con la semántica pertinente para cada sistema operativo.
import subprocess#Gestión de ejecuciones y subprocesos directamente desde Python. Eso implica trabajar con el  stdin,  stdouty códigos de retorno.
from pathlib import Path
from humanize import naturalsize
from shutil import rmtree
import zipfile
from tqdm.auto import tqdm
from time import sleep
print("-------------------------")
print("|Instalador de mods 1.0v|")
print("-------------------------")
def verificacion():
    name=(str(os.getlogin()))
    test_path = Path(r"C:\Users\{}\AppData\Roaming\.minecraft".format(name))#Remplazar por carpeta
    size = 0
    comprobacion=os.path.exists('test_path')#Verifica si la carpeta existe
    if comprobacion==True:
        print("Se encontro la carpeta minecraft")
        for file_ in Path(test_path).rglob('*'):
            size += file_.stat().st_size
        tamano=naturalsize(size)
        confirmacion=False
        if tamano == 0:
            print("se encontraron 0 archivos dentro")
            confirmacion=False
        else:
            print("se encontraron archivos dentro ")
            rmtree("{}".format(test_path))
            confirmacion=False
    else:
        print("No se encontro la carpeta minecraft por lo que se creara al extraer los archivos")
        confirmacion=False
    if confirmacion == False:
        zf = zipfile.ZipFile('mods.zip')
        ruta_zip = "mods.zip"#Remplazar por nombre del zip
        ruta_extraccion = (r"C:\Users\{}\AppData\Roaming\.minecraft".format(name))#Remplazar por carpeta
        password = None
        uncompress_size = sum((file.file_size for file in zf.infolist()))
        extracted_size = 0
        for member in tqdm(zf.infolist(), desc='Extracting '):
            #print("Extrañendo archivos")
            #print ("%s %%\r" % (extracted_size * 100/uncompress_size))
            zf.extract(member,pwd=password,path=ruta_extraccion)
    print("Extracion completada con exito")
verificacion()#extrae el zip
print("Presione una tecla para salir")
input()
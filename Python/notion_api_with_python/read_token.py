def token (): #** Funcion para leer el token de un archivo de texto
    abrir_txt=open('TOKEN.txt','r')
    token = abrir_txt.read()
    abrir_txt.close()
    return token

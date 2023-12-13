"""
                ENUNCIADO
------------------------------------------

Escriba una función que elimine los espacios de la cadena y luego devuelva la cadena resultante.

Ejemplos:

Input -> Output
"8 j 8   mBliB8g  imjB8B8  jl  B" -> "8j8mBliB8gimjB8B8jlB"
"8 8 Bi fk8h B 8 BB8B B B  B888 c hl8 BhB fd" -> "88Bifk8hB8BB8BBBB888chl8BhBfd"
"8aaaaa dddd r     " -> "8aaaaaddddr"

------------------------------------------- 
"""
def no_space(x):
    return x.replace(" ", "")
#!En este método, replace(" ", "") busca todos los espacios en la cadena y los 
#!reemplaza con una cadena vacía, eliminándolos efectivamente.
"""         
           OTRA SOLUCION
--------------------------------------

def no_space(x):
     return ''.join(x.split())
     
 método, split() divide la cadena en palabras, y luego join() une esas palabras sin ningún separador, 
 eliminando así los espacios entre ellas.
 
 def no_space(x):
    # Inicializa una cadena vacía para almacenar los caracteres sin espacios
    str_char = ''
    
    # Itera a través de cada índice en la cadena 'x'
    for i in range(len(x)):
        # Verifica si el carácter en la posición 'i' es un espacio
        if x[i] == ' ':
            # Si es un espacio, pasa al siguiente carácter
            continue
        else:
            # Si no es un espacio, agrega el carácter a la cadena 'str_char'
            str_char = str_char + x[i]
    
    # Retorna la cadena resultante sin espacios
    return str_char 
    
-------------------------------------
def no_space(x):
    # Inicializa una cadena vacía para almacenar los caracteres sin espacios
    str_char = ''
    
    # Itera a través de cada índice en la cadena 'x'
    for i in range(len(x)):
        # Verifica si el carácter en la posición 'i' es un espacio
        if x[i] == ' ':
            # Si es un espacio, pasa al siguiente carácter
            continue
        else:
            # Si no es un espacio, agrega el carácter a la cadena 'str_char'
            str_char = str_char + x[i]
    
    # Retorna la cadena resultante sin espacios
    return str_char
    
Este código crea una cadena vacía str_char y recorre cada carácter en la cadena x. Si el 
carácter actual es un espacio, se omite; de lo contrario, se agrega a la cadena str_char. Finalmente, 
se devuelve la cadena str_char, 
que contiene todos los caracteres de la cadena original x excepto los espacios.

--------------------------------------

"""    
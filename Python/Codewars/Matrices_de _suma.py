"""
                ENUNCIADO
------------------------------------------

Escribe una función que tome una matriz de números y devuelva la suma de los números. 
Los números pueden ser negativos o no enteros. Si la matriz no contiene ningún número, entonces deberías devolver 0.

Ejemplos
De entrada y [1, 5.2, 4, 0, -1]
salida:9.2

De entrada y []
salida:0

De entrada y [-2.398]
salida:-2.398

Suposiciones
Puedes asumir que solo te dan números.
No puede asumir el tamaño de la matriz.
Puede asumir que obtiene una matriz y, si la matriz está vacía, devuelve 0.
Lo que estamos probando
Estamos probando bucles básicos y operaciones matemáticas. Esto es para principiantes que recién están 
aprendiendo bucles y operaciones matemáticas.
Los usuarios avanzados pueden encontrar esto extremadamente fácil y pueden escribirlo fácilmente en una línea.

------------------------------------------- 
"""
def sum_array(a):
    len_a=len(a)
    total=0
    if len_a != 0:
        for i in range (len_a):
            total += (a[i])
        return total
    else:
        return 0
"""         
           OTRA SOLUCION
--------------------------------------

def sum_array(a):
  return sum(a)
    
--------------------------------------

"""    
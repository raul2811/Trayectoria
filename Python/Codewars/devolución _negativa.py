"""
                ENUNCIADO
------------------------------------------

En esta sencilla tarea te dan un número y tienes que hacerlo negativo. ¿Pero tal vez el número ya sea negativo?

Ejemplos
make_negative(1);  # return -1
make_negative(-5); # return -5
make_negative(0);  # return 0
Notas
El número ya puede ser negativo, en cuyo caso no se requiere ningún cambio.
El cero (0) no se verifica para ningún signo específico. Los ceros negativos no tienen sentido matemático.

------------------------------------------- 
"""

def make_negative( number ):
    if number > 0:
        return number * -1
    else:
        return number
number=1
print(make_negative(number)) 

"""

este código define una función llamada make_negative que toma un argumento llamado number.

La función verifica si el número dado (number) es mayor que 0 usando la instrucción if.
Si el número es mayor que 0, lo multiplica por -1 utilizando number * -1 para convertirlo en su equivalente negativo.
Si el número es menor o igual a 0 (es decir, si ya es negativo o cero), 
la función simplemente devuelve el mismo número sin modificarlo.

"""
"""
            OTRA SOLUCION
--------------------------------------

def make_negative( number ):
    return -abs(number)
    
--------------------------------------

este código define una función llamada make_negative que toma un argumento llamado number.

abs(number) calcula el valor absoluto del número dado. El valor absoluto es la magnitud de un número sin considerar 
su signo, es decir, devuelve el número positivo equivalente sin importar si el número original es positivo o negativo.

Luego, el operador - colocado antes del abs(number) toma el valor absoluto calculado y lo multiplica por -1, 
convirtiéndolo en su equivalente negativo.

En resumen, esta función toma un número dado y devuelve su equivalente negativo, 
independientemente de si el número original es positivo o negativo.

"""
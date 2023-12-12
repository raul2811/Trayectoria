"""
                ENUNCIADO
------------------------------------------

Timmy y Sarah creen que están enamorados, pero en el lugar donde viven, 
solo lo sabrán una vez que recojan una flor cada uno. 

Si una de las flores tiene un número par de pétalos y la otra tiene un número 
impar de pétalos significa que están enamoradas.

Escribe una función que tome el número de pétalos de cada flor y 
devuelva verdadero si están enamorados y falso si no lo están.

------------------------------------------- 
"""
flower1 = 449  # Se define la variable flower1 con el valor 10
flower2 = 469   # Se define la variable flower2 con el valor 2

def lovefunc(flower1, flower2):
    # La función lovefunc toma dos argumentos: flower1 y flower2

    # Verifica si ambos números son pares
    if flower1 % 2 == 0 and flower2 % 2 == 0 or flower1 % 2 != 0 and flower2 % 2 != 0:
        return False  # Si ambos números son pares, la función devuelve False (no es amor)
    else:
        return True  # Si al menos uno de los números no es par, la función devuelve True (es amor)

# Se llama a la función lovefunc con los valores de flower1 y flower2 definidos anteriormente
print(lovefunc(flower1, flower2))  # Imprime el resultado de la función

"""

En este código, lovefunc es una función que verifica si al menos uno de los números dados es impar. 
Si ambos números son pares, devuelve False (no es amor),indicando que no hay amor entre las flores. 
Si al menos uno de los números es impar, devuelve True (es amor), indicando que hay amor entre las flores.

En el ejemplo proporcionado, flower1 tiene el valor de 10 (par) y flower2 tiene el valor de 2 (par). 
Por lo tanto, ambos números son pares y la función devuelve False, indicando que no hay amor entre estas flores.
    
"""

"""
            OTRA SOLUCION
--------------------------------------

def lovefunc( flower1, flower2 ):
    return (flower1+flower2)%2
    
--------------------------------------

Este código define una función llamada lovefunc que toma dos argumentos: flower1 y flower2. 
La función calcula la suma de estos dos valores (flower1 + flower2) y luego toma el módulo de esa suma con 2 (% 2), 
devolviendo el resultado.

El operador % (módulo) devuelve el resto de la división de un número por otro. En este caso, flower1 + flower2 
es la suma de los dos valores. Al tomar % 2, el resultado será 0 si la suma es un número par y será 1 si 
es un número impar.

La función lovefunc devuelve este resultado, que será 0 si la suma de flower1 y flower2 es par, y 
será 1 si la suma es impar.

"""
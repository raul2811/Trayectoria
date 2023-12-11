n = 0
def reverse_seq(n):
    new_array = []  # Creamos una lista vacía llamada new_array
    if n > 0:  # Verifica si n es mayor que 0
        for i in range(n):  # Iteramos desde 0 hasta n - 1
            i += 1  # Incrementamos i en 1 (esto no afectará el rango del bucle)
            new_array.append(i)  # Agregamos i a la lista new_array
    return new_array[::-1]  # Usamos slicing para invertir el orden de los elementos en la lista

prueba = reverse_seq(100)  # Llamamos a la función con n = 100
print(prueba)  # Imprimimos el resultado de la función

"""
Ahora vamos a analizar el código en detalle:
n = 0: Se asigna el valor 0 a la variable n.
new_array = []: Se crea una lista vacía llamada new_array.
La función reverse_seq(n) toma un número n.
Verifica si n es mayor que 0.
Si n es mayor que 0, entra en un bucle for.
El bucle for itera desde 0 hasta n - 1.
En cada iteración, se incrementa i en 1 y se agrega a la lista new_array.
Luego, la función devuelve new_array con sus elementos invertidos utilizando slicing ([::-1]).
El código crea una lista con números del 1 al n en orden descendente si n es mayor que 0. Si n es 0 o un número negativo, la lista new_array permanece vacía ya que no entra en el bucle for.
"""
"""
Otra forma de hacerlos es :

 def reverseseq(n):
    return list(range(n, 0, -1))
    
    Vamos a desglosar lo que hace:

range(n, 0, -1): Esta es una función de Python que crea una secuencia de números desde n hasta 1 con un paso de -1. Especifica el rango desde n (inclusive) hasta 0 (exclusivo) con decrementos de -1 en cada paso. Por ejemplo:

range(5, 0, -1) generará la secuencia: 5, 4, 3, 2, 1
list(): Toma el objeto de rango generado por range() y lo convierte en una lista.

"""


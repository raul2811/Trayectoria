def string_to_number(s):
    s = float(s)  # Convierte la cadena 's' a un número de punto flotante (float)
    return s  # Devuelve el número convertido
cadena_numero="1234"
numero=string_to_number(cadena_numero)
print(numero)
"""
Este código define una función llamada string_to_number que toma una cadena s como argumento.

float(s): Esta línea convierte la cadena s en un número de punto flotante (float). Si s es una cadena que contiene caracteres numéricos, esta operación intentará convertir esa cadena en un número de coma flotante.

return s: Devuelve el número convertido. Esta línea es importante ya que retorna el resultado de la conversión a punto flotante de la cadena s.
"""
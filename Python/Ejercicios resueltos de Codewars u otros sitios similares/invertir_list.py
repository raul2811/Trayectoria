def digitize(n):
    # Convierte el número en una cadena de texto y luego cada dígito en un entero, creando una lista de dígitos.
    nueva_lista = list(map(int, str(n)))
    
    # Invierte el orden de los elementos en la lista utilizando la función reversed().
    nueva_lista = list(reversed(nueva_lista))
    
    # Imprime el número de entrada y la lista de dígitos en orden inverso.
    print(f"Dato ingresado: {n}\nDato salida: {nueva_lista}")
    
    # Devuelve la lista de dígitos en orden inverso.
    return nueva_lista
'''
                ENUNCIADO
------------------------------------------

Tus compañeros de clase te pidieron que les copiaras algunos documentos. Sabes que hay 'n' compañeros y el papeleo tiene 'm' páginas.

Tu tarea es calcular cuántas páginas en blanco necesitas. Si n < 0o m < 0regreso 0.

Ejemplo:
n= 5, m=5: 25
n=-5, m=5:  0

------------------------------------------- 
'''

def paperwork(n, m):
    if n > 0 and m > 0:
        papeles=n*m
        return papeles
    else:
        return 0
 

'''
Otra forma de hacerlos es :

def paperwork(n, m):
    return n * m if n > 0 and m > 0 else 0

hace lo mismo solo que en una sola linea
'''    
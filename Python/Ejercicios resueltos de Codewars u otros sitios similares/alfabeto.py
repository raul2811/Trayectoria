
a1="aretheyhere"
a2="yestheyarehere"

def longest(a1, a2):
    alfabeto="a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"
    a3=a1 + a2
    print ("contenido de a1={}\ncontenido de a2={}\n contenido de a3={}".format(a1,a2,a3))
    a3_len=len(a3)
    alfabeto_len=len(alfabeto)
    a4=""
    for i in range (a3_len):
        for ii in range (alfabeto_len):
            if alfabeto[ii] in a3[i] and alfabeto[ii] not in a4:
                a4=alfabeto[ii]+a4
                
    cadena_ordenada= "".join(sorted(a4))    
    print ("cadena_ordenada={}".format(cadena_ordenada))
    return cadena_ordenada           
longest(a1, a2)

'''
solucion optima "el mas capito "
def longest(a1, a2):
    return "".join(sorted(set(a1 + a2)))
'''
def Formato(Palabra):
    Decoracion=len(Palabra)
    print("+"+"-"*int(Decoracion)+"+",end=("\n"),);
    print("|"+str(Palabra)+"|");
    print("+"+"-"*int(Decoracion)+"+")
Palabra=(input("Ingrese Lo Que Desea Imprimir"));
Formato(Palabra);
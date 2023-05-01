/*--Pilas y colas--
Pilas= 
1)Para ingresar datos en una pila se utilizan los topes salen y entran en el mismo orden en que entraron
Escribir un programa que simule el comportamiento de una pila y aplicar y desplegar la pila debe almacenar los datos de la pila 
el tipo de plato y capacidad de onza del plato 
-Plato de sopa 8oz, 4oz.
2 tipos de dato el tipo de plato y la capacidad
EL programa debe funcionar con el uso de un menu*/

#define MAX 10//Declaramos objeto GLOBAl con valor de 10
#include<iostream>
using namespace std;

struct platos //Iniciamos una estructura
{
    string tipo;
    int capacidad;
};
typedef struct platos pl;//Declaramos abreviatura a pl

int main ()
{
pl pila[MAX];
int tope=-1;//!Declaramos en -1 por fines de inicializar en la posicion 0
int pone_pila(int,pl[],pl);
int quitar_pila(int,pl[],pl);
int menu();
}
int menu()
{
cout<<"Menu"<<endl<<"Poner dato"<<endl<<"Quitar datos"<<"Salir"<<end
}




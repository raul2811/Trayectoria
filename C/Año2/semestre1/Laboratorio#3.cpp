/*
+--------------------------------------------------------------------------------------------------------------------------------+
|el programa debe solicitar el codigo del pasajero y la ciudad de destino y la ciudad de origen, distancia entre las ciudades    |
|en kilometros.                                                                                                                  |
|--------------------------------------------------------------------------------------------------------------------------------|
|Debe incluir el itbms,si la distancia entre las ciudades es menos de                                                            | 
|1- 400 kilometros 100 dolares                                                                                                   |
|2- de 400 a 800 kilometros  250 dolares                                                                                         |
|3- mas de 800 kilometros  1000 dolares                                                                                          |
|--------------------------------------------------------------------------------------------------------------------------------|
|guardar en un documento.                                                                                                        |
|Imprimir el codigo del pasajero y todos los datos y el monto a pagar.                                                           |
+--------------------------------------------------------------------------------------------------------------------------------+
*/ 

#include<iostream>
#include<fstream>
#include<stdlib.h>
using namespace std;

int main()
{

//-------------------Declaro Variables-------------------

int opciones,codigo_p,costo_total;
double distancia_c;
string ciudad_d,ciudad_o;

//-------------------Declaro Funciones-------------------

int menu(void);
void ingresa_datos(int*,double*,string*,string*);//Inicialisamos punteros.
void calculo(double*,int*);
void imprimir(int*,double*,string*,string*,int*);
//-------------------------------------------------------

do
{
   opciones=menu();//Llamo a la funcion menu
   //system("pause");//Detenemos la pantalla
   switch (opciones)
    {
    case 1:
        cout<<"Usted escogio la opcion #1"<<endl;
        cout<<"Usted escogio la opcion Registrar un pasajero"<<endl;
        ingresa_datos(&codigo_p,&distancia_c,&ciudad_d,&ciudad_o);//Llamamos a la funcion para ingresar los datos.
        break;
    case 2:
        cout<<"Usted escogio la opcion #2"<<endl;
        cout<<"Usted escogio la opcion Calcular el importe del viaje"<<endl;
        cout<<"La distancia ingresada es:"<<distancia_c<<endl;
        calculo(&distancia_c,&costo_total);  
        break;
    case 3:
        cout<<"Usted escogio la opcion #3"<<endl;
        cout<<"Usted escogio la imprimir datos de pasajeros e importe"<<endl;
        imprimir(&codigo_p,&distancia_c,&ciudad_d,&ciudad_o,&costo_total);
    case 4:
        cout<<"Salir y Grabar"<<endl;
        break;
    default:
        cout<<"Esta opcion no esta disponible en el menu"<<endl;
        break;
    }
    //system("cls");
} while (opciones !=4);
}
int menu(void)
{
int num;
cout<<endl<<"INGRESE UNA OPCION"<<endl<<"1- Registrar un pasajero"
<<endl<<"2- Calcular el importe del viaje"<<endl
<<"3- Imprimir datos de pasajero e importe"<<endl
<<"4- Salir y Grabar"<<endl;
cin>>num;
return num;
}

void ingresa_datos(int*codigo_p,double*distancia_c,string*ciudad_d,string*ciudad_o)
{

cout<<"Ingrese el codigo de pasajero"<<endl;
cin>>*codigo_p;
cout<<"Ingrese la ciudad de destino"<<endl;
cin>>*ciudad_d;
cout<<"Ingrese la ciudad de origen"<<endl;
cin>>*ciudad_o;
cout<<"Ingrese la distancia en kilometros"<<endl;
cin>>*distancia_c;

}

void calculo(double*distancia_c,int *costo_total)
{
if (*distancia_c>800)//Distancia mayor a 800   || *distancia_c>800  
{
cout<<"La distancia ingresada es 800 o menor a mayor a 800"<<endl;
*costo_total=1000+(1000*0.07);
}
if(*distancia_c<800 && *distancia_c>400)//Distancia entre 400 y 800 || *distancia_c>400
{
cout<<"La distancia ingresada es menor a 800 o mayor a 400"<<endl;
*costo_total=250+(250*0.07);
}
else//Distancia menor a 400
{
cout<<"La distancia ingresada es 400 o menor a 400"<<endl;
*costo_total=100+(100*0.07);
}
}
void imprimir(int *codigo_p,double*distancia_c,string *ciudad_d,string *ciudad_o,int *costo_total)
{
cout<<"CODIGO DEL PASAJERO"<<endl<<*codigo_p<<endl<<"CIUDAD DE DESTINO"<<endl<<*ciudad_d<<endl<<"CIUDAD DE ORIGEN"<<*ciudad_o<<endl<<"DISTANCIA"<<*distancia_c<<endl<<"COSTO TOTAL:"<<*costo_total<<endl;
}

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
|Codigo de Raul Serrano Alias Calamarino                                                                                         |
+--------------------------------------------------------------------------------------------------------------------------------+
*/ 

#include<iostream>
#include<fstream>
#include<stdlib.h>
using namespace std;

int main()
{

//-------------------Declaro Variables-------------------

int opciones,codigo_p,costo_total,comprobacion=0;
double distancia_c;
string ciudad_d,ciudad_o;

//-------------------Declaro Funciones-------------------

int menu(void);
void ingresa_datos(int*,double*,string*,string*,int*);//Inicialisamos punteros.
void calculo(double*,int*,int*);
void imprimir(int*,double*,string*,string*,int*,int*);
void grabar(int*,double*,string*,string*,int*,int*);
//-------------------------------------------------------

do
{
   opciones=menu();//Llamo a la funcion menu
   //system("pause");//Detenemos la pantalla
   switch (opciones)
    {
    case 1:
        cout<<endl<<"\tUsted escogio la opcion #1"<<endl;
        cout<<"Usted escogio la opcion Registrar un pasajero"<<endl;
        ingresa_datos(&codigo_p,&distancia_c,&ciudad_d,&ciudad_o,&comprobacion);//Llamamos a la funcion para ingresar los datos.
        break;
    case 2:
        cout<<endl<<"\tUsted escogio la opcion #2"<<endl;
        cout<<"Usted escogio la opcion Calcular el importe del viaje"<<endl;
        calculo(&distancia_c,&costo_total,&comprobacion);  
        break;
    case 3:
        cout<<endl<<"\tUsted escogio la opcion #3"<<endl;
        cout<<"Usted escogio la imprimir datos de pasajeros e importe"<<endl;
        imprimir(&codigo_p,&distancia_c,&ciudad_d,&ciudad_o,&costo_total,&comprobacion);
    case 4:
        cout<<"Salir y Grabar"<<endl;
        grabar(&codigo_p, &distancia_c, &ciudad_d, &ciudad_o,&costo_total,&comprobacion);
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
<<"4- Salir y Grabar"<<endl<<"-";
cin>>num;
return num;
}

void ingresa_datos(int*codigo_p,double*distancia_c,string*ciudad_d,string*ciudad_o,int*comprobacion)
{
cout<<endl<<"Ingrese el codigo de pasajero"<<endl<<"-";
cin>>*codigo_p;
cout<<"Ingrese la ciudad de destino"<<endl<<"-";
cin>>*ciudad_d;
cout<<"Ingrese la ciudad de origen"<<endl<<"-";
cin>>*ciudad_o;
cout<<"Ingrese la distancia en kilometros"<<endl<<"-";
cin>>*distancia_c;
cout<<"Registro exitoso"<<endl<<"-";
*comprobacion=1;
}

void calculo(double*distancia_c,int*costo_total,int*comprobacion)
{
    if (*comprobacion==1)//comprueba si se han ingresado datos de pasajeros
    {
    cout<<"La distancia ingresada es:"<<*distancia_c<<endl;
    if (*distancia_c>800)//Distancia mayor a 800   || *distancia_c>800  
        {
        cout<<endl<<"La distancia ingresada es 800 o menor a mayor a 800"<<endl;
        *costo_total=1000+(1000*0.07);
        }
    else if(*distancia_c<800 && *distancia_c>400)//Distancia entre 400 y 800 || *distancia_c>400
        {
        cout<<endl<<"La distancia ingresada es menor a 800 o mayor a 400"<<endl;
        *costo_total=250+(250*0.07);
        }
    else//Distancia menor a 400
    {
    cout<<endl<<"La distancia ingresada es 400 o menor a 400"<<endl;
    *costo_total=100+(100*0.07);
    }
    }
    else{
        cout<<endl<<"\t!Primero debe ingresar los datos del pasajero!"<<endl;    
        }
    *comprobacion=2;
}

void imprimir(int *codigo_p,double*distancia_c,string *ciudad_d,string *ciudad_o,int *costo_total,int*comprobacion)
{
    if (*comprobacion==0 )//comprueba si se han ingresado datos de pasajeros
        {
        cout<<endl<<"No ha ingresado datos de pasajero"<<endl;    
        }
    else if (*comprobacion==2)
    {
        cout<<endl<<"CODIGO DEL PASAJERO"<<endl<<*codigo_p<<endl<<"CIUDAD DE DESTINO"<<endl<<*ciudad_d<<endl<<"CIUDAD DE ORIGEN"<<endl<<*ciudad_o<<endl<<"DISTANCIA"<<endl<<*distancia_c<<" Km"<<endl<<"COSTO TOTAL: $"<<*costo_total<<endl;
    }
    
    else{
        cout<<endl<<"No ha calculado los datos del pasajero porfavor utilize la opcion 2"<<endl;
    }

}

void grabar(int *codigo_p, double *distancia_c, string *ciudad_d, string *ciudad_o, int *costo_total,int*comprobacion)
{
    ofstream grabar; // Declara variable de tipo ofstream
    grabar.open("datos_pasajeros.txt"); // Abre el archivo
    if(*comprobacion==2){
        // Escribe los datos del pasajero y el costo total en el archivo
        grabar <<*codigo_p<<" "<<*ciudad_d<<" "<<" "<<*ciudad_o<<" "<<*distancia_c<<" "<<*costo_total<<endl;
        cout<<"Se a creado el archivo datos_pasajeros.txt con !EXITO!"<<endl;
    }
    else{
        cout<<"No se han grabado los datos"<<endl;
    }
    grabar.close(); // Cierra el archivo
}
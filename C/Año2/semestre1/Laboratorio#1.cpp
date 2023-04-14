#include<iostream>
#include<fstream>
#include<stdlib.h>
using namespace std;
int main()
{
int menu(void);
void cargadatos(string[],string[],string[]);
int opc;
string cedula[10],carrera[10],ano[10];

do
{ 
   opc=menu();
   system("pause");
   switch (opc)
    {
    case 1:
        cout<<"Usted escogio la opcion #1"<<endl;
        cout<<"Ingrese dato alumnos"<<endl;
        cargadatos(cedula[10],carrera[10],ano[10]);
        break;
    case 2:
        cout<<"Usted escogio la opcion #2"<<endl;
        cout<<"Imprimir reporte alumnos"<<endl;   
        break;
    case 3:
        cout<<"Usted escogio la opcion #3"<<endl;
        cout<<"Realizar una consulta"<<endl;
        break;
    case 4:
        cout<<"Salir y Grabar"<<endl;
        break;
    default:
        cout<<"Esta opcion no esta disponible en el menu"<<endl;
        break;
    }
    system("cls");
} while (opc !=4);
}
int menu(void)
{
int num;
cout<<endl<<"Ingrese una opcion "<<endl<<"1 Ingrese dato alumnos"
<<endl<<"2 Imprimir reporte alumnos"<<endl
<<"3 Realizar una consulta"<<endl
<<"4 Salir y Grabar"<<endl;
cin>>num;
return num;
}
void cargadatos(string cedula[10],string carrera[10],string ano[10])
{
cout<<"Ingrese la cedula del alumno"<<endl<<"-";
cin>>cedula[10];
cout<<"Ingrese la carrera del alumno"<<endl<<"-";
cin>>carrera[10];
cout<<"Ingrese el año del alumno"<<endl<<"-";
cin>>ano[10];
}
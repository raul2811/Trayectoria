#include <iostream>
#include <string>
#include <stdlib.h> 

using namespace std;

struct Cliente{
	string nombre;
	string cedula;
	int numCuenta;
	}; 
	typedef Cliente Datos1;
	
int main(){
	
	Datos1 informacion;
    int menuOpcion, cant = 0;
    int num_aleatorio;
    int montoApertura, total,desposito, retiro, estado_cuenta;
int FuncionMenu();  //declaci�n para el menu
void generarNum(int);
void CargaDatos(Datos1 informacion, float, float);
/*void imprimir(float, int);
void depositar(Datos1 informacion, float, float, float, int, int);
void retirar(Datos1 informacion, float, float, int, int);
void estadoCuenta(Datos1 informacion, float, float,int);*/

do
    {   //Inicio del bucle
        system("pause");
        system("cls");
        
        menuOpcion = FuncionMenu(); //LLamada a la funci�n menu, que retorna la opcion elegida
 
        switch(menuOpcion)  //Variable que contiene el valor que escogio el usuario
        {
        case 1:
            cout<<"Bienvenido"<<endl;
            cout<<"Ingrese sus datos y monto de apertura."<<endl;
            cout<<"Atenci�n: Debe ser mayor a B/.10.00"<<endl;
            CargaDatos(informacion, montoApertura, total); //Llamado a la funci�n
            break;
        case 2:
          /*  cout<<"Eligio la opcion 2"<<endl;
            Reporte(cantidad, vectorAl);*/
            break;
        case 3:
            cout<<"Eligio la opcion 3"<<endl;
            break;
        case 4:
            cout<<"Eligio la opcion 4"<<endl;
            break;
        default:
            cout<<"Opcion no disponible en el menu"<<endl;
            break;
        }
    }   //Fin del bucle
    while(menuOpcion != 4); //Condicion del bucle, mientras sea diferente del 4.
 


	return 0;
}
int FuncionMenu()   //Funci�n del menu
{
    int opcion; //Variable
    cout<<"Banco Amigo"<<endl;
    cout<<"Menu de opciones"<<endl;
    cout<<"1. Abrir cuenta bancaria."<<endl;
    cout<<"2. Efectuar un deposito."<<endl;
    cout<<"3. Efectuar un retiro."<<endl;
    cout<<"4. Consultar estado de cuenta."<<endl;
    cout<<"Ingrese -> ";
    cin>>opcion;    //Se ingresa el valor de retorno

    return opcion;  //Se retorna el valor elegido
}
void CargaDatos(Datos1 informacion, float montoApertura, float total)
{
		cout<<"Ingrese nombre: ";
		cin>>informacion.nombre;
		cout<<"Ingrese cedula: ";
		cin>>informacion.cedula;
		total=montoApertura;
}



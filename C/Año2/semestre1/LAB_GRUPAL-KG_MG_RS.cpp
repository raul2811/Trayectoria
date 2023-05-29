
/*
---Simulador De Banco---

Programa=	C++

Fecha=	19/05/2023

Desarrolladores=

Nombres			Cedula

Kevin Gatica	EC-0046-12163
Maria Gutierrez	8-912-1033
Reynaldo blanco	5-716-284
Raul serrano 	6-723-584

*/






#include<iostream>
#include<fstream> //libreria para archivos
#include<stdlib.h>
#include<cstdlib>//Librerias para generar los 4 digitos aleatorios
#include<ctime>
using namespace std;

struct Datos
{
    string Nombre,Cedula;
    int Numero_Cuenta=0;
    double Saldo;
};
typedef struct Datos dt;

int main() /*Funcion Principal Main */
{
    dt Datos[5];
    /***Declaraciones***/
    int Menu(void); // Prototipo Men�
    void Ingresar_Datos(dt[]); //Prototipo de captura datos
    void Deposito(dt[]); // Prototipo de deposito
    void Retiro(dt[]); // Prototipo de retiro
    void Consulta(dt[]);//Prototipo de consulta
    void Grabar(dt[]); // Prototipo de salir y grabar
    int opc;
    
    do
    {
        opc=Menu();system("cls");
        switch(opc)
        {
        case 1:
            cout<<endl<<"\tUSTED ESCOGIO LA OPCION #1\n"<<endl;
            cout<<"Usted escojio la opcion Registrar un Cliente.\n"<<endl;
            Ingresar_Datos(Datos); //LLamado a la funcion capturar datos
            system("cls");
            break;
        case 2:
            cout<<endl<<"\tUSTED ESCOGIO LA OPCION #2\n"<<endl;
            cout<<"Usted escojio la opcion Deposito.\n"<<endl;
            Deposito(Datos); // Llamado de la funcion deposito
            system("cls");
            break;
        case 3:
            cout<<endl<<"\tUSTED ESCOGIO LA OPCION #3\n"<<endl;
            cout<<"Usted escojio la opcion retiro.\n"<<endl;
            Retiro(Datos); //Llamado de la funcion retiro
            system("cls");
            break;
        case 4:
        	cout<<endl<<"\tUSTED ESCOGIO LA OPCION #4\n"<<endl;
        	cout<<"\tUsted escogio la opcion Consulta.\n"<<endl;
        	Consulta(Datos);//LLamado de la funcion consulta   	
			break;
        case 5:
            cout<<"Salir y Grabar"<<endl;
            Grabar(Datos); // Llamado de la funcion grabar
            break;
        default:
            cout<<"Esta opcion no esta dispobible en el menu"<<endl;
            break;
        }
    }while (opc!=5);
}
/***Deficiones de las Funciones**/
int Menu(void) // Funcion menu 
{
	
    int Num;
    cout<<endl<<"**********"<<endl;
    cout<<endl<<"*        Banco UNIDOS        *"<<endl;
    cout<<endl<<"**********"<<endl;
    cout<<endl<<"*            MENU            *"<<endl;
    cout<<endl<<"*  1- Registrar un cliente   *"<<endl;
    cout<<endl<<"*  2- Realizar un deposito   *"<<endl;
    cout<<endl<<"*  3- Realizar un retiro     *"<<endl;
    cout<<endl<<"*  4- Realizar consulta      *"<<endl;
    cout<<endl<<"*  5- Salir y grabar         *"<<endl;
    cout<<endl<<"**********"<<endl;
    cout<<endl<<"*     INGRESE UNA OPCION     *"<<endl;
    cout<<endl<<"**********"<<endl;
    cin>>Num;
    return Num;
}

void Ingresar_Datos(dt Datos[5]) // Captura los datos de un nuevo cliente y los almacena en el arreglo de estructuras "Datos".
{
    cout<<"Ingrese su nombre"<<endl;
    cin>>Datos[1].Nombre;
    cout<<"Ingrese cedula"<<endl;
    cin>>Datos[1].Cedula;
    cout<<"Ingrese saldo de apertura"<<endl;
    cin>>Datos[1].Saldo;
    if (Datos[1].Saldo<10)
    {
    	cout<<"\tERROR"<<endl;
        cout<<"La cuenta no se creo, El monto minimo es 10 dolares"<<endl;
        system("pause"); system("cls");
    }
    else
    {
        srand(time(NULL));
        Datos[1].Numero_Cuenta=rand()% 9000 + 1000;
        system("cls");
        cout<<"\tCUENTA CREADA\n\t---------------\n\n";
        cout<<"La cuenta se a creado Exitosamente...\n"<<endl;
        cout<<"NUMERO DE CUENTA: "<<Datos[1].Numero_Cuenta<<endl<<"\n! ADVERTENCIA: NO OLVIDE SU NUMERO DE CUENTA, PORQUE SE USARA COMO CONTRASENIA PARA ADCEDER A OTRAS OPCIONES DEL MENU !"<<endl<<endl;
        system("pause"); system("cls");
    }
}

void Deposito(dt Datos[5]) // Permite realizar un dep�sito en la cuenta de un cliente espec�fico.
{
    cout<<"Ingrese su numero de cuenta"<<endl;
    int Numero;
    double Saldo;
    cin>>Numero; cout<<endl; system("cls");
    if (Numero==Datos[1].Numero_Cuenta)
    {
    	cout<<"\tDEPOSITANDO MONTO\n"<<endl;
        cout<<"Ingrese el monto a depositar"<<endl;
        cin>>Saldo;system("cls");
        Datos[1].Saldo=Saldo+Datos[1].Saldo;
        cout<<"\tMONTO ACTUALIZADO\n"<<endl;
		cout<<"su nuevo saldo es: "<<Datos[1].Saldo<<endl<<endl; 
		system("pause"); system("cls");    
	}
    else
    {
        cout<<"No se encontro el numero de cuenta"<<endl; 
		system("pause"); system("cls");
    }
}

void Retiro(dt Datos[5]) // Permite realizar un retiro de la cuenta de un cliente espec�fico.
{
    cout<<"Ingrese su numero de cuenta"<<endl;
    int Numero;
    double Saldo;
    cin>>Numero; system("cls");
    if (Numero==Datos[1].Numero_Cuenta)
    {
        if (Datos[1].Saldo!=0)
        {
        	cout<<"\tRETIRANDO MONTO\n"<<endl;
            cout<<"Su saldo es de: "<<Datos[1].Saldo<<endl;
            cout<<"Cuanto desea retirar?"<<endl;
            cin>>Saldo;
            if (Saldo<Datos[1].Saldo)
            {
                cout<<"Retiro: "<<Saldo<<endl;
                Datos[1].Saldo=Datos[1].Saldo-Saldo;
                cout<<"Su nuevo saldo es de: "<<Datos[1].Saldo<<endl<<endl; 
                system("pause"); system("cls");
            }
            else{
                cout<<"La cantidad ingresada excede el la cantidad disponible "<<endl;
                
            }
            
        }
        else
        {
            cout<<"Su saldo es de: "<<Datos[1].Saldo<<endl; 
			system("pause"); system("cls");
        }
    }
    else
    {
        cout<<"No se encontro el numero de cuenta"<<endl;
        system("pause"); system("cls");
	}
    
}


void Consulta(dt Datos[5])//Muestra la informaci�n de la cuenta de un cliente espec�fico.
{
	int Numero;
	cout<<"Ingrese su numero de cuenta"<<endl;
	cin>>Numero;
    system("cls");
    if (Numero==Datos[1].Numero_Cuenta)
    {
	   if (Datos[1].Numero_Cuenta!=0)
	   {
	    cout<<"               SISTEMA CLAVE "<<endl;
        cout<<"               BANCO  UNIDOS "<<endl;
        cout<<"-------------------------------------------"<<endl;
        cout<<"-Nombre:                    "<<Datos[1].Nombre<<endl;
        cout<<"-Cedula:                    "<<Datos[1].Cedula<<endl;
        cout<<"-Numero de cuenta:          "<<Datos[1].Numero_Cuenta<<endl;
        cout<<""<<endl;
        cout<<""<<endl;
        cout<<"                 NUEVO SALDO"<<endl;
        cout<<"DIS  :     "<<Datos[1].Saldo<<endl;
        cout<<"TOTAL:     "<<Datos[1].Saldo<<endl;
	    cout<<""<<endl;
        cout<<""<<endl;
        cout<<"AUTORIZACION                         000001"<<endl;
        cout<<"-------------------------------------------"<<endl;
	    system("pause"); system("cls");
	   }
	        else
	        {
	        cout<<endl<<"Debe registrar un cliente antes de hacer una consulta"<<endl;	
	        system("pause"); system("cls");
	        }
    }
    }
void Grabar(dt Datos[5])//Graba los datos de la cuenta de un cliente en un archivo llamado "datos_banco.txt".
{
    ofstream Recibo;
    Recibo.open("datos_banco.txt");
    Recibo<<"               SISTEMA CLAVE "<<endl;
    Recibo<<"               BANCO  UNIDOS "<<endl;
    Recibo<<"-------------------------------------------"<<endl;
    Recibo<<"-Nombre:                    "<<Datos[1].Nombre<<endl;
    Recibo<<"-Cedula:                    "<<Datos[1].Cedula<<endl;
    Recibo<<"-Numero de cuenta:          "<<Datos[1].Numero_Cuenta<<endl;
    Recibo<<""<<endl;
    Recibo<<""<<endl;
    Recibo<<"                 NUEVO SALDO"<<endl;
    Recibo<<"DIS  :     "<<Datos[1].Saldo<<endl;
    Recibo<<"TOTAL:     "<<Datos[1].Saldo<<endl;
    Recibo<<""<<endl;
    Recibo<<""<<endl;
    Recibo<<"AUTORIZACION                         000001"<<endl;
    Recibo<<"-------------------------------------------"<<endl;
    cout<<endl<<"Se acreado el archivo datos_banco.txt con !EXITO!"<<endl;
    Recibo.close();
}

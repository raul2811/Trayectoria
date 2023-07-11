
/*
---Simulador De Banco---

Programa=	C++

Fecha=	19/020/2023

Desarrolladores=

Nombres			Cedula

Kevin Gatica	EC-0046-12163
Maria Gutierrez	8-912-1033
Reynaldo blanco	20-716-284
Raul serrano 	6-723-2084

*/

#include<iostream>
#include<fstream> //libreria para archivos
#include<stdlib.h>
#include<cstdlib>//Librerias para generar los 4 digitos aleatorios
#include<ctime>
#include<limits>
using namespace std;

struct Datos
{
    string Nombre,Cedula;
    int Numero_Cuenta;
    float Saldo;
};
typedef struct Datos dt;

int main() /*Funcion Principal Main */
{
    dt Datos[20];
    int contador=0;
    /***Declaraciones***/
    int Menu(void); // Prototipo Menu
    void Ingresar_Datos(dt[],int*); //Prototipo de captura datos
    void Cargar(dt[],int*);//Prototipo de cargar
    void Deposito(dt[]); // Prototipo de deposito
    void Retiro(dt[]); // Prototipo de retiro
    void Consulta(dt[],int*);//Prototipo de consulta
    void Grabar(dt[],int*); // Prototipo de salir y grabar
    Cargar(Datos, &contador);
    int opc;
    
    do
    {
        opc=Menu();system("cls");
        switch(opc)
        {
        case 1:
            cout<<endl<<"\tUSTED ESCOGIO LA OPCION #1\n"<<endl;
            cout<<"Usted escojio la opcion Registrar un Cliente.\n"<<endl;
            Ingresar_Datos(Datos,&contador); //LLamado a la funcion capturar datos
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
        	Consulta(Datos,&contador);//LLamado de la funcion consulta   	
			break;
        case 5:
            cout<<"Salir y Grabar"<<endl;
            Grabar(Datos,&contador); // Llamado de la funcion grabar
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

void Ingresar_Datos(dt Datos[20], int *contador) {
    float valor_invalido(void); // Prototipo de la funcion valor_invalido.
    int num;
    bool deseaRegistrarOtraCuenta = true;

    while (deseaRegistrarOtraCuenta && *contador < 20) {
        cout << "Ingrese su nombre: ";
        cin >> Datos[*contador].Nombre;
        cout << "Ingrese su cedula: ";
        cin >> Datos[*contador].Cedula;
        Datos[*contador].Saldo = valor_invalido();

        if (Datos[*contador].Saldo < 10) {
            cout << "\tERROR" << endl;
            cout << "La cuenta no se creo. El monto minimo es de 10 dolares." << endl;
            system("pause");
            system("cls");
        } else {
            srand(time(NULL));
            Datos[*contador].Numero_Cuenta = rand() % 9000 + 1000;
            system("cls");
            cout << "\tCUENTA CREADA\n\t---------------\n\n";
            cout << "La cuenta se ha creado exitosamente." << endl;
            cout << "NUMERO DE CUENTA: " << Datos[*contador].Numero_Cuenta << endl;
            cout << "! ADVERTENCIA: NO OLVIDE SU NUMERO DE CUENTA, PORQUE SE USARA COMO CONTRASEÑA PARA ACCEDER A OTRAS OPCIONES DEL MENU !" << endl << endl;
            system("pause");
            system("cls");
            (*contador)++;
        }

        if (*contador < 20) {
            do {
                cout << "Desea registrar otra cuenta? Si(1) o No(2): ";
                cin >> num;
                system("pause");
                system("cls");
            } while (num != 1 && num != 2);

            deseaRegistrarOtraCuenta = (num == 1);
        }
    }

    if (*contador == 0) {
        cout << "Debe registrar al menos una cuenta." << endl;
        system("pause");
        system("cls");
    } else if (*contador == 20) {
        cout << "Se ha alcanzado el limite mAximo de cuentas registradas." << endl;
        system("pause");
        system("cls");
    }
}
void Deposito(dt Datos[20]) // Permite realizar un deposito en la cuenta de un cliente especifico.
{
    float valor_invalido(void);// prototipo de la funcion valor invalido.
    int buscar_cuenta(dt[]);//prototipo de la funcion buscar cuenta.
    int Numero = buscar_cuenta(Datos); system("cls");
    if (Numero!=-1)
    {
        Datos[Numero].Saldo = valor_invalido() + Datos[Numero].Saldo;
        cout<<"\tMONTO ACTUALIZADO\n"<<endl;
		cout<<"su nuevo Saldo es: "<<Datos[Numero].Saldo<<endl<<endl; 
		system("pause"); system("cls");    
	}
    else
    {
        cout<<"No se encontro el numero de cuenta"<<endl; 
		system("pause"); system("cls");
    }
}

void Retiro(dt Datos[20]) // Permite realizar un retiro de la cuenta de un cliente espec�fico.
{
    float valor_invalido(void);// prototipo de la funcion valor invalido.
    int buscar_cuenta(dt[]);//prototipo de la funcion buscar cuenta.
    int Numero = buscar_cuenta(Datos); system("cls");
    if (Numero!=-1)
    {
        if (Datos[Numero].Saldo!=0)
        {
        	cout<<"\tRETIRANDO MONTO\n"<<endl;
            cout<<"Su Saldo es de: "<<Datos[Numero].Saldo<<endl;
            float saldoRetiro = valor_invalido();
            if (saldoRetiro <= Datos[Numero].Saldo) {
            cout << "Retiro: " << saldoRetiro << endl;
            Datos[Numero].Saldo -= saldoRetiro;
            cout << "Su nuevo Saldo es de: " << Datos[Numero].Saldo << endl << endl;
            system("pause");
            system("cls");
    }
            else {
            cout << "La cantidad ingresada excede la cantidad disponible." << endl;
            system("pause");
            system("cls");
}
            
        }
        else
        {
            cout<<"Su Saldo es de: "<<Datos[Numero].Saldo<<endl; 
			system("pause"); system("cls");
        }
    }
    else
    {
        cout<<"No se encontro el numero de cuenta"<<endl;
        system("pause"); system("cls");
	}
    
}


void Consulta(dt Datos[20],int*contador) {
    int buscar_cuenta(dt[]);
    int Numero = buscar_cuenta(Datos);
    if (Numero != -1) {
        cout << "               SISTEMA CLAVE " << endl;
        cout << "               BANCO  UNIDOS " << endl;
        cout << "-------------------------------------------" << endl;
        cout << "-Nombre:                    " << Datos[Numero].Nombre << endl;
        cout << "-Cedula:                    " << Datos[Numero].Cedula << endl;
        cout << "-Numero de cuenta:          " << Datos[Numero].Numero_Cuenta << endl;
        cout << "" << endl;
        cout << "" << endl;
        cout << "         Saldo         " << endl;
        cout << "DIS  :     " << Datos[Numero].Saldo << endl;
        cout << "TOTAL:     " << Datos[Numero].Saldo << endl;
        cout << "" << endl;
        cout << "" << endl;
        cout << "AUTORIZACION                         " <<*contador<< endl;
        cout << "-------------------------------------------" << endl;
        system("pause");
        system("cls");
    }
    else {
        cout << "No se encontro la cuenta." << endl;
        system("pause");
        system("cls");
    }
}
void Grabar(dt Datos[20],int*contador)//Graba los datos de la cuenta de un cliente en un archivo llamado "datos_banco.txt".
{
    ofstream Recibo;
    Recibo.open("datos_banco.txt");
    for (int i = 0; i < *contador; i++)
    {
        Recibo<<Datos[i].Nombre<<" "<<Datos[i].Cedula<<" "<<Datos[i].Numero_Cuenta<<" "<<Datos[i].Saldo<<endl;
    }
    cout<<endl<<"Se a creado el archivo datos_banco.txt con !EXITO!"<<endl;
    Recibo.close();
}

float valor_invalido(void)//verifica si la entrada captura solo numeros
{
bool valorInvalido = true;
        string Saldo;
        float Saldof;
        while (valorInvalido) {//!mientras valor invalido sea true ,while se ejecuta hasta que valor invalido sea igual a false
        cout<<"Ingrese la cantidad"<<endl;
        cin>>Saldo;
        // Verificar si la cadena ingresada contiene solo digitos
        bool esNumero = true;
        for (char c : Saldo) {
            if (!isdigit(c)&& c != '.') {//! Si el caracter no es un digito ni un punto decimal
                esNumero = false;
                break;
            }
        }
        if (esNumero) {
            //!Convertir la cadena a un nUmero float
            Saldof = stof(Saldo);
            valorInvalido = false;
        }
        else {
            cout << "Se ha ingresado un valor invalido. Intente nuevamente." << endl;
            cin.clear(); //! Restablecer el estado del objeto cin
            cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Ignorar el resto de la linea
        }
    }
    return Saldof;
}

int buscar_cuenta(dt Datos[20]) {
    cout << "Ingrese su numero de cuenta:" << endl;
    int Numero;
    cin >> Numero;
    system("cls");

    for (int i = 0; i < 20; i++) {
        if (Numero == Datos[i].Numero_Cuenta)//!Si numero de cuenta es igual a numero ingresado retorna el indice. 
        {
            return i;
        }
    }

    return -1;
}
void Cargar(dt Datos[20], int* contador) {
    ifstream archivo;
    archivo.open("datos_banco.txt");
    if (archivo.is_open()) {
        string nombre, cedula;
        int numeroCuenta;
        float saldo;
        while (archivo >> nombre >> cedula >> numeroCuenta >> saldo) {
            Datos[*contador].Nombre = nombre;
            Datos[*contador].Cedula = cedula;
            Datos[*contador].Numero_Cuenta = numeroCuenta;
            Datos[*contador].Saldo = saldo;
            (*contador)++;
        }
        cout << "Se cargaron los datos del archivo exitosamente." << endl;
        archivo.close();
    }
    else {
        cout << "No se pudo abrir el archivo datos_banco.txt." << endl;
    }
}
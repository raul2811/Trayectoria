#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<cstdlib>
#include<ctime>
using namespace std;

struct Datos
{
    string nombre,cedula;
    int numero_cuenta,comprobacion;
    double saldo;
};
typedef struct Datos dt;
int main()
{
    dt datos[100];
    int contador=0,numero_lineas;
    string archivo_nombre="datos_banco.txt";
    int menu(void);
    int contarLineas(string*);
    bool buscar(dt[],string*,int*);
    void ingresar_datos(dt[],int*,string*,int*);
    void deposito(dt[]);
    void retiro(dt[]);
    void grabar(dt[],int*,string*);
    numero_lineas=contarLineas(&archivo_nombre);
    cout<<"El numero de lineas del documento es:"<<numero_lineas<<endl;
    int opc;
do
{
   opc=menu();//Llamo a la funcion menu
   //system("pause");//Detenemos la pantalla
   switch (opc)
    {
    case 1:
        cout<<endl<<"\tUsted escogio la opcion #1"<<endl;
        cout<<"Usted escogio la opcion Registrar un cliente"<<endl;
        ingresar_datos(datos,&contador,&archivo_nombre,&numero_lineas);
        break;
    case 2:
        cout<<endl<<"\tUsted escogio la opcion #2"<<endl;
        cout<<"Usted escogio la opcion deposito"<<endl;
        deposito(datos); 
        break;
    case 3:
        cout<<endl<<"\tUsted escogio la opcion #3"<<endl;
        cout<<"Usted escogio la opcion retiro"<<endl;
        retiro(datos);
        break;
    case 4:
        cout<<"Salir y Grabar"<<endl;
        grabar(datos,&contador,&archivo_nombre);
        break;
    default:
        cout<<"Esta opcion no esta disponible en el menu"<<endl;
        break;
    }
    //system("cls");
} while (opc!=4);
}
int menu(void)
{
int num;
cout<<endl<<"INGRESE UNA OPCION"<<endl<<"1- Registrar un cliente"
<<endl<<"2- realizar deposito"<<endl
<<"3- realizar retiro"<<endl
<<"4- Salir y Grabar"<<endl<<"-";
cin>>num;
return num;
}

int contarLineas(string *archivo_nombre) {
    ifstream archivo(*archivo_nombre);
    if (!archivo.good()) {
        return 0; // Retorna 0
    }
    int num = 0;
    string linea;
    while (getline(archivo, linea)) {
        ++num;
    }
    archivo.close();
    return num;
}
bool buscar(dt datos[100],string*&archivo_nombre,int*contador) {

    ifstream archivo(*archivo_nombre);
    if (!archivo.is_open()) {
        return false;
    }
    string nombre, cedula, saldo;
    int numeroCuenta;
    bool con = false;
    while (archivo >> nombre >> cedula >> numeroCuenta >> saldo) {
        if (numeroCuenta == datos[*contador].numero_cuenta) {
            con = true;
            
            break;
        }
    }
    archivo.close();
    return con;
}
void ingresar_datos(dt datos[100],int*contador,string *archivo_nombre,int *numero_lineas)
{   
    int respuesta;
    do {
        cout<<"Ingresa el nombre del cliente"<<endl;
        cin>>datos[*contador].nombre;
        cout<<"Ingresa la cedula del cliente"<<endl;
        cin>>datos[*contador].cedula;
        cout<<"Ingrese el saldo de apertura"<<endl;
        cin>>datos[*contador].saldo;
        if (datos[*contador].saldo < 10) {
            cout<<"La cuenta no ha sido creada debido a que el monto de apertura es demasiado bajo"<<endl;
        } else {
            bool generado, encontrado;
            do {
                // Generar número de cuenta aleatorio
                srand(time(NULL));
                datos[*contador].numero_cuenta = rand() %9999 + 0000 ;
                // Comprobar si el número de cuenta ya existe en el vector
                generado = false;
                for (int i = 0; i < *contador; i++) {
                    if (datos[i].numero_cuenta == datos[*contador].numero_cuenta) {
                        generado = true;
                        break;
                    }
                }
                // Comprobar si el número de cuenta ya existe en el archivo
                encontrado = buscar(datos, archivo_nombre, contador);
                if (!generado && !encontrado) {
                    // El número de cuenta no ha sido generado previamente ni está en el archivo
                    cout << "La cuenta se ha creado EXITOSAMENTE" << endl;
                    cout << "El número de cuenta es: " << datos[*contador].numero_cuenta << endl;
                } else {
                    // El número de cuenta ya existe, generando otro número
                    cout << "El número de cuenta ya existe, generando otro número..." << endl;
                }
            } while (generado || encontrado);
            (*contador)++;
            if (*contador >=100) {
                cout << "Se ha alcanzado el límite máximo de cuentas" << endl;
                break;
            }
        }
        cout<<"¿Desea ingresar un nuevo cliente? Si(1) o NO(2)"<<endl;
        cin>>respuesta;   
    } while (respuesta == 1);
}
void deposito(dt datos[100],int*contador)
{
    cout<<"Ingresa el numero de cuenta"<<endl;
    int numero;
    double saldo;
    cin>>numero;
    if (numero==datos[1].numero_cuenta)
    {
    cout<<"Ingrese el monto a depositar"<<endl;
    cin>>saldo;
    datos[1].saldo=saldo+datos[1].saldo;
    cout<<"Su nuevo saldo es:"<<datos[1].saldo<<endl;
    }
    else
    {
    cout<<"No se ha con el numero de cuenta"<<endl;    
    }
}
void retiro(dt datos[100],int*contador)
{
cout<<"Ingrese numero de cuenta"<<endl;
int numero;cin>>numero;
double saldo;
for (size_t i = 0; i < *contador; i++)
{
    if (numero==datos[i].numero_cuenta)
    {
    if (datos[i].saldo!=0)
    {
        cout<<"Su saldo es de:"<<datos[i].saldo<<endl;
        cout<<"Cuanto desea retirar"<<endl;cin>>saldo;
        cout<<"Retiro:"<<saldo<<endl;
        datos[i].saldo=datos[i].saldo-saldo;
        cout<<"nuevo saldo:"<<datos[i].saldo;
    }
    else
    {
    cout<<"Su saldo es de:"<<datos[i].saldo<<endl;  
    }
    }
    else
    {
    cout<<"No se ha con el numero de cuenta"<<endl;    
    }
}


}
void grabar(dt datos[100],int *contador,string *archivo_nombre)
{
    int x=0;
    do
    {
    ofstream grabar;
    grabar.open(*archivo_nombre, ios::app);
    if (!grabar.is_open()) { // Si el archivo no se pudo abrir
        grabar.open(*archivo_nombre); // Crea el archivo
        grabar.close(); // Cierra el archivo
        grabar.open(*archivo_nombre, ios::app); // Abre el archivo en modo de agregar
    }
    grabar << datos[x].nombre << " " << datos[x].cedula << " " << datos[x].numero_cuenta << " " << datos[x].saldo << endl;
    grabar.close();
    (x)++;
    } while (x!=*contador);
}
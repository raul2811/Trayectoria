//!Prueba de estructura.

#include<iostream>
using namespace std;

struct Datos
{
    int numero_cuenta;
};
typedef struct Datos dt;
dt dt1[10];

int main()
{
    cout<<"Ingresa un numero"<<endl;
    cin>>dt1[1].numero_cuenta;
    cout<<"El numero ingresado fue:"<<dt1[1].numero_cuenta;
}



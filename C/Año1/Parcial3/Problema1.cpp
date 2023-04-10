#include<iostream>
using namespace std;
//!--------Funcion---------------//!
float calculos(float a, float b) 
{
    return (a*b);
}
//!----------------------------------//!
int main()
{
int x,num;//!Almacena El Numero De Empleados
float horas,shoras,salariot;
std::string cdg[20];//!codigo de empleado
cout<<"Ingrese La Cantidad De Empleados  Que Desea Ingresar\n-"<<endl;
cin>>x;//!capturamos el Valor
for (num=0;num<x;num++)
{
cout<<"\nIngrese El Codigo De Empleado\n"<<endl;
cin>>cdg;
cout<<"\nIngrese El Salario Por Hora\n"<<endl;
cin>>horas;
cout<<"\nIngrese El Numero De Horas Trabajadas\n"<<endl;
cin>>horas;
salariot=calculos(shoras,horas);
cout<<"\nAl Empleado Con Codigo"<<cdg<<"Se Le Debe Pagar:"<<salariot<<endl;
}
return 0;
}


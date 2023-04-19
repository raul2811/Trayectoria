#include <iostream>
#include <fstream>
#include <iomanip>
using namespace std;
int main()
{
//Declarar variables
string nombre_hotel;
int numero_dias,numero_adulto,numero_ninos;
float tarifa;
//Declaramos las funciones
void  captura_datos(string*,int*,float*,int*,int*);
void  calculos(int*,int*,int*,float*);
void imprime_datos();
captura_datos(&nombre_hotel,&numero_dias,&tarifa,&numero_adulto,&numero_ninos);
calculos(&numero_ninos,&numero_adulto,&numero_dias,&tarifa);
}
void captura_datos(string *nombre_hotel,int *numero_dias,float *tarifa,int *numero_adulto,int *numero_ninos)
{
cout<<"Ingrese el nombre del hotel"<<endl;
cin>>*nombre_hotel;
cout<<"Ingrese el numero de dias"<<endl;
cin>>*numero_dias;
cout<<"Ingrese la tarifa"<<endl;
cin>>*tarifa;
cout<<"Ingrese el numero de adultos"<<endl;
cin>>*numero_adulto;
cout<<"Ingrese el numero de niños"<<endl;
cin>>*numero_ninos;
}
void calculos(int *numero_ninos,int *numero_adultos,int*numero_dias,float*tarifa)
{
float impuesto=0.7,total;
numero_ninos=*numero_ninos((*numero_dias)(*tarifa)/2);
numero_adultos=*numero_adultos((*numero_dias)(*tarifa));
total=numero_ninos+numero_adultos(impuesto);
}
void imprime_datos();
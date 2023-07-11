#include<iostream>
#include<stdlib.h>

using namespace std;

class Fecha{

    private://!Atributos
        int dia,mes,ano;

    public://!Metodos
        Fecha(int,int,int);
        void mostrar_fecha();
        
};

//!Constructor-1

Fecha::Fecha(int _dia , int _mes , int _ano)
{
    ano= _ano;
    mes= _mes;
    dia= _dia;
}

void Fecha::mostrar_fecha()
{
   cout<<"La fecha es: "<<dia<<"/"<<mes<<"/"<<ano<<endl; 
}

int main ()
{
Fecha hoy(9,1,2017);
hoy.mostrar_fecha();
system("pause");
return 0;
}
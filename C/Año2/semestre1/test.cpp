#include <iostream>//! Libreria prederminada C++.
#include <iomanip>//! Libreria para asignar el rendodeo del punto decimal.
#include <fstream>//! Libreria para leer y escribir en un archivo.
using namespace std;
//----------------------------------//
int main()
{
int x,num,horas;//Almacena El Numero De Empleados
double  shoras,salariot;
double  calculos(double a,int b);
void    guardar_archivo(string cdg ,double shoras,int horas ,double salariot );
string cdg;//codigo de empleado
cout<<"Ingrese La Cantidad De Empleados Que Desea Ingresar"<<endl<<"-";
cin>>x;//capturamos el Valor
for (num=0;num<x;num++)
{
cout<<"Empleado#"<<num+1<<"/"<<x<<endl;    
cout<<"Ingrese El Codigo De Empleado"<<endl<<"-";
cin>>cdg;
cout<<"Ingrese El Salario Por Hora"<<endl<<"-";
cin>>shoras;
cout<<"Ingrese El Numero De Horas Trabajadas"<<endl<<"-";
cin>>horas;
salariot=calculos(shoras,horas);
cout<<"Al Empleado Con Codigo:#"<<cdg<<endl<<"Se Le Debe Pagar:$"<<fixed<<setprecision(2)<<salariot<<endl;
guardar_archivo(cdg,shoras,horas,salariot);
return 0;
}
}

double calculos(double a,int b)//Se encarga de calcular el pago
{
    double multiplicacion;
    multiplicacion=a*b;
    return (multiplicacion);
}

void guardar_archivo(string cdg ,double shoras,int horas ,double salariot )
{
    ofstream guardar__archivo;
    guardar__archivo.open("empleados.txt");
    guardar__archivo<<cdg<<" "<<shoras<<" "<<horas<<" "<<salariot<<" ";
    guardar__archivo.close();
}




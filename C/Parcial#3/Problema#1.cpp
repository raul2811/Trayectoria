#include<conio.h>
#include<locale.h>
#include<stdio.h>
#include<iostream>
using namespace std;
//----------------------------------//
float calculos(float a, float b) 
{
    return (a*b);
}
//----------------------------------//
main()
{
int x,num;//Almacena El Numero De Empleados
float horas,shoras,salariot;
char cdg[20];//codigo de empleado
printf("Ingrese La Cantidad De Empleados  Que Desea Ingresar\n-");
scanf("%d",&x);//capturamos el Valor
for (num=0;num<x;num++)
{
getchar();//borra la basura del buffer de entrada 
printf("\nIngrese El Codigo De Empleado\n");
scanf("%s",&cdg);
getchar();//borra la basura del buffer de entrada 
printf("\nIngrese El Salario Por Hora\n");
scanf("%f",&shoras);
getchar();//borra la basura del buffer de entrada 
printf("\nIngrese El Numero De Horas Trabajadas\n");
scanf("%f",&horas);
getchar();//borra la basura del buffer de entrada 
salariot=calculos(shoras,horas);
printf("\nAl Empleado Con Codigo %s Se Le Debe Pagar:%.2f",cdg,salariot);
}
getch();
return 0;
}


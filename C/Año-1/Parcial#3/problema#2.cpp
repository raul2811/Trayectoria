#include<conio.h>
#include<locale.h>
#include<stdio.h>
#include<iostream>
using namespace std;
struct Datos   
{
char nb[50];//Nombre
char ed[50];//edad
float sl;//salario
float ic;//incremento
float st;//salario total
}dt;//datos
main()
{
  printf("Ingrese El Nombre Del Empleado\n-");
  cin.getline(dt.nb,50,'\n');//guadamos nombre
  printf("Ingrese la Edad\n-");
  cin.getline(dt.ed,50,'\n');//almacenamos edad
  printf("Ingrese El Salario Actual\n-");
  scanf("%f",&dt.sl);//almaceno salario
  printf("\nResultados De Generados De El Empleado\n");//encabezado
  printf("Nombre:%s\nEdad:%s\nSalario:%.2f\n",dt.nb,dt.ed,dt.sl);
    if (dt.sl<800)
  {
    dt.ic=(dt.sl*0.10);
    dt.st=(dt.sl+dt.ic);
    printf("\nSu Incremento Sera Del %10");
    printf("\nSalario Incrementado:%.2f",dt.st);
  }
  else{
    dt.ic=(dt.sl*0.05);
    dt.st=(dt.sl+dt.ic);
    printf("\nSu Incremento Sera Del %5");
    printf("\nSalario Incrementado:%.2f",dt.st);
  }
  
}
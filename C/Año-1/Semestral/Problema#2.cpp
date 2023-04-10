#include<conio.h>
#include<locale.h>
#include<stdio.h>
#include <math.h>
#include<iostream>
using namespace std;
int calculo(int a) 
{
    int x3,x2,operacion;
    x3=(int)(pow(a,3));
    x2=(int)(2*pow(a,2));
    operacion=(x3+x2-1);
    return(operacion);//x^3+2x^2-1
}
main()
{
int num=0,x=6,y=0;
printf("Resultado De Y=x^3+2x^2-1 Remplazando X Con Un Valor De -1 A 5\n");
for (num=-1;num<x;num++)
{
y=calculo(num);
printf("\nEl Resultado De La Operacion Y=%d^3+2(%d^2)-1 Es: Y=%d",num,num,y);
}
}
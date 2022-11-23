#include <stdio.h>

main()
{
int x;//Almacena El Numero maximo que tendra el arreglo
printf("Ingrese La Cantidad De Estudiantes De los Cual Ingresara Notas\n-");
scanf("%d",&x);
float A[x];
float B[x];
float C[x][2];
float D[x];
int num;float resultado,nota_grupal=0;
printf("Cantidad De Estudiantes%d",x);
for (num=0;num<x;num++)
{
printf("\nIngrese La Nota #1 Del Estudiantes#%d\n-",num+1);
scanf("%f",&A[num]);
printf("\nIngrese La Nota #2 Del Estudiantes#%d\n-",num+1);
scanf("%f",&B[num]);
}
for (num=0;num<x;num++)
{
C[num][1]=(A[num]);
C[num][2]=(B[num]);
}
printf("\t  UNIVERSIDAD DE PANAMA\n\t\tPARCIAL#3\n\n\tIMPRIMIENDO LOS ARREGLOS A,B,C,D");
printf("\n\t\t  A\t  B\t\tC\t  D");
for (num=0;num<x;num++)
{
resultado=((A[num]+B[num])/2);
nota_grupal=(resultado+nota_grupal);
printf("\nEst.#%d\t\t%.2f\t%.2f\t   %.2f %.2f\t%.2f",num+1,A[num],B[num],C[num][1],C[num][2],resultado);
}
printf("\n\n\t\t\t\tPromedio Grupal:%.2f\n",(nota_grupal/x));
printf("\nEstudiante:Remby Apellido.\tCedula:#-###-####");
}
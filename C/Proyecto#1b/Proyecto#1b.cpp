#include<stdio.h>
main()
{
//----Declaracion De Variables----//
char *almacenes[]={"Albrook Mall","Arraijan","Metro Mall","Costa Del Este","Herrera","Chiriqui","Las Tablas","Colon","Chorrera","Altaplasa"};
char *mes[]={"Marzo","Abril","Mayo"};
int matriz[10][3],fila,col;
int marzo=(0),mayo=(0),abril=(0),condicion=(0),Albrook_Mall,Arraijan,Metro_Mall,Costa_Del_Este,Herrera,Chiriqui,Las_Tablas,Colon,Chorrera,Altaplasa;
//----Proceso----//
do
{
printf("Elija Una Opcion\n1)Permita llenar la tabla\n2)Imprimir el total de ventas por cada mes\n3)Imprimir el promedio de venta por almacen \n4)Imprimir La Tabla Completa \n5)Salida del programa\n");
scanf("%d",&condicion);
    if (condicion==1)
    {
    printf("usted Eligio Llenar la Tabla\n");
    for(col = 0;col < 3;col++){
        for (fila = 0;fila < 10;fila++){
            printf("De La columna #%d De La Fila #%d Del Mes:%s , digite el valor #%d\n%d)",col+1,fila+1,mes[col],fila+1,fila+1);
            scanf("%d",&matriz[fila][col]);
        }
    }
    printf("Desea Realizar Otra Consulta? 1(Si) Y 5(No)\n");
    scanf("%d",&condicion);
    }
    else if (condicion==2)
    {
    printf("Imprimir el total de ventas por cada mes\n");
    for (fila = 0;fila < 10;fila++){
        marzo=(marzo+matriz[fila][0]);
        abril=(abril+matriz[fila][1]);
        mayo=(mayo+matriz[fila][2]);
        }
    printf("\tMarzo\tAbril\tMayo\n");
    printf("\t%d\t%d\t%d\n",marzo,abril,mayo);
    printf("\nDesea Realizar Otra Consulta? 1(Si) Y 5(No)\n");
    scanf("%d",&condicion);
    }
    else if (condicion==3){
    printf("Imprimir el Promedio De Ventas \n");
        Albrook_Mall=(matriz[0][0]+matriz[0][1]+matriz[0][2]);
        Arraijan=(matriz[1][0]+matriz[1][1]+matriz[1][2]);
        Metro_Mall=(matriz[2][0]+matriz[2][1]+matriz[2][2]);
        Costa_Del_Este=(matriz[3][0]+matriz[3][1]+matriz[3][2]);
        Herrera=(matriz[4][0]+matriz[4][1]+matriz[4][2]);
        Chiriqui=(matriz[5][0]+matriz[5][1]+matriz[5][2]);
        Las_Tablas=(matriz[6][0]+matriz[6][1]+matriz[6][2]);
        Colon=(matriz[7][0]+matriz[7][1]+matriz[7][2]);
        Chorrera=(matriz[8][0]+matriz[8][1]+matriz[8][2]);
        Altaplasa=(matriz[9][0]+matriz[9][1]+matriz[9][2]);
        printf("Almacen\t\tPromedio\n");
        printf("%s\t%d\n",almacenes[0],Albrook_Mall/3);
        printf("%s\t%d\n",almacenes[1],Arraijan/3);
        printf("%s\t%d\n",almacenes[2],Metro_Mall/3);
        printf("%s\t%d\n",almacenes[3],Costa_Del_Este/3);
        printf("%s\t\t%d\n",almacenes[4],Herrera/3);
        printf("%s\t%d\n",almacenes[5],Chiriqui/3);
        printf("%s\t%d\n",almacenes[6],Las_Tablas/3);
        printf("%s\t\t%d\n",almacenes[7],Colon/3);
        printf("%s\t%d\n",almacenes[8],Chorrera/3);
        printf("%s\t%d\n",almacenes[9],Altaplasa/3);
        printf("\nDesea Realizar Otra Consulta? 1(Si) Y 5(No)\n");
        scanf("%d",&condicion);
        } 
    else if (condicion==4)
    {
    printf("Imprimir Toda La Tabla\n");
    printf("Almacen\t\tMarzo\tAbril\tMayo\n");
    for (fila = 0;fila < 5;fila++){
        printf("%s\t%d%d%d\n",almacenes[fila],matriz[fila][0],matriz[fila][1],matriz[fila][2]);
        }
    printf("\nDesea Realizar Otra Consulta? 1(Si) Y 5(No)\n");
    scanf("%d",&condicion);
    }
    else if (condicion>6){
    printf("Escogio una opcion no valida");
    printf("\nDesea Realizar Otra Consulta? 1(Si) Y 5(No)\n");
    scanf("%d",&condicion);
    }
    }
while(condicion!=5);  
}

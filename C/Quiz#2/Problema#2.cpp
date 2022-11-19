#include<stdio.h>
main(){
    float ventas[3][2];int fila1,col1;
    float total[3][1];int fila,col;
    float total2[3][1];int fila3,col3;
    float total7[3][1];int fila2,col2;
    printf("ingrese las Ventas\n");
    for(col1 = 0;col1 < 1;col1++){
        for (fila1 = 0;fila1 < 3;fila1++){
            printf("Ingrese el precio del producto #%d \n",col1+1);
            scanf("%f",&ventas[fila1][col1]);
            printf("Ingrese el precio del producto #%d \n",col1+2);
            scanf("%f",&ventas[fila1][col1+1]);
              }
    }
        for (fila = 0;fila < 3;fila++){
            total[fila][0]=(ventas[fila][0]+ventas[fila][1]);
            total7[fila][0]=(total[fila][0]*0.07);
            total2[fila][0]=(total[fila][0]+total7[fila][0]);
            }
    printf("Producto#1\tProducto#2\tTotal\n");
    for (fila1 = 0;fila1 < 3;fila1++){
        printf("%.2f\t\t%.2f\t\t%.2f\t\n",ventas[fila1][0],ventas[fila1][1],total2[fila1][0]);
        }
}
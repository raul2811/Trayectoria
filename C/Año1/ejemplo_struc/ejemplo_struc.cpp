#include<stdlib.h>
#include<string.h>
#include<stdio.h>
struct datos_caja
{
    char ndd[20];//Nombre Del Distribuidor
    char ndc[20];//Nombre Del Cliente
    char ndp[20];//Nombre Del Producto
    char tdp[20];//Tipo De Producto
    float pdp;//Precio Del Producto
    int cdp;//cantidad de productos
    char fecha[20];//fecha 
    float st;//subtotal
    float tt;//total
};

main()
{
    struct datos_caja d1;
    d1.pdp=0.0;
    d1.st=0;
    d1.tt=0;
    return 0;
}


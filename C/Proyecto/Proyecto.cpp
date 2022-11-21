#include<stdio.h>
main()
{
int x;//Almacena El Numero maximo que tendra el arreglo
printf("Ingrese La Cantidad De Clientes Que Desea Ingresar\n-");
scanf("%d",&x);
//------------------------------------//
struct Datos
#define clientes x   
{
const char*ndd[50];//Nombre Del Distribuidor
const char*ndc[50];//Nombre Del Cliente
const char*ndp[50];//Nombre Del Producto
const char*tdp[50];//Tipo De Producto
int cantidad;//Cantidad De Producto
float pdp;//Precio Del Producto
float subtotal;//subtotal De La Compra
float total;//Total De La Compra
}ftr[clientes];//factura
//------------------------------------//
int num;float incremento,porciento7;
porciento7=(0.07);//siete porciento
//------------------------------------//
printf("\n\t\tDATOS DE VENTA");
for (num=0;num<clientes;num++)
{
printf("\n\nCliente#%d\n",x);
printf("\nIngresa El Nombre Del Distribuidor\n-");
scanf("%s[^\n]",&ftr[num].ndd);//Almacenamos El Nombre Del Distribuidor En La Variablle ftr.ndd//
printf("\nIngrese El Nombre Del Cliente\n-");
scanf("%s[^\n]",&ftr[num].ndc);//Almacenamos El Nombre Del Cliente En La Variablle ftr.ndc//
printf("\nIngrese El Nombre Del Producto\n-");
scanf("%s[^\n]",&ftr[num].ndp);//Almacenamos El Nombre Del Producto En La Variablle ftr.ndp//
printf("\nIngrese El Tipo De Producto\n-");
scanf("%s[^\n]",&ftr[num].tdp);//Almacenamos El Tipo De Producto En La Variablle ftr.tdp//
printf("\nIngrese La Cantidad De Producto\n-");
scanf("%d[^\n]",&ftr[num].cantidad);//Almacenamos La Cantidad De Producto En La Variablle ftr.cantidad//
printf("\nIngrese El Precio Del Producto\n-");
scanf("%f[^\n]",&ftr[num].pdp);//Almacenamos LEl Precio Del Producto En La Variablle ftr.pdp/
}
for (num=0;num<clientes;num++)
{
ftr[num].subtotal=(ftr[num].pdp*ftr[num].cantidad);
incremento=(ftr[num].subtotal*porciento7);
ftr[num].total=(ftr[num].subtotal+incremento);
printf("%.2f",ftr[num].total);
}
}




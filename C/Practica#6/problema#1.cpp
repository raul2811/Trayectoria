#include<stdio.h>
#include<string.h>
#include<stdio.h>
struct estudiantes
{
    const char*nombre[30];
    const char*apellido[30];
    const char*provincia[30];
    const char*distrito[30];
    const char*corregimiento[30];
    const char*telefono[30];
}estu;
main(){
    printf("DATOS DE ESTUDIANTES");
    printf("\nIngrese Su Nombre\n");//El usuario ingresa el nombre
    scanf("%s",&estu.nombre);
    printf("Ingrese Su Apellido\n");//El usuario ingresa el apellido
    scanf("%s",&estu.apellido);
    printf("Ingrese Su Provincia\n");//El usuario ingresa el provincia
    scanf("%s",&estu.provincia);
    printf("Ingrese Su Distrito\n");//El usuario ingresa el distrito
    scanf("%s",&estu.distrito);
    printf("Ingrese Su Corregimineto\n");//El usuario ingresa el corregimiento
    scanf("%s",&estu.corregimiento);
    printf("Ingrese Su Telefono\n");//El usuario ingresa el telefono
    scanf("%s",&estu.telefono);
    printf("\n\nNombre:%s\nApellido:%s\nProvincia:%s\nDistrito:%s\nCorregimiento:%s\nTelefono:%s",estu.nombre,estu.apellido,estu.provincia,estu.provincia,
    estu.distrito,estu.telefono);//Se imprimen los datos
    return 0;
}

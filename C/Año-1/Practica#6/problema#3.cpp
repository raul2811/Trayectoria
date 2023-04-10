#include<stdio.h>
struct trabajador
{
    const char* nombre[30];
    const char* apellido[30];
    const char* edad[30];
    const char* cedula[30];
    float salario;
}datos;
main(){
    float incremento,porciento5,porciento10,salario_final;
    porciento5=(0.05);//cinco porciento
    porciento10=(0.1);//dies porciento
    printf("DATOS DE Trabajador");
    printf("\nIngrese Su Nombre\n");//El usuario ingresa el nombre
    scanf("%s",&datos.nombre);
    printf("Ingrese Su Apellido\n");//El usuario ingresa el apellido
    scanf("%s",&datos.apellido);
    printf("Ingrese Su Edad\n");//El usuario ingresa la edad
    scanf("%s",&datos.edad);
    printf("Ingrese Su Cedula\n");//El usuario la cedula
    scanf("%s",&datos.cedula);
    printf("Ingrese Su salario\n");//El usuario ingresa el salario actual
    scanf("%f",&datos.salario);
    if (datos.salario>500)
    {
        incremento=(datos.salario*porciento10);
        salario_final=(incremento+datos.salario);
    }
    else{
        incremento=(datos.salario*porciento5);
        salario_final=(incremento+datos.salario);
    }
    printf("%f",salario_final);
}


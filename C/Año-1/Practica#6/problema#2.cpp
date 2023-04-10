#include<stdio.h>
struct datos_estudiantes
#define notas 5
{
const char* nombre[30];
const char* apellido[30];
const char* cedula[30];
float promedio1;//promedio de parciales
float promedio2;//promedio de laboratorios 
float promedio3;//promedi de proyectos
float nota_final;//nota del semestral
}estu[notas];
main(){
int num;
for (num=0;num<notas;num++)
    {
        printf("\nIntrodusca Los Datos De El Estudiante  #%d ",num+1);
        printf("\nIntrodusca El Nombre Del Estudiante\n");
        scanf("%s",&estu[num].nombre);
        printf("Introdusca El Apellido Del Estudiante\n");
        scanf("%s",&estu[num].apellido);
        printf("Introdusca El Promedio De Parciales Del Estudiante\n");
        scanf("%f",&estu[num].promedio1);
        printf("Introdusca El Promedio De Laboratorios Del Estudiante\n");
        scanf("%f",&estu[num].promedio2);
        printf("Introdusca El Promedio de Proyectos Del Estudiante\n");
        scanf("%f",&estu[num].promedio3);
        printf("Introdusca La Nota Del Semestral Del Estudiante\n");
        scanf("%f",&estu[num].nota_final);
    }
for (num=0;num<notas;num++)
    {
        printf("\nNombre:%s\tApellido:%s\nPromedio Final:%.2f\n",estu[num].nombre,estu[num].apellido,((estu[num].promedio1+estu[num].promedio2+estu[num].promedio3
        +estu[num].nota_final)/4));
    }
}

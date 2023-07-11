#include<iostream>
#include<stdlib.h>

using namespace std;


class Persona{//creamos la clase

    private://atributos
        int edad;
        string nombre;
    public://metodos
        Persona(int,string);//contructor de la clase
        void leer();
        void correr();

};
//contructor, nos sirva para iniciliazar los atributos
Persona::Persona(int _edad , string _nombre)
{
    edad= _edad;
    nombre =  _nombre;
}
void Persona :: leer(){/*El operador de ámbito :: se utiliza para especificar que la función leer() es parte de la clase Persona.
leer(): Es el nombre de la función.*/

    cout<<"Soy "<<nombre<<" Y estoy leyendo un libro"<<endl;
}

void Persona :: correr(){
    cout<<"Soy"<<nombre<<"Y estoy corriendo un maraton"<<endl;
}
int main ()
{
    Persona p1 = Persona(20,"Alejadro");
    Persona p2(19,"Maria");
    Persona p3(21,"Juan");

    p1.leer();
    p2.correr();
    p3.correr();
    
    system("pause");
    return 0;
}
#include<iostream>
#include<stdlib.h>
//#include "./JSON.hpp"
//using JSON = dnc::JSON;
using namespace std;

class Pokemon{//Los nombres de clases siempre empiezan con mayuscula
    private: //!Atributos
        string name,move_1_name,move_2_name,move_3_name;//Nombre del pokemon
        float health;//Salud "Vida del pokemon"
        int move_1,move_2,move_3;//daño de los movimientos de cada pokemon
    public: //!metodos
        Pokemon(string,float,string,int,string,int,string,int);//!contructor de la clase
        void impresion();
};
//contructor, nos sirve para iniciliazar los atributos
Pokemon::Pokemon(string _name ,float _health ,string _move_1_name , int _move_1, string _move_2_name,int _move_2,string _move_3_name,int _move_3)
{
    //!asignamos sus variables correspondientes a cada variable
    name= _name;
    health= _health;
    move_1_name= _move_1_name;
    move_1= _move_1;
    move_2_name= _move_2_name;
    move_2= _move_2;
    move_3_name= _move_3_name;
    move_3= _move_3;
}

void Pokemon :: impresion(){/*El operador de ámbito :: se utiliza para especificar que la función impresion() 
es parte de la clase Pokemones , impresion(): Es el nombre de la función.*/

    cout<<name<<" !YO TE ELIJO! "<<endl<<"Salud: "<<health<<endl;
    cout<<"\t\tLista de movimientos"<<endl
    <<"Ataque\t\tDaño"<<endl
    <<move_1_name<<"\t"<<move_1<<endl
    <<move_2_name<<"\t"<<move_2<<endl
    <<move_3_name<<"\t"<<move_3<<endl;
}

int main ()
{
    Pokemon p1("pikachu",150,"Bola Voltio",10,"Impactrueno",40,"Ataque Rápido",40);
    p1.impresion();
    system("pause");
    return 0;
}
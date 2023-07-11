#include <iostream>
#include <stdlib.h>
#include <fstream>    //libreria para Archivos
#include <vector>
using namespace std;

class Pokemon{//Los nombres de clases siempre empiezan con mayuscula
    private: //!Atributos
        static int contador; // Variable miembro para contar los Pokémon
        string name,move_1_name,move_2_name,move_3_name;//Nombre del pokemon
        float health;//Salud "Vida del pokemon"
        int move_1,move_2,move_3;//daño de los movimientos de cada pokemon
    public: //!metodos
    
        Pokemon(string,float,string,int,string,int,string,int);//!contructor de la clase
        void movimientos();
        void lista_pokemones() const;
        void guardar(ofstream&);
};

// Inicialización de la variable contador fuera de la clase
int Pokemon::contador = 1;

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

void Pokemon::lista_pokemones() const{

    cout <<contador <<"-"<< name << endl;
    contador++;
}

void Pokemon :: movimientos(){/*El operador de ámbito :: se utiliza para especificar que la función impresion() 
es parte de la clase Pokemones , impresion(): Es el nombre de la función.*/

    cout<<endl<<name<<" !YO TE ELIJO! "<<endl<<"Salud: "<<health<<endl;
    cout<<"\t\tLista de movimientos"<<endl
    <<"Ataque\t\tDaño"<<endl
    <<move_1_name<<"\t"<<move_1<<endl
    <<move_2_name<<"\t"<<move_2<<endl
    <<move_3_name<<"\t"<<move_3<<endl;
}

void Pokemon::guardar(ofstream& archivo)
{
    archivo <<name<<" "<<health<<" "<<move_1_name<<" "<<move_1<<" "<<move_2_name<<" "<<move_2<<" "<<move_3_name<<" "<<move_3<<endl;
}

int main ()
{   
    int menun ();//Funcion menu
    void registrar();//Funcion registrar
    void cargarPokemones(vector<Pokemon>& pokemonVector);
    int opc;
    vector<Pokemon> pokemonVector; // Vector para almacenar los Pokémon
    do {
        // Cargar los Pokémon desde el archivo
        cargarPokemones(pokemonVector);
        cout<<"Lista de pokemones"<<endl<<endl;
        // Mostrar los Pokémon cargados
        for (const Pokemon& pokemon : pokemonVector) {
            pokemon.lista_pokemones();
        }

        system("pause");
        system ("cls");
		opc = menun(); //LLAMADO funcion menun(), con retorno int
		
		switch (opc){    
          	case 1: 
                
                system("cls");
          		break;        
            case 2:
                system("cls");   
          		break;
			case 3:
                registrar();
                system("cls");
          		break;
			case 4:
                system("cls");          
          		break;
			default:
				cout<<"Opcion invalida, Intente nuevamente"<<endl;
				break;       
            }
        } while (opc!=4);
    return 0; 
}

int menun(void){
	int opcion;
	cout<<" POKEMON!"<<endl;
    cout<<"1- Jugar contra CPU"<<endl;
    cout<<"2- Multijugador"<<endl;
    cout<<"3- Registrar nuevo pokemon"<<endl;
    cout<<"4- Salir"<<endl;
    cin>>opcion;     
	return opcion; //Retorno int de la funcion
}

void registrar(void){

    string newName,newMove1Name,newMove2Name,newMove3Name;
    float newHealth;
    int newMove1,newMove2,newMove3;
    cout << "Ingrese el nombre del  Pokémon: ";
    cin >> newName;
    cout << "Ingrese la salud de "<<newName<<endl;
    cin >> newHealth;
    cout << "Ingrese el nombre del movimiento 1 de "<<newName<<endl;
    cin >> newMove1Name;
    cout << "Ingrese el daño del movimiento 1 de "<<newName<<endl;
    cin >> newMove1;
    cout << "Ingrese el nombre del movimiento 2 de "<<newName<<endl;
    cin >> newMove2Name;
    cout << "Ingrese el daño del movimiento 2 de "<<newName<<endl;
    cin >> newMove2;
    cout << "Ingrese el nombre del movimiento 3 de "<<newName<<endl;
    cin >> newMove3Name;
    cout << "Ingrese el daño del movimiento 3 de "<<newName<<endl;
    cin >> newMove3;

    Pokemon newPokemon(newName, newHealth, newMove1Name, newMove1, newMove2Name, newMove2, newMove3Name, newMove3);

    ofstream archivo("pokemons.txt" , ios::app);

    if (archivo.is_open()) {

        newPokemon.guardar(archivo);
        archivo.close();
        cout << "Los datos del  Pokémon han sido guardados en el archivo pokemons.txt" << endl;

    }

    else {

        cout << "No se pudo abrir el archivo para guardar los datos" << endl;

    }
}

void cargarPokemones(vector<Pokemon>& pokemonVector) {
    ifstream archivo("pokemons.txt");
    if (archivo.is_open()) {
        string name, move_1_name, move_2_name, move_3_name;
        float health;
        int move_1, move_2, move_3;

        while (archivo >> name >> health >> move_1_name >> move_1 >> move_2_name >> move_2 >> move_3_name >> move_3) {
            Pokemon pokemon(name, health, move_1_name, move_1, move_2_name, move_2, move_3_name, move_3);
            pokemonVector.push_back(pokemon);
        }

        archivo.close();
        cout << "Se han cargado " << pokemonVector.size() << " Pokemons desde el archivo pokemons.txt" << endl<<endl;
    }
    else {
        cout << "No se pudo abrir el archivo para cargar los datos" << endl;
    }
}

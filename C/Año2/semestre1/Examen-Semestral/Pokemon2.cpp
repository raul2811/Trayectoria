#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <cstdlib>
#include <vector>
#include <ctime>
using namespace std;

class Pokemon {
private:
    static int contador;
    string name, move_1_name,move_1_tipe,move_2_name,move_2_tipe,move_3_name,move_3_tipe;
    float health;
    int move_1, move_2, move_3;

public:
    Pokemon(string, float, string, int, string, int, string, int, string,string,string);
    float gethealth() const;
    string getname() const;
    void movimientos(Pokemon&);
    void lista_pokemones() const;
    void guardar(ofstream&);
    void atacar(Pokemon&);
    int  danoataque(Pokemon&);
    void mostrarVida() const {
        cout << "Vida de " << name << ": ";
        int numBars = static_cast<int>(health / 10.0f);
        for (int i = 0; i < numBars; i++) {
            cout << "|";
        }
        cout << " " << health << endl;
    }
    void ataqueCPU(Pokemon& move);
    
};

// Inicialización de la variable contador fuera de la clase
int Pokemon::contador = 1;

float Pokemon::gethealth() const {
    return health;
}
string Pokemon::getname() const {
    return name;
}

Pokemon::Pokemon(string _name, float _health, string _move_1_name, int _move_1, string _move_2_name, int _move_2, string _move_3_name, int _move_3,string _move_1_tipe,string _move_2_tipe,string _move_3_tipe) {
    name = _name;
    health = _health;
    move_1_name = _move_1_name;
    move_1 = _move_1;
    move_2_name = _move_2_name;
    move_2 = _move_2;
    move_3_name = _move_3_name;
    move_3 = _move_3;
    move_1_tipe=_move_1_tipe;
    move_2_tipe=_move_2_tipe;
    move_3_tipe=_move_3_tipe;
}

void Pokemon::lista_pokemones() const {
    cout << contador << "-" << name << endl;
    contador++;
}
int Pokemon::danoataque(Pokemon& move){

if (move.movientotipo=="acero")
{
    if (tipe=="hada" || tipe=="hielo" || tipe=="roca" ){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="agua" || tipe=="electrico" || tipe=="fuego"){
    dano=dano/2;//Daño se divide / 2   
    }
    else{
        dano=dano;
    }
}
else if (move.movientotipo=="agua"){
    if (tipe=="fuego" || tipe=="tierra" || tipe=="roca" ){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="dragon" || tipe=="agua" || tipe=="planta"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="bicho"){
    if (tipe=="planta" || tipe=="psquico" || tipe=="siniestro"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="fantasma" || tipe=="fuego" || tipe=="hada" || tipe=="lucha" || tipe=="veneno" || tipe=="volador"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}

else if(move.movientotipo=="dragon"){
    if (tipe=="dragon"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="hada"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="electrico"){
    if (tipe=="agua" || tipe=="volador"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="dragon" || tipe=="electrico" || tipe=="planta"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="electrico"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="fantasma"){
    if (tipe=="fantasma" || tipe=="psquico"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="siniestro"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="normal"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="fuego"){
    if (tipe=="acero" || tipe=="bicho" || tipe=="hielo"|| tipe=="planta"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="agua" || tipe=="dragon" || tipe=="fuego" || tipe=="roca"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="hada"){
    if (tipe=="dragon" || tipe=="lucha" || tipe=="siniestro"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="veneno" || tipe=="fuego"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="hielo"){
    if (tipe=="dragon" || tipe=="planta" || tipe=="tierra"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="agua" || tipe=="fuego" || tipe=="hielo"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="lucha"){
    if (tipe=="acero" || tipe=="hielo" || tipe=="normal"|| tipe=="roca"|| tipe=="siniestro"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="bicho" || tipe=="hada" || tipe=="psiquico" || tipe=="veneno" || tipe=="volador"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="fantasma"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="normal"){
    if (tipe=="acero" || tipe=="roca" ){
        dano=dano/2;//Daño se divide / 2
    }
    else if(tipe=="fantasma"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="psiquico"){
    if (tipe=="lucha" || tipe=="veneno"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="psiquico"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="siniestro"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="planta"){
    if (tipe=="agua" || tipe=="roca" || tipe=="tierra"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="bicho" || tipe=="dragon" || tipe=="fuego" || tipe=="planta" || tipe=="veneno" || tipe=="volador"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="roca"){
    if (tipe=="bicho" || tipe=="fuego" || tipe=="hielo"|| tipe=="volador"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="tierra" || tipe=="planta" || tipe=="fuego" || tipe=="dragon" || tipe=="bicho"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="siniestro"){
    if (tipe=="fantasma" || tipe=="psquico" ){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="hada" || tipe=="lucha" || tipe=="fuego" || tipe=="hada" || tipe=="lucha" || tipe=="veneno" || tipe=="volador"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="tierra"){
    if (tipe=="acero" || tipe=="electrico" || tipe=="fuego"|| tipe=="roca"|| tipe=="veneno"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="bicho" ||  || tipe=="planta"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="volador"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="veneno"){
    if (tipe=="planta" || tipe=="hada"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="fantasma" || tipe=="roca" || tipe=="tierra" || tipe=="veneno"){
    dano=dano/2;//Daño se divide / 2    
    }
    else if(tipe=="acero"){
    dano=0;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
else if(move.movientotipo=="volador"){
    if (tipe=="bicho" || tipe=="hielo" || tipe=="planta"){
        dano=dano*2;//Daño se multiplica *2
    }
    else if(tipe=="acero" || tipe=="electrico" || tipe=="roca"){
    dano=dano/2;//Daño se divide / 2    
    }
    else{
        dano=dano;
    }
}
return dano;
}
void Pokemon::movimientos(Pokemon& move) {
    cout << endl << name << " ¡YO TE ELIJO!" << endl<<endl;
    move.mostrarVida();
    cout <<endl<< "Lista de movimientos" << endl<<endl
        << "Ataque\t\t\tDaño" << endl
        <<"1- "<< move_1_name << "\t" << move_1 << endl
        <<"2- "<< move_2_name << "\t\t" << move_2 << endl
        <<"3- "<< move_3_name << "\t\t" << move_3 << endl;
}

void Pokemon::guardar(ofstream& archivo) {
    archivo << name << " " << health << " " << move_1_name << " " << move_1 << " " << move_2_name << " " << move_2 << " " << move_3_name << " " << move_3 << endl;
}

void Pokemon::atacar(Pokemon& target) {
    int option;
    cout <<endl<< "Seleccione un movimiento: ";
    cin >> option;

    switch (option) {
    case 1:
        target.health -= move_1;
        cout <<endl<< name << " ha usado " << move_1_name << ". " << target.name << " ha perdido " << move_1 << " puntos de salud." << endl<< endl;
        target.mostrarVida();
        break;
    case 2:
        target.health -= move_2;
        cout <<endl<< name << " ha usado " << move_2_name << ". " << target.name << " ha perdido " << move_2 << " puntos de salud." << endl<< endl;
        target.mostrarVida();
        break;
    case 3:
        target.health -= move_3;
        cout <<endl<< name << " ha usado " << move_3_name << ". " << target.name << " ha perdido " << move_3 << " puntos de salud." << endl<< endl;
        target.mostrarVida();
        break;
    default:
        cout <<endl<< "Opción inválida." << endl;
        break;
    }
}

void Pokemon::ataqueCPU(Pokemon& move) {
    int cpuMove = rand() % 3 + 1;

    switch (cpuMove) {
    case 1:
        move.health -= move_1;
        cout <<endl<< "La CPU ha usado " << move_1_name << ". " << move.name << " ha perdido " << move_1 << " puntos de salud." << endl<< endl;
        move.mostrarVida();
        break;
    case 2:
        move.health -= move_2;
        cout <<endl<< "La CPU ha usado " << move_2_name << ". " << move.name << " ha perdido " << move_2 << " puntos de salud." << endl<< endl;
        move.mostrarVida();
        break;
    case 3:
        move.health -= move_3;
        cout <<endl<< "La CPU ha usado " << move_3_name << ". " << move.name << " ha perdido " << move_3 << " puntos de salud." << endl<< endl;
        move.mostrarVida();
        break;
    }
}

void jugarContraCPU(Pokemon& move, vector<Pokemon>& pokemonVector) {
    int cpuChoice= rand() % 18 + 1;
    int cpuIndex;
    cpuIndex = cpuChoice - 1;
    Pokemon& cpu = pokemonVector[cpuIndex];
    system("cls");
    cout << endl << "¡Comienza la batalla!" << endl<<endl;
    cout << endl << "Movimientos del jugador: "<< endl;
    move.movimientos(move); 
    while (true) {
        move.atacar(cpu);

        if (cpu.gethealth() <= 0) {
            system("cls");
            cout <<move.getname()<<endl<<endl;
            cout << "¡Has ganado la batalla!" << endl;
            system("pause");
            break;
        }

        cpu.ataqueCPU(move);

        if (move.gethealth() <= 0) {
            system("cls");
            cout <<move.getname()<<endl<<endl;
            cout << "¡Has perdido la batalla!" << endl;
            system("pause");
            break;
        }
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
        cout << "Se han cargado " << pokemonVector.size() << " Pokemons desde el archivo pokemons.txt" << endl << endl;
    }
    else {
        cout << "No se pudo abrir el archivo para cargar los datos" << endl;
    }
}

void registrar() {
    string newName, newMove1Name, newMove2Name, newMove3Name;
    float newHealth;
    int newMove1, newMove2, newMove3;
    cout << "Ingrese el nombre del Pokémon: ";
    cin >> newName;
    cout << "Ingrese la salud de " << newName << ": ";
    cin >> newHealth;
    cout << "Ingrese el nombre del movimiento 1 de " << newName << ": ";
    cin >> newMove1Name;
    cout << "Ingrese el daño del movimiento 1 de " << newName << ": ";
    cin >> newMove1;
    cout << "Ingrese el nombre del movimiento 2 de " << newName << ": ";
    cin >> newMove2Name;
    cout << "Ingrese el daño del movimiento 2 de " << newName << ": ";
    cin >> newMove2;
    cout << "Ingrese el nombre del movimiento 3 de " << newName << ": ";
    cin >> newMove3Name;
    cout << "Ingrese el daño del movimiento 3 de " << newName << ": ";
    cin >> newMove3;

    Pokemon newPokemon(newName, newHealth, newMove1Name, newMove1, newMove2Name, newMove2, newMove3Name, newMove3);

    ofstream archivo("pokemons.txt", ios::app);

    if (archivo.is_open()) {
        newPokemon.guardar(archivo);
        archivo.close();
        cout << "Los datos del Pokémon han sido guardados en el archivo pokemons.txt" << endl;
    }
    else {
        cout << "No se pudo abrir el archivo para guardar los datos" << endl;
    }
}

int menun() {
    int opcion;
    cout << "                                   ,',\n";
    cout << "    _.----.        ____         ,'  _\\   ___    ___     ____\n";
    cout << "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n";
    cout << "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n";
    cout << " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n";
    cout << "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n";
    cout << "    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n";
    cout << "     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n";
    cout << "      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n";
    cout << "       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n";
    cout << "        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n";
    cout << "                                `'                            '-._|\n"<<endl<<endl;
    cout << R"(
▄█░ ░░ 　 ░░░▒█ █░░█ █▀▀▀ █▀▀█ █▀▀█ 　 █▀▀ █▀▀█ █▀▀▄ ▀▀█▀▀ █▀▀█ █▀▀█ 　 ▒█▀▀█ ▒█▀▀█ ▒█░▒█ 
░█░ ▀▀ 　 ░▄░▒█ █░░█ █░▀█ █▄▄█ █▄▄▀ 　 █░░ █░░█ █░░█ ░░█░░ █▄▄▀ █▄▄█ 　 ▒█░░░ ▒█▄▄█ ▒█░▒█ 
▄█▄ ░░ 　 ▒█▄▄█ ░▀▀▀ ▀▀▀▀ ▀░░▀ ▀░▀▀ 　 ▀▀▀ ▀▀▀▀ ▀░░▀ ░░▀░░ ▀░▀▀ ▀░░▀ 　 ▒█▄▄█ ▒█░░░ ░▀▄▄▀)" << endl;
    cout << R"(
█▀█ ░░ 　 ▒█▀▀█ █▀▀ █▀▀▀ ░▀░ █▀▀ ▀▀█▀▀ █▀▀█ █▀▀█ █▀▀█ 　 █▀▀▄ █░░█ █▀▀ ▀█░█▀ █▀▀█ 
░▄▀ ▀▀ 　 ▒█▄▄▀ █▀▀ █░▀█ ▀█▀ ▀▀█ ░░█░░ █▄▄▀ █▄▄█ █▄▄▀ 　 █░░█ █░░█ █▀▀ ░█▄█░ █░░█ 
█▄▄ ░░ 　 ▒█░▒█ ▀▀▀ ▀▀▀▀ ▀▀▀ ▀▀▀ ░░▀░░ ▀░▀▀ ▀░░▀ ▀░▀▀ 　 ▀░░▀ ░▀▀▀ ▀▀▀ ░░▀░░ ▀▀▀▀  

            ▒█▀▀█ █▀▀█ █░█ █▀▀ █▀▄▀█ █▀▀█ █▀▀▄ 
            ▒█▄▄█ █░░█ █▀▄ █▀▀ █░▀░█ █░░█ █░░█ 
            ▒█░░░ ▀▀▀▀ ▀░▀ ▀▀▀ ▀░░░▀ ▀▀▀▀ ▀░░▀)" << endl;
    cout << R"(
█▀▀█ ░░ 　 ▒█▀▀▀█ █▀▀█ █░░ ░▀░ █▀▀█ 
░░▀▄ ▀▀ 　 ░▀▀▀▄▄ █▄▄█ █░░ ▀█▀ █▄▄▀ 
█▄▄█ ░░ 　 ▒█▄▄▄█ ▀░░▀ ▀▀▀ ▀▀▀ ▀░▀▀)" << endl;
    cin >> opcion;
    return opcion;
}

int main() {
    int opc;
    vector<Pokemon> pokemonVector;
    cargarPokemones(pokemonVector);
    system("pause");
    do {
        system("cls");
        opc = menun();

        switch (opc) {
        case 1:
            system("cls");
            cout<<"\tLista De Pokemones\t"<<endl<<endl;
            for (const Pokemon& pokemon : pokemonVector) {
                pokemon.lista_pokemones();
            }

            int moveChoice;
            int moveIndex;

            cout << "Seleccione su Pokémon (Ingrese el número correspondiente): ";
            cin >> moveChoice;
            moveIndex = moveChoice - 1;

            if (moveIndex >= 0 && moveIndex < pokemonVector.size()) {
                Pokemon& move = pokemonVector[moveIndex];
                jugarContraCPU(move, pokemonVector);
                system("cls");
            } else {
                cout << "¡Opción inválida! Seleccione un Pokémon válido." << endl;
            }
            
            break;

        case 2:
            system("cls");
            registrar();
            system("cls");
            break;

        case 3:
          
            system("cls");
            break;

        default:
            cout << "Opción inválida. Intente nuevamente" << endl;
            break;
        }
    } while (opc != 3);

    return 0;
}
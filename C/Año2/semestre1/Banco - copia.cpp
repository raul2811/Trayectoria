#include <iostream>  //libreria principal 
#include <random>    //libreria para generar numeros random
#include <stdlib.h>   //libreria para la limpieza de pantalla
#include <fstream>    //libreria para el archivo 

using namespace std;

struct banco{
    string nombre;
    float apertura, depo, retiro;
    int cuenta; 
}; typedef struct banco datos;

int buscar(datos[], int);

int main (){ // FUNCION PRINCIPA
	
	datos banco[5];
	int c=-1, menu;     
	
	/***** DECLARACION de las funciones**********/
	
    int menun ();
    int ingreso(datos[], int);
    void deposito (datos[], int);
    void retiror (datos[], int);
    void grabar (datos[], int);
    int leer (datos[], int);
    
    
    c = leer(banco, c);
    
    //inicializacion de la variable para el do while y la variable para almacenar el retorno del menu 
    
    do {
    	
        system("pause");
        system ("cls");
		
		menu = menun(); //LLAMADO funcion menun(), con retorno int
		
		switch (menu){    
          	case 1:
         		c = ingreso(banco, c);  /****** LLAMADOS de las funciones ********/
          		break;        
            case 2:
           		deposito(banco, c);     //
          		break;
			case 3:
           		retiror (banco, c);
          		break;
			case 4:          
          	    grabar (banco, c) ;
          		break;
			default:
				cout<<"Opcion invalida, Intente nuevamente"<<endl;
				break;       
            }
        } while (menu!=4);
    return 0;
}


/********* DEFINICION de las funciones  **********/

int menun(){
	int opcion;
    
	cout<<" BIENVENIDO... \n>>>> Banco  <<<<"<<endl;
    cout<<"1- ingresa datos, clientes y cuentas"<<endl;
    cout<<"2- realiza un deposito de la cuenta"<<endl;
    cout<<"3- espectua un retiro de la cuenta"<<endl;
    cout<<"4- Salir y grabar el archivo"<<endl;
    
	cout<<"opcion:  ";cin>>opcion;//2        
    
	return opcion; //Retorno int de la funcion
}


int ingreso(datos banco[5], int c){
    int r;
    float inicio;
    
    do {
    	c++;
    	random_device rd;
    mt19937 gen (rd()); //generador del numero aleatorio 
    
	uniform_int_distribution<> dis(1,9999); //definir rango
	
	cout<<"\nIngrese el nombre del cliente:  ";
    cin>>banco[c].nombre;
    
    /***genreral numeri aleatorio*/
    int random_num = dis (gen);
    
	/***imprimir */
    cout <<"El numero de cuenta es:  "<<random_num<<endl;
    cout<<"\n";
	banco[c].cuenta = random_num;  //gel numero se guarda en una variable para luego utilizarse en la confirmacion 
    
    
		cout<<"ingrese el saldo de apertura (debe ser mayor a 10$)" <<endl;
		cin>>inicio;
		/*condision de la apertura de la cuenta*/
		if (inicio <=10){
        cout<<"saldo de apertura bajo"<<endl;
           }else{
           	banco[c].apertura=inicio;
           	cout<<"Cuenta creada... \nCuenta:  "<<banco[c].apertura<<endl;
		   }
		   cout<<"Desea abrir una nueva cuenta (1. Si)(0. no):  "<<endl; cin>>r;
	}while (r!=0);
	return c;
}


/************funsion deposito************/

void deposito (datos banco[5], int c){
	int busca=-1;
	busca = buscar(banco, c);
	if (busca==-1){
		cout<<"cuenta no encontrada "<<endl;
	}else{
		 cout<<"\ningrese el deposito :  ";
                cin>>banco[busca].depo;
                banco[busca].apertura+=banco[busca].depo;
                
				cout<<banco[busca].apertura<<endl;
			}
        }
        
           /**********funcion retiro**********/
            
void retiror (datos banco[5], int c){
    int busca=-1; //variabke para la verificacion y la inicializacion de una variable para uso de do while
          /******verificacion del numero de cuenta*************/
          busca= buscar(banco, c);
        
        if (busca==-1){
                   cout<<"numero de cuenta incorrecto"<<endl;
        } else {
        	/****************retiro de la cuenta **********/
              cout<<"\ningrese retiro:  ";
                cin>>banco[busca].retiro; 
             /************condicion para el retiro *************/   
           if (banco[busca].retiro > banco[busca].apertura) {
                      cout<<"saldo insuficiente"<<endl;
           }else {
           	/*******proceso *********/
    banco[busca].apertura-=banco[busca].retiro ;
	cout<<"Retiro culminado... \n Cuenta: "<<banco[busca].apertura<<endl; 
		   } 
		}            
}
           
/**************funcion para guardar en un archivo ***********/


void grabar (datos banco[5], int c){
        ofstream archi;   //declaracion del archivo 
        
           archi.open("banco.txt");      //apertura del archivo 
        
        if (archi.fail()){
                   cout<<"No se pudo abrir el archivo correctamente"<<endl;
        } else{
        	cout<<"el archivo se creo correctamente\nSalida... "<<endl;
        	
        	for(int g=0; g<=c; g++){
        		archi<<banco[g].cuenta<<" "<<banco[g].nombre<<" "<<banco[g].apertura<<endl;     //datos que se guardaran el archivo
			}
        	
		}
        archi.close();               //cerramos el archivo 
}

int leer (datos banco[5], int c){
	ifstream leer;
	leer.open("banco.txt");
	if (leer.fail()){
		cout<<"NO SE ENCONTRO... "<<endl;
		return (c);
	}else{
		c++;
		cout<<"Archivo encontrado... "<<endl;
		while(leer.eof()){
			leer>>banco[c].cuenta>>banco[c].nombre>>banco[c].apertura;
			c++;
		}
		return (c-1);
	}
	leer.close();
	return c;
}

int buscar(datos banco[5], int c){
	int busca=-1, verificar; 
	cout<<"Ingrese el numero de cuenta:  "<<endl; cin>>verificar;
	for(int g=0; g<=c; g++){
		if (verificar==banco[g].cuenta){
			busca=g;
			
			return busca;
		}
	} return busca;
}
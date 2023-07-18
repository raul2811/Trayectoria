#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <cstdlib>
#include <vector>
#include <ctime>

using namespace std;
int main ()
{

if (player.movientotipo=="acero")
{
    if (cpu.tipe=="hada" || cpu.tipe=="hielo" || cpu.tipe=="roca" ){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="agua" || cpu.tipe=="electrico" || cpu.tipe=="fuego"){
    player.dano=player.dano/2;//Daño se divide / 2   
    }
    else{
        player.dano=player.dano;
    }
}
else if (player.movientotipo=="agua"){
    if (cpu.tipe=="fuego" || cpu.tipe=="tierra" || cpu.tipe=="roca" ){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="dragon" || cpu.tipe=="agua" || cpu.tipe=="planta"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="bicho"){
    if (cpu.tipe=="planta" || cpu.tipe=="psquico" || cpu.tipe=="siniestro"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="fantasma" || cpu.tipe=="fuego" || cpu.tipe=="hada" || cpu.tipe=="lucha" || cpu.tipe=="veneno" || cpu.tipe=="volador"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}

else if(player.movientotipo=="dragon"){
    if (cpu.tipe=="dragon"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="hada"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="electrico"){
    if (cpu.tipe=="agua" || cpu.tipe=="volador"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="dragon" || cpu.tipe=="electrico" || cpu.tipe=="planta"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="electrico"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="fantasma"){
    if (cpu.tipe=="fantasma" || cpu.tipe=="psquico"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="siniestro"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="normal"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="fuego"){
    if (cpu.tipe=="acero" || cpu.tipe=="bicho" || cpu.tipe=="hielo"|| cpu.tipe=="planta"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="agua" || cpu.tipe=="dragon" || cpu.tipe=="fuego" || cpu.tipe=="roca"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="hada"){
    if (cpu.tipe=="dragon" || cpu.tipe=="lucha" || cpu.tipe=="siniestro"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="veneno" || cpu.tipe=="fuego"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="hielo"){
    if (cpu.tipe=="dragon" || cpu.tipe=="planta" || cpu.tipe=="tierra"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="agua" || cpu.tipe=="fuego" || cpu.tipe=="hielo"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="lucha"){
    if (cpu.tipe=="acero" || cpu.tipe=="hielo" || cpu.tipe=="normal"|| cpu.tipe=="roca"|| cpu.tipe=="siniestro"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="bicho" || cpu.tipe=="hada" || cpu.tipe=="psiquico" || cpu.tipe=="veneno" || cpu.tipe=="volador"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="fantasma"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="normal"){
    if (cpu.tipe=="acero" || cpu.tipe=="roca" ){
        player.dano=player.dano/2;//Daño se divide / 2
    }
    else if(cpu.tipe=="fantasma"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="psiquico"){
    if (cpu.tipe=="lucha" || cpu.tipe=="veneno"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="psiquico"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="siniestro"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="planta"){
    if (cpu.tipe=="agua" || cpu.tipe=="roca" || cpu.tipe=="tierra"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="bicho" || cpu.tipe=="dragon" || cpu.tipe=="fuego" || cpu.tipe=="planta" || cpu.tipe=="veneno" || cpu.tipe=="volador"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="roca"){
    if (cpu.tipe=="bicho" || cpu.tipe=="fuego" || cpu.tipe=="hielo"|| cpu.tipe=="volador"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="tierra" || cpu.tipe=="planta" || cpu.tipe=="fuego" || cpu.tipe=="dragon" || cpu.tipe=="bicho"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="siniestro"){
    if (cpu.tipe=="fantasma" || cpu.tipe=="psquico" ){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="hada" || cpu.tipe=="lucha" || cpu.tipe=="fuego" || cpu.tipe=="hada" || cpu.tipe=="lucha" || cpu.tipe=="veneno" || cpu.tipe=="volador"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="tierra"){
    if (cpu.tipe=="acero" || cpu.tipe=="electrico" || cpu.tipe=="fuego"|| cpu.tipe=="roca"|| cpu.tipe=="veneno"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="bicho" ||  || cpu.tipe=="planta"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="volador"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="veneno"){
    if (cpu.tipe=="planta" || cpu.tipe=="hada"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="fantasma" || cpu.tipe=="roca" || cpu.tipe=="tierra" || cpu.tipe=="veneno"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else if(cpu.tipe=="acero"){
    player.dano=0;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
else if(player.movientotipo=="volador"){
    if (cpu.tipe=="bicho" || cpu.tipe=="hielo" || cpu.tipe=="planta"){
        player.dano=player.dano*2;//Daño se multiplica *2
    }
    else if(cpu.tipe=="acero" || cpu.tipe=="electrico" || cpu.tipe=="roca"){
    player.dano=player.dano/2;//Daño se divide / 2    
    }
    else{
        player.dano=player.dano;
    }
}
}
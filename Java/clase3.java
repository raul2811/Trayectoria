/*Escribir en java un programa con un menu que se debe llamar desde el metodo main con 2 opciones
 que se deben crear como un metodo, opcion#1 marca y cantidad de litros de gasolina que va a repostar 
 opcion#2 calcula la cantidad de kilometros que el vehiculo recorrera, considerando que cada 5 litros recorre 30 kilometros
 imprimir dentro de este mismo metodo cuantos kilometros va a a recorrer y a que ciudad se aproximara(arraijan cabecera 25km
 chorrera 50Km ,interior de el pais +50Km)
 */


//import java.util.Scanner;
import javax.swing.JOptionPane;

public class clase3 {
    
    public static void main(String[] args) {

        
    int opc=0;
    opc=Menu(opc);

    do {
        switch (opc) {
            case 1:         
                JOptionPane.showMessageDialog(null, "usted escogio la opcion ingrese los datos");
                opc=Integer.parseInt(JOptionPane.showInputDialog(null, "Desea volver al menu? (4)"));
            case 2:
                JOptionPane.showMessageDialog(null, "usted escogio Calcular Datos");
                opc=Integer.parseInt(JOptionPane.showInputDialog(null, "Desea volver al menu? (4)"));    
            case 3:
                JOptionPane.showMessageDialog(null, "usted escogio la opcion Salir");      
            default:
                break;
        }
    } while (opc!=3);
    
}
private static int Menu(int opc) {
    opc=Integer.parseInt(JOptionPane.showInputDialog(null, "Opcion#1 Ingrese los datos\nOpcion#2 Calcular los datos"));
    return opc;

    }
}
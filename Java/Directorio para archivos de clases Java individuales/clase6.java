//Escribir un programa con un metodo leerdatos va a solicitar el nombre de un pais, capital, y poblacion.
//El segundo metodo graba archivo va a tomar los datos ingresados y y los va a grabar en un archivo de datos.
//el tercer metodolee desde archivo va a buscar e imprimir por pantalla.

import javax.swing.JOptionPane;

public class clase6 {
    
        static String nompais,nomcapital;
        static int poblacion;

    public static void main (String [] args)    
    {
        
        SolicitarDatos();
    }
    private static void SolicitarDatos () {

        nompais = JOptionPane.showInputDialog("Ingrese nombre del pais: ");
        nomcapital = JOptionPane.showInputDialog("Ingrese nombre del pais: ");
        poblacion = Integer.parseInt("Ingrese la poblacion");
    }

}

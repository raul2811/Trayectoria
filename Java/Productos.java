import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Productos {
    static String nombreproducto;
    static float precio;
    static int codigo;
    // Se declara aqui por seran usadas por el metodo 
    public static void main(String[] arg) throws IOException{
        Capturados ();
    }
    private static void  Capturados() throws IOException{
        throw new UnsupportedOperationException("Not Supported yet.");
        nombreproducto = JOptionPane.showInputDialog("Ingresa el nombre del producto");
        
    }        
}

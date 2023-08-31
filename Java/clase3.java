/*Escribir en java un programa con un menu que se debe llamar desde el metodo main con 2 opciones
 que se deben crear como un metodo, opcion#1 marca y cantidad de litros de gasolina que va a repostar 
 opcion#2 calcula la cantidad de kilometros que el vehiculo recorrera, considerando que cada 5 litros recorre 30 kilometros
 imprimir dentro de este mismo metodo cuantos kilometros va a a recorrer y a que ciudad se aproximara(arraijan cabecera 25km
 chorrera 50Km ,interior de el pais +50Km)
 */
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class clase3 {
    
    public static void main(String[] args) {
        
        int opc = 0;
    
        // Bucle do-while para mostrar el menú y procesar opciones hasta que el usuario decida salir
        do {
            opc = Menu(opc); // Llama a la función Menu y guarda la opción elegida
            switch (opc) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Ingrese los datos");
                    Repostar(); // Llama a la función Repostar para ingresar y mostrar los datos
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Calcular Datos");
                    Calcular_Datos();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Salir");
                    break;
                default:
                    break;
            }
        } while (opc != 3); // La condición de salida del bucle
    
    }
    
    
    // Función para mostrar el menú y obtener la opción elegida por el usuario
    private static int Menu(int opc) {
        opc = Integer.parseInt(JOptionPane.showInputDialog(null, "MENU\n#1 Ingrese los datos\n#2 Calcular los datos\n#3 Salir"));
        return opc;
    }
    
    // Función para ingresar y mostrar los datos de repostaje
    private static void Repostar() {
        float gasolina_galones;
        String name = JOptionPane.showInputDialog("Ingrese La marca del vehiculo:");
        JOptionPane.showMessageDialog(null, "La marca del vehiculo es: " + name);
        float gasolina_litros = Float.parseFloat(JOptionPane.showInputDialog("Ingrese La cantidad de litros de combustible que desea repostar"));
        
        gasolina_galones = (float) (gasolina_litros / 3.785);
        
        int cantidadDecimales = 2; // Número de decimales deseado
        
        // Formateo de gasolina_galones con la cantidad de decimales deseada
        DecimalFormat df = new DecimalFormat("#." + "0".repeat(cantidadDecimales));
        String gasolina_galones_formateado = df.format(gasolina_galones);
        
        // Mostrar los datos ingresados y calculados
        JOptionPane.showMessageDialog(null, "La Cantidad de Combustible que va a repostar es de: " + "\n" + gasolina_litros + " (L)" + "\n" + gasolina_galones_formateado + " (GAL)");
    }
    private static void Calcular_Datos() {
        gasolina_galones;
    }

}

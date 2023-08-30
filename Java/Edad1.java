import java.util.Scanner;
import javax.swing.JOptionPane;

public class Edad1 {
    static String nombre, mensajeedad;
    static int a_nac,edad,a_act; 

public static void main(String[] args){
    Solicitadotos();
    Calcularedad();
    Verificaredad();
    Imprimir();
    Imprimir_grafico();
    
}

private static void Imprimir_grafico() {
    JOptionPane.showMessageDialog(null, "Hola " + nombre + ", tienes " + edad + " años. " + mensajeedad);
}

private static void Calcularedad() {
    edad= a_act-a_nac;
}

private static void Verificaredad() {
     if (edad <= 12) {
            mensajeedad = "Eres un niño";
        } else if (edad <= 18) {
            mensajeedad = "Eres un adolescente";
        } else if (edad <= 30) {
            mensajeedad = "Eres un joven adulto";
        } else if (edad <= 60) {
            mensajeedad = "Eres un adulto";
        } else {
            mensajeedad = "Eres un adulto mayor";
        }
}

private static void Imprimir() {
    System.out.println(nombre); 
    System.out.println(a_nac); 
    System.out.println(a_act); 
    System.out.println(edad); 
    System.out.println("Hola " + nombre + ", tienes " + edad + " años. " + mensajeedad);
}

private static void Solicitadotos() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese su nombre");
    nombre= scanner.nextLine();

    System.out.print("Ingrese su Año de nacimiento");
    a_nac= scanner.nextInt();
    
    System.out.print("Ingrese El año actual");
    a_act=scanner.nextInt();
}
}


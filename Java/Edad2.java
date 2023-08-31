import javax.swing.JOptionPane;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Edad2 {

    public static void main(String[] args) {
        // Llamamos al método SolicitarDatos() y manejamos InputMismatchException si ocurre
        try {
            DatosUsuario datos = SolicitarDatos();
            CalcularEdad(datos);
            VerificarEdad(datos);
            Imprimir(datos);
            MostrarMensajeGrafico(datos);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
        }
    }

    // Clase para almacenar los datos del usuario
    static class DatosUsuario {
        String nombre, mensajeEdad;
        int anioNac, edad, anioAct;
    }

    // Método para solicitar y validar los datos del usuario
    private static DatosUsuario SolicitarDatos() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        DatosUsuario datos = new DatosUsuario();
    
        boolean inputValido = false;
    
        // Ciclo para obtener datos válidos
        while (!inputValido) {
            try {
                System.out.print("Ingrese su nombre: ");
                datos.nombre = scanner.nextLine();
    
                // Verificamos si el nombre contiene números
                if (ContieneNumeros(datos.nombre)) {
                    System.out.println("El nombre no puede contener números. Intente nuevamente.");
                    continue;
                }
    
                System.out.print("Ingrese su Año de nacimiento: ");
                datos.anioNac = scanner.nextInt();
    
                System.out.print("Ingrese El año actual: ");
                datos.anioAct = scanner.nextInt();
    
                // Consumir la nueva línea después de leer los valores numéricos
                scanner.nextLine();
    
                if (datos.anioAct < datos.anioNac) {
                    System.out.println("El año actual no puede ser menor que el año de nacimiento. Intente nuevamente.");
                    continue;
                }
    
                inputValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.nextLine(); // Consumir la nueva línea para evitar bucle infinito en caso de excepción
            }
        }
    
        scanner.close(); // Cerramos el Scanner
        return datos;
    }

    // Método para verificar si un String contiene números
    private static boolean ContieneNumeros(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    // Método para calcular la edad
    private static void CalcularEdad(DatosUsuario datos) {
        datos.edad = datos.anioAct - datos.anioNac;
    }

    // Método para verificar la edad y asignar el mensaje correspondiente
    private static void VerificarEdad(DatosUsuario datos) {
        if (datos.edad <= 12) {
            datos.mensajeEdad = "Eres un niño";
        } else if (datos.edad <= 18) {
            datos.mensajeEdad = "Eres un adolescente";
        } else if (datos.edad <= 30) {
            datos.mensajeEdad = "Eres un joven adulto";
        } else if (datos.edad <= 60) {
            datos.mensajeEdad = "Eres un adulto";
        } else {
            datos.mensajeEdad = "Eres un adulto mayor";
        }
    }

    // Método para imprimir información en la consola
    private static void Imprimir(DatosUsuario datos) {
        System.out.println(datos.nombre);
        System.out.println(datos.anioNac);
        System.out.println(datos.anioAct);
        System.out.println(datos.edad);
        System.out.println("Hola " + datos.nombre + ", tienes " + datos.edad + " años. " + datos.mensajeEdad);
    }

    // Método para mostrar un cuadro de diálogo con el mensaje
    private static void MostrarMensajeGrafico(DatosUsuario datos) {
        String mensaje = "Hola " + datos.nombre + ", tienes " + datos.edad + " años. " + datos.mensajeEdad;
        JOptionPane.showMessageDialog(null, mensaje); // Mostrar mensaje usando JOptionPane
        JOptionPane.showMessageDialog(null, "Presiona OK para salir.");
    }
}

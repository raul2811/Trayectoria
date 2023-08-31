import java.util.Scanner;
/* 
es una clase que se utiliza para leer la entrada del usuario desde 
la consola o desde otros flujos de entrada. En tu programa, 
se utiliza para recopilar los datos ingresados por el usuario
*/
import javax.swing.JOptionPane;
/*
es una clase que proporciona una interfaz gráfica 
para mostrar cuadros de diálogo en aplicaciones de escritorio. 
En tu programa, se usa para mostrar mensajes y recopilar datos 
en cuadros de diálogo. 
*/
import java.util.InputMismatchException;
/*
es una excepción que se lanza cuando se produce un error en la 
lectura de datos utilizando un Scanner. 
En tu programa, se utiliza para capturar 
excepciones cuando el usuario ingresa datos incorrectos.
*/

public class Edad1 {//Recordar siempre iniciar la clase principal con el mismo nombre con el cual se guarda el archivo.
    
    // Declaración de variables estáticas
    static String nombre, mensajeedad;
    static int a_nac, edad, a_act;

    public static void main(String[] args) {
        try {
            // Llamamos al método Solicitadotos() y manejamos InputMismatchException si ocurre
            Solicitadotos();

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
        }

        // Llamamos a los demás métodos para calcular la edad, verificarla y mostrar resultados
        Calcularedad();
        containsNumbers(nombre);
        Verificaredad();
        Imprimir();
        Imprimir_grafico();
    }

    // Método para mostrar un cuadro de diálogo con el mensaje
    private static void Imprimir_grafico() {
        String mensaje = "Hola " + nombre + ", tienes " + edad + " años. " + mensajeedad;
        JOptionPane.showMessageDialog(null, mensaje);
        JOptionPane.showMessageDialog(null, "Presiona OK para salir.");
    }

    // Método para calcular la edad
    private static void Calcularedad() {
        edad = a_act - a_nac;
    }

    // Método para verificar la edad y asignar el mensaje correspondiente
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

    // Método para imprimir información en la consola
    private static void Imprimir() {
        System.out.println(nombre);
        System.out.println(a_nac);
        System.out.println(a_act);
        System.out.println(edad);
        System.out.println("Hola " + nombre + ", tienes " + edad + " años. " + mensajeedad);
    }

    // Método para solicitar datos con manejo de InputMismatchException
    private static void Solicitadotos() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
    
        boolean inputValido = false;
    
        // Ciclo para obtener datos válidos
        while (!inputValido) {
            try {
                System.out.print("Ingrese su nombre: ");
                nombre = scanner.nextLine();
    
                // Verificamos si el nombre contiene números
                if (containsNumbers(nombre)) {
                    System.out.println("El nombre no puede contener números. Intente nuevamente.");
                    continue; // Regresamos al inicio del ciclo para solicitar el nombre nuevamente
                }
    
                System.out.print("Ingrese su Año de nacimiento: ");
                a_nac = scanner.nextInt();
    
                System.out.print("Ingrese El año actual: ");
                a_act = scanner.nextInt();
    
                // Consumir la nueva línea después de leer los valores numéricos
                scanner.nextLine();
    
                if (a_act < a_nac) {
                    System.out.println("El año actual no puede ser menor que el año de nacimiento. Intente nuevamente.");
                    continue; // Regresamos al inicio del ciclo para solicitar los datos nuevamente
                }
    
                inputValido = true; // Si no hay excepciones y las verificaciones pasan, la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.nextLine(); // Consumir la nueva línea para evitar un bucle infinito
            }
        }
    
        scanner.close(); // Cerramos el Scanner
    }

    // Método para verificar si un String contiene números
    private static boolean containsNumbers(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
}
}

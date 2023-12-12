import javax.swing.JOptionPane; // Importa la clase JOptionPane para mostrar diálogos de usuario
import java.nio.file.*; // Importa clases relacionadas con operaciones de archivos
import java.util.*; // Importa clases relacionadas con estructuras de datos como ArrayList

public class simulator_banco {

    static String username = System.getenv("USERNAME"); // Obtiene el nombre de usuario actual
    static int contador = 0; // Variable para contar clientes (no se utiliza en este código)
    static ArrayList<String> nombre = new ArrayList<>(); // ArrayList para almacenar nombres de clientes
    static ArrayList<String> cedula = new ArrayList<>(); // ArrayList para almacenar cédulas de clientes
    static ArrayList<Integer> numerocuenta = new ArrayList<>(); // ArrayList para almacenar números de cuenta de clientes
    static ArrayList<Float> saldo = new ArrayList<>(); // ArrayList para almacenar saldos de cuentas de clientes

    public static void main(String[] args) {
        LeerArchivo(); // Llama a la función para leer datos desde un archivo
        int opc = 0; // Variable para almacenar la opción del menú seleccionada por el usuario
        // Bucle do-while para mostrar el menú y procesar opciones hasta que el usuario decida salir
        do {
            opc = Menu(opc); // Llama a la función para mostrar el menú y obtiene la opción elegida
            switch (opc) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Registrar un cliente");
                    Registrar(); // Llama a la función para registrar un nuevo cliente
                    GrabarArchivo();
                    LeerArchivo();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Deposito");
                    Deposito(); // Llama a la función para realizar un depósito
                    GrabarArchivo();
                    LeerArchivo();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Retiro");
                    Retiro(); // Llama a la función para realizar un retiro
                    GrabarArchivo();
                    LeerArchivo();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Usted escogió la opción Consulta");
                    Consulta(); // Llama a la función para consultar información de una cuenta
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Salir y grabar");
                    GrabarArchivo(); // Llama a la función para guardar datos en un archivo y salir
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Esta opcion no esta dispobible en el menu");
                    break;
            }
        } while (opc != 5); // La condición de salida del bucle
    }

    // Función para leer el archivo
    private static void LeerArchivo() {
        try {
            // Ruta del archivo
            String rutaArchivo = "C:\\Users\\" + username + "\\Documents\\Banco\\banco.txt";
            Path path = Paths.get(rutaArchivo);

            // Verificar si el archivo existe
            if (!Files.exists(path)) {
                JOptionPane.showMessageDialog(null, "El archivo banco.txt no existe en la ruta especificada.");
                return;
            }

            // Leer todas las líneas del archivo
            List<String> lines = Files.readAllLines(path);

            // Verificar si el archivo está vacío
            if (lines.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El archivo banco.txt está vacío.");
                return;
            }

            // Limpiar las ArrayList antes de cargar los datos
            nombre.clear();
            cedula.clear();
            numerocuenta.clear();
            saldo.clear();

            // Procesar cada línea del archivo
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                StringTokenizer palabra = new StringTokenizer(line);

                if (palabra.countTokens() != 4) {
                    JOptionPane.showMessageDialog(null, "Error en el formato del archivo en la línea " + (i + 1));
                    return;
                }

                numerocuenta.add(Integer.parseInt(palabra.nextToken())); // Agrega el número de cuenta
                nombre.add(palabra.nextToken()); // Agrega el nombre
                cedula.add(palabra.nextToken()); // Agrega la cédula
                saldo.add(Float.parseFloat(palabra.nextToken())); // Agrega el saldo
            }

            JOptionPane.showMessageDialog(null, "Se cargaron los datos desde el archivo banco.txt \nrecuerde que al registrar un nuevo cleinte el nombre no puede tener espacios");

        } catch (NoSuchFileException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo o la carpeta no existen.");
        } catch (AccessDeniedException e) {
            JOptionPane.showMessageDialog(null, "Error: Permiso denegado para acceder al archivo o la carpeta.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al leer un valor numérico en el archivo.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la operación: " + e.getMessage());
        }
    }

    // Función para mostrar el menú y obtener la opción elegida por el usuario
    private static int Menu(int opc) {
        opc = Integer.parseInt(JOptionPane.showInputDialog(null, " MENU\n#1 Registrar un cliente\n#2 Deposito\n#3 Retiro\n#4 Consulta \n#5 Salir y Grabar"));
        return opc;
    }

    // Función para registrar un cliente en las ArrayList
    private static void Registrar() {
        boolean deseaRegistrarOtraCuenta = true;

        while (deseaRegistrarOtraCuenta && numerocuenta.size() < 20) {
            String nombreCliente = JOptionPane.showInputDialog(null, "Ingrese su nombre:");
            String cedulaCliente = JOptionPane.showInputDialog(null, "Ingrese su cedula:");
            float saldoCliente = valorInvalido();

            if (saldoCliente < 10) {
                JOptionPane.showMessageDialog(null, "ERROR\nLa cuenta no se creó. El monto mínimo es de 10 dólares.");
            } else {
                int numeroCuenta = generarNumeroCuenta();
                JOptionPane.showMessageDialog(null, "CUENTA CREADA\n---------------\n\nLa cuenta se ha creado exitosamente.\nNUMERO DE CUENTA: " + numeroCuenta + "\n\n¡ADVERTENCIA: NO OLVIDE SU NUMERO DE CUENTA, PORQUE SE USARA COMO CONTRASEÑA PARA ACCEDER A OTRAS OPCIONES DEL MENU!");

                // Agregar los datos a las ArrayList
                numerocuenta.add(numeroCuenta);
                nombre.add(nombreCliente);
                cedula.add(cedulaCliente);
                saldo.add(saldoCliente);

                // Incrementar el contador (no se utiliza en este código)
                contador++;

                // Preguntar si desea registrar otra cuenta
                int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea registrar otra cuenta?",
                    "Registrar Otra Cuenta",
                    JOptionPane.YES_NO_OPTION
                );

                deseaRegistrarOtraCuenta = (respuesta == JOptionPane.YES_OPTION);
            }

            if (numerocuenta.size() == 0) {
                JOptionPane.showMessageDialog(null, "Debe registrar al menos una cuenta.");
            } 
        }
    }

    // Función para generar un número de cuenta aleatorio
    private static int generarNumeroCuenta() {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

    // Función para validar el saldo ingresado
    private static float valorInvalido() {
        float saldo = 0;
        boolean esValido = false;

        do {
            try {
                saldo = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el saldo:"));
                esValido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico válido.");
            }
        } while (!esValido);

        return saldo;
    }

    // Función para realizar un depósito en la cuenta de un cliente específico
    private static void Deposito() {
        int numeroCuentaBuscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta en la que desea realizar el depósito:"));
        int indice = buscarCuenta(numeroCuentaBuscar);

        if (indice != -1) {
            float montoDeposito = valorInvalido();

            if (montoDeposito > 0) {
                float saldoActual = saldo.get(indice);
                saldoActual += montoDeposito;
                saldo.set(indice, saldoActual);

                JOptionPane.showMessageDialog(null, "Depósito realizado con éxito.\nNuevo saldo: $" + saldoActual);
            } else {
                JOptionPane.showMessageDialog(null, "El monto del depósito debe ser mayor que cero.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cuenta no se encontró.");
        }
    }

    // Función para consultar una cuenta por número de cuenta
    private static void Consulta() {
        int numeroCuentaBuscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta a consultar:"));
        int indice = buscarCuenta(numeroCuentaBuscar);

        if (indice != -1) {
            JOptionPane.showMessageDialog(null, "Información de la cuenta:\n" +
                    "Nombre: " + nombre.get(indice) + "\n" +
                    "Cédula: " + cedula.get(indice) + "\n" +
                    "Número de Cuenta: " + numerocuenta.get(indice) + "\n" +
                    "Saldo: $" + saldo.get(indice));
        } else {
            JOptionPane.showMessageDialog(null, "La cuenta no se encontró.");
        }
    }

    // Función para realizar un retiro en la cuenta de un cliente específico
    private static void Retiro() {
        int numeroCuentaBuscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cuenta de la que desea realizar el retiro:"));
        int indice = buscarCuenta(numeroCuentaBuscar);

        if (indice != -1) {
            float saldoActual = saldo.get(indice);

            if (saldoActual > 0) {
                JOptionPane.showMessageDialog(null, "Saldo actual: $" + saldoActual);

                float montoRetiro = valorInvalido();

                if (montoRetiro > 0 && montoRetiro <= saldoActual) {
                    saldoActual -= montoRetiro;
                    saldo.set(indice, saldoActual);

                    JOptionPane.showMessageDialog(null, "Retiro realizado con éxito.\nNuevo saldo: $" + saldoActual);
                } else {
                    JOptionPane.showMessageDialog(null, "El monto ingresado es inválido o excede el saldo disponible.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No puede realizar retiros en una cuenta con saldo cero.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cuenta no se encontró.");
        }
    }

    // Función para buscar una cuenta por número de cuenta
    private static int buscarCuenta(int numeroCuentaBuscar) {
        for (int i = 0; i < numerocuenta.size(); i++) {
            if (numerocuenta.get(i) == numeroCuentaBuscar) {
                return i; // Se encontró la cuenta, devuelve el índice
            }
        }
        return -1; // No se encontró la cuenta
    }

    // Función para grabar el archivo
    private static void GrabarArchivo() {
        try {
            // Ruta del archivo
            String rutaArchivo = "C:\\Users\\" + username + "\\Documents\\Banco\\banco.txt";
            Path path = Paths.get(rutaArchivo);

            // Verificar si el archivo existe
            if (!Files.exists(path)) {
                // El archivo no existe, preguntar al usuario si desea crearlo
                int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "El archivo banco.txt no existe en la ruta especificada.\n¿Desea crearlo?",
                        "Crear Archivo",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    // Crear la carpeta y el archivo
                    Files.createDirectories(path.getParent());
                    Files.createFile(path);
                } else {
                    return; // El usuario no desea crear el archivo, salir de la función
                }
            }

            // Continuar con la escritura
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < numerocuenta.size(); i++) {
                strBuilder.append(numerocuenta.get(i)).append(" ").append(nombre.get(i)).append(" ").append(cedula.get(i)).append(" ").append(saldo.get(i)).append(" ").append("\n");
            }
            byte[] strToBytes = strBuilder.toString().getBytes();

            Files.write(path, strToBytes);

            JOptionPane.showMessageDialog(null, "Se grabaron los datos en el archivo banco.txt");

        } catch (NoSuchFileException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo o la carpeta no existen.");
        } catch (AccessDeniedException e) {
            JOptionPane.showMessageDialog(null, "Error: Permiso denegado para acceder al archivo o la carpeta.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la operación: " + e.getMessage());
        }
    }
}

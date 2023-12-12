import javax.swing.JOptionPane;
import java.nio.file.*;
import java.util.*;

public class guardararchivo {

    static String[] producto = new String[1];
    static int[] codigo = new int[1];
    static float[] precio = new float[1];
    static String username = System.getenv("USERNAME");
    public static void main(String[] args) {

        Solicitar_Datos();
        GrabarArchivo();
        LeerDesdeArchivo();
    }

    private static void Solicitar_Datos() {
        for (int i = 0; i < codigo.length; i++) {
            producto[i] = JOptionPane.showInputDialog(null, "Ingrese el nombre del Producto #" + (i + 1));
            codigo[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del Producto #" + (i + 1)));
            precio[i] = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el precio del Producto #" + (i + 1)));
        }
    }

    private static void GrabarArchivo() {
        try {
            // Ruta del archivo
            String rutaArchivo = "C:\\Users\\"+ username +"\\Documents\\Banco\\banco.txt";
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

                    // Continuar con la escritura
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < codigo.length; i++) {
                        strBuilder.append(producto[i]).append(" ").append(codigo[i]).append(" ").append(precio[i]).append("\n");
                    }
                    byte[] strToBytes = strBuilder.toString().getBytes();

                    Files.write(path, strToBytes);

                    JOptionPane.showMessageDialog(null, "Se grabaron los datos en el archivo banco.txt");
                }
            } else {
                // El archivo existe, continuar con la escritura
                StringBuilder strBuilder = new StringBuilder();
                for (int i = 0; i < codigo.length; i++) {
                    strBuilder.append(producto[i]).append(" ").append(codigo[i]).append(" ").append(precio[i]).append("\n");
                }
                byte[] strToBytes = strBuilder.toString().getBytes();

                Files.write(path, strToBytes);

                JOptionPane.showMessageDialog(null, "Se grabaron los datos en el archivo banco.txt");
            }

        } catch (NoSuchFileException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo o la carpeta no existen.");
        } catch (AccessDeniedException e) {
            JOptionPane.showMessageDialog(null, "Error: Permiso denegado para acceder al archivo o la carpeta.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la operación: " + e.getMessage());
        }
    }

    private static void LeerDesdeArchivo() {
        try {
            String rutaArchivo = "C:\\Users\\"+ username +"\\Documents\\Banco\\banco.txt";
            Path path = Paths.get(rutaArchivo);

            // Verificar si el archivo existe
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);

                for (String line : lines) {
                    StringTokenizer palabra = new StringTokenizer(line);

                    String prod = palabra.nextToken();
                    int cod = Integer.parseInt(palabra.nextToken());
                    float prec = Float.parseFloat(palabra.nextToken());
                    System.out.println("Producto: " + prod + ", Código: " + cod + ", Precio: " + prec);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo banco.txt no existe en la ruta especificada.");
            }

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

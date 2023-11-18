import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class base_de_datos {
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement statement = null;

    public void conexion() {
        // If already connected, don't attempt to connect again
        if (connection != null) {
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Banco";//poner la base de dastos a utilizar
        String user = "postgres";//poner el usuario de su base de datos
        String password = "2810";// poner su contrasena

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Conexión Establecida");
                // Puedes realizar consultas u otras operaciones con la base de datos aquí
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas de Conexión en el método conexion: " + e.getMessage());
        }
    }
    public void agregarCliente(String nombre, String apellido, int edad, float dinero) {
        try {
            // Verifica que la conexión esté establecida antes de realizar la inserción
            if (connection == null || connection.isClosed()) {
                JOptionPane.showMessageDialog(null, "No hay una conexión establecida");
                return;
            }
    
            // Consulta SQL para insertar un nuevo cliente
            String query = "INSERT INTO clientes (nombre, apellido, edad, dinero) VALUES (?, ?, ?, ?)";

            // Preparar la declaración SQL con los parámetros del nuevo cliente
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setInt(3, edad);
            preparedStatement.setFloat(4, dinero);
    
            // Ejecutar la consulta de inserción
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el cliente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + e.getMessage());
        }
    }
    public void buscarCliente(String nombreCliente) {
        try {
            // Verifica que la conexión esté establecida antes de realizar la búsqueda
            if (connection == null || connection.isClosed()) {
                JOptionPane.showMessageDialog(null, "No hay una conexión establecida");
                return;
            }

            // Consulta SQL para buscar un cliente por nombre
            String query = "SELECT * FROM clientes WHERE nombre = ?";
            
            // Preparar la declaración SQL
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombreCliente);

            // Ejecutar la consulta
            rs = preparedStatement.executeQuery();

            // Iterar sobre los resultados si se encontró algún cliente
            if (rs.next()) {
                // Por ejemplo, mostrar información del cliente encontrado
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                // ... otras columnas que desees mostrar

                JOptionPane.showMessageDialog(null, "Cliente encontrado - ID: " + id + ", Nombre: " + nombre);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
        }
    }
    
    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

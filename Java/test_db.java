import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class test_db {
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement statement = null;

    public void conexion() {
        // If already connected, don't attempt to connect again
        if (connection != null) {
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/test";//poner la base de dastos a utilizar
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

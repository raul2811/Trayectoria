package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class base_de_datos {

    private Connection connection = null;

    

    public boolean conectar(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Agregar otros métodos para realizar consultas, cerrar la conexión, etc.
}

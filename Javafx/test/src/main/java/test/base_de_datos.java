package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class base_de_datos {
    private String url = "jdbc:postgresql://bubble.db.elephantsql.com:5432/qnkqtlwp";
    private String user = "qnkqtlwp";
    private String password = "WzeaecAaHo5dSgTPgMTotp_mikvPSm2_";
    private Connection connection;

    public boolean conectarBaseDatos() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    public void cerrarRecursos(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }

    public Usuario login(String username, String password) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            conectarBaseDatos();
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                double dinero = resultSet.getDouble("dinero");
                int id = resultSet.getInt("id");
                return new Usuario(nombre, apellido, dinero,id);
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el login: " + e.getMessage());
        } finally {
            cerrarRecursos(resultSet, statement, connection);
        }
        return null;
    }

    public boolean realizarRetiro(Usuario usuario, double cantidadARetirar) {
        PreparedStatement statement = null;

        try {
            conectarBaseDatos();
            String sql = "UPDATE usuarios SET dinero = dinero - ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, cantidadARetirar);
            statement.setInt(2, usuario.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al realizar el retiro: " + e.getMessage());
        } finally {
            cerrarRecursos(null, statement, connection);
        }
        return false;
    }

    public boolean realizarDeposito(Usuario usuario, double cantidadADepositar) {
        PreparedStatement statement = null;

        try {
            conectarBaseDatos();
            String sql = "UPDATE usuarios SET dinero = dinero + ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, cantidadADepositar);
            statement.setInt(2, usuario.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al realizar el depósito: " + e.getMessage());
        } finally {
            cerrarRecursos(null, statement, connection);
        }
        return false;
    }
}
    

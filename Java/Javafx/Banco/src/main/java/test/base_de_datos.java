package test; // Este código está dentro del paquete 'test'

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Definición de la clase base_de_datos en el paquete 'test'
public class base_de_datos {
    // Definición de atributos para la conexión a la base de datos
    private String url = "jdbc:postgresql://bubble.db.elephantsql.com:5432/qnkqtlwp"; // URL de la base de datos
    private String user = "qnkqtlwp"; // Nombre de usuario de la base de datos
    private String password = "WzeaecAaHo5dSgTPgMTotp_mikvPSm2_"; // Contraseña de la base de datos
    private Connection connection; // Objeto Connection para la conexión a la base de datos

    // Método para conectar a la base de datos
    public boolean conectarBaseDatos() {
        try {
            connection = DriverManager.getConnection(url, user, password); // Establecer la conexión
            System.out.println("Conexión exitosa a la base de datos"); // Mensaje de éxito
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage()); // Mensaje de error
            return false;
        }
    }

    // Método para cerrar los recursos de la base de datos (ResultSet, PreparedStatement, Connection)
    public void cerrarRecursos(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            // Cerrar el ResultSet si no es nulo
            if (resultSet != null) {
                resultSet.close();
            }
            // Cerrar el PreparedStatement si no es nulo
            if (statement != null) {
                statement.close();
            }
            // Cerrar la Connection si no es nula
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage()); // Mensaje de error al cerrar recursos
        }
    }

    // Método para realizar el login de un usuario
    public Usuario login(String username, String password) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            conectarBaseDatos(); // Conectar a la base de datos
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?"; // Consulta SQL
            statement = connection.prepareStatement(sql); // Preparar la consulta
            statement.setString(1, username); // Establecer el valor del primer parámetro (?)
            statement.setString(2, password); // Establecer el valor del segundo parámetro (?)
            resultSet = statement.executeQuery(); // Ejecutar la consulta

            // Verificar si se encontró un usuario con las credenciales proporcionadas
            if (resultSet.next()) {
                // Obtener los datos del usuario
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                double dinero = resultSet.getDouble("dinero");
                int id = resultSet.getInt("id");
                String username1 = resultSet.getString("username");
                // Crear y retornar un objeto Usuario con los datos obtenidos
                return new Usuario(nombre, apellido, dinero, id, username1);
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el login: " + e.getMessage()); // Mensaje de error al realizar el login
        } finally {
            cerrarRecursos(resultSet, statement, connection); // Cerrar recursos de base de datos
        }
        return null; // Retornar null si no se encuentra el usuario
    }

     // Método para registrar un nuevo usuario en la base de datos
    public boolean registrarse(String username, String password, String nombre, String apellido, int edad, String cedula) {
        PreparedStatement statement = null;

        try {
            conectarBaseDatos(); // Conectar a la base de datos
            String sql = "INSERT INTO usuarios (username, password, nombre, apellido, edad, cedula, dinero) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql); // Preparar la consulta SQL
            // Establecer los valores para los parámetros de la consulta
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, nombre);
            statement.setString(4, apellido);
            statement.setInt(5, edad);
            statement.setString(6, cedula);
            statement.setDouble(7, 0); // Nuevo usuario, generalmente sin dinero inicialmente

            int rowsAffected = statement.executeUpdate(); // Ejecutar la consulta de inserción

            return rowsAffected > 0; // Devolver verdadero si la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage()); // Mensaje de error al registrar usuario
        } finally {
            cerrarRecursos(null, statement, connection); // Cerrar recursos de base de datos
        }
        return false; // Retornar falso si no se pudo registrar el usuario
    }

    public boolean realizarRetiro(Usuario usuario, double cantidadARetirar) {
    PreparedStatement statement = null;

    try {
        conectarBaseDatos(); // Establecer la conexión a la base de datos
        String sql = "UPDATE usuarios SET dinero = dinero - ? WHERE id = ?"; // Consulta SQL para realizar el retiro
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        statement.setDouble(1, cantidadARetirar); // Establecer el valor del retiro
        statement.setInt(2, usuario.getId()); // Establecer el ID del usuario

        int rowsAffected = statement.executeUpdate(); // Ejecutar la consulta de actualización

        return rowsAffected > 0; // Devolver verdadero si la actualización fue exitosa
    } catch (SQLException e) {
        System.out.println("Error al realizar el retiro: " + e.getMessage()); // Mensaje de error en caso de falla
    } finally {
        cerrarRecursos(null, statement, connection); // Cerrar recursos de la base de datos
    }
    return false; // Retornar falso si el retiro no se realizó con éxito
}

public boolean realizarDeposito(Usuario usuario, double cantidadADepositar) {
    PreparedStatement statement = null;

    try {
        conectarBaseDatos(); // Establecer la conexión a la base de datos
        String sql = "UPDATE usuarios SET dinero = dinero + ? WHERE id = ?"; // Consulta SQL para realizar el depósito
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        statement.setDouble(1, cantidadADepositar); // Establecer el valor del depósito
        statement.setInt(2, usuario.getId()); // Establecer el ID del usuario

        int rowsAffected = statement.executeUpdate(); // Ejecutar la consulta de actualización

        return rowsAffected > 0; // Devolver verdadero si la actualización fue exitosa
    } catch (SQLException e) {
        System.out.println("Error al realizar el depósito: " + e.getMessage()); // Mensaje de error en caso de falla
    } finally {
        cerrarRecursos(null, statement, connection); // Cerrar recursos de la base de datos
    }
    return false; // Retornar falso si el depósito no se realizó con éxito
}
    private Usuario obtenerUsuarioPorUsername(String username, Connection connection) {
    ResultSet resultSet = null;
    PreparedStatement statement = null;

    try {
        // Consulta SQL para obtener un usuario por su nombre de usuario
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        statement.setString(1, username); // Establecer el valor del parámetro en la consulta
        resultSet = statement.executeQuery(); // Ejecutar la consulta

        // Verificar si se encuentra un resultado en el conjunto de resultados
        if (resultSet.next()) {
            // Obtener los datos del usuario encontrado en la base de datos
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            double dinero = resultSet.getDouble("dinero");
            int id = resultSet.getInt("id");
            String username1 = resultSet.getString("username");
            // Crear y retornar un objeto Usuario con los datos obtenidos
            return new Usuario(nombre, apellido, dinero, id, username1);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener usuario por username: " + e.getMessage()); // Mensaje de error en caso de fallo
    } finally {
        cerrarRecursos(resultSet, statement, null); // Cerrar recursos de la base de datos
    }
    return null; // Retornar null si no se encuentra el usuario
}

public boolean transferirDinero(Usuario remitente, String destinatarioUsername, String montoStr) {
    PreparedStatement statementRemitente = null;
    PreparedStatement statementDestinatario = null;
    Connection connection = null;

    try {
        // Establecer la conexión a la base de datos
        connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false); // Iniciar una transacción

        // Validar que el monto ingresado sea un número válido
        double monto = 0;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException ex) {
            System.out.println("Error: El monto ingresado no es válido"); // Mensaje de error si el monto no es válido
            return false;
        }

        // Verificar que el destinatario sea diferente al remitente
        if (destinatarioUsername.equals(remitente.getUsername())) {
            System.out.println("Error: El destinatario es el mismo que el remitente"); // Mensaje de error si el destinatario es el mismo que el remitente
            return false;
        }

        // Validar que el monto sea mayor que cero
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor que cero"); // Mensaje de error si el monto es menor o igual a cero
            return false;
        }

        // Obtener el usuario destinatario
        Usuario destinatario = obtenerUsuarioPorUsername(destinatarioUsername, connection);

        // Verificar si el destinatario existe y el remitente tiene suficiente dinero para transferir
        if (destinatario != null && remitente.getDinero() >= monto) {
            // Actualizar el dinero del remitente
            String sqlRemitente = "UPDATE usuarios SET dinero = dinero - ? WHERE id = ?";
            statementRemitente = connection.prepareStatement(sqlRemitente);
            statementRemitente.setDouble(1, monto);
            statementRemitente.setInt(2, remitente.getId());
            int rowsAffectedRemitente = statementRemitente.executeUpdate();

            if (rowsAffectedRemitente > 0) {
                // Actualizar el dinero del destinatario
                String sqlDestinatario = "UPDATE usuarios SET dinero = dinero + ? WHERE id = ?";
                statementDestinatario = connection.prepareStatement(sqlDestinatario);
                statementDestinatario.setDouble(1, monto);
                statementDestinatario.setInt(2, destinatario.getId());
                int rowsAffectedDestinatario = statementDestinatario.executeUpdate();

                if (rowsAffectedDestinatario > 0) {
                    connection.commit(); // Confirmar la transacción
                    return true;
                } else {
                    connection.rollback(); // Revertir la transacción si falla la actualización
                }
            }
        } else {
            if (destinatario == null) {
                System.out.println("Error: Destinatario no encontrado"); // Mensaje de error si el destinatario no es encontrado
            } else {
                System.out.println("Error: Fondos insuficientes"); // Mensaje de error si el remitente no tiene suficiente dinero
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al transferir dinero: " + e.getMessage()); // Mensaje de error en caso de fallo
        if (connection != null) {
            try {
                connection.rollback(); // Revertir la transacción si hay una excepción
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex.getMessage()); // Mensaje de error al hacer rollback
            }
        }
    } finally {
        // Cerrar los recursos y restaurar la configuración de la conexión
        cerrarRecursos(null, statementRemitente, null);
        cerrarRecursos(null, statementDestinatario, null);
        cerrarRecursos(null, null, connection);
    }
    return false; // Retornar falso si la transferencia no se realiza con éxito
    }
}
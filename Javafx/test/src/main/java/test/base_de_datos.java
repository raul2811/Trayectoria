package test; // Este código está dentro del paquete 'test'

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLTimeoutException;

// Definición de la clase base_de_datos en el paquete 'test'
public class base_de_datos {
    // Definición de atributos para la conexión a la base de datos
    private String url = "jdbc:postgresql://bubble.db.elephantsql.com:5432/qajngngv"; // URL de la base de datos
    private String user = "qajngngv"; // Nombre de usuario de la base de datos
    private String password = "eR7p7wS0iyvaIcgXvNYzcAPHDC1FWu5F"; // Contraseña de la base de datos
    private Connection connection; // Objeto Connection para la conexión a la base de datos

    public boolean conectarBaseDatos() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            if (e instanceof SQLTimeoutException) {
                System.out.println("La conexión ha expirado debido a un tiempo de espera agotado.");
            } else if (e instanceof SQLInvalidAuthorizationSpecException) {
                System.out.println("Las credenciales de la base de datos son inválidas.");
            } else {
                System.out.println("Otro error de SQL desconocido.");
            }
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

    // Método de login 
public Usuario login(String username, String password) {
    ResultSet resultSet = null;
    PreparedStatement statement = null;

    try {
        conectarBaseDatos(); // Conectar a la base de datos
        String sql = "SELECT usuarios.id, usuarios.nombre, usuarios.apellido, cuentas.tipo_cuenta_id, cuentas.monto " +
                     "FROM usuarios " +
                     "INNER JOIN cuentas ON usuarios.id = cuentas.id_usuario " +
                     "WHERE usuarios.username = ? AND usuarios.password = ?"; // Consulta SQL
        statement = connection.prepareStatement(sql); // Preparar la consulta
        statement.setString(1, username); // Establecer el valor del primer parámetro (?)
        statement.setString(2, password); // Establecer el valor del segundo parámetro (?)
        resultSet = statement.executeQuery(); // Ejecutar la consulta

        // Verificar si se encontró un usuario con las credenciales proporcionadas
        if (resultSet.next()) {
            // Obtener los datos del usuario y la cuenta
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String tipoCuenta = resultSet.getString("tipo_cuenta_id");
            double monto = resultSet.getDouble("monto");

            // Crear y retornar un objeto Usuario con los datos obtenidos
            Usuario usuario = new Usuario(nombre, apellido, id, username);
            usuario.setTipoCuenta(tipoCuenta); // Establecer el tipo de cuenta
            usuario.setMontoCuenta(monto); // Establecer el monto de la cuenta
            return usuario;
        }
    } catch (SQLException ex) {
        System.out.println("Error al realizar el login: " + ex.getMessage()); // Mensaje de error al realizar el login
    } finally {
        cerrarRecursos(resultSet, statement, connection); // Cerrar recursos de base de datos
    }
    return null; // Retornar null si no se encuentra el usuario
};

public boolean registrarse(String username, String password, String nombre, String apellido, int edad, String cedula) {
    PreparedStatement statement = null;

    try {
        conectarBaseDatos(); // Conectar a la base de datos
        String sql = "INSERT INTO usuarios (username, password, nombre, apellido, edad, cedula) VALUES (?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS); // Preparar la consulta SQL y obtener las claves generadas
        // Establecer los valores para los parámetros de la consulta
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, nombre);
        statement.setString(4, apellido);
        statement.setInt(5, edad);
        statement.setString(6, cedula);

        int rowsAffected = statement.executeUpdate(); // Ejecutar la consulta de inserción

        if (rowsAffected > 0) {
            // Obtener el ID generado para el usuario registrado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            // Crear una cuenta por defecto para el usuario con monto 0 y tipo de cuenta ID 1
            crearCuentaPorDefecto(userId);

            System.out.println("Usuario registrado exitosamente."); // Mensaje de éxito
            return true; // Devolver verdadero si la inserción fue exitosa
        }
    } catch (SQLException e) {
        System.out.println("Error al registrar usuario: " + e.getMessage()); // Mensaje de error al registrar usuario
    } finally {
        cerrarRecursos(null, statement, connection); // Cerrar recursos de base de datos
    }
    return false; // Retornar falso si no se pudo registrar el usuario
}

// Método para crear una cuenta por defecto para un usuario dado
private void crearCuentaPorDefecto(int userId) throws SQLException {
    PreparedStatement statement = null;

    try {
        String sql = "INSERT INTO cuentas (id_usuario, tipo_cuenta_id, monto) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        // Establecer los valores para los parámetros de la consulta
        statement.setInt(1, userId);
        statement.setInt(2, 1); // Tipo de cuenta ID 1 (asumiendo que es el tipo por defecto)
        statement.setDouble(3, 0); // Monto inicial de la cuenta: 0

        statement.executeUpdate(); // Ejecutar la consulta de inserción para la nueva cuenta
    } finally {
        cerrarRecursos(null, statement, null); // Cerrar recursos de base de datos
    }
}

public boolean realizarRetiro(Usuario usuario, double cantidadARetirar) {
    PreparedStatement statement = null;

    try {
        conectarBaseDatos(); // Establecer la conexión a la base de datos
        String sql = "UPDATE cuentas SET monto = monto - ? WHERE id_usuario = ? AND tipo = ?"; // Consulta SQL para realizar el retiro
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        statement.setDouble(1, cantidadARetirar); // Establecer el valor del retiro
        statement.setInt(2, usuario.getId()); // Establecer el ID del usuario
        statement.setString(3, usuario.getTipoCuenta()); // Establecer el tipo de cuenta del usuario

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
        String sql = "UPDATE cuentas SET monto = monto + ? WHERE id_usuario = ?"; // Consulta SQL para realizar el depósito
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
        String sql = "SELECT usuarios.id, usuarios.nombre, usuarios.apellido, cuentas.monto " +
                     "FROM usuarios " +
                     "INNER JOIN cuentas ON usuarios.id = cuentas.id_usuario " +
                     "WHERE usuarios.username = ?";
        statement = connection.prepareStatement(sql); // Preparar la consulta SQL
        statement.setString(1, username); // Establecer el valor del parámetro en la consulta
        resultSet = statement.executeQuery(); // Ejecutar la consulta

        // Verificar si se encuentra un resultado en el conjunto de resultados
        if (resultSet.next()) {
            // Obtener los datos del usuario y la cuenta
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            double monto = resultSet.getDouble("monto");

            // Crear y retornar un objeto Usuario con los datos obtenidos
            Usuario usuario = new Usuario(nombre, apellido, id, username);
            usuario.setMontoCuenta(monto); // Establecer el monto de la cuenta
            return usuario;
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
        conectarBaseDatos(); // Establecer la conexión a la base de datos
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
        if (destinatario != null && remitente.getMontoCuenta() >= monto) {
            // Actualizar el dinero del remitente
            String sqlRemitente = "UPDATE cuentas SET monto = monto - ? WHERE id_usuario = ?";
            statementRemitente = connection.prepareStatement(sqlRemitente);
            statementRemitente.setDouble(1, monto);
            statementRemitente.setInt(2, remitente.getId());
            int rowsAffectedRemitente = statementRemitente.executeUpdate();

            if (rowsAffectedRemitente > 0) {
                // Actualizar el dinero del destinatario
                String sqlDestinatario = "UPDATE cuentas SET monto = monto + ? WHERE id_usuario = ?";
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
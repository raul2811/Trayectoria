package test; // Este código está dentro del paquete 'test'

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    private String password_db = "eR7p7wS0iyvaIcgXvNYzcAPHDC1FWu5F"; // Contraseña de la base de datos
    private Connection connection; // Objeto Connection para la conexión a la base de datos

    public boolean conectarBaseDatos() {
        try {
            connection = DriverManager.getConnection(url, user, password_db);
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

    public void cerrarRecursos(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar ResultSet: " + e.getMessage());
            }
        }
        
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
        
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar Connection: " + e.getMessage());
            }
        }
    }

    public List<CuentaInfo> obtenerInfoCuentasUsuario(int idUsuario) {
    List<CuentaInfo> cuentas = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(
            "SELECT id, tipo, monto FROM cuentas WHERE id_usuario = ?")) {

        statement.setInt(1, idUsuario);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int idCuenta = resultSet.getInt("id");
            String tipoCuenta = resultSet.getString("tipo");
            double monto = resultSet.getDouble("monto");
            
            CuentaInfo cuenta = new CuentaInfo(idCuenta, tipoCuenta, monto);
            cuentas.add(cuenta);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener la información de las cuentas del usuario: " + e.getMessage());
    }
    
    return cuentas;
}

    private void registrarActividad(int idUsuario, int idTipoActividad, double monto, int cuentaOrigenId, int cuentaDestinoId) {
        try {
            conectarBaseDatos(); // Conectar a la base de datos
            String sql;
    
            switch (idTipoActividad) {
                case TipoActividad.USUARIO_REGISTRADO:
                    sql = "INSERT INTO actividades_registro (id_usuario, id_tipo_actividad) VALUES (?, ?)";
                    PreparedStatement statement1 = connection.prepareStatement(sql);
                    statement1.setInt(1, idUsuario);
                    statement1.setInt(2, idTipoActividad);
                    statement1.executeUpdate();
                    System.out.println("Registro de usuario exitoso.");
                    break;
    
                case TipoActividad.RETIRO:
                case TipoActividad.DEPOSITO:
                    sql = "INSERT INTO actividades_registro (id_usuario, id_tipo_actividad, monto) VALUES (?, ?, ?)";
                    PreparedStatement statement2 = connection.prepareStatement(sql);
                    statement2.setInt(1, idUsuario);
                    statement2.setInt(2, idTipoActividad);
                    statement2.setDouble(3, monto);
                    statement2.executeUpdate();
                    System.out.println("Operación registrada exitosamente.");
                    break;
    
                case TipoActividad.TRANSFERENCIA:
                    sql = "INSERT INTO actividades_registro (id_usuario, id_tipo_actividad, monto, cuenta_origen_id, cuenta_destino_id) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement3 = connection.prepareStatement(sql);
                    statement3.setInt(1, idUsuario);
                    statement3.setInt(2, idTipoActividad);
                    statement3.setDouble(3, monto);
                    statement3.setInt(4, cuentaOrigenId);
                    statement3.setInt(5, cuentaDestinoId);
                    statement3.executeUpdate();
                    System.out.println("Transferencia registrada exitosamente.");
                    break;
    
                default:
                    System.out.println("Tipo de actividad no reconocido.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
        }
    }
    // Método de login 
    public Usuario login(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password_db);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT usuarios.id, usuarios.nombre, usuarios.apellido, cuentas.tipo_cuenta_id, cuentas.monto " +
                             "FROM usuarios " +
                             "INNER JOIN cuentas ON usuarios.id = cuentas.id_usuario " +
                             "WHERE usuarios.username = ? AND usuarios.password = ?")) {
    
            statement.setString(1, username); // Establecer el valor del primer parámetro (?)
            statement.setString(2, password); // Establecer el valor del segundo parámetro (?)
            try (ResultSet resultSet = statement.executeQuery()) { // Ejecutar la consulta
    
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String tipoCuenta = resultSet.getString("tipo_cuenta_id");
                    double monto = resultSet.getDouble("monto");
    
                    Usuario usuario = new Usuario(nombre, apellido, id, username);
                    usuario.setTipoCuenta(tipoCuenta);
                    usuario.setMontoCuenta(monto);
                    return usuario;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar el login: " + ex.getMessage());
        }
        return null;
    }

    public boolean registrarse(String username, String password, String nombre, String apellido, int edad, String cedula) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
    
        try {
            conectarBaseDatos(); // Conectar a la base de datos
            String sql = "INSERT INTO usuarios (username, password, nombre, apellido, edad, cedula) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Preparar la consulta SQL y obtener las claves generadas
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
                generatedKeys = statement.getGeneratedKeys();
                int userId = -1;
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                }
    
                // Crear una cuenta por defecto para el usuario con monto 0 y tipo de cuenta ID 1
                crearCuentaPorDefecto(userId);
                // Aquí debes definir el ID del tipo de actividad que corresponda al registro de usuario
                int idTipoActividad=TipoActividad.USUARIO_REGISTRADO;
                registrarActividad(userId, idTipoActividad,0,0,0);
                System.out.println("Usuario registrado exitosamente."); // Mensaje de éxito
                return true; // Devolver verdadero si la inserción fue exitosa
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage()); // Mensaje de error al registrar usuario
        } finally {
            // Cerrar recursos de base de datos en bloques separados
            cerrarResultSet(generatedKeys);
            cerrarRecursos(null, statement, connection);
        }
        return false; // Retornar falso si no se pudo registrar el usuario
    }
    
    // Método para cerrar el ResultSet
    private void cerrarResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar ResultSet: " + e.getMessage());
        }
    }

    private void crearCuentaPorDefecto(int userId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cuentas (id_usuario, tipo_cuenta_id, monto) VALUES (?, ?, ?)")) {
    
            statement.setInt(1, userId);
            statement.setInt(2, 1); // Tipo de cuenta ID 1 (asumiendo que es el tipo por defecto)
            statement.setDouble(3, 0); // Monto inicial de la cuenta: 0
    
            statement.executeUpdate(); // Ejecutar la consulta de inserción para la nueva cuenta
        } catch (SQLException e) {
            System.out.println("Error al crear cuenta por defecto: " + e.getMessage());
            throw e; // Relanzar la excepción para manejarla en un nivel superior si es necesario
        }
    }

    public boolean realizarRetiro(int idCuenta, double cantidadARetirar) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE cuentas SET monto = monto - ? WHERE id = ?")) {
    
            statement.setDouble(1, cantidadARetirar);
            statement.setInt(2, idCuenta);
    
            int rowsAffected = statement.executeUpdate();
    
            if (rowsAffected > 0) {
                // Obtener el ID del usuario asociado a la cuenta
                int idUsuario = obtenerIdUsuarioPorCuenta(idCuenta);
    
                if (idUsuario != -1) {
                    // Registrar la actividad de retiro
                    registrarActividad(idUsuario, TipoActividad.RETIRO, cantidadARetirar, 0, 0);
                    return true;
                } else {
                    System.out.println("Error: No se pudo obtener el ID del usuario asociado a la cuenta.");
                }
            }
            return false;
    
        } catch (SQLException e) {
            System.out.println("Error al realizar el retiro: " + e.getMessage());
            return false;
        }
    }

    public boolean realizarDeposito(int idCuenta, double cantidadADepositar) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE cuentas SET monto = monto + ? WHERE id = ?")) {
    
            statement.setDouble(1, cantidadADepositar);
            statement.setInt(2, idCuenta);
    
            int rowsAffected = statement.executeUpdate();
            
            // Si la actualización fue exitosa, registra la actividad
            if (rowsAffected > 0) {
                // Se asume que registrarActividad toma el id del usuario, el tipo de actividad y el monto
                // Aquí deberías ajustar los parámetros según la lógica de tu función registrarActividad
                registrarActividad(obtenerIdUsuarioPorCuenta(idCuenta), TipoActividad.DEPOSITO, cantidadADepositar,0,0);
            }
    
            return rowsAffected > 0;
    
        } catch (SQLException e) {
            System.out.println("Error al realizar el depósito: " + e.getMessage());
            return false;
        }
    }
    public int obtenerIdUsuarioPorCuenta(int idCuenta) {
        int idUsuario = -1; // Valor por defecto si no se encuentra el ID del usuario
    
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id_usuario FROM cuentas WHERE id = ?")) {
    
            statement.setInt(1, idCuenta);
            ResultSet resultSet = statement.executeQuery();
    
            // Verificar si se encontró una fila y obtener el ID del usuario asociado a la cuenta
            if (resultSet.next()) {
                idUsuario = resultSet.getInt("id_usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID de usuario por cuenta: " + e.getMessage());
        }
    
        return idUsuario;
    }

    private Usuario obtenerUsuarioPorUsername(String username, Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
    
        try {
            statement = connection.prepareStatement(
                    "SELECT usuarios.id, usuarios.nombre, usuarios.apellido, cuentas.monto " +
                            "FROM usuarios " +
                            "INNER JOIN cuentas ON usuarios.id = cuentas.id_usuario " +
                            "WHERE usuarios.username = ?");
            statement.setString(1, username);
    
            resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                double monto = resultSet.getDouble("monto");
    
                Usuario usuario = new Usuario(nombre, apellido, id, username);
                usuario.setMontoCuenta(monto);
                return usuario;
            } else {
                System.out.println("No se encontró ningún usuario con el nombre de usuario: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario por username: " + e.getMessage());
        } finally {
            // Cerrar recursos en el bloque finally para asegurar su cierre
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
        return null;
    }
    public boolean transferirdinerocuentas(int idCuentaOrigen, int idCuentaDestino, double cantidadATransferir) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);
    
            // Actualización para restar la cantidad de la cuenta de origen
            try (PreparedStatement statement1 = connection.prepareStatement(
                    "UPDATE cuentas SET monto = monto - ? WHERE id = ?")) {
    
                statement1.setDouble(1, cantidadATransferir);
                statement1.setInt(2, idCuentaOrigen);
                statement1.executeUpdate();
            }
    
            // Actualización para sumar la cantidad a la cuenta de destino
            try (PreparedStatement statement2 = connection.prepareStatement(
                    "UPDATE cuentas SET monto = monto + ? WHERE id = ?")) {
    
                statement2.setDouble(1, cantidadATransferir);
                statement2.setInt(2, idCuentaDestino);
                statement2.executeUpdate();
            }
    
            // Confirma la transacción
            connection.commit();
            return true;
    
        } catch (SQLException e) {
            try {
                // En caso de error, se revierte la transacción
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.out.println("Error al realizar el rollback: " + rollbackException.getMessage());
            }
            System.out.println("Error al realizar la transferencia: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Se restaura el modo de autocommit
            } catch (SQLException rollbackException) {
                System.out.println("Error al restaurar el autocommit: " + rollbackException.getMessage());
            }
        }
    }

    public boolean transferirDinero(Usuario remitente, String destinatarioUsername, String montoStr) {
        PreparedStatement statementRemitente = null;
        PreparedStatement statementDestinatario = null;
        Connection connection = null;
    
        try {
            conectarBaseDatos();
            connection = DriverManager.getConnection(url, user, password_db);
            connection.setAutoCommit(false);
    
            double monto = 0;
            try {
                monto = Double.parseDouble(montoStr);
            } catch (NumberFormatException ex) {
                System.out.println("Error: El monto ingresado no es válido");
                return false;
            }
    
            if (destinatarioUsername.equals(remitente.getUsername())) {
                System.out.println("Error: El destinatario es el mismo que el remitente");
                return false;
            }
    
            if (monto <= 0) {
                System.out.println("Error: El monto debe ser mayor que cero");
                return false;
            }
    
            Usuario destinatario = obtenerUsuarioPorUsername(destinatarioUsername, connection);
    
            if (destinatario != null && remitente.getMontoCuenta() >= monto) {
                String sqlRemitente = "UPDATE cuentas SET monto = monto - ? WHERE id_usuario = ?";
                statementRemitente = connection.prepareStatement(sqlRemitente);
                statementRemitente.setDouble(1, monto);
                statementRemitente.setInt(2, remitente.getId());
                int rowsAffectedRemitente = statementRemitente.executeUpdate();
    
                if (rowsAffectedRemitente > 0) {
                    String sqlDestinatario = "UPDATE cuentas SET monto = monto + ? WHERE id_usuario = ?";
                    statementDestinatario = connection.prepareStatement(sqlDestinatario);
                    statementDestinatario.setDouble(1, monto);
                    statementDestinatario.setInt(2, destinatario.getId());
                    int rowsAffectedDestinatario = statementDestinatario.executeUpdate();
    
                    if (rowsAffectedDestinatario > 0) { 
                        // Registrar actividad de transferencia para el remitente
                    registrarActividad(remitente.getId(), TipoActividad.TRANSFERENCIA, monto, remitente.getId(), destinatario.getId());
                    
                    // Registrar actividad de transferencia para el destinatario
                    registrarActividad(destinatario.getId(), TipoActividad.TRANSFERENCIA, monto, remitente.getId(), destinatario.getId());
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        System.out.println("Error: Fallo al actualizar el destinatario");
                    }
                } else {
                    System.out.println("Error: Fallo al actualizar el remitente");
                    connection.rollback();
                }
            } else {
                if (destinatario == null) {
                    System.out.println("Error: Destinatario no encontrado");
                } else {
                    System.out.println("Error: Fondos insuficientes");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al transferir dinero: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
        } finally {
            cerrarRecursos(null, statementRemitente, null);
            cerrarRecursos(null, statementDestinatario, null);
            cerrarRecursos(null, null, connection);
        }
        return false;
    }
    
}
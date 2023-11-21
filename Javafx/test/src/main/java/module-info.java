module test {
    requires javafx.controls; // Requiere el módulo de JavaFX para controles de interfaz gráfica
    requires javafx.fxml; // Requiere el módulo de JavaFX para archivos FXML
    requires java.sql; // Requiere el módulo para operaciones JDBC

    opens test to javafx.fxml; // Permite el acceso desde el archivo FXML al paquete test
    exports test; // Hace que el paquete test sea visible para otros módulos
}
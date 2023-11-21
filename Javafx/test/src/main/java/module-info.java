module test {
    requires javafx.controls; // Requiere el módulo de JavaFX para los controles de interfaz de usuario
    requires transitive javafx.graphics; // Requiere el módulo de JavaFX para gráficos
    requires javafx.fxml; // Requiere el módulo de JavaFX para archivos FXML
    requires transitive java.sql; // Requiere el módulo de Java para operaciones JDBC

    opens test to javafx.fxml; // Permite el acceso desde archivos FXML al paquete 'test'
    exports test; // Hace visible el paquete 'test' para otros módulos
}

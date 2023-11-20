module test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Requiere el módulo para operaciones JDBC

    opens test to javafx.fxml;
    exports test;
}
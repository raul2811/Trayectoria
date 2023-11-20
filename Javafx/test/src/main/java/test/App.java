package test;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private base_de_datos db = new base_de_datos(); // Instancia de la clase BaseDeDatos

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inicio de Sesión");

        // Crear elementos de la interfaz de usuario
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("Usuario:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Iniciar Sesión");

        // Agregar elementos al grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Evento para el botón de inicio de sesión
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (db.conectar("jdbc:postgresql://localhost:5432/Banco", username, password)) {
                mostrarAlerta("Conexión Exitosa", "Se ha conectado a la base de datos correctamente", AlertType.INFORMATION);
                // Realizar acciones adicionales después de una conexión exitosa
            } else {
                mostrarAlerta("Error de Conexión", "Usuario o contraseña incorrectos", AlertType.ERROR);
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
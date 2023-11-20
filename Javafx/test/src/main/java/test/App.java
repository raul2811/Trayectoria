package test;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
        Button connectButton = new Button("Conectar"); // Botón para conectar

        // Agregar elementos al grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(connectButton, 0, 2); // Agregar el botón al grid  

        connectButton.setOnAction(e -> {
            // Acción al presionar el botón "Conectar"
            boolean connected = db.conectarBaseDatos();
            if (connected) {
                mostrarAlerta("Conexión Exitosa", "Conectado a la base de datos", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error de Conexión", "No se pudo conectar a la base de datos", Alert.AlertType.ERROR);
            }
        });

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Usuario usuario = db.login(username, password);
            if (usuario != null) {
                mostrarInformacionUsuario(usuario);
            } else {
                mostrarAlerta("Error de inicio de sesión", "Credenciales incorrectas", AlertType.ERROR);
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mostrarInformacionUsuario(Usuario usuario) {
    Stage infoStage = new Stage();
    infoStage.setTitle("Información del Usuario");

    GridPane infoGrid = new GridPane();
    infoGrid.setPadding(new Insets(20, 20, 20, 20));
    infoGrid.setVgap(10);
    infoGrid.setHgap(10);

    Label nombreLabel = new Label("Nombre:");
    Label dineroLabel = new Label("Dinero:");
    Label nombreValor = new Label(usuario.getNombre() + " " + usuario.getApellido());
    Label dineroValor = new Label(String.valueOf(usuario.getDinero()));

    Button depositoButton = new Button("Depósito");
    Button retiroButton = new Button("Retiro");

    depositoButton.setOnAction(e -> {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Depósito");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la cantidad a depositar:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(cantidadStr -> {
            double cantidad = Double.parseDouble(cantidadStr);
            if (cantidad > 0) {
                boolean depositoExitoso = db.realizarDeposito(usuario, cantidad);
                if (depositoExitoso) {
                    dineroValor.setText(String.valueOf(usuario.getDinero() + cantidad));
                } else {
                    mostrarAlerta("Error", "No se pudo realizar el depósito", Alert.AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error", "Ingrese una cantidad válida", Alert.AlertType.ERROR);
            }
        });
    });

    retiroButton.setOnAction(e -> {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Retiro");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la cantidad a retirar:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(cantidadStr -> {
            double cantidad = Double.parseDouble(cantidadStr);
            if (cantidad > 0 && cantidad <= usuario.getDinero()) {
                boolean retiroExitoso = db.realizarRetiro(usuario, cantidad);
                if (retiroExitoso) {
                    dineroValor.setText(String.valueOf(usuario.getDinero() - cantidad));
                } else {
                    mostrarAlerta("Error", "No se pudo realizar el retiro", Alert.AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error", "Ingrese una cantidad válida", Alert.AlertType.ERROR);
            }
        });
    });

    infoGrid.add(nombreLabel, 0, 0);
    infoGrid.add(nombreValor, 1, 0);
    infoGrid.add(dineroLabel, 0, 1);
    infoGrid.add(dineroValor, 1, 1);
    infoGrid.add(depositoButton, 0, 2);
    infoGrid.add(retiroButton, 1, 2);

    Scene infoScene = new Scene(infoGrid, 300, 200);
    infoStage.setScene(infoScene);
    infoStage.show();
}

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta) {
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

    

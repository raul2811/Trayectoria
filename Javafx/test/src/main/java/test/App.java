package test;

import java.util.List;
import java.util.Optional;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;

import java.time.LocalDate;


public class App extends Application {

    private base_de_datos db = new base_de_datos();
    private GridPane registroGrid = new GridPane();
    private VBox loginVBox;
    private VBox registroVBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banco universitario");

        Image logoImage = new Image("negativo-horizontal.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitHeight(100);
        logoView.setFitWidth(200);

        Label usernameLabel = new Label("Usuario:");
        usernameLabel.getStyleClass().add("white-label");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Ingrese su usuario");
        usernameField.getStyleClass().add("white-field");
        usernameField.setId("username");

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.getStyleClass().add("white-label");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Ingrese su contraseña");
        passwordField.getStyleClass().add("white-field");
        passwordField.setId("password");

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.getStyleClass().add("transparent-button");

        Button registrarButton = new Button("Crear cuenta");
        registrarButton.getStyleClass().add("crear-cuenta-button");

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(loginButton, registrarButton);

        loginVBox = new VBox(10);
        loginVBox.setAlignment(Pos.CENTER);
        loginVBox.getChildren().addAll(logoView, usernameLabel, usernameField, passwordLabel, passwordField, hbox);

        Scene scene = new Scene(loginVBox, 600, 300);
        scene.getStylesheets().add("style.css");
        loginVBox.getStyleClass().add("root");

        Image appIcon = new Image("horizontal.png");
        primaryStage.getIcons().add(appIcon);

        // VBox para el formulario de registro
        registroVBox = new VBox(10);
        registroVBox.setAlignment(Pos.CENTER);
        registroVBox.getChildren().addAll(registroGrid);
        registroVBox.setVisible(false);

        registrarButton.setOnAction(e -> {
            loginVBox.setVisible(false);
            registroVBox.setVisible(true);
        });


        primaryStage.setScene(scene);
        primaryStage.show();

        registroGrid.setAlignment(Pos.CENTER);
        registroGrid.setHgap(10);
        registroGrid.setVgap(10);
        registroGrid.setVisible(false); // Inicialmente oculto

        loginVBox.getChildren().addAll(registroVBox);

        registrarButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Registro");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese su nombre de usuario:");

            Optional<String> usernameResult = dialog.showAndWait();
            usernameResult.ifPresent(username -> {
                TextInputDialog passwordDialog = new TextInputDialog();
                passwordDialog.setTitle("Registro");
                passwordDialog.setHeaderText(null);
                passwordDialog.setContentText("Ingrese su contraseña:");

                Optional<String> passwordResult = passwordDialog.showAndWait();
                passwordResult.ifPresent(password -> {
                    TextInputDialog nombreDialog = new TextInputDialog();
                    nombreDialog.setTitle("Registro");
                    nombreDialog.setHeaderText(null);
                    nombreDialog.setContentText("Ingrese su nombre:");

                    Optional<String> nombreResult = nombreDialog.showAndWait();
                    nombreResult.ifPresent(nombre -> {
                        TextInputDialog apellidoDialog = new TextInputDialog();
                        apellidoDialog.setTitle("Registro");
                        apellidoDialog.setHeaderText(null);
                        apellidoDialog.setContentText("Ingrese su apellido:");

                        Optional<String> apellidoResult = apellidoDialog.showAndWait();
                        apellidoResult.ifPresent(apellido -> {
                            TextInputDialog edadDialog = new TextInputDialog();
                            edadDialog.setTitle("Registro");
                            edadDialog.setHeaderText(null);
                            edadDialog.setContentText("Ingrese su edad:");

                            Optional<String> edadResult = edadDialog.showAndWait();
                            edadResult.ifPresent(edadStr -> {
                                try {
                                    int edad = Integer.parseInt(edadStr);

                                    TextInputDialog cedulaDialog = new TextInputDialog();
                                    cedulaDialog.setTitle("Registro");
                                    cedulaDialog.setHeaderText(null);
                                    cedulaDialog.setContentText("Ingrese su cédula:");

                                    Optional<String> cedulaResult = cedulaDialog.showAndWait();
                                    cedulaResult.ifPresent(cedula -> {
                                        // Aquí llamas a tu función de registro
                                        boolean registroExitoso = db.registrarse(username, password, nombre, apellido, edad, cedula);
                                        if (registroExitoso) {
                                            mostrarAlerta("Éxito", "Registro exitoso", Alert.AlertType.INFORMATION);
                                        } else {
                                            mostrarAlerta("Error", "No se pudo realizar el registro", Alert.AlertType.ERROR);
                                        }
                                    });
                                } catch (NumberFormatException ex) {
                                    mostrarAlerta("Error", "Ingrese una edad válida", Alert.AlertType.ERROR);
                                }
                            });
                        });
                    });
                });
            });
        });

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Usuario usuario = db.login(username, password);
            if (usuario != null) {
                loginVBox.getChildren().clear();

                GridPane grid = crearGridUsuario(usuario);
                loginVBox.getChildren().add(grid);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.9), grid);
                tt.setFromY(-scene.getHeight());
                tt.setToY(0);
                tt.play();

                primaryStage.setScene(scene);
            } else {
                // Si las credenciales son incorrectas, mostrar alerta de error
                mostrarAlerta("Error de inicio de sesión", "Credenciales incorrectas", Alert.AlertType.ERROR);
            }
        });

        usernameField.setMaxWidth(150);
        usernameField.setPrefHeight(30);

        passwordField.setMaxWidth(150);
        passwordField.setPrefHeight(30);
    }
    private double obtenerCantidadADepositar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Depósito");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la cantidad a depositar:");
    
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Double.parseDouble(result.get());
            } catch (NumberFormatException e) {
                // Handle invalid input (non-numeric or other invalid formats)
                mostrarAlerta("Error", "Ingrese un número válido", Alert.AlertType.ERROR);
            }
        }
        return 0; // Return 0 if no valid input is provided
    }
    
    private GridPane crearGridUsuario(Usuario usuario) {

        GridPane infoGrid = new GridPane();
        infoGrid.setId("infoGrid");

        DatePicker calendar = new DatePicker(LocalDate.now());
        

        Image image = new Image("horizontal.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        infoGrid.add(imageView, 0, 0);

        /*StackPane gifContainer = new StackPane();
        Image gifImage = new Image("atajos.gif");
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setFitHeight(50);
        gifImageView.setFitWidth(50);

        Label textoLabel = new Label("Haz tus recargas en nuestro banco");
        textoLabel.setFont(Font.font(20));

        gifContainer.getChildren().addAll(gifImageView, textoLabel);

        infoGrid.add(gifContainer, 0, 0);/* */

        Label nombreLabel = new Label("Nombre:");
        Label dineroLabel = new Label("Dinero:");
        Label nombreValor = new Label(usuario.getNombre() + " " + usuario.getApellido());
        Label dineroValor = new Label(String.valueOf(usuario.getMontoCuenta()));

        Button depositoButton = new Button("Depósito");
        Button retiroButton = new Button("Retiro");

        depositoButton.getStyleClass().add("mi-boton");
        retiroButton.getStyleClass().add("mi-boton");

        depositoButton.setOnAction(e -> {
    // Crear un ComboBox para mostrar las cuentas del usuario
    ComboBox<CuentaInfo> cuentasComboBox = new ComboBox<>();
    cuentasComboBox.setPromptText("Seleccione una cuenta");

    // Obtener la lista de cuentas del usuario
    List<CuentaInfo> cuentasUsuario = db.obtenerInfoCuentasUsuario(usuario.getId());

    // Llenar el ComboBox con las cuentas del usuario
    cuentasComboBox.getItems().addAll(cuentasUsuario);

    // Crear un diálogo que contenga el ComboBox
    Dialog<CuentaInfo> dialog = new Dialog<>();
    dialog.setTitle("Seleccione la cuenta");
    dialog.setHeaderText(null);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    dialog.getDialogPane().setContent(cuentasComboBox);

    // Mostrar el diálogo y esperar a que el usuario seleccione una cuenta o cancele
    Optional<CuentaInfo> selectedAccount = dialog.showAndWait();
    if (selectedAccount.isPresent()) {
        CuentaInfo cuentaSeleccionada = selectedAccount.get();
        double cantidad = obtenerCantidadADepositar(); // Esta función debe obtener la cantidad a depositar

        if (cantidad > 0) {
            // Realizar el depósito en la cuenta seleccionada
            boolean depositoExitoso = db.realizarDeposito(cuentaSeleccionada.getId(), cantidad);
            if (depositoExitoso) {
                // Actualizar la interfaz con el nuevo saldo
                dineroValor.setText(String.valueOf(usuario.getMontoCuenta() + cantidad));
            } else {
                mostrarAlerta("Error", "No se pudo realizar el depósito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Ingrese una cantidad válida", Alert.AlertType.ERROR);
        }
    }
});

retiroButton.setOnAction(e -> {
    // Obtener la lista de cuentas del usuario actual
    List<CuentaInfo> cuentasUsuario = db.obtenerInfoCuentasUsuario(usuario.getId());

    // Crear un diálogo de selección de cuenta
    ChoiceDialog<CuentaInfo> dialog = new ChoiceDialog<>(cuentasUsuario.get(0), cuentasUsuario);
    dialog.setTitle("Selección de Cuenta para Retiro");
    dialog.setHeaderText("Seleccione la cuenta de la cual desea realizar el retiro:");
    dialog.setContentText("Cuentas disponibles:");

    Optional<CuentaInfo> result = dialog.showAndWait();
    result.ifPresent(cuentaSeleccionada -> {
        TextInputDialog amountDialog = new TextInputDialog();
        amountDialog.setTitle("Retiro");
        amountDialog.setHeaderText(null);
        amountDialog.setContentText("Ingrese la cantidad a retirar:");

        Optional<String> amountResult = amountDialog.showAndWait();
        amountResult.ifPresent(cantidadStr -> {
            double cantidad = Double.parseDouble(cantidadStr);
            if (cantidad > 0 && cantidad <= cuentaSeleccionada.getMonto()) {
                boolean retiroExitoso = db.realizarRetiro(cuentaSeleccionada.getId(), cantidad);
                if (retiroExitoso) {
                    dineroValor.setText(String.valueOf(cuentaSeleccionada.getMonto() - cantidad));
                } else {
                    mostrarAlerta("Error", "No se pudo realizar el retiro", Alert.AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error", "Ingrese una cantidad válida", Alert.AlertType.ERROR);
            }
        });
    });
});
        Button transferirButton = new Button("Transferir");
        transferirButton.getStyleClass().add("mi-boton");
        transferirButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Transferir");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese el nombre de usuario del destinatario:");

            Optional<String> destinatarioResult = dialog.showAndWait();
            destinatarioResult.ifPresent(destinatarioUsername -> {
                TextInputDialog cantidadDialog = new TextInputDialog();
                cantidadDialog.setTitle("Transferir");
                cantidadDialog.setHeaderText(null);
                cantidadDialog.setContentText("Ingrese la cantidad a transferir:");

                Optional<String> cantidadResult = cantidadDialog.showAndWait();
                cantidadResult.ifPresent(cantidadStr -> {
                    Double cantidad = Double.parseDouble(cantidadStr);
                    if (cantidad > 0) {
                        boolean transferenciaExitosa = db.transferirDinero(usuario, destinatarioUsername, cantidadStr);
                        if (transferenciaExitosa) {
                            dineroValor.setText(String.valueOf(usuario.getMontoCuenta() - cantidad));
                            mostrarAlerta("Exito", "La transferencia se completó con éxito",
                                    Alert.AlertType.INFORMATION);
                        } else {
                            mostrarAlerta("Error", "No se pudo realizar la transferencia", Alert.AlertType.ERROR);
                        }
                    } else {
                        mostrarAlerta("Error", "Ingrese una cantidad válida", Alert.AlertType.ERROR);
                    }
                });
            });
        });

        infoGrid.add(transferirButton, 7, 3);
        infoGrid.add(nombreLabel, 0, 1);
        infoGrid.add(nombreValor, 1, 1);
        infoGrid.add(dineroLabel, 0, 2);
        infoGrid.add(dineroValor, 1, 2);
        infoGrid.add(depositoButton, 0, 3);
        infoGrid.add(retiroButton, 1, 3);
        infoGrid.add(calendar, 0, 4);

        GridPane.setMargin(retiroButton, new Insets(10));

        return infoGrid;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.getDialogPane().getStylesheets().add("style.css");

        ButtonType aceptarButtonType = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().setAll(aceptarButtonType);

        Button aceptarButton = (Button) alert.getDialogPane().lookupButton(aceptarButtonType);
        aceptarButton.getStyleClass().add("aceptar-button");

        Button cancelarButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelarButton.getStyleClass().add("cancelar-button");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


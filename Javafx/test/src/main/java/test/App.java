package test;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private base_de_datos db = new base_de_datos(); // Instancia de la clase BaseDeDatos

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inicio de Sesión");

        // Crea un ImageView para tu logo
        Image logoImage = new Image("negativo-horizontal.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitHeight(100); // Establece el alto de la imagen a 50
        logoView.setFitWidth(200); // Establece el ancho de la imagen a 50

        // Crear elementos de la interfaz de usuario
        Label usernameLabel = new Label("Usuario:");
        usernameLabel.getStyleClass().add("white-label"); // Aplica la clase CSS
        TextField usernameField = new TextField();
        usernameField.getStyleClass().add("white-field"); // Aplica la clase CSS

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.getStyleClass().add("white-label"); // Aplica la clase CSS
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("white-field"); // Aplica la clase CSS

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.getStyleClass().add("transparent-button"); // Añade la clase CSS

        Button registrarButton = new Button("Crear cuenta");
        registrarButton.getStyleClass().add("crear-cuenta-button"); // Añade la clase CSS

        HBox hbox = new HBox(10); // 10 es el espaciado horizontal entre los elementos
        hbox.setAlignment(Pos.CENTER); // Alinea los elementos al centro horizontalmente
        hbox.getChildren().addAll(loginButton, registrarButton);

        VBox vbox = new VBox(10); // 10 es el espaciado vertical entre los elementos
        vbox.setAlignment(Pos.CENTER); // Alinea los elementos al centro verticalmente
        vbox.getChildren().addAll(logoView, usernameLabel, usernameField, passwordLabel, passwordField, hbox);

        Scene scene = new Scene(vbox, 600, 300); // Ajusta el tamaño según tus necesidades
        scene.getStylesheets().add("style.css"); // Carga el archivo CSS
        vbox.getStyleClass().add("root");

        primaryStage.setScene(scene);
        primaryStage.show();

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
                mostrarInformacionUsuario(usuario);
                primaryStage.hide();
            } else {
                mostrarAlerta("Error de inicio de sesión", "Credenciales incorrectas", Alert.AlertType.ERROR);
            }
        });

        usernameField.setMaxWidth(150);
        usernameField.setPrefHeight(30);

        passwordField.setMaxWidth(150);
        passwordField.setPrefHeight(30);
    }

    public void mostrarInformacionUsuario(Usuario usuario) {
        Stage infoStage = new Stage();
        infoStage.setTitle("Información del Usuario");
    
        GridPane infoGrid = new GridPane();
        infoGrid.setPadding(new Insets(20, 20, 20, 20));
        infoGrid.setVgap(10);
        infoGrid.setHgap(10);
        infoGrid.setId("infoGrid");  // Asigna un id a la GridPane

        
    
        // Añade la imagen en la esquina superior izquierda
        Image image = new Image("horizontal.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50); // ajusta el alto de la imagen
        imageView.setFitWidth(50); // ajusta el ancho de la imagen
        infoGrid.add(imageView, 0, 0); // añade la imagen a la esquina superior izquierda
    
        Label nombreLabel = new Label("Nombre:");
        Label dineroLabel = new Label("Dinero:");
        Label nombreValor = new Label(usuario.getNombre() + " " + usuario.getApellido());
        Label dineroValor = new Label(String.valueOf(usuario.getDinero()));


    
        Button depositoButton = new Button("Depósito");
        Button retiroButton = new Button("Retiro");
    
        // Añade la clase CSS a los botones
        depositoButton.getStyleClass().add("mi-boton");
        retiroButton.getStyleClass().add("mi-boton");
    
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

        Button transferirButton = new Button("Transferir");
        transferirButton.getStyleClass().add("mi-boton");  // Añade la clase CSS
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
                            dineroValor.setText(String.valueOf(usuario.getDinero() - cantidad));
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

        Scene infoScene = new Scene(infoGrid, 400, 200);
        infoScene.getStylesheets().add("style.css"); // Añade tu archivo CSS a la escena
        infoStage.setScene(infoScene);
        infoStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
    
        // Agrega tu archivo CSS a la alerta
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

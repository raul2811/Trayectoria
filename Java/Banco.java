import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco extends Application {

    private List<Client> clients = new ArrayList<>();
    private int clientCount = 0;

    private TextField nameField = new TextField();
    private TextField idField = new TextField();
    private TextField depositField = new TextField();
    private TextField withdrawField = new TextField();
    private Label balanceLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking System");

        // Create layout components
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // Create buttons
        Button registerButton = new Button("Register Client");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button balanceButton = new Button("Check Balance");
        Button exitButton = new Button("Exit");

        // Register Client button action
        registerButton.setOnAction(e -> registerClient());

        // Deposit button action
        depositButton.setOnAction(e -> deposit());

        // Withdraw button action
        withdrawButton.setOnAction(e -> withdraw());

        // Check Balance button action
        balanceButton.setOnAction(e -> checkBalance());

        // Exit button action
        exitButton.setOnAction(e -> saveAndExit(primaryStage));

        // Create labels and text fields
        Label nameLabel = new Label("Name:");
        Label idLabel = new Label("ID:");
        Label depositLabel = new Label("Deposit Amount:");
        Label withdrawLabel = new Label("Withdraw Amount:");

        // Add components to the layout
        layout.getChildren().addAll(nameLabel, nameField, idLabel, idField, registerButton,
                depositLabel, depositField, depositButton, withdrawLabel, withdrawField, withdrawButton,
                balanceButton, balanceLabel, exitButton);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);

        // Load client data from a file (if available)
        loadClientData();

        // Show the stage
        primaryStage.show();
    }

    // Register a new client
    private void registerClient() {
        String name = nameField.getText();
        String id = idField.getText();

        if (!name.isEmpty() && !id.isEmpty()) {
            Client client = new Client(name, id);
            clients.add(client);
            clientCount++;

            // Clear input fields
            nameField.clear();
            idField.clear();

            showAlert("Client Registered", "Client registration successful.");
        } else {
            showAlert("Error", "Name and ID fields cannot be empty.");
        }
    }

    // Deposit funds into a client's account
    private void deposit() {
        Client client = getClientById(idField.getText());

        if (client != null) {
            try {
                double amount = Double.parseDouble(depositField.getText());

                if (amount > 0) {
                    client.deposit(amount);
                    depositField.clear();
                    showAlert("Deposit Successful", "Deposit of $" + amount + " successful.");
                } else {
                    showAlert("Error", "Please enter a valid deposit amount.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid numeric amount.");
            }
        } else {
            showAlert("Error", "Client with the provided ID not found.");
        }
    }

    // Withdraw funds from a client's account
    private void withdraw() {
        Client client = getClientById(idField.getText());

        if (client != null) {
            try {
                double amount = Double.parseDouble(withdrawField.getText());

                if (amount > 0 && client.getBalance() >= amount) {
                    client.withdraw(amount);
                    withdrawField.clear();
                    showAlert("Withdrawal Successful", "Withdrawal of $" + amount + " successful.");
                } else {
                    showAlert("Error", "Invalid withdrawal amount or insufficient balance.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid numeric amount.");
            }
        } else {
            showAlert("Error", "Client with the provided ID not found.");
        }
    }

    // Check the balance of a client's account
    private void checkBalance() {
        Client client = getClientById(idField.getText());

        if (client != null) {
            balanceLabel.setText("Balance: $" + client.getBalance());
        } else {
            showAlert("Error", "Client with the provided ID not found.");
        }
    }

    // Save client data to a file and exit
    private void saveAndExit(Stage stage) {
        saveClientData();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Do you want to exit the application?");
        alert.setContentText("Unsaved data will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }

    // Load client data from a file
    private void loadClientData() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("clientData.dat"))) {
            clients = (List<Client>) input.readObject();
            clientCount = clients.size();
        } catch (IOException | ClassNotFoundException e) {
            // Ignore errors when loading data
        }
    }

    // Save client data to a file
    private void saveClientData() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("clientData.dat"))) {
            output.writeObject(clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a client object by ID
    private Client getClientById(String id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    // Show a simple alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Client implements Serializable {
        private String name;
        private String id;
        private double balance;

        public Client(String name, String id) {
            this.name = name;
            this.id = id;
            this.balance = 0;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }
    }
}
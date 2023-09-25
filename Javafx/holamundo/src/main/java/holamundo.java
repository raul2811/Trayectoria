import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class holamundo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a label with the text "Hello, World!"
        Label helloLabel = new Label("Hello, World!");

        // Create a layout (a StackPane) and add the label to it
        StackPane root = new StackPane();
        root.getChildren().add(helloLabel);

        // Create a scene with the layout
        Scene scene = new Scene(root, 300, 200);

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Hello, World!");

        // Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


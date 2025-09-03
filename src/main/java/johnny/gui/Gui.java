package johnny.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import johnny.guiElements.DialogBox;
import johnny.guiElements.MainWindow;
import johnny.bot.Johnny;

public class Gui extends Application {

    private Johnny johnny = new Johnny("data/tasks.txt");

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJohnny(johnny); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // // Setting up required components

        // // Make new scroll pane for messages to be displayed
        // // Dialog box for messages will be inside this scroll pane
        // scrollPane = new ScrollPane();
        // dialogContainer = new VBox();
        // scrollPane.setContent(dialogContainer);

        // // Stationary text box and sen button at the bottom of the screen
        // userInput = new TextField();
        // sendButton = new Button("Send");

        // // Make anchor pane to put elements like scroll pane containing dialog boxes
        // and
        // // text field and button
        // AnchorPane mainLayout = new AnchorPane();
        // mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // // UI settings and sizing
        // stage.setTitle("Johnny");
        // stage.setResizable(false);
        // stage.setMinHeight(600.0);
        // stage.setMinWidth(400.0);

        // mainLayout.setPrefSize(400.0, 600.0);

        // scrollPane.setPrefSize(385, 535);
        // scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // scrollPane.setVvalue(1.0);
        // scrollPane.setFitToWidth(true);

        // dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // userInput.setPrefWidth(325.0);

        // sendButton.setPrefWidth(55.0);

        // AnchorPane.setTopAnchor(scrollPane, 1.0);

        // AnchorPane.setBottomAnchor(sendButton, 1.0);
        // AnchorPane.setRightAnchor(sendButton, 1.0);

        // AnchorPane.setLeftAnchor(userInput, 1.0);
        // AnchorPane.setBottomAnchor(userInput, 1.0);

        // // Handling user input
        // // Make event handlers for the button and text field with the same callback
        // sendButton.setOnMouseClicked((event) -> {
        // handleUserInput();
        // });

        // userInput.setOnAction((event) -> {
        // handleUserInput();
        // });

        // // Set the scene for the stage as main layout
        // scene = new Scene(mainLayout);

        // stage.setScene(scene);
        // stage.show();

        // // Auto scroll and show the latest dialog box
        // dialogContainer.heightProperty().addListener((observable ->
        // scrollPane.setVvalue(1.0)));
    }

    /**
     * Creates 2 dialog boxes in the scroll pane.
     * This dialog box displays user input and Johnny's output to the user input.
     * Clears the user input after processing
     * so that user can type in another command afterwards.
     */

    /**
     * private void handleUserInput() {
     * // User input and Johnny's output
     * String userMessage = userInput.getText();
     * String johnnyMessage = johnny.mirrorInput(userMessage);
     * 
     * // Gets children of container, adds new dialog box with user input + pic
     * dialogContainer.getChildren().addAll(
     * DialogBox.getUserDialog(userMessage, userImage),
     * DialogBox.getJohnnyDialog(johnnyMessage, johnnyImage));
     * 
     * // Clears user input for them to type again
     * userInput.clear();
     * }
     */

}

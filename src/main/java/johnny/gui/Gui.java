package johnny.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import johnny.bot.Johnny;
import johnny.guielements.MainWindow;

public class Gui extends Application {

    private Johnny johnny = new Johnny("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJohnny(johnny); // inject the Johnny instance
            fxmlLoader.<MainWindow>getController().greeting(); // Show dialog box with greeting
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

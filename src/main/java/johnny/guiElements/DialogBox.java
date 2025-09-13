package johnny.guielements;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for DialogBox, instantiate using factory methods
     * getUserDialog or getJohnnyDialog.
     * Takes in the text to be displayed in the dialog box, and the image
     * of the person/bot speaking.
     * 
     * @param text Text to be displayed. It is either the user input
     *             or Johnny's output
     * @param img  Image of either the user or Johnny in the dialog box
     */
    private DialogBox(String text, Image img) {
        try {
            // Create new fxmlLoader. This will be the controller for the
            // format and hierarchy specified in the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        /**
         * Styling the dialog box, deprecated code.
         * Using XML for formatting now.
         * 
         * text.setWrapText(true);
         * displayPicture.setFitWidth(100.0);
         * displayPicture.setFitHeight(100.0);
         * this.setAlignment(Pos.TOP_RIGHT);
         * this.getChildren().addAll(text, displayPicture);
         * 
         */

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getJohnnyDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

package Theme_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NetChatController {

    @FXML
    private void sendMessage(ActionEvent event) {
//        System.out.println(textField.getCharacters());
        textArea.appendText(textField.getText() + "\n");
        textField.setText("");
    }

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;
}

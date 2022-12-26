package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class SignUpController {
    public Button xButton;
    public Button minimizeButton;


    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)xButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setContentText("Da li želite izaći?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get().getButtonData().isDefaultButton())
            stage.close();
    }

    public void minimizeAction(ActionEvent actionEvent) {

        Stage stage = (Stage)minimizeButton.getScene().getWindow();
        minimizeButton.setOnAction(e -> {
            ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
        });
    }
}

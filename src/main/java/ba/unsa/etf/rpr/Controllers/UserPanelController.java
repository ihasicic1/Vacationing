package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserPanelController {

    public Button createYourAdventureId;
    public Button locationsId;
    public Button logOutId;
    public BorderPane userPanel;

    public void createYourAdventureAction(ActionEvent actionEvent) {
        Stage s = (Stage) locationsId.getScene().getWindow();
        s.close();
        openDialog("Locations", "/fxml/createYourAdventure.fxml", null);
    }

    public void locationsAction(ActionEvent actionEvent) {
        Stage s = (Stage) locationsId.getScene().getWindow();
        s.close();
        openDialog("Locations", "/fxml/locations.fxml", null);
    }

    public void logOutAction(ActionEvent actionEvent) {
        Stage s = (Stage) locationsId.getScene().getWindow();
        s.close();
        openDialog("Log In", "/fxml/login.fxml", null);
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void myBookingsAction(ActionEvent actionEvent) {
        Stage s = (Stage) locationsId.getScene().getWindow();
        s.close();
        openDialog("My Bookings", "/fxml/myBookingsPanel.fxml", null);
    }
}

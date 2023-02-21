package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.util.List;

public class AddTourController {
    public TextField destinationId;
    public TextField priceId;
    public Button confirmButtonId;
    public Button cancelButtonId;

    TourManager tourManager = new TourManager();
    public void confirmAction(ActionEvent actionEvent) throws MyException {
        List<Tour> tourList = tourManager.getAll();
        if(destinationId.getText().isEmpty() || priceId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Input is not correct!");
            alert.setHeaderText(null);
            alert.show();
        }
        else{
            Tour tour = new Tour();
            tour.setDestination(destinationId.getText());
            tour.setPrice(Double.valueOf(priceId.getText()));
            tourManager.add(tour);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully added new tour!");
            alert.setHeaderText(null);
            alert.showAndWait();
            Stage s = (Stage) confirmButtonId.getScene().getWindow();
            s.close();
            openDialog("Admin Panel", "/fxml/adminPanel.fxml", null);
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage s = (Stage) cancelButtonId.getScene().getWindow();
        s.close();
        openDialog("Admin Panel", "/fxml/adminPanel.fxml", null);
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}

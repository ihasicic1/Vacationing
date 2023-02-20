package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.email;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class createYourAdventureController {

    public ChoiceBox<String> destinationId;
    public Spinner<Integer> numberOfTicketsId;
    public DatePicker dateId;
    public Button cancelId;
    public Button confirmId;
    public Label priceId;

    int currentValue;

    TourManager tourManager = new TourManager();

    public void chooseDestinationAction(MouseEvent mouseEvent) {
    }

    public void displayPriceAction(ActionEvent actionEvent) {
    }

    public void pickADateAction(ActionEvent actionEvent) {
    }

    public void confirmAction(ActionEvent actionEvent) throws MyException {

    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage s = (Stage) cancelId.getScene().getWindow();
        s.close();
        openDialog("User Panel", "/fxml/userPanel.fxml", null);
    }

    public void initialize() throws MyException {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        numberOfTicketsId.setValueFactory(valueFactory);


        List<Tour> tourList = tourManager.getAll();
        for(Tour t: tourList){
            destinationId.getItems().add(t.getDestination());
        }
        destinationId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            try {
                priceId.setText(String.valueOf(tourManager.searchByDestination(newValue).getPrice()));
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
            numberOfTicketsId.valueProperty().addListener((obs1, oldValue1, newValue1) -> {
                try {
                        priceId.setText(String.valueOf(tourManager.searchByDestination(newValue).getPrice() * newValue1));
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        numberOfTicketsId.valueProperty().addListener((obs1, oldValue1, newValue1) -> {
            destinationId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                try {
                    priceId.setText(String.valueOf(tourManager.searchByDestination(newValue).getPrice() * newValue1));
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });
        });




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

}
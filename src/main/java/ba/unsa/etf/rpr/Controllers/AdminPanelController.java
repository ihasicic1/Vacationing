package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdminPanelController {

    public Button signOutButton;
    public Tab DashboardTabId;
    public Tab CustomersTabId;
    public Tab BookingsTabId;
    public Tab ToursTabId;
    public TableColumn<Tour, Integer> idColumn;
    public TableColumn<Tour, String> destinationColumn;
    public TableColumn<Tour, Double> priceColumn;
    public Button dashboardId;
    public Button customersId;
    public Button bookingsId;
    public Button toursId;
    public TableView<Tour> toursListId;
    public TabPane TabPaneId;
    public Button logOutId;

    TourManager tourManager = new TourManager();


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Tour, Integer>("id"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Tour, String>("destination"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Tour, Double>("price"));
        refreshTour();
    }

    void refreshTour(){
        try{
            toursListId.setItems(FXCollections.observableList(tourManager.getAll()));
            toursListId.refresh();
        } catch (MyException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void dashboardIdAction(ActionEvent actionEvent) {
        TabPaneId.getSelectionModel().select(0);
    }

    public void customersIdAction(ActionEvent actionEvent) {
        TabPaneId.getSelectionModel().select(1);
    }

    public void bookingsIdAction(ActionEvent actionEvent) {
        TabPaneId.getSelectionModel().select(2);
    }

    public void toursIdAction(ActionEvent actionEvent) {
        TabPaneId.getSelectionModel().select(3);
    }

    public void logOutIdAction(ActionEvent actionEvent) throws IOException {
        Stage s = (Stage) signOutButton.getScene().getWindow();
        s.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}

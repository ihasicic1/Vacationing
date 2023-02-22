package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.BookingManager;
import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MyBookingsController {
    public TableView<Booking> myBookingsListTable;
    public TableColumn<Booking, Tour> destinationColumn;
    public TableColumn<Booking, Double> priceColumn;
    public TableColumn<Booking, Date> dateColumn;
    public Button backButtonId;
    BookingManager bookingManager = new BookingManager();
    CustomerManager customerManager = new CustomerManager();

    public void initialize() throws MyException, IOException {

        destinationColumn.setCellValueFactory(new PropertyValueFactory<Booking, Tour>("tourId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Booking, Double>("ticket_price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Booking, Date>("date"));
        refreshBookings();
    }

    void refreshBookings() throws MyException, IOException {
        try{
           myBookingsListTable.setItems(FXCollections.observableList(bookingManager.searchByCustomer(customerManager.getByEmail(LoginController.getEmail()))));
           myBookingsListTable.refresh();
        }catch(MyException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void backAction(ActionEvent actionEvent) {
        Stage s = (Stage) backButtonId.getScene().getWindow();
        s.close();
        openDialog("User Panel", "/fxml/userPanel.fxml", null);
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

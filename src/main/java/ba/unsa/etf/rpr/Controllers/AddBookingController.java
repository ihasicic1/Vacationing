package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.BookingManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddBookingController {
    public TextField ticketPriceId;
    public TextField tourId;
    public TextField dateId;
    public TextField customerId;
    public Button cancelButtonId;
    public Button confirmButtonId;
    public DatePicker datePickerId;

    BookingManager bookingManager = new BookingManager();
    public void confirmAction(ActionEvent actionEvent) throws MyException {
        List<Booking> bookingList = bookingManager.getAll();
        if(ticketPriceId.getText().isEmpty() || tourId.getText().isEmpty() || customerId.getText().isEmpty() || datePickerId.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Input is not correct!");
            alert.setHeaderText(null);
            alert.show();
        }
        else{
            Booking booking = new Booking();
            java.sql.Date date = Date.valueOf(datePickerId.getValue());
            booking.setTicket_price(Double.valueOf(ticketPriceId.getText()));
            booking.setTourId(Integer.parseInt(tourId.getText()));
            booking.setCustomerId(Integer.parseInt(customerId.getText()));
            booking.setDate(date);
            bookingManager.add(booking);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully added new customer!");
            alert.setHeaderText(null);
            alert.showAndWait();
            Stage s = (Stage) confirmButtonId.getScene().getWindow();
            s.close();
            openDialog("Admin Panel", "/fxml/adminPanel.fxml", null);
        }
    }

    public void initialize(){
        datePickerId.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                if(date != null){
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Field can not be empty!");
                    alert.show();
                }
            }
        });
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

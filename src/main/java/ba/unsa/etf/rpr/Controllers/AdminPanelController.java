package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.BookingManager;
import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Booking;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AdminPanelController {

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
    public TableView<Booking> bookingListId;
    public TableColumn<Booking, Integer> bookingIdColumn;
    public TableColumn<Booking, Double> bookingTicketPriceColumn;
    public TableColumn<Booking, Integer> bookingTourIdColumn;
    public TableColumn<Booking, Integer> bookingCustomerIdColumn;
    public TableColumn<Booking, Date> bookingDateColumn;
    public TableView<Customer> customerListId;
    public TableColumn<Customer, Integer> customerIdColumn;
    public TableColumn<Customer, String> customerFirstNameColumn;
    public TableColumn<Customer, String> customerLastNameColumn;
    public TableColumn<Customer, String> customerGenderColumn;
    public TableColumn<Customer, String> customerPhoneNumberColumn;
    public TableColumn<Customer, String> customerEmailColumn;
    public TableColumn<Customer, String> customerPasswordColumn;
    public Button customersAddButtonId;
    public Button customersDeleteButtonId;
    public Button bookingsAddButtonId;
    public Button bookingsDeleteButtonId;
    public Button toursAddButtonId;
    public Button toursDeleteButtonId;

    TourManager tourManager = new TourManager();
    BookingManager bookingManager = new BookingManager();
    CustomerManager customerManager = new CustomerManager();


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Tour, Integer>("id"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Tour, String>("destination"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Tour, Double>("price"));
        refreshTours();

        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("id"));
        bookingTicketPriceColumn.setCellValueFactory(new PropertyValueFactory<Booking, Double>("ticket_price"));
        bookingTourIdColumn.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("tourId"));
        bookingCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("customerId"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, Date>("date"));
        refreshBookings();

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("first_name"));
        customerLastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("last_name"));
        customerGenderColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("gender"));
        customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone_number"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        customerPasswordColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        refreshCustomers();
    }

    void refreshCustomers(){
        try {
            customerListId.setItems(FXCollections.observableList(customerManager.getAll()));
            customerListId.refresh();
        } catch (MyException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    void refreshTours(){
        try{
            toursListId.setItems(FXCollections.observableList(tourManager.getAll()));
            toursListId.refresh();
        } catch (MyException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    void refreshBookings(){
        try {
            bookingListId.setItems(FXCollections.observableList(bookingManager.getAll()));
            bookingListId.refresh();
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
        Stage s = (Stage) logOutId.getScene().getWindow();
        s.close();
        openDialog("Log In", "/fxml/login.fxml", null);
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

    public void deleteCustomerAction(ActionEvent actionEvent) throws MyException {
        Customer customer = (Customer) customerListId.getSelectionModel().getSelectedItem();
        List<Booking> bookingList = bookingManager.getAll();
        if(customer != null && !checkBooking(bookingList, customer.getId())){
            customerManager.delete(customer.getId());
            refreshCustomers();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("You can not delete a customer with booked destination! Delete bookings first!");
            alert.showAndWait();
        }
    }

    /**
     * method for checking if a customer that is set to be deleted has a destination booked
     * @param bookingList
     * @param id
     * @return
     * @throws MyException
     */
    public static boolean checkBooking(List<Booking> bookingList, int id) throws MyException {
        for(Booking b: bookingList){
            if(b.getCustomerId() == id) return true;
        }
        return false;
    }

    public void addCustomerAction(ActionEvent actionEvent) throws MyException {
        openDialog("Add Customer", "/fxml/addCustomerPanel.fxml", null);
        Stage s = (Stage) customersAddButtonId.getScene().getWindow();
        s.close();
    }

    public static boolean checkPhoneNumber(List<Customer> customerList, String phoneNumber){
        for(Customer c: customerList){
            if(c.getPhone_number().equals(phoneNumber)) return true;
        }
        return false;
    }

    public static boolean checkEmail(List<Customer> customerList, String email){
        for(Customer c: customerList){
            if(c.getEmail().equals(email)) return true;
        }
        return false;
    }


    public void addBookingAction(ActionEvent actionEvent) {
        openDialog("Add Customer", "/fxml/addBookingPanel.fxml", null);
        Stage s = (Stage) bookingsAddButtonId.getScene().getWindow();
        s.close();
    }

    public void deleteBookingAction(ActionEvent actionEvent) throws MyException {
        Booking booking = (Booking) bookingListId.getSelectionModel().getSelectedItem();
        if(booking != null){
            bookingManager.delete(booking.getId());
            refreshBookings();
        }
    }

    public void addTourAction(ActionEvent actionEvent) {
        openDialog("Add Customer", "/fxml/addTourPanel.fxml", null);
        Stage s = (Stage) toursAddButtonId.getScene().getWindow();
        s.close();
    }

    public void deleteTourAction(ActionEvent actionEvent) throws MyException {
        Tour tour = (Tour) toursListId.getSelectionModel().getSelectedItem();
        if(tour != null){
            tourManager.delete(tour.getId());
            refreshTours();
        }
    }
}

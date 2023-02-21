package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class AddCustomerController {

    public TextField firstNameId;
    public TextField lastNameId;
    public TextField genderId;
    public TextField phoneNumberId;
    public TextField emailId;
    public TextField passwordId;
    public Button cancelButtonId;
    CustomerManager customerManager = new CustomerManager();

    public void confirmAction(ActionEvent actionEvent) throws MyException {
        List<Customer> customerList = customerManager.getAll();
        if(firstNameId.getText().isEmpty() || lastNameId.getText().isEmpty() || genderId.getText().isEmpty() || phoneNumberId.getText().isEmpty() || emailId.getText().isEmpty() || passwordId.getText().isEmpty() || AdminPanelController.checkPhoneNumber(customerList, phoneNumberId.getText()) || AdminPanelController.checkEmail(customerList, emailId.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Input is not correct!");
            alert.setHeaderText(null);
            alert.show();
        }
        else{
            Customer customer = new Customer();
            customer.setFirst_name(firstNameId.getText());
            customer.setLast_name(lastNameId.getText());
            customer.setGender(genderId.getText());
            customer.setPhone_number(phoneNumberId.getText());
            customer.setEmail(emailId.getText());
            customer.setPassword(passwordId.getText());
            customerManager.add(customer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully added new customer!");
            alert.setHeaderText(null);
            alert.showAndWait();
            Stage s = (Stage) firstNameId.getScene().getWindow();
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

package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    public Button xButton;
    public Button minimizeButton;
    public TextField firstNameId;
    public TextField lastNameId;
    public TextField emailId;
    public PasswordField passwordId;
    public TextField phoneNumberId;
    public Hyperlink loginHyper;
    public Label invalidFirstNameId;
    public Label invalidLastNameId;
    public Label invalidEmailId;
    public Label invalidPasswordId;
    public Label invalidNumberId;
    public RadioButton maleButton;
    public RadioButton femaleButton;
    public Button signUpButton;
    public ToggleGroup SpolOdabir;

    List<Customer> customers = DaoFactory.customerDao().getAll();

    public SignUpController() throws MyException {
    }

    //private CustomerDaoSQLImpl dao = (CustomerDaoSQLImpl) DaoFactory.customerDao();
   //private DaoFactory dao = new DaoFactory();


    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage)xButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Do You want to exit?");
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

    public void changeToLogin(ActionEvent actionEvent) throws IOException {
        Stage s = (Stage) emailId.getScene().getWindow();
        s.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void pickMaleAction(ActionEvent actionEvent) {

    }

    public void pickFemaleAction(ActionEvent actionEvent) {
    }

    public void signUpAction(ActionEvent actionEvent) {
        if(firstNameId.getText().isEmpty()){
            invalidFirstNameId.setText("First name field can not be empty!");
        } else{
            invalidFirstNameId.setText("");
        }

        if(lastNameId.getText().isEmpty()){
            invalidLastNameId.setText("Last name field can not be empty!");
        } else{
            invalidLastNameId.setText("");
        }

        if(emailId.getText().isEmpty()){
            invalidEmailId.setText("Email field can not be empty!");
        } else {
            boolean sameEmail = false;
            for(Customer cust: customers){
                if(cust.getEmail().equals(emailId.getText())){
                    sameEmail = true;
                    break;
                }
            }
            if(sameEmail) invalidEmailId.setText("Email is already taken!");
            if(!sameEmail && emailId.getLength() < 6) invalidEmailId.setText("Email must contain at least 6 characters!");
            else invalidEmailId.setText("");
        }

        if(passwordId.getText().isEmpty()){
            invalidPasswordId.setText("Password field can not be empty!");
        } else{
            if(passwordId.getLength() < 8) invalidPasswordId.setText("Password must contain at least 8 characters!");
            else invalidPasswordId.setText("");
        }

        if(phoneNumberId.getText().isEmpty()){
            invalidNumberId.setText("Phone number field can not be empty!");
        } else{
            boolean sameNumber = false;
            for(Customer cust: customers){
                if(cust.getPhone_number().equals(phoneNumberId.getText())){
                    sameNumber = true;
                    break;
                }
            }
            if(sameNumber) invalidNumberId.setText("Phone number is already taken!");
            else invalidNumberId.setText("");
        }

    }

    public void initalize(){

    }
}

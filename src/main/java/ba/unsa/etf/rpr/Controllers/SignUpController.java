package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomerManager;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    private final CustomerManager customerManager = new CustomerManager();
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

    public boolean checkEmail(String emailField){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailField);
        return matcher.matches();
    }

    public void signUpAction(ActionEvent actionEvent) throws MyException {
        boolean sameEmail = false;
        boolean samePhoneNumber = false;
        List<Customer> customers = DaoFactory.customerDao().getAll();
        for(Customer c: customers){
            if(c.getEmail().equals(emailId.getText())){
                sameEmail = true;
                break;
            }
            else if(c.getPhone_number().equals(phoneNumberId.getText())){
                samePhoneNumber = true;
                break;
            }
        }
        if(firstNameId.getText().isEmpty() || lastNameId.getText().isEmpty() || emailId.getText().isEmpty() || passwordId.getText().isEmpty() || phoneNumberId.getText().isEmpty() || !checkEmail(emailId.getText()) || sameEmail || samePhoneNumber){

            if(firstNameId.getText().isEmpty()){
                invalidFirstNameId.setText("Empty");
            }
            else if(!firstNameId.getText().isEmpty()){
                invalidFirstNameId.setText("");
            }

            if(lastNameId.getText().isEmpty()){
                invalidLastNameId.setText("Empty");
            }
            else if(!lastNameId.getText().isEmpty()){
                invalidLastNameId.setText("");
            }

            if(emailId.getText().isEmpty()){
                invalidEmailId.setText("Empty");
            }
            else if(!emailId.getText().isEmpty() && checkEmail(emailId.getText()) && sameEmail){
                invalidEmailId.setText("Email already exists!");
            }
            else if(!emailId.getText().isEmpty() && !checkEmail(emailId.getText()) && !sameEmail){
                invalidEmailId.setText("Invalid email format");
            }
            else if(!emailId.getText().isEmpty() && checkEmail(emailId.getText()) && !sameEmail){
                invalidEmailId.setText("");
            }

            if(passwordId.getText().isEmpty()){
                invalidPasswordId.setText("Empty");
            }
            else if(!passwordId.getText().isEmpty() && passwordId.getLength()<8){
                invalidPasswordId.setText("Password must contain at least 8 characters!");
            }
            else{
                invalidPasswordId.setText("");
            }

            if(phoneNumberId.getText().isEmpty()){
                invalidNumberId.setText("Empty");
            }
            else if(!phoneNumberId.getText().isEmpty() && samePhoneNumber){
                invalidNumberId.setText("Phone number already exists!");
            }
            else if(!phoneNumberId.getText().isEmpty() && !samePhoneNumber){
                invalidNumberId.setText("");
            }

        }
        else{
            Customer customer = new Customer();
            customer.setFirst_name(firstNameId.getText());
            customer.setLast_name(lastNameId.getText());
            customer.setEmail(emailId.getText());
            customer.setPassword(passwordId.getText());
            customer.setPhone_number(phoneNumberId.getText());
            if (maleButton.isSelected()) {
                customer.setGender("M");
            } else customer.setGender("F");

            try {
                customerManager.add(customer);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sign Up");
                alert.setHeaderText("Congratulations!");
                alert.setContentText("You have successfully signed up!");   //alert da sam uspjesno signed up i da me prebaci na login page
                alert.showAndWait();
                openDialog("Log In", "/fxml/login.fxml", null);
                Stage s = (Stage) emailId.getScene().getWindow();
                s.close();
            } catch (Exception ex) {
                throw new MyException(ex.getMessage(), ex);
            }
        }


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

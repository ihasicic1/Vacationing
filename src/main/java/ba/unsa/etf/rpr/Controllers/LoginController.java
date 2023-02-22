package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.dao.CustomerDao;
import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;
import com.fasterxml.jackson.core.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    private final CustomerManager customerManager = new CustomerManager();
    public Hyperlink signUpHyper;
    
    public Label invalidEmail;
    
    public Label invalidPassword;
    public Button loginButton;
    public TextField emailId;
    public PasswordField passwordId;
    public static String email;

    public LoginController(){
    }


    public void login(ActionEvent actionEvent) throws IOException {

        if(emailId.getText().isEmpty() && passwordId.getText().isEmpty()){
            invalidEmail.setText("Email field can not be empty!");
            invalidPassword.setText("Password field can not be empty!");
        }
        else if(!emailId.getText().isEmpty() && passwordId.getText().isEmpty()){
            invalidEmail.setText("");
            invalidPassword.setText("Password field can not be empty!");
        }
        else if(emailId.getText().isEmpty() && !passwordId.getText().isEmpty()){
            invalidEmail.setText("Email field can not be empty!");
            invalidPassword.setText("");
        }
        else if(!emailId.getText().isEmpty() && !passwordId.getText().isEmpty()){
            try{
                if(passwordId.getText().equals(customerManager.getByEmail(emailId.getText()).getPassword())){
                    if(emailId.getText().equals("ihasicic1@etf.unsa.ba")){
                        openDialog("AdminPanel", "/fxml/adminPanel.fxml", null);
                        Stage s = (Stage) loginButton.getScene().getWindow();
                        s.close();
                        //dodaj da jedna labela u admin panelu govori welcome "Ilhan"
                    }
                    else{
                        openDialog("UserPanel", "/fxml/userPanel.fxml", null);
                        email = emailId.getText();
                        //dodaj da jedna labela kaze welcome "first name"
                        Stage s = (Stage) loginButton.getScene().getWindow();
                        s.close();
                    }
                }
                else{
                    invalidPassword.setText("Email or password are not correct!");
                }
            } catch (MyException e) {
                invalidPassword.setText("Email or password are not correct!");
            }
        }

    }

    public static String getEmail(){
        return email;
    }


    public void changeToSignupWin(ActionEvent actionEvent) throws IOException {
        Stage s = (Stage) emailId.getScene().getWindow();
        s.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/signUp.fxml"));
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
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

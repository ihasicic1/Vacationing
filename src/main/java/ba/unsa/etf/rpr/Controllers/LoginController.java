package ba.unsa.etf.rpr.Controllers;

import com.fasterxml.jackson.core.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {


    public Hyperlink signUpHyper;
    
    public Label invalidEmail;
    
    public Label invalidPassword;
    public Button loginButton;
    public TextField emailId;
    public PasswordField passwordId;

    public LoginController(){
    }


    public void login(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminPanel.fxml"));
        stage.setTitle("Admin");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    public void initialize(){
        emailId.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue.length() < 5) invalidEmail.setText("Invalid email!");
            else invalidEmail.setText("");

        });

        passwordId.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue.length() < 8) invalidPassword.setText("Password must contain at least 8 characters!");
            else invalidPassword.setText("");
        });

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


}

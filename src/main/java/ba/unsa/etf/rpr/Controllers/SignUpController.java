package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    public Button xButton;
    public Button minimizeButton;
    public Hyperlink loginHyper;
    public TextField firstNameId;
    public TextField lastNameId;
    public TextField emailId;
    public PasswordField passwordId;
    public TextField phoneNumberId;

   //private CustomerDaoSQLImpl dao = (CustomerDaoSQLImpl) DaoFactory.customerDao();
   private DaoFactory dao = new DaoFactory();


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
        Stage s = (Stage) firstNameId.getScene().getWindow();
        s.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}

package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mft.controller.CustomerController;
import mft.model.entity.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFrameController implements Initializable {
    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private Label msgLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        loginBtn.setOnAction ((event) -> {
            try {
                Customer customer = (Customer) CustomerController.getController().findByUsernameAndPassword(
                        usernameTxt.getText(),
                        passwordTxt.getText()
                );
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getResource("loginFrame.fxml"))
                );
//todo
                stage.setScene(scene);
                stage.setTitle("");
                stage.show();
                resetForm();
                loginBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

    }
    public void resetForm() {
        try {
            usernameTxt.clear();
            passwordTxt.clear();
            msgLbl.setVisible(false);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

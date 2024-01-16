package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class SigninFrameController implements Initializable {
    @FXML
    private Button saveBtn, logBtn;

    @FXML
    private TextField idTxt, nameTxt, familyTxt, usernameTxt, phoneTxt, emailTxt;

    @FXML
    private TextArea addressTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ToggleGroup activeToggleGroup;

    @FXML
    private Label msgLbl, star1, star2;

    @FXML
    private RadioButton enableRdo, disableRdo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        saveBtn.setOnAction((event) -> {
            try {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
                Customer customer = CustomerController.getController().save(
                        nameTxt.getText(),
                        familyTxt.getText(),
                        usernameTxt.getText(),
                        passwordTxt.getText(),
                        addressTxt.getText(),
                        phoneTxt.getText(),
                        emailTxt.getText(),
                        (radioButton.getText().equals("Enable") ? true : false));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                resetFormError();
                msgLbl.setVisible(true);
                star1.setVisible(true);
                star2.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        logBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("loginFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("Log in");
                stage.show();
                resetForm();
                logBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });
    }

    public void resetForm() {
        try {
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();
            addressTxt.clear();
            emailTxt.clear();
            activeToggleGroup.getSelectedToggle();
            msgLbl.setVisible(false);
            star1.setVisible(false);
            star2.setVisible(false);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
    public void resetFormError() {
        try {
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}


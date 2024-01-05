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
    private Button saveBtn;

    @FXML
    private TextField idTxt, nameTxt, familyTxt, usernameTxt, phoneTxt, emailTxt;

    @FXML
    private TextArea addressTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ToggleGroup activeToggleGroup;

    @FXML
    private Label msgLbl;

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

//                Stage stage = new Stage();
//                Scene scene = new Scene(
//                        FXMLLoader.load(getClass().getResource("loginFrame.fxml"))
//                );
//
//                stage.setScene(scene);
//                stage.setTitle("Login");
//                stage.show();
//                saveBtn.getParent().getScene().getWindow().hide();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

    }

    public void resetForm() {
        try {
            idTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            phoneTxt.clear();
            activeToggleGroup.getSelectedToggle();
            msgLbl.setVisible(false);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}


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
    private Button loginBtn, signBtn, adminBtn;

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
                        FXMLLoader.load(getClass().getClassLoader().getResource("searchProductFrame.fxml"))
                );
                stage.setScene(scene);
                stage.setTitle("فروشگاه اینترنتی|مشاهده کالاها");
                stage.show();
                resetForm();
                loginBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        adminBtn.setOnAction ((event) -> {
            try {
                if (usernameTxt.getText().equals("admin") && passwordTxt.getText().equals("admin")) {
                    Stage stage = new Stage();
                    Scene scene = new Scene(
                            FXMLLoader.load(getClass().getClassLoader().getResource("ProductFrame.fxml"))
                    );
                    stage.setScene(scene);
                    stage.setTitle("دسترسی ادمین به بخش کالاها");
                    stage.show();
                    resetForm();
                    adminBtn.getParent().getScene().getWindow().hide();
                }else {
                    msgLbl.setVisible(true);
                }
            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });


        signBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("signinFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("Register Information");
                stage.show();
                resetForm();
                signBtn.getParent().getScene().getWindow().hide();

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

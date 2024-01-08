package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.CustomerController;
import mft.controller.ProductController;
import mft.model.entity.Customer;
import mft.model.entity.Products;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductFrameController implements Initializable {

    @FXML
    private Button saveBtn;

    @FXML
    private TextField idTxt, nameTxt, brandTxt, sizeTxt, priceTxt ;

    @FXML
    private TextArea descTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        saveBtn.setOnAction((event) -> {
            try {
                Products products = ProductController.getController().save(
                        nameTxt.getText(),
                        brandTxt.getText(),
                        sizeTxt.getText(),
                        Double.valueOf(priceTxt.getText()),
                        descTxt.getText());

//                Stage stage = new Stage();
//                Scene scene = new Scene(
//                        FXMLLoader.load(getClass().getResource("loginFrame.fxml"))
//                );
//
//                stage.setScene(scene);
//                stage.setTitle("Login");
//                stage.show();
//                saveBtn.getParent().getScene().getWindow().hide();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameTxt.clear();
            brandTxt.clear();
            sizeTxt.clear();
            priceTxt.clear();
            descTxt.clear();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}


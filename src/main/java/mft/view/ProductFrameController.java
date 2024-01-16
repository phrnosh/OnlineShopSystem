package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mft.controller.CustomerController;
import mft.controller.ProductController;
import mft.model.entity.Customer;
import mft.model.entity.Payment;
import mft.model.entity.Products;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFrameController implements Initializable {

    @FXML
    private Button saveBtn, editBtn, removeBtn, customerBtn, exitBtn;

    @FXML
    private TextField idTxt, nameTxt, brandTxt, sizeTxt, priceTxt, searchTxt ;

    @FXML
    private TextArea descTxt;

    @FXML
    private TableView<Products> productTbl;

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

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        resetForm();
        editBtn.setOnAction((event) -> {
            try {
                Products products = ProductController.getController().edit(
                        Integer.valueOf(idTxt.getText()),
                        nameTxt.getText(),
                        brandTxt.getText(),
                        sizeTxt.getText(),
                        Double.valueOf(priceTxt.getText()),
                        descTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product Edited");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Edit Error " + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction((event) -> {
            try {
                Products products = ProductController.getController().remove(Integer.valueOf(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product Removed");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Remove Error " + e.getMessage());
                alert.show();
            }
        });

        productTbl.setOnMouseClicked((event) -> {
            Products products = productTbl.getSelectionModel().getSelectedItem();

            idTxt.setText(String.valueOf(products.getId()));
            nameTxt.setText(products.getName());
            brandTxt.setText(products.getBrand());
            sizeTxt.setText(products.getSize());
            priceTxt.setText(String.valueOf(products.getPrice()));
            descTxt.setText(products.getDescription());
        });

        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(ProductController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        customerBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("customerFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("اطلاعات مشتریان");
                stage.show();
                resetForm();
                customerBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        exitBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("loginFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("Log in");
                stage.show();
                resetForm();
                exitBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

    }

    private void showDataOnTable(List<Products> productsList) {

        ObservableList<Products> products = FXCollections.observableList(productsList);

        productTbl.getColumns().clear();

        TableColumn<Products, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Products, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Products, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Products, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<Products, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Products, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        productTbl.getColumns().addAll(idCol, nameCol, brandCol, sizeCol, priceCol, descriptionCol);
        productTbl.setItems(products);
    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameTxt.clear();
            brandTxt.clear();
            sizeTxt.clear();
            priceTxt.clear();
            descTxt.clear();

            showDataOnTable(ProductController.getController().findAll());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}


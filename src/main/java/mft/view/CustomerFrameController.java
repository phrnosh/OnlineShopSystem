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
import mft.model.entity.Products;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFrameController implements Initializable {

    @FXML
    private Button searchBtn, productBtn;

    @FXML
    private TextField searchTxt ;

    @FXML
    private TableView<Customer> customerTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(CustomerController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        searchBtn.setOnAction((event) -> {
            try {
                showDataOnTable(CustomerController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        productBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("ProductFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("دسترسی ادمین به بخش کالاها");
                stage.show();
                resetForm();
                productBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

    }

    private void showDataOnTable(List<Customer> customerList) {

        ObservableList<Customer> customers = FXCollections.observableList(customerList);

        customerTbl.getColumns().clear();

        TableColumn<Customer, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> familyCol = new TableColumn<>("Family");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));

        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Customer, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Customer, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Customer, String> phoneNumberCol = new TableColumn<>("PhoneNumber");
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Customer, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Customer, Boolean> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        customerTbl.getColumns().addAll(idCol, nameCol, familyCol, usernameCol, passwordCol, addressCol, phoneNumberCol, emailCol, statusCol);
        customerTbl.setItems(customers);
    }

    public void resetForm() {
        try {
            showDataOnTable(CustomerController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

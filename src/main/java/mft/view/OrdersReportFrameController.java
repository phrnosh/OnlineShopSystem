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
import mft.controller.OrderController;
import mft.model.entity.Customer;
import mft.model.entity.Orders;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersReportFrameController implements Initializable {

    @FXML
    private Button searchBtn, productBtn;

    @FXML
    private TextField searchTxt ;

    @FXML
    private TableView<Orders> orderTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(OrderController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        searchBtn.setOnAction((event) -> {
            try {
                showDataOnTable(OrderController.getController().findByAll(searchTxt.getText()));

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

    private void showDataOnTable(List<Orders> ordersList) {

        ObservableList<Orders> orders = FXCollections.observableList(ordersList);

        orderTbl.getColumns().clear();

        TableColumn<Orders, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Orders, Integer> customerIdCol = new TableColumn<>("Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Orders, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Orders, String> familyCol = new TableColumn<>("Family");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Orders, Integer> productsCol = new TableColumn<>("Product Id");
        productsCol.setCellValueFactory(new PropertyValueFactory<>("orderDetails"));

        TableColumn<Orders, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Orders, Float> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<Orders, LocalDate> orderDateCol = new TableColumn<>("Order Date");
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        orderTbl.getColumns().addAll(idCol, customerIdCol, nameCol, familyCol, productsCol, amountCol, discountCol, orderDateCol);
        orderTbl.setItems(orders);
    }

    public void resetForm() {
        try {
            showDataOnTable(OrderController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

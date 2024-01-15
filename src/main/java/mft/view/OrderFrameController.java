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
import mft.controller.OrderController;
import mft.controller.OrderDetailsController;
import mft.controller.PaymentController;
import mft.model.entity.OrderDetails;
import mft.model.entity.Orders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFrameController implements Initializable {
    @FXML
    private Button backBtn, homeBtn;

    @FXML
    private TableView<Orders> orderTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        System.out.println(AppState.customer.getName());
//        try {
//            System.out.println(OrderController.getController().findByCustomerId(AppState.customer.getId()));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


    resetForm();
        backBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("OrderDetailFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("جزییات سفارش");
                stage.show();
                resetForm();
                backBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        homeBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("searchProductFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("مشاهده کالاها");
                stage.show();
                resetForm();
                homeBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });


    }

    private void showDataOnTable(List<Orders> ordersList) {

        ObservableList<Orders> orders = FXCollections.observableList(ordersList);

        orderTbl.getColumns().clear();

        TableColumn<Orders, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Orders, String> productNameCol = new TableColumn<>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Product_name"));

        TableColumn<Orders, String> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Orders, Boolean> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTbl.getColumns().addAll(idCol, productNameCol, quantityCol, priceCol);
        orderTbl.setItems(orders);
    }

    public void resetForm(){
        try {
            showDataOnTable(OrderController.getController().findAll());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

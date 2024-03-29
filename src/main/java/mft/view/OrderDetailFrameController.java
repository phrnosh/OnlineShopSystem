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
import mft.model.entity.AppState;
import mft.model.entity.OrderDetails;
import mft.model.entity.Orders;
import mft.model.entity.Payment;
import mft.model.entity.enums.OrderStatus;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDetailFrameController implements Initializable {

    @FXML
    private Label idLbl;

    @FXML
    private Button addBtn, removeBtn, homeBtn;

    @FXML
    private TableView<OrderDetails> orderTbl;

    @FXML
    private TableView<Payment> paymentTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(AppState.customer.getName());
        try {
            System.out.println(OrderController.getController().findByCustomerId(AppState.customer.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        resetForm();

        addBtn.setOnAction((event) -> {
            try {
                Orders orders = OrderController.getController().save(
                        AppState.customer.getId(),
                        OrderDetailsController.getController().findSumOrder(AppState.customer.getId()).getPrice(),
                        200000,
                        OrderStatus.New,
                        LocalDateTime.now());

                resetForm();
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("orderFrame.fxml"))
                );
                stage.setScene(scene);
                stage.setTitle("سفارشات");
                stage.show();
                resetForm();
                homeBtn.getParent().getScene().getWindow().hide();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Saved");
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });


        removeBtn.setOnAction((event) -> {
            try {
                OrderDetails orderDetails = OrderDetailsController.getController().remove(Integer.valueOf(idLbl.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Removed");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Remove Error " + e.getMessage());
                alert.show();
            }
        });

        orderTbl.setOnMouseClicked((event) -> {
            OrderDetails orderDetails = orderTbl.getSelectionModel().getSelectedItem();
            idLbl.setText(String.valueOf(orderDetails.getId()));
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
    private void showDataOnOrderTable(List<OrderDetails> orderDetailsList) {

        ObservableList<OrderDetails> orderDetails = FXCollections.observableList(orderDetailsList);

        orderTbl.getColumns().clear();

        TableColumn<OrderDetails, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrderDetails, Integer> customerIdCol = new TableColumn<>("ddCustomer Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<OrderDetails, Integer> productNameCol = new TableColumn<>("Product Id");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("products"));

        TableColumn<OrderDetails, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderDetails, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTbl.getColumns().addAll(idCol, customerIdCol, productNameCol, quantityCol, priceCol);
        orderTbl.setItems(orderDetails);
    }

    private void showDataOnPaymentTable(List<Payment> paymentList) {

        ObservableList<Payment> payments = FXCollections.observableList(paymentList);

        paymentTbl.getColumns().clear();

        TableColumn<Payment, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Payment, Integer> customerIdCol = new TableColumn<>("Customer Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Payment, Double> totalCostCol = new TableColumn<>("Total Cost");
        totalCostCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        TableColumn<Payment, String> paymentDetailsCol = new TableColumn<>("Payment Details");
        paymentDetailsCol.setCellValueFactory(new PropertyValueFactory<>("paymentDetails"));

        TableColumn<Payment, String> typeCol = new TableColumn<>("Payment Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("PaymentType"));

        TableColumn<Payment, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderType"));

        TableColumn<Payment, LocalDateTime> paymentDateCol = new TableColumn<>("Payment Date");
        paymentDateCol.setCellValueFactory(new PropertyValueFactory<>("PaymentTimeStamp"));

        paymentTbl.getColumns().addAll(idCol, customerIdCol, totalCostCol, paymentDetailsCol, typeCol, statusCol, paymentDateCol);
        paymentTbl.setItems(payments);
    }

    public void resetForm(){
        try {
            showDataOnOrderTable(OrderDetailsController.getController().findByCustomerId(AppState.customer.getId()));
            showDataOnPaymentTable(PaymentController.getController().findByCustomerId(AppState.customer.getId()));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }


}

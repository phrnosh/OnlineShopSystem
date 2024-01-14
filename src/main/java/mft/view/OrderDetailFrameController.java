package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.OrderDetailsController;
import mft.controller.PaymentController;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDetailFrameController implements Initializable {

    @FXML
    private Button addBtn, removeBtn;
//
    @FXML
    private TableView<OrderDetails> orderTbl;

    @FXML
    private TableView<Payment> paymentTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

//        addBtn.setOnAction((event) -> {
//            try {
//                OrderDetails orderDetails = OrderDetailsController.getController().save(
//                        Integer.valueOf(custTxt.getText()),
//                        1,
//                        Integer.valueOf(idTxt.getText()),
//                        Integer.valueOf(countTxt.getText()),
//                        Double.valueOf(priceTxt.getText()));
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Staff Saved");
//                alert.show();
//                resetForm();
//
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
//                alert.show();
//            }
//        });





//        removeBtn.setOnAction((event) -> {
//            try {
//                User user = UserController.getController().remove(Integer.valueOf(idTxt.getText()));
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Removed");
//                alert.show();
//                resetForm();
//
//            } catch (Exception e) {
//                msgLbl.setVisible(true);
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Remove Error " + e.getMessage());
//                alert.show();
//            }
//        });

//        orderTbl.setOnMouseClicked((event) -> {
//            OrderDetails orderDetails = orderTbl.getSelectionModel().getSelectedItem();
//
//            idTxt.setText(String.valueOf(user.getId()));
//            usernameTxt.setText(user.getUsername());
//            passwordTxt.setText(user.getPassword());
//            if(user.isActive()) {
//                enableRdo.fire();
//            }else{
//                disableRdo.fire();
//            }
//
//        });
    }
    private void showDataOnOrderTable(List<OrderDetails> orderDetailsList) {

        ObservableList<OrderDetails> orderDetails = FXCollections.observableList(orderDetailsList);

        orderTbl.getColumns().clear();

        TableColumn<OrderDetails, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrderDetails, Integer> customerIdCol = new TableColumn<>("Customer Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

//        TableColumn<OrderDetails, Integer> orderIdCol = new TableColumn<>("Order Id");
//        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("order"));

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

        TableColumn<Payment, Double> totalCostCol = new TableColumn<>("Total Cost");
        totalCostCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        TableColumn<Payment, String> paymentDetailsCol = new TableColumn<>(" Payment Details");
        paymentDetailsCol.setCellValueFactory(new PropertyValueFactory<>("paymentDetails"));

        TableColumn<Payment, PaymentType> typeCol = new TableColumn<>("Payment Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Payment, LocalDateTime> paymentDateCol = new TableColumn<>("Payment Date");
        paymentDateCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        paymentTbl.getColumns().addAll(idCol, totalCostCol, paymentDetailsCol, typeCol, paymentDateCol);
        paymentTbl.setItems(payments);
    }

    public void resetForm(){
        try {
            //TODO edit findAll
            showDataOnOrderTable(OrderDetailsController.getController().findAll());
            showDataOnPaymentTable(PaymentController.getController().findAll());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }


}

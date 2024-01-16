package mft.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import mft.controller.OrderController;
import mft.controller.OrderDetailsController;
import mft.controller.PaymentController;
import mft.model.entity.OrderDetails;
import mft.model.entity.Orders;
import mft.model.entity.Payment;
import mft.model.entity.enums.OrderStatus;
import mft.model.entity.enums.PaymentType;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFrameController implements Initializable {

    @FXML
    private Button backBtn, homeBtn, addBtn;

    @FXML
    private ComboBox<String> payCmb;

    @FXML
    private Label payLbl;

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

        addBtn.setOnAction((event) -> {
            try {
                Payment payment = PaymentController.getController().save(
                        OrderController.getController().findAmountOrder(42).getAmount(),
                        "تشکر از خرید شما",
                        payLbl.getText(),
                        LocalDateTime.now());

                resetForm();
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("orderDetailFrame.fxml"))
                );
                stage.setScene(scene);
                stage.setTitle("سبد کالا");
                stage.show();
                resetForm();
                homeBtn.getParent().getScene().getWindow().hide();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Saved");
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });


        ObservableList<String> options = FXCollections.observableArrayList(
                "OnlinePayment", "PayOnDelivery"
        );
        payCmb.setItems(options);

        payCmb.setOnAction ((event) -> {
            try {
                payLbl.setText( payCmb.getSelectionModel().getSelectedItem().toString());
            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        backBtn.setOnAction ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("OrderDetailFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("سبد کالا");
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
//todo order_report
    private void showDataOnTable(List<Orders> ordersList) {

        ObservableList<Orders> orders = FXCollections.observableList(ordersList);

        orderTbl.getColumns().clear();

        TableColumn<Orders, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Orders, Integer> customerIdCol = new TableColumn<>("Customer Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Orders, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Orders, Float> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<Orders, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderType"));

        TableColumn<Orders, LocalDate> orderDateCol = new TableColumn<>("Order Date");
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        orderTbl.getColumns().addAll(idCol, customerIdCol, amountCol, discountCol, statusCol, orderDateCol);
        orderTbl.setItems(orders);
    }

    public void resetForm(){
        try {
            showDataOnTable(OrderController.getController().findByCustomerId(42));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

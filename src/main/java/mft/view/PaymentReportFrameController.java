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
import mft.controller.PaymentController;
import mft.model.entity.Orders;
import mft.model.entity.Payment;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentReportFrameController implements Initializable {

    @FXML
    private Button searchBtn, productBtn;

    @FXML
    private TextField searchTxt ;

    @FXML
    private TableView<Payment> paymentTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(PaymentController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        searchBtn.setOnAction((event) -> {
            try {
                showDataOnTable(PaymentController.getController().findByAll(searchTxt.getText()));

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

    private void showDataOnTable(List<Payment> paymentList) {

        ObservableList<Payment> payments = FXCollections.observableList(paymentList);

        paymentTbl.getColumns().clear();

        TableColumn<Payment, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Payment, Integer> customerIdCol = new TableColumn<>("Customer Id");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Payment, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Payment, String> familyCol = new TableColumn<>("Family");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Payment, Double> totalCostCol = new TableColumn<>("Total Cost");
        totalCostCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        TableColumn<Payment, String> typeCol = new TableColumn<>("Payment Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("PaymentType"));

        TableColumn<Payment, LocalDateTime> paymentDateCol = new TableColumn<>("Payment Date");
        paymentDateCol.setCellValueFactory(new PropertyValueFactory<>("PaymentTimeStamp"));

        paymentTbl.getColumns().addAll(idCol, customerIdCol, nameCol, familyCol, totalCostCol, typeCol, paymentDateCol);
        paymentTbl.setItems(payments);
    }

    public void resetForm() {
        try {
            showDataOnTable(PaymentController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }
}

package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.OrderDetailsController;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDetailFrameController implements Initializable {
//    @FXML
//    private Button saveBtn, editBtn, removeBtn, searchBtn;
//
//    @FXML
//    private TextField idTxt, usernameTxt, searchTxt;

    @FXML
    private Label msgLbl;

    @FXML
    private TableView<OrderDetails> orderTbl;

    @FXML
    private TableView<Payment> paymentTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

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

//        userTbl.setOnMouseClicked((event) -> {
//            RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
//            User user = userTbl.getSelectionModel().getSelectedItem();
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
    private void showDataOnTable(List<OrderDetails> orderDetailsList) {

        ObservableList<OrderDetails> users = FXCollections.observableList(orderDetailsList);

        orderTbl.getColumns().clear();

        TableColumn<OrderDetails, Integer> idCol = new TableColumn<>("#");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrderDetails, String> productNameCol = new TableColumn<>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Product_Id"));

        TableColumn<OrderDetails, String> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderDetails, Boolean> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTbl.getColumns().addAll(idCol, productNameCol, quantityCol, priceCol);
        orderTbl.setItems(users);
    }


    public void resetForm(){
        try {
            showDataOnTable(OrderDetailsController.getController().findAll());

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }


}

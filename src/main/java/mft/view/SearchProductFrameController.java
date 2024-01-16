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
import mft.controller.OrderDetailsController;
import mft.controller.ProductController;
import mft.model.entity.Customer;
import mft.model.entity.OrderDetails;
import mft.model.entity.Products;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchProductFrameController implements Initializable {

    @FXML
    private Button searchBtn, addBtn, exitBtn;

    @FXML
    private TextField searchTxt, countTxt, custTxt;

    @FXML
    private Label idTxt, nameTxt, brandTxt, priceTxt, numLb;

    @FXML
    private TitledPane editTp, orderTp, orderDetailTp;

    @FXML
    private TableView<Products> productTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        addBtn.setOnAction((event) -> {
            try {
                //todo problem in user Id
                OrderDetails orderDetails = OrderDetailsController.getController().save(
                        Integer.valueOf(custTxt.getText()),
                        1,
                        Integer.valueOf(idTxt.getText()),
                        Integer.valueOf(countTxt.getText()),
                        Double.valueOf(priceTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Staff Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        productTbl.setOnMouseClicked((event) -> {
            Products products = productTbl.getSelectionModel().getSelectedItem();

            countTxt.setVisible(true);
            numLb.setVisible(true);
            countTxt.clear();

            idTxt.setText(String.valueOf(products.getId()));
            nameTxt.setText(products.getName());
            brandTxt.setText(products.getBrand());
            priceTxt.setText(String.valueOf(products.getPrice()));

        });
        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(ProductController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        searchBtn.setOnAction((event) -> {
            try {
                showDataOnTable(ProductController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        editTp.setOnMouseClicked ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("editCustomerFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("ویرایش حساب کاربری");
                stage.show();
                resetForm();
                editTp.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        orderDetailTp.setOnMouseClicked ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("orderDetailFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("سبد کالا");
                stage.show();
                resetForm();
                editTp.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

        orderTp.setOnMouseClicked ((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getClassLoader().getResource("orderFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("سفارشات");
                stage.show();
                resetForm();
                editTp.getParent().getScene().getWindow().hide();

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

    public void resetForm(){
        try {
//            idTxt.setVisible(false);
//            nameTxt.setVisible(false);
//            brandTxt.setVisible(false);
//            priceTxt.setVisible(false);
//            countTxt.setVisible(false);
//            numTxt.setVisible(false);

            showDataOnTable(ProductController.getController().findAll());

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }

}

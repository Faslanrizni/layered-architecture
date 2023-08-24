package com.devstack.pos.controller;

import com.devstack.pos.dao.DatabaseAccessCode;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.view.tm.productTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainFormController {
    public AnchorPane context;
    public JFXTextField txtProductCode;
    public TextArea txtProductDescription;
    public JFXButton btnSaveProduct;
    public JFXButton btnNewProduct;
    private String searchText = "";

    public void initialize(){
        //load new product id
        loadProductId();
        //load new product id

    }

    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(new DatabaseAccessCode().getLastProductId()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNewProductOnAction(ActionEvent actionEvent) {


    }

    private void loadAllProducts(String searchText) throws SQLException, ClassNotFoundException {
      /*  ObservableList<productTm>observableList = FXCollections.observableArrayList();
        for(ProductDto dto :
                searchText.length()>0 ? DatabaseAccessCode.searchProduct(searchText);
            )*/


    }

    private void clearFields() {
     txtProductDescription.clear();
     txtProductCode.clear();
     loadProductId();
    }

    public void newbatchOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnActiom(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");

    }

    public void btnSaveProductOnAction(ActionEvent actionEvent) {
        try {
            if (btnSaveProduct.getText().equals("Save Products")){
                if (new DatabaseAccessCode().saveProduct(Integer.parseInt(txtProductCode.getText()),txtProductDescription.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"Product Saved").show();
                    clearFields();
                    loadAllProducts(searchText);
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again");
                }

            }else {
                if(new DatabaseAccessCode().saveProduct(Integer.parseInt(txtProductCode.getText()),txtProductDescription.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"Product Updated");
                    clearFields();
                    loadAllProducts(searchText);
                    btnSaveProduct.setText("Save Product");
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again!..");
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void setUi(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml"))));
        stage.centerOnScreen();


    }
}

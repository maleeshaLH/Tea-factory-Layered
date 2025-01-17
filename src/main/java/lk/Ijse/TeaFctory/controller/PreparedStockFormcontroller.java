package lk.Ijse.TeaFctory.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.TeaFctory.bo.custom.PreparedStockBO;
import lk.Ijse.TeaFctory.bo.custom.impl.PreparedStockBOImpl;
import lk.Ijse.TeaFctory.dto.PreparedstockDto;
import lk.Ijse.TeaFctory.dto.tm.PreparedstockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


public class PreparedStockFormcontroller {
    @FXML
    public AnchorPane root;
    @FXML
    public TextField txtUnitPrice;
    @FXML
    public TableColumn <? , ?>colUnitPrice;
    @FXML
    private TableColumn<?, ?> colproductionid;
    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colweight;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtweight;
    @FXML
    private TableView<PreparedstockTm> tblStock;
    PreparedStockBO preparedStockBO = new PreparedStockBOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colproductionid.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colweight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadAllCustomers() {

        ObservableList<PreparedstockTm> obList = FXCollections.observableArrayList();

        try {
            List<PreparedstockDto> dtoList = preparedStockBO.getAllStock();

            for (PreparedstockDto dto : dtoList) {
                obList.add(
                        new PreparedstockTm(
                                dto.getP_id(),
                                dto.getDescription(),
                                dto.getUnit_price(),
                                dto.getWeight(),
                                dto.getQty()

                        )
                );
            }

            tblStock.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"))));
        stage.setTitle("dashboard Form");
        stage.centerOnScreen();

        stage.show();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) { clesrFields(); }

    @FXML
    void btndeleteOnAction(ActionEvent event) {
        String id = txtId.getText();


        try {
            boolean isDelete = preparedStockBO.deleteStock(id);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"stock delete").show();
                setCellValueFactory();
                loadAllCustomers();


            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnsaveeOnAction(ActionEvent event) {

        boolean isPreparedStockIdValidated = validatePreparedStock();
        boolean isPreparedStockNameValidated =validatePreparedStock();
        boolean isPreparedStockUnitPriceValidated = validatePreparedStock();
        boolean isPreparedStockWeightValidated = validatePreparedStock();
        boolean isPreparedStockQtyValidated = validatePreparedStock();


        if (isPreparedStockIdValidated && isPreparedStockNameValidated && isPreparedStockUnitPriceValidated &&
                isPreparedStockWeightValidated && isPreparedStockQtyValidated) {


            String p_id = txtId.getText();
            String p_name = txtname.getText();
            int unit_price = Integer.parseInt(txtUnitPrice.getText());
            int weight = Integer.parseInt(txtweight.getText());
            int quality = Integer.parseInt(txtqty.getText());

            var dto = new PreparedstockDto(p_id, p_name, unit_price, weight, quality);



            try {
                boolean isSaved = preparedStockBO.saveStock(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "saved stock").show();
                    clesrFields();
                    setCellValueFactory();
                    loadAllCustomers();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validatePreparedStock() {
        String id = txtId.getText();
//        boolean isCustomerIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isPreparedStockIdValidated = Pattern.matches("[p][0-9]{2,}", id);
        if (!isPreparedStockIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Prepared Stock ID!").show();
            return false;
        }

        String name =txtname.getText();
        boolean isPreparedStockNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",name);
        if (!isPreparedStockNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Prepared Stock name!").show();
            return false;
        }

        String unitPrice =txtUnitPrice.getText();
        boolean isPreparedStockUnitPriceValidated =Pattern.matches("[0-9]{1,}",unitPrice);
        if (!isPreparedStockUnitPriceValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Prepared Stock Unit Price!").show();
            return false;
        }



        String weight =txtweight.getText();
        boolean isPreparedStockWeightValidated =Pattern.matches("[0-9]{1,}",weight);
        if (!isPreparedStockWeightValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Prepared Stock Weight!").show();
            return false;
        }

        String qty =txtqty.getText();
        boolean isPreparedStockQtyValidated =Pattern.matches("[0-9]{1,}",qty);
        if (!isPreparedStockQtyValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Prepared Stock Qty!").show();
            return false;
        }

        return true;

    }

    private void clesrFields() {
        txtId.setText("");
        txtname.setText("");
        txtUnitPrice.setText("");
        txtweight.setText("");
        txtqty.setText("");


    }

    @FXML
    void btnupdateOnAction(ActionEvent event) {
        String p_id= txtId.getText();
        String p_name=txtname.getText();
        int unit_price = Integer.parseInt(txtUnitPrice.getText());
        int weight =Integer.parseInt(txtweight.getText());
        int quality = Integer.parseInt(txtqty.getText());

        var dto = new PreparedstockDto(p_id,p_name,unit_price,weight,quality);



        try {
            boolean isUpdate = preparedStockBO.updateP_Stock(dto);
            System.out.println(isUpdate);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"update stock").show();
                setCellValueFactory();
                loadAllCustomers();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }



    }

    @FXML
    void txtsearchOnAction(ActionEvent event) {
        String id = txtId.getText();


        try {
            PreparedstockDto dto = preparedStockBO.searchStock(id);
            if (dto != null){
                fillFielde(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"stock not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void fillFielde(PreparedstockDto dto) {
        txtId.setText(dto.getP_id());
        txtname.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnit_price()));
        txtweight.setText(String.valueOf(dto.getWeight()));
        txtqty.setText(String.valueOf(dto.getQty()));

    }

    @FXML
    void btnRawStockOnAction(ActionEvent event) throws IOException {
//        Stage stage=new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/rawStock_form.fxml"))));
//        stage.setTitle("rawStock Form");
//        stage.centerOnScreen();
//
//        stage.show();
        this.root.getChildren().clear();
      this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/rawStock_form.fxml")));

    }
    @FXML
    public void btnStockDetailsOnActiom(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = (FXMLLoader.load(this.getClass().getResource("/view/Stock_details_form.fxml")));
//        Scene scene = new Scene(anchorPane);
//        Stage stage =(Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Stock Details Form");
//        stage.centerOnScreen();
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/Stock_details_form.fxml")));
    }
}

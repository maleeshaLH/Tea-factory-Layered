package lk.Ijse.TeaFctory.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.Ijse.TeaFctory.bo.custom.RawStockBO;
import lk.Ijse.TeaFctory.bo.custom.impl.RawStockBOIMpl;
import lk.Ijse.TeaFctory.dto.RawStockDto;
import lk.Ijse.TeaFctory.dto.tm.RawStockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class RawStockFormController {
    @FXML
    public TextField txtUnicprice;
    @FXML
    public TableColumn <? ,?> colUnic_price;
    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuality;

    @FXML
    private TableColumn<?, ?> colR_id;

    @FXML
    private TableColumn<?, ?> colWeight;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RawStockTm> tblRawStock;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtR_id;

    @FXML
    private TextField txtqulity;

    @FXML
    private TextField txtweight;
    RawStockBO rawStockBO = new RawStockBOIMpl();


    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colR_id.setCellValueFactory(new PropertyValueFactory<>("rs_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnic_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));

    }

    private void loadAllCustomers() {


        ObservableList<RawStockTm> obList = FXCollections.observableArrayList();

        try {
            List<RawStockDto> dtoList = rawStockBO.getAllRawStock();

            for (RawStockDto dto : dtoList) {
                obList.add(
                        new RawStockTm(
                                dto.getRs_id(),
                                dto.getDescription(),
                                dto.getUnit_price(),
                                dto.getWeight(),
                                dto.getQuality()

                                )
                );
            }

            tblRawStock.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @FXML
    void btnClearOnAction(ActionEvent event) {
        clesrFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtR_id.getText();


        try {
            boolean isDeleted = rawStockBO.deleteRawStock(id);

            if(isDeleted) {
                tblRawStock.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, " deleted raw stock!").show();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isRawStockIdValidated = validateRawStock();
        boolean isRawStockNameValidated = validateRawStock();
        boolean isRawStockUnitPriceValidated = validateRawStock();
        boolean isRawStockWeightValidated = validateRawStock();
        boolean isRawStockQtyValidated = validateRawStock();

        if (isRawStockIdValidated && isRawStockNameValidated && isRawStockUnitPriceValidated &&
        isRawStockWeightValidated && isRawStockQtyValidated){

            String ra_id = txtR_id.getText();
            String name = txtName.getText();
            int unic_price = Integer.parseInt(txtUnicprice.getText());
            int weight =Integer.parseInt(txtweight.getText());
            int quality = Integer.parseInt(txtqulity.getText());


            var dto = new RawStockDto(ra_id,name,unic_price,weight,quality);


            try {
                boolean isSaved = rawStockBO.saveRawStock(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "save raw stock!").show();
                    clesrFields();
                    setCellValueFactory();
                    loadAllCustomers();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    }

    private boolean validateRawStock() {
        String id = txtR_id.getText();
//        boolean isCustomerIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isRawStockIdValidated = Pattern.matches("[R][0-9]{2,}", id);
        if (!isRawStockIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid RawStock Stock ID!").show();
            return false;
        }

        String name =txtName.getText();
        boolean isRawStockNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",name);
        if (!isRawStockNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid RawStock Stock name!").show();
            return false;
        }

        String unitPrice =txtUnicprice.getText();
        boolean isRawStockUnitPriceValidated =Pattern.matches("[0-9]{1,}",unitPrice);
        if (!isRawStockUnitPriceValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid RawStock Stock Unit Price!").show();
            return false;
        }



        String weight =txtweight.getText();
        boolean isRawStockWeightValidated =Pattern.matches("[0-9]{1,}",weight);
        if (!isRawStockWeightValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid RawStock Stock Weight!").show();
            return false;
        }

        String qty =txtqulity.getText();
        boolean isRawStockQtyValidated =Pattern.matches("[0-9]{1,}",qty);
        if (!isRawStockQtyValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid RawStock Stock Qty!").show();
            return false;
        }

        return true;

    }

    private void clesrFields() {
        txtR_id.setText("");
        txtName.setText("");
        txtUnicprice.setText("");
        txtweight.setText("");
        txtqulity.setText("");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String ra_id = txtR_id.getText();
        String name = txtName.getText();
        Integer unic_price =Integer.parseInt(txtUnicprice.getText());
        Integer weight =Integer.parseInt(txtweight.getText());
        Integer quality = Integer.parseInt(txtqulity.getText());


        var dto = new RawStockDto(ra_id,name,unic_price,weight,quality);


        try {
            boolean isUpdated = rawStockBO.updateRawStock(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated raw stock!").show();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml")));

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtR_id.getText();


        try {
            RawStockDto dto = rawStockBO.searchRawStock(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void fillFields(RawStockDto dto) {
        txtR_id.setText(dto.getRs_id());
        txtName.setText(dto.getDescription());
        txtUnicprice.setText(String.valueOf(dto.getQuality()));
        txtweight.setText(String.valueOf(dto.getWeight()));
        txtqulity.setText(String.valueOf(dto.getQuality()));

    }

}

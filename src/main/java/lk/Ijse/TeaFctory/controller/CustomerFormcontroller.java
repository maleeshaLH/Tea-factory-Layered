package lk.Ijse.TeaFctory.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.Ijse.TeaFctory.bo.custom.CustomerBO;
import lk.Ijse.TeaFctory.bo.custom.impl.CustomerBOImpl;
import lk.Ijse.TeaFctory.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.TeaFctory.dto.CustomerDto;
import lk.Ijse.TeaFctory.dto.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class CustomerFormcontroller {

    @FXML
    public Pane root;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colFirst_name;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLast_name;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtFirst_name;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLast_name;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txttel;

    @FXML
    private TableView<CustomerTm> tblCustomer;
    CustomerDAOImpl customerDAO =new CustomerDAOImpl();
    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirst_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLast_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDto> allcustomer =  customerBO.getAllCustomers();

            for (CustomerDto dto : allcustomer) {
                obList.add(
                        new CustomerTm(
                                dto.getId(),
                                dto.getFirst_name(),
                                dto.getLast_name(),
                                dto.getTel(),
                                dto.getAddress(),
                                dto.getCity()
                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private boolean validateCustomer() {
        String id = txtId.getText();
//        boolean isCustomerIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isCustomerIDValidated = Pattern.matches("[c][0-9]{2,}", id);
        if (!isCustomerIDValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
        return false;
        }

        String Firstname =txtFirst_name.getText();
        boolean isCustomerFirstNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",Firstname);
        if (!isCustomerFirstNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer First name!").show();
            return false;
        }

        String Lastname =txtLast_name.getText();
        boolean isCustomerLastNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",Lastname);
        if (!isCustomerLastNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Last name!").show();
            return false;
        }



        String Address =txtAddress.getText();
        boolean isCustomerAddressValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",Address);
        if (!isCustomerAddressValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Address!").show();
            return false;
        }

        String City =txtcity.getText();
        boolean isCustomerCityValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",City);
        if (!isCustomerCityValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer City!").show();
            return false;
        }

        String tel =txttel.getText();
        boolean isCustomerTelValidated =Pattern.matches("[0-9]{10}",tel);
        if (!isCustomerTelValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Telephone Number!").show();
            return false;
        }
        return true;

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = customerBO.deleteCustomer(id);

            if(isDeleted) {
                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event)  {
        boolean isCustomerIdValidated = validateCustomer();
        boolean isCustomerFirstNameValidated = validateCustomer();
        boolean isCustomerLastNameValidated = validateCustomer();
        boolean isCustomerTelValidated = validateCustomer();
        boolean isCustomerAddressValidated = validateCustomer();
        boolean isCustomerCityValidated = validateCustomer();

        if (isCustomerIdValidated && isCustomerFirstNameValidated && isCustomerLastNameValidated && isCustomerTelValidated &&
                isCustomerAddressValidated && isCustomerCityValidated){

            String id = txtId.getText();
            String first_name = txtFirst_name.getText();
            String last_name =txtLast_name.getText();
            String tel = txttel.getText();
            String address = txtAddress.getText();
            String city = txtcity.getText();
            try {
                boolean isSaved = customerBO.saveCustomer(new CustomerDto(id,first_name,last_name,tel,address,city));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clesrFields();
                    setCellValueFactory();
                    loadAllCustomers();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    }

    private void clesrFields() {
        txtFirst_name.setText("");
        txtLast_name.setText("");
        txtId.setText("");
        txtAddress.setText("");
        txtcity.setText("");
        txttel.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String first_name = txtFirst_name.getText();
        String last_name =txtLast_name.getText();
        String tel = txttel.getText();
        String address = txtAddress.getText();
        String city = txtcity.getText();
        try {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDto(id,first_name,last_name,tel,address,city));
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            CustomerDto dto = customerBO.searchCustomer(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void fillFields(CustomerDto dto) {
        txtId.setText(dto.getId());
        txtFirst_name.setText(dto.getFirst_name());
        txtLast_name.setText(dto.getLast_name());
        txttel.setText(dto.getTel());
        txtAddress.setText(dto.getAddress());
        txtcity.setText(dto.getCity());
    }


    public void btnbackOnAction(ActionEvent event) throws IOException {
//        Stage stage=new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"))));
//        stage.setTitle("dashboard Form");
//        stage.centerOnScreen();
//
////        stage.show();
//        this.root.getChildren().clear();
//        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml")));
    }

    public void btnClearOnAction(ActionEvent event) {
        clesrFields();
    }
}

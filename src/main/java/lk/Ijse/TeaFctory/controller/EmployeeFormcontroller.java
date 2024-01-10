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
import lk.Ijse.TeaFctory.bo.custom.EmployeeBO;
import lk.Ijse.TeaFctory.bo.custom.impl.EmployeeBOImpl;
import lk.Ijse.TeaFctory.dao.custom.EmployeeDAO;
import lk.Ijse.TeaFctory.dao.custom.impl.EmployeeDAOImpl;
import lk.Ijse.TeaFctory.dto.EmployeeDto;
import lk.Ijse.TeaFctory.dto.tm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmployeeFormcontroller {

    @FXML
    private TableColumn<?, ?> clocity;


    @FXML
    private TableColumn<?, ?> colEId;

    @FXML
    private TableColumn<?, ?> colFirst_name;

    @FXML
    private TableColumn<?, ?> colLast_name;

    @FXML
    private TableColumn<?, ?> coltel;

    @FXML
    private TableColumn<?, ?> colnic;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtEmployeeid;

    @FXML
    private TextField txtFirst_name;

    @FXML
    private TextField txtLast_name;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txttel;
    EmployeeBO employeeBO = new EmployeeBOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
    }

    private void setCellValueFactory() {
        colEId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colFirst_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLast_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        clocity.setCellValueFactory(new PropertyValueFactory<>("city"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllEmployee() {


       ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDto> dtoList = employeeBO.getAllEmployee();

            for (EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmp_id(),
                                dto.getFirst_name(),
                                dto.getLast_name(),
                                dto.getNic(),
                                dto.getCity(),
                                dto.getTel()
                        )
                );
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmployeeid.getText();


        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);

            if(isDeleted) {
                tblEmployee.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                setCellValueFactory();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean isEmployeeIdValideted = validatedEmployee();
        boolean isEmployeeFirstNameValidated = validatedEmployee();
        boolean isEmployeeLastNameValidated = validatedEmployee();
        boolean isEmployeeNicValidated = validatedEmployee();
        boolean isEmployeeCityValidated = validatedEmployee();
        boolean isEmployeeTelValidated = validatedEmployee();

        if (isEmployeeIdValideted && isEmployeeFirstNameValidated && isEmployeeLastNameValidated && isEmployeeNicValidated &&
        isEmployeeCityValidated && isEmployeeTelValidated) {


            String id = txtEmployeeid.getText();
            String first_name = txtFirst_name.getText();
            String last_name = txtLast_name.getText();
            String nic = txtnic.getText();
            String city = txtcity.getText();
            String tel = txttel.getText();

            try {
                boolean isSaved = employeeBO.saveEmployee(new EmployeeDto(id, first_name, last_name, nic, city, tel));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                    clearFields();
                    setCellValueFactory();
                    loadAllEmployee();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean validatedEmployee() {
        String id = txtEmployeeid.getText();
//        boolean isCustomerIDValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        boolean isEmployeeIdValideted = Pattern.matches("[e][0-9]{2,}", id);
        if (!isEmployeeIdValideted) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID!").show();
            return false;
        }
        String Firstname =txtFirst_name.getText();
        boolean isEmployeeFirstNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",Firstname);
        if (!isEmployeeFirstNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee First name!").show();
                    return false;
        }

        String Lastname =txtLast_name.getText();
        boolean isEmployeeLastNameValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",Lastname);
        if (!isEmployeeLastNameValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Last name!").show();
            return false;
        }

        String nic =txtnic.getText();
        boolean isEmployeeNicValidated =Pattern.matches("[0-9]{6,}",nic);
        if (!isEmployeeNicValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee NIC!").show();
            return false;
        }

        String city =txtcity.getText();
        boolean isEmployeeCityValidated =Pattern.matches("[A-Za-z]+([ '-][A-Za-z]+)*$",city);
        if (!isEmployeeCityValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee City!").show();
            return false;
        }

        String tel =txttel.getText();
        boolean isEmployeeTelValidated =Pattern.matches("[0-9]{10}",tel);
        if (!isEmployeeTelValidated){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Telephone Number!").show();
            return false;
        }
        return true;

    }

    private void clearFields() {
        txtFirst_name.setText("");
        txtLast_name.setText("");
        txtEmployeeid.setText("");
        txtnic.setText("");
        txtcity.setText("");
        txttel.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeid.getText();
        String first_name = txtFirst_name.getText();
        String last_name =txtLast_name.getText();
        String nic = txtnic.getText();
        String city = txtcity.getText();
        String tel = txttel.getText();

        var dto = new EmployeeDto(id,first_name,last_name,nic,city,tel);


        try {
            boolean isUpdated = employeeBO.updateEmployee(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                setCellValueFactory();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
    void txtSearchOnAction(ActionEvent event) {
        String id = txtEmployeeid.getText();


        try {
            EmployeeDto dto = employeeBO.searchEmployee(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void fillFields(EmployeeDto dto) {
        txtEmployeeid.setText(dto.getEmp_id());
        txtFirst_name.setText(dto.getFirst_name());
        txtLast_name.setText(dto.getLast_name());
        txtnic.setText(dto.getNic());
        txtcity.setText(dto.getCity());
        txttel.setText(dto.getTel());
    }

}

package lk.Ijse.TeaFctory.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.TeaFctory.bo.custom.EmployeeBO;
import lk.Ijse.TeaFctory.bo.custom.SalaryBO;
import lk.Ijse.TeaFctory.bo.custom.impl.EmployeeBOImpl;
import lk.Ijse.TeaFctory.bo.custom.impl.SalaryBOImpl;
import lk.Ijse.TeaFctory.dto.EmployeeDto;
import lk.Ijse.TeaFctory.dto.SalaryDto;
import lk.Ijse.TeaFctory.dto.tm.SalaryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SalaryFormController {
    SalaryBO salaryBO = new SalaryBOImpl();
    EmployeeBO employeeBO = new EmployeeBOImpl();


    @FXML
    public Label lblSalaryId;
    @FXML
    public Label lbltime;
    @FXML
    private ComboBox<String > cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmp_id;


    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblSalaryDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txtsalaryid;

    public void initialize() {
        loadEmployeeIds();
        setDate();
        setTime();
        setCellValueFactory();
        generateNextSalaryId();
        loadAllSalary();

    }

    private void generateNextSalaryId() {
        try {
            String orderId = salaryBO.generateNextOrderIds();
            lblSalaryId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTime() {
        String date = String.valueOf(LocalTime.now());
        lbltime.setText(date);
    }

    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        colEmp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void loadAllSalary() {


        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDto> dtoList = salaryBO.getAllCustomers();

            for (SalaryDto dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getS_id(),
                                dto.getEmp_id(),
                                dto.getDate(),
                                dto.getSalary()

                        )
                );
            }

            tblSalary.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblSalaryDate.setText(date);
    }

    private void loadEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> empList = employeeBO.getAllEmployee();

            for (EmployeeDto dto : empList) {
                obList.add(dto.getEmp_id());
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String s_id = lblSalaryId.getText();
        String emp_id = cmbEmployeeId.getValue();
        LocalDate date = LocalDate.parse(lblSalaryDate.getText());
        String salary = txtsalary.getText();

        var salaryDto = new SalaryDto(
                s_id,
                emp_id,
                date,
                salary
        );
        //var model = new SalaryModel();

        try {
            boolean isSaved = salaryBO.saveSalary(new SalaryDto(salaryDto.getS_id(),salaryDto.getEmp_id(),salaryDto.getDate(),salaryDto.getSalary()));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "salary completed!").show();
                setCellValueFactory();
                loadAllSalary();
                generateNextSalaryId();


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
    void cmbEmployeeOnAction(ActionEvent event) throws SQLException {
        String id = cmbEmployeeId.getValue();
        EmployeeDto dto = employeeBO.searchEmployee(id);

        lblEmployeeName.setText(dto.getFirst_name());

    }
    void clearFields() {
        txtsalaryid.setText("");
        cmbEmployeeId.setValue("");
        lblSalaryDate.setText("");
        txtsalary.setText("");
    }
}

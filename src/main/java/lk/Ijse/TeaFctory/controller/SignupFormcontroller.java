package lk.Ijse.TeaFctory.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.TeaFctory.bo.custom.UserBO;
import lk.Ijse.TeaFctory.bo.custom.impl.UserBOImpl;
import lk.Ijse.TeaFctory.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormcontroller {

    @FXML
    private JFXButton btnlogin;

    @FXML
    private JFXButton btnsignup;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;
    UserBO userBO =new UserBOImpl();

    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginpage_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Loginpage Manage");
        stage.centerOnScreen();

    }

    @FXML
    void btnsignupOnAction(ActionEvent event) {
        String username = txtusername.getText();
        String email = txtemail.getText();
        String password = txtpassword.getText();

        try {
            boolean isSaved = userBO.saveUser(new UserDto(username,email,password));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Added Successfully").show();
                return;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
package lk.Ijse.TeaFctory.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.TeaFctory.bo.custom.UserBO;
import lk.Ijse.TeaFctory.bo.custom.impl.UserBOImpl;

import java.io.IOException;

public class LoginpageFormcontroller {

    @FXML
    private JFXButton btnlogin;

    @FXML
    private JFXButton btnsigiUp1;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;
    UserBO userBO =new UserBOImpl();



    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {
        String username = txtusername.getText();
        String password = txtpassword.getText();


            if (userBO.saveUser(username,password)){
                NavigatetoDashBoard();

            }else {

                //System.out.println("Invalid username or passsword");
                new Alert(Alert.AlertType.ERROR, "invalid username or password!").show();
        }


           // String dto = userModel.searchUser(username,password);
    }

    private void NavigatetoDashBoard() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBoard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("dashboard Manage");
        stage.centerOnScreen();
    }



    @FXML
    void btnsignUpOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Signup Manage");
        stage.centerOnScreen();

    }
    public static void switchNavigation(String path, AnchorPane event ) throws IOException {
        event.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/" + path));
        Parent root = loader.load();
        event.getChildren().add(root);
    }


    private static class Navigation {
    }
}


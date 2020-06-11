package sample.controllers;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.JavaFxApplication;
import sample.Security;
import sample.services.AdminServiceInterface;
import sample.services.FactoryServiceInterface;
//import sample.services.TransportOperatorServiceInterface;
import sample.services.TransporterServiceInterface;

@Component
@FxmlView("login.fxml")
public class LoginController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buttonClose;
    @FXML
    private Button buttonLogin;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField passwordField;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private RadioButton radioButtonFactory;
    @FXML
    private RadioButton radioButtonTransporter;
    @FXML
    private RadioButton radioButtonAdmin;

    @Autowired
    FactoryServiceInterface factoryService;

    @Autowired
    TransporterServiceInterface transporterService;

    @Autowired
    AdminServiceInterface adminService;

    @FXML
    private void buttonCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buttonLoginOnAction(ActionEvent event) {
        Security security = new Security();
        anchorPane.setDisable(true);
        if (radioButtonFactory.isSelected() && !radioButtonTransporter.isSelected() && !radioButtonAdmin.isSelected()) {
            try {
                Boolean passwordCheck = security.passwordCheckForFactory(factoryService, textFieldUsername.getText(), passwordField.getText());
                if (passwordCheck) {
                    FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                    Parent root = fxWeaver.loadView(FactoryController.class);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle(textFieldUsername.getText());
                    stage.setScene(scene);
                    stage.show();
                    buttonCloseOnAction(new ActionEvent());
                } else {showAlertWithoutHeaderText(); }
            } catch (Exception e) {
                showAlertWithoutHeaderText();
                System.out.println(e.fillInStackTrace());
            }
        } else if(!radioButtonFactory.isSelected() && radioButtonTransporter.isSelected() && !radioButtonAdmin.isSelected()) {
            try {
                Boolean passwordCheck = security.passwordCheckForTransporter(transporterService, textFieldUsername.getText(), passwordField.getText());
                if (passwordCheck) {
                    FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                    Parent root = fxWeaver.loadView(TransporterController.class);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle(textFieldUsername.getText());
                    stage.setScene(scene);
                    stage.show();
                    buttonCloseOnAction(new ActionEvent());
                } else {showAlertWithoutHeaderText();}
            } catch (Exception e) {
                showAlertWithoutHeaderText();
                System.out.println(e.fillInStackTrace());
            }


        }else if(!radioButtonFactory.isSelected() && !radioButtonTransporter.isSelected() && radioButtonAdmin.isSelected()) {
            try {
                Boolean passwordCheck = security.passwordCheckForAdmin(adminService, textFieldUsername.getText(), passwordField.getText());
                if (passwordCheck) {
                    FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                    Parent root = fxWeaver.loadView(AdminController.class);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle(textFieldUsername.getText());
                    stage.setScene(scene);
                    stage.show();
                    buttonCloseOnAction(new ActionEvent());
                } else {showAlertWithoutHeaderText();}
            } catch (Exception e) {
                showAlertWithoutHeaderText();
                System.out.println(e.fillInStackTrace());
            }


        }

    }

    @FXML
    private void radioButtonFactoryOnAction(ActionEvent event) {
        radioButtonFactory.setSelected(true);
        radioButtonTransporter.setSelected(false);
        radioButtonAdmin.setSelected(false);
    }

    @FXML
    private void radioButtonTransporterOnAction(ActionEvent event) {
        radioButtonFactory.setSelected(false);
        radioButtonTransporter.setSelected(true);
        radioButtonAdmin.setSelected(false);
    }

    @FXML
    private void radioButtonAdminOnAction(ActionEvent event) {
        radioButtonAdmin.setSelected(true);
        radioButtonFactory.setSelected(false);
        radioButtonTransporter.setSelected(false);

    }

    @FXML
    private void initialize() {

    }

    public LoginController(){

    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Information");
        alert.setContentText("Incorrect Username or Password!");
        alert.showAndWait();
        anchorPane.setDisable(false);
    }
}

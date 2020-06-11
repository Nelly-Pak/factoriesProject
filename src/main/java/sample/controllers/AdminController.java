package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.JavaFxApplication;
import sample.Security;
import sample.entities.FactoryEntity;
import sample.entities.OrderingEntity;
import sample.entities.ProductEntity;
import sample.entities.TransporterEntity;
import sample.services.FactoryServiceInterface;
import sample.services.OrderingServiceInterface;
import sample.services.ProductServiceInterface;
import sample.services.TransporterServiceInterface;

import java.util.List;

@Component
@FxmlView("admin.fxml")
public class AdminController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textFieldFactoryName;
    @FXML
    private TextField textFieldTransporterName;
    @FXML
    private TextField textFieldProductName;
    @FXML
    private PasswordField passwordFieldFactoryPassword;
    @FXML
    private PasswordField passwordFieldTransporterPassword;
    @FXML
    private Button buttonCreateFactory;
    @FXML
    private Button buttonCreateTransporter;
    @FXML
    private Button buttonCreateProduct;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonDeleteFactory;
    @FXML
    private Button buttonDeleteProduct;
    @FXML
    private Button buttonDeleteTransporter;
    @FXML
    private SplitMenuButton splitMenuButtonFactoryProduct;
    @FXML
    private SplitMenuButton splitMenuButtonSetFactoryDelete;
    @FXML
    private SplitMenuButton splitMenuButtonSetProductDelete;
    @FXML
    private SplitMenuButton splitMenuButtonSetTransporterDelete;
    @FXML
    private CheckBox checkBoxDelete;

    private Stage stageThis;

    private Security security = new Security();

    @Autowired
    FactoryServiceInterface factoryService;

    @Autowired
    OrderingServiceInterface orderingService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    TransporterServiceInterface transporterService;

    private FactoryEntity factoryEntityNew;

    private ProductEntity productEntityNew;

    private TransporterEntity transporterEntityNew;

    private TransporterEntity transporterEntityForDelete;

    private FactoryEntity factoryEntityForDelete;

    private ProductEntity productEntityForDelete;


    @FXML
    private void checkBoxDeleteOnAction(ActionEvent event) {
        if (checkBoxDelete.isSelected()) {
            checkBoxDelete.setSelected(false);
            splitMenuButtonSetProductDelete.setDisable(true);
        }else {
            checkBoxDelete.setSelected(true);
            splitMenuButtonSetProductDelete.setDisable(false);
        }
    }

    @FXML
    private void buttonDeleteTransporterOnAction(ActionEvent event) {
        List<OrderingEntity> orderingEntities = orderingService.findEntitiesByIdTransporter(transporterEntityForDelete.getId());
        for (OrderingEntity orderingEntity : orderingEntities) {
            orderingEntity.setIdTransporter(null);
            orderingEntity.setPrice(null);
            orderingEntity.setAccepted(false);
            orderingService.save(orderingEntity);
        }
        transporterService.delete(transporterEntityForDelete);
        updateSplitMenuButtonSetTransporterDelete();
    }

    @FXML
    private void buttonDeleteProductOnAction(ActionEvent event) {
        productService.delete(productEntityForDelete);
        updateSplitButtonMenuSetProductDeleteProduct();
    }

    @FXML
    private void buttonDeleteFactoryOnAction(ActionEvent event) {
        List<OrderingEntity> orderingEntities = orderingService.findEntitiesByIdFactory(factoryEntityForDelete.getId());
        for (OrderingEntity orderingEntity : orderingEntities) {
            orderingService.deleteById(orderingEntity.getId());
        }
        factoryService.delete(factoryEntityForDelete);
        updateSplitMenuButtonSetFactoryDelete();
    }

    @FXML
    private void buttonLogOutOnAction(ActionEvent event) {
        anchorPane.setDisable(true);
        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stageThis.close();
    }

    @FXML
    private void buttonCreateFactoryOnAction (ActionEvent event) {
        if (textFieldFactoryName.getText() != "") {
            if (factoryService.findEntityByName(textFieldFactoryName.getText()) == null) {
                if (passwordFieldFactoryPassword.getText() != "") {
                    anchorPane.setDisable(true);
                    factoryEntityNew.setName(textFieldFactoryName.getText());
                    factoryEntityNew.setPassword(security.passwordEncode(passwordFieldFactoryPassword.getText()));
                    factoryService.saveNewEntity(factoryEntityNew);
                    anchorPane.setDisable(false);

                }
            }
        }
        updateSplitButtonMenuSetProductCreateFactory();
        textFieldFactoryName.setText("");
        passwordFieldFactoryPassword.setText("");
    }

    @FXML
    private void buttonCreateTransporterOnAction(ActionEvent event) {
        if (textFieldTransporterName.getText() != "") {
            if (transporterService.findEntityByName(textFieldTransporterName.getText()) == null) {
                if (passwordFieldTransporterPassword.getText() != "") {
                    anchorPane.setDisable(true);
                    transporterEntityNew.setName(textFieldTransporterName.getText());
                    transporterEntityNew.setPassword(security.passwordEncode(passwordFieldTransporterPassword.getText()));
                    transporterService.saveNewEntity(transporterEntityNew);
                    anchorPane.setDisable(false);

                }
            }
        }
        transporterEntityNew = new TransporterEntity();
        textFieldTransporterName.setText("");
        passwordFieldTransporterPassword.setText("");
    }

    @FXML
    private void buttonCreateProductOnAction(ActionEvent event) {
        if (textFieldProductName.getText() != "") {
            if (productService.findEntityByName(textFieldProductName.getText()) == null) {
                anchorPane.setDisable(true);
                productEntityNew.setName(textFieldProductName.getText());
                productService.saveNewEntity(productEntityNew);
                anchorPane.setDisable(false);
            }
        }
        productEntityNew = new ProductEntity();
        textFieldProductName.setText("");
    }

    @FXML
    private void initialize() {
        checkBoxDelete.setSelected(false);
        splitMenuButtonSetProductDelete.setDisable(true);
        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stageThis = (Stage) this.anchorPane.getScene().getWindow();
                        transporterEntityNew = new TransporterEntity();
                        productEntityNew = new ProductEntity();
                        updateSplitButtonMenuSetProductCreateFactory();
                        updateSplitButtonMenuSetProductDeleteProduct();
                        updateSplitMenuButtonSetTransporterDelete();
                        updateSplitMenuButtonSetFactoryDelete();
                        ((Stage) newWindow).maximizedProperty().addListener((a, b, c) -> {
                            if (c) {
                                System.out.println("I am maximized!");
                            }
                        });
                    }
                });
            }
        });

    }

    private void updateSplitButtonMenuSetProductCreateFactory() {
        splitMenuButtonFactoryProduct.setText("Set Product");
        splitMenuButtonFactoryProduct.getItems().clear();
        List<ProductEntity> productEntityList = productService.findAllEntities();
        buttonCreateFactory.setDisable(true);
        for (ProductEntity productEntity: productEntityList) {
            MenuItem choice = new MenuItem(productEntity.getName());
            choice.setOnAction((e) -> {
                factoryEntityNew = new FactoryEntity();
                ProductEntity productEntity1 = productService.findEntityByName(choice.getText());
                factoryEntityNew.setExportId(productEntity1.getId());
                buttonCreateFactory.setDisable(false);
                splitMenuButtonFactoryProduct.setText(choice.getText());
            });
            splitMenuButtonFactoryProduct.getItems().add(choice);
        }
    }

    private void updateSplitButtonMenuSetProductDeleteProduct() {
        buttonDeleteProduct.setDisable(true);
        splitMenuButtonSetProductDelete.setText("Set Product");
        splitMenuButtonSetProductDelete.getItems().clear();
        List<ProductEntity> productEntityList = productService.findAllEntities();
        for (ProductEntity productEntity: productEntityList) {
            MenuItem choice = new MenuItem(productEntity.getName());
            choice.setOnAction((e) -> {
                buttonDeleteProduct.setDisable(false);
                productEntityForDelete = productService.findEntityByName(choice.getText());
                buttonCreateFactory.setDisable(false);
                splitMenuButtonSetProductDelete.setText(choice.getText());
            });
            splitMenuButtonSetProductDelete.getItems().add(choice);
        }
    }

    private void updateSplitMenuButtonSetTransporterDelete() {
        buttonDeleteTransporter.setDisable(true);
        splitMenuButtonSetTransporterDelete.setText("Set Transporter");
        splitMenuButtonSetTransporterDelete.getItems().clear();
        List<TransporterEntity> transporterEntityList = transporterService.findAllEntities();
        for (TransporterEntity transporterEntity: transporterEntityList) {
            MenuItem choice = new MenuItem(transporterEntity.getName());
            choice.setOnAction((e) -> {
                buttonDeleteTransporter.setDisable(false);
                transporterEntityForDelete = transporterService.findEntityByName(choice.getText());
                splitMenuButtonSetTransporterDelete.setText(choice.getText());
            });
            splitMenuButtonSetTransporterDelete.getItems().add(choice);
        }
    }


    private void updateSplitMenuButtonSetFactoryDelete() {
        buttonDeleteFactory.setDisable(true);
        splitMenuButtonSetFactoryDelete.setText("Set Factory");
        splitMenuButtonSetFactoryDelete.getItems().clear();
        List<FactoryEntity> factoryEntityList = factoryService.findAllEntities();
        for (FactoryEntity factoryEntity: factoryEntityList) {
            MenuItem choice = new MenuItem(factoryEntity.getName());
            choice.setOnAction((e) -> {
                buttonDeleteFactory.setDisable(false);
                factoryEntityForDelete = factoryService.findEntityByName(choice.getText());
                splitMenuButtonSetFactoryDelete.setText(choice.getText());
            });
            splitMenuButtonSetFactoryDelete.getItems().add(choice);
        }
    }

}

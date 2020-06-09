package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entities.OrderingEntity;
import sample.entities.TransporterEntity;
import sample.services.*;

import java.util.List;

@Component
@FxmlView("orderingTransporter.fxml")
public class OrderingTransporterController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelMaterial;
    @FXML
    private Label labelCustomer;
    @FXML
    private Label labelTransporter;
    @FXML
    private Label labelPriceName;
    @FXML
    private Label labelPriceValue;
    @FXML
    private Label labelSupplierName;
    @FXML
    private Label labelDistance;
    @FXML
    private Button buttonSetPrice;
    @FXML
    private TextField textFieldSetPrice;

    @Autowired
    FactoryServiceInterface factoryService;

    @Autowired
    OrderingServiceInterface orderingService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    TransporterServiceInterface transporterService;

    private Stage stageThis;

    private OrderingEntity orderingEntityThis;

    private TransporterEntity transporterEntityThis;

    private List<OrderingEntity> orderingEntities;

    private Boolean accepted;

    @FXML
    public void buttonSetPriceOnAction(ActionEvent event) {
        if (textFieldSetPrice.getText() != "") {
            int price = (int) Integer.valueOf(textFieldSetPrice.getText());
            if (orderingEntityThis.getPrice() !=null) {
                if (price < orderingEntityThis.getPrice()) {
                    orderingEntityThis.setAccepted(false);
                    orderingEntityThis.setPrice(Integer.valueOf(textFieldSetPrice.getText()));
                    orderingEntityThis.setIdTransporter(transporterEntityThis.getId());
                    orderingService.save(orderingEntityThis);
                    stageThis.close();
                } else {
                    textFieldSetPrice.setText("");
                }
            } else {
                orderingEntityThis.setAccepted(false);
                orderingEntityThis.setPrice(Integer.valueOf(textFieldSetPrice.getText()));
                orderingEntityThis.setIdTransporter(transporterEntityThis.getId());
                orderingService.save(orderingEntityThis);
                stageThis.close();
            }
        }else {
            textFieldSetPrice.setText("");
        }
    }

    @FXML
    private void textFieldSetPriceOnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().charAt(0) >= '0' && keyEvent.getCharacter().charAt(0) <= '9') {

        }else {
            textFieldSetPrice.setText("");
        }
    }

    @FXML
    private void initialize() {
        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stageThis = (Stage) this.anchorPane.getScene().getWindow();
                        String Title = stageThis.getTitle();
                        orderingEntityThis = orderingService.findEntityById(Integer.valueOf(String.valueOf(Title.charAt(0))));
                        transporterEntityThis = transporterService.findEntityById(Integer.valueOf(String.valueOf(Title.charAt(1))));
                        stageThis.setTitle(productService.findEntityById(orderingEntityThis.getIdProduct()).getName());
                        labelMaterial.setText(productService.findEntityById(orderingEntityThis.getIdProduct()).getName());
                        labelCustomer.setText(factoryService.findEntityById(orderingEntityThis.getIdFactory()).getName());
                        labelSupplierName.setText(factoryService.findEntityById(orderingEntityThis.getIdFactoryFrom()).getName());
                        labelPriceValue.setText(String.valueOf(orderingEntityThis.getPrice()));
                        //TODO
                        labelDistance.setText(String.valueOf(orderingEntityThis.getDistance()));
                        accepted=orderingEntityThis.isAccepted();
                        if (accepted) {
                            labelTransporter.setText(transporterService.findEntityById(orderingEntityThis.getIdTransporter()).getName());
                            buttonSetPrice.setVisible(false);
                        }else {
                            labelTransporter.setText("NOBODY");
                            buttonSetPrice.setVisible(true);
                        }
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

}
package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entities.OrderingEntity;
import sample.services.*;

import java.util.List;

@Component
@FxmlView("orderingFactory.fxml")
public class OrderingFactoryController {
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
    private Button buttonAcceptOrder;
    @FXML
    private Button buttonDeleteOrder;
    @FXML
    private Label labelDistance;


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

    private Boolean accepted;

    @FXML
    public void buttonAcceptOrderOnAction(ActionEvent event) {
        orderingEntityThis.setAccepted(true);
        orderingEntityThis.setPrice(Integer.valueOf(labelPriceValue.getText()));
        orderingService.save(orderingEntityThis);
        stageThis.close();
    }

    @FXML
    public void buttonDeleteOrderOnAction(ActionEvent event) {
        orderingService.deleteById(orderingEntityThis.getId());
        stageThis.close();
    }


    @FXML
    private void initialize() {
        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stageThis = (Stage) this.anchorPane.getScene().getWindow();
                        orderingEntityThis = orderingService.findEntityById(Integer.valueOf(stageThis.getTitle()));
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
                            buttonAcceptOrder.setVisible(false);
                        }else {
                            labelTransporter.setText("NOBODY");
                            buttonAcceptOrder.setVisible(true);
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

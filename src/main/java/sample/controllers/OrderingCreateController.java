package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entities.FactoryEntity;
import sample.entities.OrderingEntity;
import sample.entities.ProductEntity;
import sample.services.*;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("orderingCreate.fxml")
public class OrderingCreateController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private SplitMenuButton splitMenuButtonSetProduct;
    @FXML
    private Label labelProduct;
    @FXML
    private Label labelCustomer;
    @FXML
    private Label labelSupplier;
    @FXML
    private Label labelDistance;
    @FXML
    private Button buttonCreateOrder;


    @Autowired
    FactoryServiceInterface factoryService;

    @Autowired
    OrderingServiceInterface orderingService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    TransporterServiceInterface transporterService;

    private Stage stageThis;

    private FactoryEntity factoryEntityThis;

    private OrderingEntity orderingEntityNew = null;

    private void updateSplitButtonMenu() {
        splitMenuButtonSetProduct.getItems().clear();
        List<ProductEntity> productEntityList = productService.findAllEntities();
        for (ProductEntity productEntity: productEntityList) {
            MenuItem choice = new MenuItem(productEntity.getName());
            choice.setOnAction((e) -> {
                orderingEntityNew = new OrderingEntity();
                labelProduct.setText(productEntity.getName());
                labelCustomer.setText(factoryEntityThis.getName());
                List<FactoryEntity> factoryEntities = factoryService.findEntitiesByProductIdWithoutFactoryId(productEntity.getId(), factoryEntityThis.getId());
                if (factoryEntities.size() != 0) {
                    buttonCreateOrder.setDisable(false);
                    int min = Integer.MAX_VALUE;
                    FactoryEntity factoryEntityShortestWay;
                    for (FactoryEntity factoryEntity : factoryEntities) {
                        int weight = factoryService.shortestWayWeight(factoryEntityThis.getId(), factoryEntity.getId());
                        System.out.println(ShortestWay(factoryEntityThis.getId(), factoryEntity.getId()));
                        if (weight <= min) {
                            min = weight;
                            factoryEntityShortestWay = factoryEntity;
                            labelSupplier.setText(factoryEntityShortestWay.getName());
                            orderingEntityNew.setIdFactoryFrom(factoryEntityShortestWay.getId());
                            labelDistance.setText(String.valueOf(min));
                            //TODO
                        }
                    }
                    orderingEntityNew.setIdTransporter(null);
                    orderingEntityNew.setAccepted(false);
                    orderingEntityNew.setPrice(null);
                    orderingEntityNew.setIdFactory(factoryEntityThis.getId());
                    orderingEntityNew.setIdProduct(productEntity.getId());
                    orderingEntityNew.setDistance(min);
                }else {
                    labelSupplier.setText("Can't find supplier for this product");
                    labelDistance.setText("Can't find supplier for this product");
                    buttonCreateOrder.setDisable(true);
                }
            });
            splitMenuButtonSetProduct.getItems().add(choice);
        }
    }

    private String ShortestWay(int origId, int destId) {
        return factoryService.shortestWay(origId, destId) + " " + factoryService.shortestWayWeight(origId, destId);
    }

    @FXML
    private void buttonCreateOrderOnAction(ActionEvent event) {
        if (orderingEntityNew != null) {
            orderingService.saveNewEntity(orderingEntityNew);
            stageThis.close();
        }
    }

    @FXML
    private void initialize() {
        buttonCreateOrder.setDisable(true);
        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stageThis = (Stage) this.anchorPane.getScene().getWindow();
                        factoryEntityThis = factoryService.findEntityByName(stageThis.getTitle());
                        updateSplitButtonMenu();
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

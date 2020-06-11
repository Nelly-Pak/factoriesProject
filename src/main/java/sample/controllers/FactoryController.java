package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.JavaFxApplication;
import sample.entities.FactoryEntity;
import sample.entities.OrderingEntity;
import sample.services.FactoryServiceInterface;
import sample.services.OrderingServiceInterface;
import sample.services.ProductServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("factory.fxml")
public class FactoryController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buttonCreateOrder;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Label labelListView;
    @FXML
    private Label labelFactoryName;
    @FXML
    private ListView listViewAcceptedOrders;
    @FXML
    private ListView listViewUnacceptedOrders;

    @Autowired
    private FactoryServiceInterface factoryService;

    @Autowired
    private OrderingServiceInterface orderingService;

    @Autowired
    private ProductServiceInterface productService;

    private ObservableList<String> forListViewAcceptedOrders = FXCollections.observableArrayList();

    private ObservableList<String> forListViewUnacceptedOrders = FXCollections.observableArrayList();

    private List<OrderingEntity> orderingEntitiesAccepted = new ArrayList<>();

    private List<OrderingEntity> orderingEntitiesUnaccepted = new ArrayList<>();

    private Stage stageThis;

    private FactoryEntity factoryEntityThis;

    private List<OrderingEntity> orderingEntities;

    @FXML
    private void buttonCreateOrderOnAction(ActionEvent event) {
        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
        Parent root = fxWeaver.loadView(OrderingCreateController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(factoryEntityThis.getName());
        stage.setScene(scene);
        stage.showAndWait();
        System.out.println("SHowAndWaitENDED");
        updateListViewAcceptedOrders();
        updateListViewUnacceptedOrders();
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
    private void listViewAcceptedOrdersOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                if (listViewAcceptedOrders.getItems().size() != 0) {
                    int orderIdFromListView = listViewAcceptedOrders.getSelectionModel().getSelectedIndex();
                    if (orderIdFromListView <= orderingEntitiesAccepted.size() && orderIdFromListView > -1) {
                        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                        Parent root = fxWeaver.loadView(OrderingFactoryController.class);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(String.valueOf(orderingEntitiesAccepted.get(orderIdFromListView).getId()));
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                    }
                }
            }
        }

    }

    @FXML
    private void listViewUnacceptedOrdersOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                if (listViewUnacceptedOrders.getItems().size() != 0) {
                    int orderIdFromListView = listViewUnacceptedOrders.getSelectionModel().getSelectedIndex();
                    if (orderIdFromListView <= orderingEntitiesUnaccepted.size() && orderIdFromListView > -1) {
                        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                        Parent root = fxWeaver.loadView(OrderingFactoryController.class);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(String.valueOf(orderingEntitiesUnaccepted.get(orderIdFromListView).getId()));
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                    }
                }
            }
        }
    }

    private void updateListViewAcceptedOrders() {
        forListViewAcceptedOrders = FXCollections.observableArrayList();
        orderingEntitiesAccepted = new ArrayList<>();
        orderingEntities = orderingService.findEntitiesByFactoryIdAccepted(factoryEntityThis.getId());
        listViewAcceptedOrders.setItems(forListViewAcceptedOrders);
        int k = 0;
        for (OrderingEntity orderingEntity : orderingEntities) {
            k++;
            forListViewAcceptedOrders.add(String.valueOf(k) + ". " + productService.findEntityById(orderingEntity.getIdProduct()).getName());
            orderingEntitiesAccepted.add(orderingEntity);
        }
        listViewAcceptedOrders.setItems(forListViewAcceptedOrders);
    }

    private void updateListViewUnacceptedOrders() {
        forListViewUnacceptedOrders = FXCollections.observableArrayList();
        orderingEntitiesUnaccepted= new ArrayList<>();
        orderingEntities = orderingService.findEntitiesByFactoryIdUnaccepted(factoryEntityThis.getId());
        listViewUnacceptedOrders.setItems(forListViewUnacceptedOrders);
        int k = 0;
        for (OrderingEntity orderingEntity : orderingEntities) {
            k++;
            forListViewUnacceptedOrders.add(String.valueOf(k) + ". " + productService.findEntityById(orderingEntity.getIdProduct()).getName());
            orderingEntitiesUnaccepted.add(orderingEntity);
        }
        listViewUnacceptedOrders.setItems(forListViewUnacceptedOrders);
    }

    @FXML
    private void initialize() {
        anchorPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stageThis = (Stage) this.anchorPane.getScene().getWindow();
                        factoryEntityThis = factoryService.findEntityByName(stageThis.getTitle());
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
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

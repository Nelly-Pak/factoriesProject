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
import sample.entities.OrderingEntity;
import sample.entities.TransporterEntity;
import sample.services.*;

import java.util.ArrayList;
import java.util.List;


@Component
@FxmlView("transporter.fxml")
public class TransporterController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buttonLogOut;
    @FXML
    private ListView listViewAcceptedOrders;
    @FXML
    private ListView listViewUnacceptedOrders;
    @FXML
    private ListView listViewBidedOrders;
    @FXML
    private Label labelAcceptedOrders;
    @FXML
    private Label labelUnacceptedOrders;

    @Autowired
    FactoryServiceInterface factoryService;

    @Autowired
    OrderingServiceInterface orderingService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    TransporterServiceInterface transporterService;

    private ObservableList<String> forListViewAcceptedOrders = FXCollections.observableArrayList();

    private ObservableList<String> forListViewUnacceptedOrders = FXCollections.observableArrayList();

    private ObservableList<String> forListViewBidedOrders = FXCollections.observableArrayList();

    private List<OrderingEntity> orderingEntitiesAccepted = new ArrayList<>();

    private List<OrderingEntity> orderingEntitiesUnaccepted = new ArrayList<>();

    private List<OrderingEntity> orderingEntitiesBided = new ArrayList<>();

    private Stage stageThis;

    private TransporterEntity transporterEntityThis;

    private List<OrderingEntity> orderingEntities;

    @FXML
    private void buttonLogOutOnAction(ActionEvent event) {
        anchorPane.setDisable(true);
        Stage stageThis = (Stage) buttonLogOut.getScene().getWindow();;
        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stageThis.close();
    }

    private void updateListViewAcceptedOrders() {
        forListViewAcceptedOrders = FXCollections.observableArrayList();
        orderingEntitiesAccepted = new ArrayList<>();
        orderingEntities = orderingService.findEntitiesByTransporterIdAccepted(transporterEntityThis.getId());
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
        orderingEntitiesUnaccepted = new ArrayList<>();
        orderingEntities = orderingService.findEntitiesWithoutIdTransporterUnaccepted(transporterEntityThis.getId());
        listViewUnacceptedOrders.setItems(forListViewUnacceptedOrders);
        System.out.println(orderingEntities.size());
        int k = 0;
        for (OrderingEntity orderingEntity : orderingEntities) {
            k++;
            forListViewUnacceptedOrders.add(String.valueOf(k) + ". " + productService.findEntityById(orderingEntity.getIdProduct()).getName());
            orderingEntitiesUnaccepted.add(orderingEntity);
        }
        listViewUnacceptedOrders.setItems(forListViewUnacceptedOrders);
    }

    private void updateListViewBidedOrders() {
        forListViewBidedOrders = FXCollections.observableArrayList();
        orderingEntitiesBided= new ArrayList<>();
        orderingEntities = orderingService.findEntitiesByTransporterIdUnaccepted(transporterEntityThis.getId());
        listViewBidedOrders.setItems(forListViewBidedOrders);
        int k = 0;
        for (OrderingEntity orderingEntity : orderingEntities) {
            k++;
            forListViewBidedOrders.add(String.valueOf(k) + ". " + productService.findEntityById(orderingEntity.getIdProduct()).getName());
            orderingEntitiesBided.add(orderingEntity);
        }
        listViewBidedOrders.setItems(forListViewBidedOrders);
    }

    @FXML
    private void listViewAcceptedOrdersOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                if (listViewAcceptedOrders.getItems().size() != 0) {
                    int orderIdFromListView = listViewAcceptedOrders.getSelectionModel().getSelectedIndex();
                    if (orderIdFromListView <= orderingEntitiesAccepted.size() && orderIdFromListView > -1) {
                        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                        Parent root = fxWeaver.loadView(OrderingTransporterController.class);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(String.valueOf(orderingEntitiesAccepted.get(orderIdFromListView).getId()) + String.valueOf(transporterEntityThis.getId()));
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                        updateListViewBidedOrders();
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
                        Parent root = fxWeaver.loadView(OrderingTransporterController.class);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(String.valueOf(orderingEntitiesUnaccepted.get(orderIdFromListView).getId()) + String.valueOf(transporterEntityThis.getId()));
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                        updateListViewBidedOrders();
                    }
                }
            }
        }
    }

    @FXML
    private void listViewBidedOrdersOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                if (listViewBidedOrders.getItems().size() != 0) {
                    int orderIdFromListView = listViewBidedOrders.getSelectionModel().getSelectedIndex();
                    if (orderIdFromListView <= orderingEntitiesBided.size() && orderIdFromListView > -1) {
                        FxWeaver fxWeaver = JavaFxApplication.getFxWeaver();
                        Parent root = fxWeaver.loadView(OrderingTransporterController.class);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(String.valueOf(orderingEntitiesBided.get(orderIdFromListView).getId()) + String.valueOf(transporterEntityThis.getId()));
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                        updateListViewBidedOrders();
                    }
                }
            }
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
                        transporterEntityThis = transporterService.findEntityByName(stageThis.getTitle());
                        updateListViewAcceptedOrders();
                        updateListViewUnacceptedOrders();
                        updateListViewBidedOrders();
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

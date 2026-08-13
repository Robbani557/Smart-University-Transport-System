package admin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Golam Robbani
 */
public class ManageBuses extends Application {

    private TableView<Bus> table =
            new TableView<Bus>();

    private ObservableList<Bus> busList =
            FXCollections.observableArrayList();

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String RED = "#C62828";
    private static final String GREEN = "#2E7D32";
    private static final String ORANGE = "#EF6C00";

    @Override
    public void start(Stage stage) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                LIGHT + ";"
        );

        // ==========================================
        // SIDEBAR
        // ==========================================

        VBox sidebar =
                createSidebar(stage);

        root.setLeft(sidebar);

        // ==========================================
        // TOP BAR
        // ==========================================

        HBox topBar =
                createTopBar();

        root.setTop(topBar);

        // ==========================================
        // MAIN CONTENT
        // ==========================================

        VBox content =
                new VBox(15);

        content.setPadding(
                new Insets(20)
        );

        // ==========================================
        // TITLE AREA
        // ==========================================

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Manage Buses");

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        Region headingSpacer =
                new Region();

        HBox.setHgrow(
                headingSpacer,
                Priority.ALWAYS
        );

        Button addBus =
                new Button("+ Add Bus");

        addBus.setPrefWidth(110);
        addBus.setPrefHeight(38);

        addBus.setTextFill(
                Color.WHITE
        );

        addBus.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        addBus.setStyle(
                "-fx-background-color: " +
                BLUE + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        addBus.setOnAction(
                e -> showAddBusDialog()
        );

        heading.getChildren().addAll(
                title,
                headingSpacer,
                addBus
        );

        // ==========================================
        // SEARCH
        // ==========================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search bus by ID, number or type..."
        );

        searchField.setPrefWidth(350);
        searchField.setPrefHeight(38);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        searchBus(newValue)
        );

        // ==========================================
        // TABLE
        // ==========================================

        createTable();

        loadBuses();

        VBox tableBox =
                new VBox();

        tableBox.setPadding(
                new Insets(15)
        );

        tableBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        tableBox.getChildren().add(
                table
        );

        VBox.setVgrow(
                tableBox,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                heading,
                searchField,
                tableBox
        );

        VBox.setVgrow(
                tableBox,
                Priority.ALWAYS
        );

        root.setCenter(content);

        // ==========================================
        // SCENE
        // ==========================================

        Scene scene =
                new Scene(
                        root,
                        1200,
                        760
                );

        stage.setTitle(
                "Smart University Transport System - Manage Buses"
        );

        stage.setScene(scene);

        stage.show();
    }

    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        launch(args);

    }

    // ==========================================
    // SIDEBAR
    // ==========================================

    private VBox createSidebar(Stage stage) {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(180);

        sidebar.setStyle(
                "-fx-background-color: " +
                BLUE + ";"
        );

        Label logo =
                new Label("🚌 SUTS");

        logo.setTextFill(
                Color.WHITE
        );

        logo.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        18
                )
        );

        Label admin =
                new Label("Admin Panel");

        admin.setTextFill(
                Color.WHITE
        );

        admin.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        VBox logoBox =
                new VBox(
                        2,
                        logo,
                        admin
                );

        logoBox.setPadding(
                new Insets(15)
        );

        VBox menu =
                new VBox(5);

        menu.setPadding(
                new Insets(10)
        );

        Button dashboard =
                createMenuButton(
                        "⌂   Dashboard"
                );

        Button students =
                createMenuButton(
                        "♙   Manage Students"
                );

        Button routes =
                createMenuButton(
                        "⌁   Manage Routes"
                );

        Button buses =
                createMenuButton(
                        "▣   Manage Buses"
                );

        Button allocation =
                createMenuButton(
                        "⇄   Bus Allocation"
                );

        Button schedules =
                createMenuButton(
                        "◷   Schedules"
                );

        Button bookings =
                createMenuButton(
                        "▤   Bookings"
                );

        Button reports =
                createMenuButton(
                        "▥   Reports"
                );

        Button settings =
                createMenuButton(
                        "⚙   Settings"
                );

        // Active button
        buses.setStyle(
                "-fx-background-color: " +
                BLUE2 + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;"
        );

        // ==========================================
        // NAVIGATION
        // ==========================================

        dashboard.setOnAction(e -> {

            showMessage(
                    "Dashboard",
                    "Dashboard page."
            );

        });

        students.setOnAction(e -> {

            ManageStudents page =
                    new ManageStudents();

            page.start(stage);

        });

        routes.setOnAction(e -> {

            ManageRoutes page =
                    new ManageRoutes();

            page.start(stage);

        });

        buses.setOnAction(e -> {

            // Current page

        });

        allocation.setOnAction(e -> {

            showMessage(
                    "Bus Allocation",
                    "Bus Allocation page will open here."
            );

        });

        schedules.setOnAction(e -> {

            showMessage(
                    "Schedules",
                    "Schedules page."
            );

        });

        bookings.setOnAction(e -> {

            showMessage(
                    "Bookings",
                    "Bookings page."
            );

        });

        reports.setOnAction(e -> {

            showMessage(
                    "Reports",
                    "Reports page."
            );

        });

        settings.setOnAction(e -> {

            showMessage(
                    "Settings",
                    "Settings page."
            );

        });

        menu.getChildren().addAll(
                dashboard,
                students,
                routes,
                buses,
                allocation,
                schedules,
                bookings,
                reports,
                settings
        );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button logout =
                createMenuButton(
                        "⇥   Logout"
                );

        logout.setOnAction(e -> {

            showMessage(
                    "Logout",
                    "You have been logged out."
            );

        });

        sidebar.getChildren().addAll(
                logoBox,
                menu,
                spacer,
                logout
        );

        return sidebar;
    }

    // ==========================================
    // MENU BUTTON
    // ==========================================

    private Button createMenuButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(40);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(10)
        );

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // ==========================================
    // TOP BAR
    // ==========================================

    private HBox createTopBar() {

        HBox topBar =
                new HBox();

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        topBar.setPrefHeight(58);

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";"
        );

        Label menu =
                new Label("☰");

        menu.setFont(
                Font.font(
                        "Segoe UI",
                        20
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification =
                new Label("♧");

        notification.setFont(
                Font.font(
                        "Segoe UI",
                        19
                )
        );

        Label user =
                new Label(
                        "Admin User\nSuper Admin"
                );

        user.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        topBar.getChildren().addAll(
                menu,
                spacer,
                notification,
                user
        );

        return topBar;
    }

    // ==========================================
    // CREATE TABLE
    // ==========================================

    private void createTable() {

        TableColumn<Bus, String> busId =
                new TableColumn<Bus, String>(
                        "Bus ID"
                );

        busId.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "busId"
                )
        );

        TableColumn<Bus, String> busNumber =
                new TableColumn<Bus, String>(
                        "Bus Number"
                );

        busNumber.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "busNumber"
                )
        );

        TableColumn<Bus, String> busType =
                new TableColumn<Bus, String>(
                        "Bus Type"
                );

        busType.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "busType"
                )
        );

        TableColumn<Bus, String> capacity =
                new TableColumn<Bus, String>(
                        "Capacity"
                );

        capacity.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "capacity"
                )
        );

        TableColumn<Bus, String> driver =
                new TableColumn<Bus, String>(
                        "Driver"
                );

        driver.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "driver"
                )
        );

        TableColumn<Bus, String> status =
                new TableColumn<Bus, String>(
                        "Status"
                );

        status.setCellValueFactory(
                new PropertyValueFactory<Bus, String>(
                        "status"
                )
        );

        TableColumn<Bus, Void> action =
                new TableColumn<Bus, Void>(
                        "Action"
                );

        action.setCellFactory(
                column -> new TableCell<Bus, Void>() {

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(5);

                    {
                        edit.setStyle(
                                "-fx-background-color: #1565C0;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 4px;" +
                                "-fx-cursor: hand;"
                        );

                        delete.setStyle(
                                "-fx-background-color: #C62828;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 4px;" +
                                "-fx-cursor: hand;"
                        );

                        edit.setOnAction(e -> {

                            Bus bus =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            editBus(bus);

                        });

                        delete.setOnAction(e -> {

                            Bus bus =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteBus(bus);

                        });

                        box.setAlignment(
                                Pos.CENTER
                        );

                        box.getChildren().addAll(
                                edit,
                                delete
                        );
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty
                    ) {

                        super.updateItem(
                                item,
                                empty
                        );

                        if (empty) {

                            setGraphic(null);

                        } else {

                            setGraphic(box);

                        }
                    }
                }
        );

        table.getColumns().addAll(
                busId,
                busNumber,
                busType,
                capacity,
                driver,
                status,
                action
        );

        table.setItems(
                busList
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(40);
    }

    // ==========================================
    // LOAD BUSES
    // ==========================================

    private void loadBuses() {

        busList.clear();

        busList.addAll(

                new Bus(
                        "B001",
                        "DHK-11-1001",
                        "University Bus",
                        "45",
                        "Abdul Karim",
                        "Active"
                ),

                new Bus(
                        "B002",
                        "DHK-11-1002",
                        "University Bus",
                        "50",
                        "Rahim Uddin",
                        "Active"
                ),

                new Bus(
                        "B003",
                        "DHK-11-1003",
                        "AC Bus",
                        "40",
                        "Karim Ahmed",
                        "Active"
                ),

                new Bus(
                        "B004",
                        "DHK-11-1004",
                        "University Bus",
                        "45",
                        "Hasan Ali",
                        "Maintenance"
                ),

                new Bus(
                        "B005",
                        "DHK-11-1005",
                        "AC Bus",
                        "40",
                        "Jamal Hossain",
                        "Active"
                ),

                new Bus(
                        "B006",
                        "DHK-11-1006",
                        "University Bus",
                        "50",
                        "Sabbir Ahmed",
                        "Inactive"
                ),

                new Bus(
                        "B007",
                        "DHK-11-1007",
                        "University Bus",
                        "45",
                        "Rashed Khan",
                        "Active"
                )
        );
    }

    // ==========================================
    // SEARCH BUS
    // ==========================================

    private void searchBus(
            String keyword
    ) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            table.setItems(
                    busList
            );

            return;
        }

        String search =
                keyword.toLowerCase();

        ObservableList<Bus> result =
                FXCollections.observableArrayList();

        for (Bus bus : busList) {

            if (
                    bus.getBusId()
                            .toLowerCase()
                            .contains(search)

                    ||

                    bus.getBusNumber()
                            .toLowerCase()
                            .contains(search)

                    ||

                    bus.getBusType()
                            .toLowerCase()
                            .contains(search)

                    ||

                    bus.getDriver()
                            .toLowerCase()
                            .contains(search)

                    ||

                    bus.getStatus()
                            .toLowerCase()
                            .contains(search)
            ) {

                result.add(bus);
            }
        }

        table.setItems(result);
    }

    // ==========================================
    // ADD BUS
    // ==========================================

    private void showAddBusDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "Add Bus"
        );

        dialog.setHeaderText(
                "Add New Bus"
        );

        TextField busId =
                new TextField();

        busId.setPromptText(
                "Bus ID"
        );

        TextField busNumber =
                new TextField();

        busNumber.setPromptText(
                "Bus Number"
        );

        ComboBox<String> busType =
                new ComboBox<String>();

        busType.getItems().addAll(
                "University Bus",
                "AC Bus",
                "Mini Bus"
        );

        busType.setValue(
                "University Bus"
        );

        TextField capacity =
                new TextField();

        capacity.setPromptText(
                "Capacity"
        );

        TextField driver =
                new TextField();

        driver.setPromptText(
                "Driver Name"
        );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Inactive",
                "Maintenance"
        );

        status.setValue(
                "Active"
        );

        VBox form =
                new VBox(10);

        form.setPadding(
                new Insets(15)
        );

        form.getChildren().addAll(

                new Label("Bus ID"),
                busId,

                new Label("Bus Number"),
                busNumber,

                new Label("Bus Type"),
                busType,

                new Label("Capacity"),
                capacity,

                new Label("Driver"),
                driver,

                new Label("Status"),
                status
        );

        dialog.getDialogPane()
                .setContent(form);

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.CANCEL,
                        ButtonType.OK
                );

        dialog.showAndWait()
                .ifPresent(result -> {

                    if (result ==
                            ButtonType.OK) {

                        if (
                                busId.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                busNumber.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                capacity.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                driver.getText()
                                        .trim()
                                        .isEmpty()
                        ) {

                            showMessage(
                                    "Error",
                                    "Please fill all required fields."
                            );

                            return;
                        }

                        Bus bus =
                                new Bus(
                                        busId.getText(),
                                        busNumber.getText(),
                                        busType.getValue(),
                                        capacity.getText(),
                                        driver.getText(),
                                        status.getValue()
                                );

                        busList.add(bus);

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // EDIT BUS
    // ==========================================

    private void editBus(
            Bus bus
    ) {

        TextField busNumber =
                new TextField(
                        bus.getBusNumber()
                );

        ComboBox<String> busType =
                new ComboBox<String>();

        busType.getItems().addAll(
                "University Bus",
                "AC Bus",
                "Mini Bus"
        );

        busType.setValue(
                bus.getBusType()
        );

        TextField capacity =
                new TextField(
                        bus.getCapacity()
                );

        TextField driver =
                new TextField(
                        bus.getDriver()
                );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Inactive",
                "Maintenance"
        );

        status.setValue(
                bus.getStatus()
        );

        VBox form =
                new VBox(10);

        form.setPadding(
                new Insets(15)
        );

        form.getChildren().addAll(

                new Label("Bus Number"),
                busNumber,

                new Label("Bus Type"),
                busType,

                new Label("Capacity"),
                capacity,

                new Label("Driver"),
                driver,

                new Label("Status"),
                status
        );

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "Edit Bus"
        );

        dialog.setHeaderText(
                "Edit " + bus.getBusId()
        );

        dialog.getDialogPane()
                .setContent(form);

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.CANCEL,
                        ButtonType.OK
                );

        dialog.showAndWait()
                .ifPresent(result -> {

                    if (result ==
                            ButtonType.OK) {

                        bus.setBusNumber(
                                busNumber.getText()
                        );

                        bus.setBusType(
                                busType.getValue()
                        );

                        bus.setCapacity(
                                capacity.getText()
                        );

                        bus.setDriver(
                                driver.getText()
                        );

                        bus.setStatus(
                                status.getValue()
                        );

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // DELETE BUS
    // ==========================================

    private void deleteBus(
            Bus bus
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle(
                "Delete Bus"
        );

        alert.setHeaderText(
                "Delete " + bus.getBusNumber() + "?"
        );

        alert.setContentText(
                "Are you sure you want to delete this bus?"
        );

        alert.showAndWait()
                .ifPresent(result -> {

                    if (result ==
                            ButtonType.OK) {

                        busList.remove(bus);
                    }
                });
    }

    // ==========================================
    // MESSAGE
    // ==========================================

    private void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // ==========================================
    // BUS MODEL CLASS
    // ==========================================

    public static class Bus {

        private String busId;
        private String busNumber;
        private String busType;
        private String capacity;
        private String driver;
        private String status;

        public Bus(
                String busId,
                String busNumber,
                String busType,
                String capacity,
                String driver,
                String status
        ) {

            this.busId = busId;
            this.busNumber = busNumber;
            this.busType = busType;
            this.capacity = capacity;
            this.driver = driver;
            this.status = status;
        }

        public String getBusId() {

            return busId;
        }

        public String getBusNumber() {

            return busNumber;
        }

        public String getBusType() {

            return busType;
        }

        public String getCapacity() {

            return capacity;
        }

        public String getDriver() {

            return driver;
        }

        public String getStatus() {

            return status;
        }

        public void setBusNumber(
                String busNumber
        ) {

            this.busNumber = busNumber;
        }

        public void setBusType(
                String busType
        ) {

            this.busType = busType;
        }

        public void setCapacity(
                String capacity
        ) {

            this.capacity = capacity;
        }

        public void setDriver(
                String driver
        ) {

            this.driver = driver;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}
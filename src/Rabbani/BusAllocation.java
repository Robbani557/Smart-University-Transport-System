package smart.university.transport.system;

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
public class BusAllocation extends Application {

    private TableView<Allocation> table =
            new TableView<Allocation>();

    private ObservableList<Allocation> allocationList =
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
        // TITLE
        // ==========================================

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Bus Allocation");

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

        Button allocateButton =
                new Button("+ New Allocation");

        allocateButton.setPrefWidth(140);
        allocateButton.setPrefHeight(38);

        allocateButton.setTextFill(
                Color.WHITE
        );

        allocateButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        allocateButton.setStyle(
                "-fx-background-color: " +
                BLUE + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        allocateButton.setOnAction(
                e -> showAllocationDialog()
        );

        heading.getChildren().addAll(
                title,
                headingSpacer,
                allocateButton
        );

        // ==========================================
        // SUMMARY CARDS
        // ==========================================

        HBox cards =
                new HBox(12);

        VBox totalCard =
                createSummaryCard(
                        "Total Allocations",
                        "7",
                        BLUE
                );

        VBox activeCard =
                createSummaryCard(
                        "Active",
                        "5",
                        GREEN
                );

        VBox pendingCard =
                createSummaryCard(
                        "Pending",
                        "1",
                        ORANGE
                );

        VBox availableCard =
                createSummaryCard(
                        "Unallocated Buses",
                        "3",
                        RED
                );

        cards.getChildren().addAll(
                totalCard,
                activeCard,
                pendingCard,
                availableCard
        );

        // ==========================================
        // SEARCH
        // ==========================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search route, bus or driver..."
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
                        searchAllocation(newValue)
        );

        // ==========================================
        // TABLE
        // ==========================================

        createTable();

        loadAllocations();

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
                cards,
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
                "Smart University Transport System - Bus Allocation"
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
        allocation.setStyle(
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

            ManageBuses page =
                    new ManageBuses();

            page.start(stage);

        });

        allocation.setOnAction(e -> {

            // Current page

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
    // SUMMARY CARD
    // ==========================================

    private VBox createSummaryCard(
            String title,
            String value,
            String color
    ) {

        VBox card =
                new VBox(5);

        card.setPadding(
                new Insets(15)
        );

        card.setPrefHeight(90);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        24
                )
        );

        valueLabel.setTextFill(
                Color.web(color)
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return card;
    }

    // ==========================================
    // CREATE TABLE
    // ==========================================

    private void createTable() {

        TableColumn<Allocation, String> allocationId =
                new TableColumn<Allocation, String>(
                        "Allocation ID"
                );

        allocationId.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "allocationId"
                )
        );

        TableColumn<Allocation, String> route =
                new TableColumn<Allocation, String>(
                        "Route"
                );

        route.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "route"
                )
        );

        TableColumn<Allocation, String> bus =
                new TableColumn<Allocation, String>(
                        "Bus Number"
                );

        bus.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "bus"
                )
        );

        TableColumn<Allocation, String> driver =
                new TableColumn<Allocation, String>(
                        "Driver"
                );

        driver.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "driver"
                )
        );

        TableColumn<Allocation, String> shift =
                new TableColumn<Allocation, String>(
                        "Shift"
                );

        shift.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "shift"
                )
        );

        TableColumn<Allocation, String> date =
                new TableColumn<Allocation, String>(
                        "Date"
                );

        date.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "date"
                )
        );

        TableColumn<Allocation, String> status =
                new TableColumn<Allocation, String>(
                        "Status"
                );

        status.setCellValueFactory(
                new PropertyValueFactory<Allocation, String>(
                        "status"
                )
        );

        TableColumn<Allocation, Void> action =
                new TableColumn<Allocation, Void>(
                        "Action"
                );

        action.setCellFactory(
                column -> new TableCell<Allocation, Void>() {

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

                            Allocation allocation =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            editAllocation(
                                    allocation
                            );

                        });

                        delete.setOnAction(e -> {

                            Allocation allocation =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteAllocation(
                                    allocation
                            );

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
                allocationId,
                route,
                bus,
                driver,
                shift,
                date,
                status,
                action
        );

        table.setItems(
                allocationList
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(40);
    }

    // ==========================================
    // LOAD ALLOCATIONS
    // ==========================================

    private void loadAllocations() {

        allocationList.clear();

        allocationList.addAll(

                new Allocation(
                        "A001",
                        "Mirpur - University",
                        "DHK-11-1001",
                        "Abdul Karim",
                        "Morning",
                        "08-08-2026",
                        "Active"
                ),

                new Allocation(
                        "A002",
                        "Uttara - University",
                        "DHK-11-1002",
                        "Rahim Uddin",
                        "Morning",
                        "08-08-2026",
                        "Active"
                ),

                new Allocation(
                        "A003",
                        "Dhanmondi - University",
                        "DHK-11-1003",
                        "Karim Ahmed",
                        "Morning",
                        "08-08-2026",
                        "Active"
                ),

                new Allocation(
                        "A004",
                        "Mohammadpur - University",
                        "DHK-11-1004",
                        "Hasan Ali",
                        "Evening",
                        "08-08-2026",
                        "Pending"
                ),

                new Allocation(
                        "A005",
                        "Badda - University",
                        "DHK-11-1005",
                        "Jamal Hossain",
                        "Morning",
                        "08-08-2026",
                        "Active"
                ),

                new Allocation(
                        "A006",
                        "Gulshan - University",
                        "DHK-11-1007",
                        "Rashed Khan",
                        "Evening",
                        "08-08-2026",
                        "Active"
                )
        );
    }

    // ==========================================
    // SEARCH
    // ==========================================

    private void searchAllocation(
            String keyword
    ) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            table.setItems(
                    allocationList
            );

            return;
        }

        String search =
                keyword.toLowerCase();

        ObservableList<Allocation> result =
                FXCollections.observableArrayList();

        for (Allocation allocation :
                allocationList) {

            if (
                    allocation.getAllocationId()
                            .toLowerCase()
                            .contains(search)

                    ||

                    allocation.getRoute()
                            .toLowerCase()
                            .contains(search)

                    ||

                    allocation.getBus()
                            .toLowerCase()
                            .contains(search)

                    ||

                    allocation.getDriver()
                            .toLowerCase()
                            .contains(search)

                    ||

                    allocation.getShift()
                            .toLowerCase()
                            .contains(search)

                    ||

                    allocation.getStatus()
                            .toLowerCase()
                            .contains(search)
            ) {

                result.add(
                        allocation
                );
            }
        }

        table.setItems(result);
    }

    // ==========================================
    // NEW ALLOCATION
    // ==========================================

    private void showAllocationDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "New Bus Allocation"
        );

        dialog.setHeaderText(
                "Allocate Bus to Route"
        );

        TextField allocationId =
                new TextField();

        allocationId.setPromptText(
                "Allocation ID"
        );

        ComboBox<String> route =
                new ComboBox<String>();

        route.getItems().addAll(
                "Mirpur - University",
                "Uttara - University",
                "Dhanmondi - University",
                "Mohammadpur - University",
                "Badda - University",
                "Gulshan - University"
        );

        route.setValue(
                "Mirpur - University"
        );

        ComboBox<String> bus =
                new ComboBox<String>();

        bus.getItems().addAll(
                "DHK-11-1001",
                "DHK-11-1002",
                "DHK-11-1003",
                "DHK-11-1004",
                "DHK-11-1005",
                "DHK-11-1006",
                "DHK-11-1007"
        );

        bus.setValue(
                "DHK-11-1001"
        );

        ComboBox<String> driver =
                new ComboBox<String>();

        driver.getItems().addAll(
                "Abdul Karim",
                "Rahim Uddin",
                "Karim Ahmed",
                "Hasan Ali",
                "Jamal Hossain",
                "Sabbir Ahmed",
                "Rashed Khan"
        );

        driver.setValue(
                "Abdul Karim"
        );

        ComboBox<String> shift =
                new ComboBox<String>();

        shift.getItems().addAll(
                "Morning",
                "Afternoon",
                "Evening"
        );

        shift.setValue(
                "Morning"
        );

        TextField date =
                new TextField();

        date.setPromptText(
                "DD-MM-YYYY"
        );

        date.setText(
                "08-08-2026"
        );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Pending"
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

                new Label("Allocation ID"),
                allocationId,

                new Label("Route"),
                route,

                new Label("Bus"),
                bus,

                new Label("Driver"),
                driver,

                new Label("Shift"),
                shift,

                new Label("Date"),
                date,

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
                                allocationId.getText()
                                        .trim()
                                        .isEmpty()
                        ) {

                            showMessage(
                                    "Error",
                                    "Please enter Allocation ID."
                            );

                            return;
                        }

                        Allocation newAllocation =
                                new Allocation(
                                        allocationId.getText(),
                                        route.getValue(),
                                        bus.getValue(),
                                        driver.getValue(),
                                        shift.getValue(),
                                        date.getText(),
                                        status.getValue()
                                );

                        allocationList.add(
                                newAllocation
                        );

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // EDIT ALLOCATION
    // ==========================================

    private void editAllocation(
            Allocation allocation
    ) {

        ComboBox<String> route =
                new ComboBox<String>();

        route.getItems().addAll(
                "Mirpur - University",
                "Uttara - University",
                "Dhanmondi - University",
                "Mohammadpur - University",
                "Badda - University",
                "Gulshan - University"
        );

        route.setValue(
                allocation.getRoute()
        );

        ComboBox<String> bus =
                new ComboBox<String>();

        bus.getItems().addAll(
                "DHK-11-1001",
                "DHK-11-1002",
                "DHK-11-1003",
                "DHK-11-1004",
                "DHK-11-1005",
                "DHK-11-1006",
                "DHK-11-1007"
        );

        bus.setValue(
                allocation.getBus()
        );

        ComboBox<String> driver =
                new ComboBox<String>();

        driver.getItems().addAll(
                "Abdul Karim",
                "Rahim Uddin",
                "Karim Ahmed",
                "Hasan Ali",
                "Jamal Hossain",
                "Sabbir Ahmed",
                "Rashed Khan"
        );

        driver.setValue(
                allocation.getDriver()
        );

        ComboBox<String> shift =
                new ComboBox<String>();

        shift.getItems().addAll(
                "Morning",
                "Afternoon",
                "Evening"
        );

        shift.setValue(
                allocation.getShift()
        );

        TextField date =
                new TextField(
                        allocation.getDate()
                );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Pending"
        );

        status.setValue(
                allocation.getStatus()
        );

        VBox form =
                new VBox(10);

        form.setPadding(
                new Insets(15)
        );

        form.getChildren().addAll(

                new Label("Route"),
                route,

                new Label("Bus"),
                bus,

                new Label("Driver"),
                driver,

                new Label("Shift"),
                shift,

                new Label("Date"),
                date,

                new Label("Status"),
                status
        );

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "Edit Allocation"
        );

        dialog.setHeaderText(
                "Edit " +
                allocation.getAllocationId()
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

                        allocation.setRoute(
                                route.getValue()
                        );

                        allocation.setBus(
                                bus.getValue()
                        );

                        allocation.setDriver(
                                driver.getValue()
                        );

                        allocation.setShift(
                                shift.getValue()
                        );

                        allocation.setDate(
                                date.getText()
                        );

                        allocation.setStatus(
                                status.getValue()
                        );

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // DELETE
    // ==========================================

    private void deleteAllocation(
            Allocation allocation
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle(
                "Delete Allocation"
        );

        alert.setHeaderText(
                "Delete " +
                allocation.getAllocationId() +
                "?"
        );

        alert.setContentText(
                "Are you sure you want to delete this allocation?"
        );

        alert.showAndWait()
                .ifPresent(result -> {

                    if (result ==
                            ButtonType.OK) {

                        allocationList.remove(
                                allocation
                        );
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
    // ALLOCATION MODEL
    // ==========================================

    public static class Allocation {

        private String allocationId;
        private String route;
        private String bus;
        private String driver;
        private String shift;
        private String date;
        private String status;

        public Allocation(
                String allocationId,
                String route,
                String bus,
                String driver,
                String shift,
                String date,
                String status
        ) {

            this.allocationId = allocationId;
            this.route = route;
            this.bus = bus;
            this.driver = driver;
            this.shift = shift;
            this.date = date;
            this.status = status;
        }

        public String getAllocationId() {

            return allocationId;
        }

        public String getRoute() {

            return route;
        }

        public String getBus() {

            return bus;
        }

        public String getDriver() {

            return driver;
        }

        public String getShift() {

            return shift;
        }

        public String getDate() {

            return date;
        }

        public String getStatus() {

            return status;
        }

        public void setRoute(
                String route
        ) {

            this.route = route;
        }

        public void setBus(
                String bus
        ) {

            this.bus = bus;
        }

        public void setDriver(
                String driver
        ) {

            this.driver = driver;
        }

        public void setShift(
                String shift
        ) {

            this.shift = shift;
        }

        public void setDate(
                String date
        ) {

            this.date = date;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}

package admin;

import data.AppData;
import data.TransportData;
import model.Bus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ManageBuses extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String RED = "#C62828";

    private final TableView<Bus> table = new TableView<>();
    private final ObservableList<Bus> buses =
            FXCollections.observableArrayList();

    private final TransportData data =
            AppData.getTransportData();

    private Stage currentStage;

    @Override
    public void start(Stage stage) {
        currentStage = stage;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT + ";");

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Manage Buses"
        );
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(200);
        sidebar.setStyle("-fx-background-color: " + BLUE + ";");

        VBox brand = new VBox(4);
        brand.setPadding(new Insets(18));
        brand.setAlignment(Pos.CENTER_LEFT);

        ImageView logo =
                createImageView("/images/logo.png", 64, 64);

        Label name = new Label("Smart University");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                15
        ));

        Label system = new Label("Transport System");
        system.setTextFill(Color.web("#DCEBFF"));
        system.setFont(Font.font("Segoe UI", 10));

        Label admin = new Label("ADMIN PANEL");
        admin.setTextFill(Color.web("#BBD6F7"));
        admin.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                10
        ));

        brand.getChildren().addAll(
                logo, name, system, admin
        );

        VBox menu = new VBox(5);
        menu.setPadding(new Insets(8, 10, 10, 10));

        Button dashboard =
                createMenuButton("⌂   Dashboard");
        Button students =
                createMenuButton("♙   Manage Students");
        Button routes =
                createMenuButton("⌁   Manage Routes");
        Button busesButton =
                createMenuButton("▣   Manage Buses");
        Button allocation =
                createMenuButton("⇄   Bus Allocation");
        Button schedules =
                createMenuButton("◷   Schedules");
        Button bookings =
                createMenuButton("▤   Bookings");
        Button reports =
                createMenuButton("▥   Reports");
        Button settings =
                createMenuButton("⚙   Settings");

        setActive(busesButton);

        dashboard.setOnAction(e ->
                new AdminDashboard().start(stage));

        students.setOnAction(e ->
                new ManageStudents().start(stage));

        routes.setOnAction(e ->
                new ManageRoutes().start(stage));

        busesButton.setOnAction(e ->
                setActive(busesButton));

        allocation.setOnAction(e -> showMessage(
                "Bus Allocation",
                "Bus Allocation page will open here."
        ));

        schedules.setOnAction(e ->
                new Schedules().start(stage));

        bookings.setOnAction(e ->
                new Bookings().start(stage));

        reports.setOnAction(e ->
                new Reports().start(stage));

        settings.setOnAction(e ->
                new Settings().start(stage));

        menu.getChildren().addAll(
                dashboard,
                students,
                routes,
                busesButton,
                allocation,
                schedules,
                bookings,
                reports,
                settings
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button logout = createMenuButton("⇥   Logout");
        logout.setOnAction(e -> {
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to log out?",
                    ButtonType.YES,
                    ButtonType.NO
            );
            alert.setTitle("Logout");
            alert.setHeaderText(null);

            if (alert.showAndWait().orElse(ButtonType.NO)
                    == ButtonType.YES) {
                stage.close();
            }
        });

        sidebar.getChildren().addAll(
                brand,
                menu,
                spacer,
                logout
        );

        return sidebar;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(14);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 20, 0, 20));
        topBar.setMinHeight(62);

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label menuIcon = new Label("☰");
        menuIcon.setFont(Font.font("Segoe UI", 20));
        menuIcon.setTextFill(Color.web(DARK));

        Label page = new Label("Manage Buses");
        page.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                14
        ));
        page.setTextFill(Color.web(DARK));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label notification = new Label("♧");
        notification.setFont(Font.font("Segoe UI", 20));
        notification.setTextFill(Color.web(BLUE));

        VBox userBox = new VBox(1);
        userBox.setAlignment(Pos.CENTER_RIGHT);

        Label user = new Label("Admin User");
        user.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                11
        ));
        user.setTextFill(Color.web(DARK));

        Label role = new Label("Super Admin");
        role.setFont(Font.font("Segoe UI", 10));
        role.setTextFill(Color.web(MUTED));

        userBox.getChildren().addAll(user, role);

        topBar.getChildren().addAll(
                menuIcon,
                page,
                spacer,
                notification,
                userBox
        );

        return topBar;
    }

    private VBox createContent() {
        VBox content = new VBox(16);
        content.setPadding(new Insets(20));
        content.setFillWidth(true);

        HBox heading = new HBox(12);
        heading.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(3);

        Label title = new Label("Manage Buses");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Add, search, edit and remove buses"
        );
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region headingSpacer = new Region();
        HBox.setHgrow(
                headingSpacer,
                Priority.ALWAYS
        );

        Button addBus = new Button("+  Add Bus");
        addBus.setPrefHeight(40);
        addBus.setMinWidth(125);
        addBus.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));
        addBus.setTextFill(Color.WHITE);
        addBus.setCursor(javafx.scene.Cursor.HAND);

        setPrimaryButtonStyle(addBus);
        addBus.setOnAction(e -> addBus());

        heading.getChildren().addAll(
                titleBox,
                headingSpacer,
                addBus
        );

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText(
                "Search by bus ID, number or driver..."
        );
        search.setPrefHeight(40);
        search.setMaxWidth(Double.MAX_VALUE);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 8 12 8 12;"
        );

        HBox.setHgrow(search, Priority.ALWAYS);

        Label count = new Label();
        count.setMinWidth(100);
        count.setAlignment(Pos.CENTER_RIGHT);
        count.setFont(Font.font("Segoe UI", 11));
        count.setTextFill(Color.web(MUTED));

        createTable();
        loadBuses();
        updateCount(count, buses.size());

        search.textProperty().addListener(
                (obs, oldValue, newValue) -> {
                    searchBus(newValue);
                    updateCount(
                            count,
                            table.getItems().size()
                    );
                }
        );

        searchBar.getChildren().addAll(
                search,
                count
        );

        VBox tableCard = new VBox(10);
        tableCard.setPadding(new Insets(14));

        tableCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        tableCard.getChildren().add(table);
        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );
        VBox.setVgrow(
                tableCard,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                heading,
                searchBar,
                tableCard
        );

        return content;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<Bus, String> busId =
                new TableColumn<>("Bus ID");
        busId.setCellValueFactory(
                new PropertyValueFactory<>("busId")
        );
        busId.setMinWidth(90);

        TableColumn<Bus, String> busNumber =
                new TableColumn<>("Bus Number");
        busNumber.setCellValueFactory(
                new PropertyValueFactory<>("busNumber")
        );
        busNumber.setMinWidth(125);

        TableColumn<Bus, Number> capacity =
                new TableColumn<>("Capacity");
        capacity.setCellValueFactory(
                new PropertyValueFactory<>("capacity")
        );
        capacity.setMinWidth(85);

        TableColumn<Bus, String> driver =
                new TableColumn<>("Driver");
        driver.setCellValueFactory(
                new PropertyValueFactory<>("driverName")
        );
        driver.setMinWidth(150);

        TableColumn<Bus, String> status =
                new TableColumn<>("Status");
        status.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        status.setMinWidth(110);

        TableColumn<Bus, Void> action =
                new TableColumn<>("Actions");
        action.setMinWidth(145);
        action.setMaxWidth(160);

        action.setCellFactory(column ->
                new TableCell<Bus, Void>() {

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(6);

                    {
                        edit.setFont(
                                Font.font("Segoe UI", 10)
                        );
                        edit.setTextFill(Color.WHITE);
                        edit.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        edit.setStyle(
                                "-fx-background-color: " +
                                BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        delete.setFont(
                                Font.font("Segoe UI", 10)
                        );
                        delete.setTextFill(Color.WHITE);
                        delete.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        delete.setStyle(
                                "-fx-background-color: " +
                                RED + ";" +
                                "-fx-background-radius: 5px;"
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

                        box.setAlignment(Pos.CENTER);
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

                        setGraphic(
                                empty ? null : box
                        );
                    }
                }
        );

        table.getColumns().addAll(
                busId,
                busNumber,
                capacity,
                driver,
                status,
                action
        );

        table.setItems(buses);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );
        table.setFixedCellSize(42);
        table.setPlaceholder(
                new Label("No buses found.")
        );
        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;"
        );
    }

    private void loadBuses() {
        buses.clear();
        buses.addAll(data.getBuses());
    }

    private void searchBus(String keyword) {
        if (keyword == null ||
                keyword.trim().isEmpty()) {
            table.setItems(buses);
            return;
        }

        String search =
                keyword.trim().toLowerCase();

        ObservableList<Bus> result =
                FXCollections.observableArrayList();

        for (Bus bus : buses) {
            String id = safe(bus.getBusId());
            String number = safe(bus.getBusNumber());
            String driver = safe(bus.getDriverName());
            String status = safe(bus.getStatus());

            if (id.toLowerCase().contains(search)
                    || number.toLowerCase().contains(search)
                    || driver.toLowerCase().contains(search)
                    || status.toLowerCase().contains(search)
                    || String.valueOf(bus.getCapacity())
                            .contains(search)) {

                result.add(bus);
            }
        }

        table.setItems(result);
    }

    private void addBus() {
        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle("Add Bus");
        dialog.setHeaderText("Add New Bus");

        TextField id = new TextField();
        id.setPromptText("Bus ID");

        TextField number = new TextField();
        number.setPromptText("Bus Number");

        TextField capacity = new TextField();
        capacity.setPromptText("Capacity");

        TextField driver = new TextField();
        driver.setPromptText("Driver Name");

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "Available",
                "Inactive",
                "Maintenance"
        );
        status.setValue("Available");
        status.setMaxWidth(Double.MAX_VALUE);

        VBox form = new VBox(8);
        form.setPadding(new Insets(12));
        form.setPrefWidth(360);

        form.getChildren().addAll(
                new Label("Bus ID"),
                id,
                new Label("Bus Number"),
                number,
                new Label("Capacity"),
                capacity,
                new Label("Driver Name"),
                driver,
                new Label("Status"),
                status
        );

        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.CANCEL,
                        ButtonType.OK
                );

        dialog.showAndWait().ifPresent(result -> {
            if (result != ButtonType.OK) {
                return;
            }

            String busId = id.getText().trim();
            String busNumber =
                    number.getText().trim();
            String capacityText =
                    capacity.getText().trim();
            String driverName =
                    driver.getText().trim();

            if (busId.isEmpty()
                    || busNumber.isEmpty()
                    || capacityText.isEmpty()
                    || driverName.isEmpty()) {

                showMessage(
                        "Missing Information",
                        "Please complete all bus fields."
                );
                return;
            }

            int capacityValue;

            try {
                capacityValue =
                        Integer.parseInt(capacityText);
            } catch (NumberFormatException ex) {
                showMessage(
                        "Invalid Capacity",
                        "Capacity must be a whole number."
                );
                return;
            }

            if (capacityValue <= 0) {
                showMessage(
                        "Invalid Capacity",
                        "Capacity must be greater than zero."
                );
                return;
            }

            for (Bus existing : data.getBuses()) {
                if (safe(existing.getBusId())
                        .equalsIgnoreCase(busId)) {

                    showMessage(
                            "Duplicate Bus ID",
                            "A bus with this ID already exists."
                    );
                    return;
                }
            }

            Bus bus = new Bus(
                    busId,
                    busNumber,
                    capacityValue,
                    driverName
            );

            bus.setStatus(status.getValue());

            data.getBuses().add(bus);
            data.getBusAllocationManager()
                    .addBus(bus);

            loadBuses();
            table.setItems(buses);
            table.refresh();
        });
    }

    private void editBus(Bus bus) {
        TextField number =
                new TextField(bus.getBusNumber());

        TextField capacity =
                new TextField(
                        String.valueOf(
                                bus.getCapacity()
                        )
                );

        TextField driver =
                new TextField(bus.getDriverName());

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "Available",
                "Assigned",
                "Inactive",
                "Maintenance"
        );
        status.setValue(bus.getStatus());
        status.setMaxWidth(Double.MAX_VALUE);

        VBox form = new VBox(8);
        form.setPadding(new Insets(12));
        form.setPrefWidth(360);

        form.getChildren().addAll(
                new Label("Bus Number"),
                number,
                new Label("Capacity"),
                capacity,
                new Label("Driver Name"),
                driver,
                new Label("Status"),
                status
        );

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle("Edit Bus");
        dialog.setHeaderText(
                "Edit " + bus.getBusId()
        );

        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.CANCEL,
                        ButtonType.OK
                );

        dialog.showAndWait().ifPresent(result -> {
            if (result != ButtonType.OK) {
                return;
            }

            int capacityValue;

            try {
                capacityValue =
                        Integer.parseInt(
                                capacity.getText().trim()
                        );
            } catch (NumberFormatException ex) {
                showMessage(
                        "Invalid Capacity",
                        "Capacity must be a whole number."
                );
                return;
            }

            if (number.getText().trim().isEmpty()
                    || driver.getText().trim().isEmpty()
                    || capacityValue <= 0) {

                showMessage(
                        "Invalid Information",
                        "Please enter valid bus details."
                );
                return;
            }

            bus.setBusNumber(
                    number.getText().trim()
            );
            bus.setCapacity(capacityValue);
            bus.setDriverName(
                    driver.getText().trim()
            );
            bus.setStatus(status.getValue());

            table.refresh();
        });
    }

    private void deleteBus(Bus bus) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Delete Bus");
        alert.setHeaderText(
                "Delete " + bus.getBusNumber() + "?"
        );
        alert.setContentText(
                "This bus will be removed from the shared transport data."
        );

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                data.getBuses().remove(bus);
                data.getBusAllocationManager()
                        .removeBus(bus.getBusId());

                buses.remove(bus);
                table.refresh();
            }
        });
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(42);
        button.setMinHeight(42);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(
                new Insets(10, 12, 10, 12)
        );
        button.setTextFill(Color.WHITE);
        button.setFont(
                Font.font("Segoe UI", 12)
        );
        button.setCursor(
                javafx.scene.Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: " +
                        "rgba(255,255,255,0.12);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    private void setActive(Button button) {
        button.setStyle(
                "-fx-background-color: " +
                BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }

    private void setPrimaryButtonStyle(Button button) {
        button.setStyle(
                "-fx-background-color: " +
                BLUE + ";" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: " +
                        BLUE2 + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: " +
                        BLUE + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    private ImageView createImageView(
            String resource,
            double width,
            double height
    ) {
        java.io.InputStream stream =
                ManageBuses.class
                        .getResourceAsStream(resource);

        if (stream == null) {
            return new ImageView();
        }

        Image image = new Image(stream);

        ImageView view =
                new ImageView(image);

        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setSmooth(true);

        return view;
    }

    private void updateCount(
            Label count,
            int number
    ) {
        count.setText(
                number + " bus" +
                (number == 1 ? "" : "es")
        );
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}

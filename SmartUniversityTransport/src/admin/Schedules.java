package admin;

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

public class Schedules extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String WHITE = "#FFFFFF";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String GREEN = "#2E7D32";
    private static final String ORANGE = "#EF6C00";
    private static final String RED = "#C62828";

    private final ObservableList<Schedule> schedules =
            FXCollections.observableArrayList();

    private final TableView<Schedule> table = new TableView<>();

    @Override
    public void start(Stage stage) {
        loadSampleSchedules();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT + ";");

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Schedules");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(205);
        sidebar.setStyle("-fx-background-color: " + BLUE + ";");

        VBox brand = new VBox(4);
        brand.setPadding(new Insets(20, 18, 18, 18));
        brand.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = createImageView("/images/logo.png", 64, 64);

        Label name = new Label("Smart University");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        Label system = new Label("Transport System");
        system.setTextFill(Color.web("#DCEBFF"));
        system.setFont(Font.font("Segoe UI", 10));

        Label admin = new Label("ADMIN PANEL");
        admin.setTextFill(Color.web("#BBD6F7"));
        admin.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));

        brand.getChildren().addAll(logo, name, system, admin);

        VBox menu = new VBox(5);
        menu.setPadding(new Insets(10, 10, 12, 10));

        Button dashboard = createMenuButton("⌂   Dashboard");
        Button students = createMenuButton("♙   Manage Students");
        Button routes = createMenuButton("⌁   Manage Routes");
        Button buses = createMenuButton("▣   Manage Buses");
        Button allocation = createMenuButton("⇄   Bus Allocation");
        Button scheduleButton = createMenuButton("◷   Schedules");
        Button bookings = createMenuButton("▤   Bookings");
        Button reports = createMenuButton("▥   Reports");
        Button settings = createMenuButton("⚙   Settings");

        setActive(scheduleButton);

        dashboard.setOnAction(e -> new AdminDashboard().start(stage));
        students.setOnAction(e -> new ManageStudents().start(stage));
        routes.setOnAction(e -> new ManageRoutes().start(stage));
        buses.setOnAction(e -> new ManageBuses().start(stage));

        allocation.setOnAction(e -> showMessage(
                "Bus Allocation",
                "Bus Allocation module is not connected yet."));

        scheduleButton.setOnAction(e -> setActive(scheduleButton));

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
                buses,
                allocation,
                scheduleButton,
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
        topBar.setPadding(new Insets(0, 24, 0, 24));
        topBar.setMinHeight(66);

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label menuIcon = new Label("☰");
        menuIcon.setFont(Font.font("Segoe UI", 20));
        menuIcon.setTextFill(Color.web(DARK));

        Label title = new Label("Schedules");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                14
        ));
        title.setTextFill(Color.web(DARK));

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
                title,
                spacer,
                notification,
                userBox
        );

        return topBar;
    }

    private VBox createContent() {
        VBox content = new VBox(18);
        content.setPadding(new Insets(24));
        content.setFillWidth(true);

        HBox heading = new HBox(12);
        heading.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(3);

        Label title = new Label("Transport Schedules");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "View and manage daily university bus schedules"
        );
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        titleBox.getChildren().addAll(title, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button add = new Button("+  Add Schedule");
        add.setMinWidth(150);
        add.setPrefHeight(40);
        add.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));
        add.setTextFill(Color.WHITE);
        add.setCursor(javafx.scene.Cursor.HAND);

        setPrimaryButtonStyle(add);

        add.setOnAction(e -> addSchedule());

        heading.getChildren().addAll(
                titleBox,
                spacer,
                add
        );

        HBox filters = createFilters();

        VBox tableCard = new VBox(10);
        tableCard.setPadding(new Insets(16));
        tableCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        createTable();
        tableCard.getChildren().add(table);
        VBox.setVgrow(table, Priority.ALWAYS);
        VBox.setVgrow(tableCard, Priority.ALWAYS);

        content.getChildren().addAll(
                heading,
                filters,
                tableCard
        );

        VBox.setVgrow(tableCard, Priority.ALWAYS);

        return content;
    }

    private HBox createFilters() {
        HBox filters = new HBox(10);
        filters.setAlignment(Pos.CENTER_LEFT);
        filters.setMinHeight(42);

        TextField search = new TextField();
        search.setPromptText(
                "Search route, bus number or driver..."
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

        ComboBox<String> day = new ComboBox<>();
        day.getItems().addAll(
                "All Days",
                "Saturday",
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        );
        day.setValue("All Days");
        day.setPrefHeight(40);

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll(
                "All Status",
                "Active",
                "Delayed",
                "Cancelled"
        );
        status.setValue("All Status");
        status.setPrefHeight(40);

        search.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterSchedules(
                                newValue,
                                day.getValue(),
                                status.getValue()
                        )
        );

        day.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterSchedules(
                                search.getText(),
                                newValue,
                                status.getValue()
                        )
        );

        status.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterSchedules(
                                search.getText(),
                                day.getValue(),
                                newValue
                        )
        );

        filters.getChildren().addAll(
                search,
                day,
                status
        );

        return filters;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<Schedule, String> time =
                new TableColumn<>("Departure");

        time.setCellValueFactory(
                new PropertyValueFactory<>("departure")
        );

        TableColumn<Schedule, String> route =
                new TableColumn<>("Route");

        route.setCellValueFactory(
                new PropertyValueFactory<>("route")
        );

        TableColumn<Schedule, String> bus =
                new TableColumn<>("Bus");

        bus.setCellValueFactory(
                new PropertyValueFactory<>("bus")
        );

        TableColumn<Schedule, String> driver =
                new TableColumn<>("Driver");

        driver.setCellValueFactory(
                new PropertyValueFactory<>("driver")
        );

        TableColumn<Schedule, String> day =
                new TableColumn<>("Day");

        day.setCellValueFactory(
                new PropertyValueFactory<>("day")
        );

        TableColumn<Schedule, String> status =
                new TableColumn<>("Status");

        status.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        TableColumn<Schedule, Void> actions =
                new TableColumn<>("Actions");

        actions.setMinWidth(130);
        actions.setMaxWidth(150);

        actions.setCellFactory(column ->
                new TableCell<Schedule, Void>() {

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(6, edit, delete);

                    {
                        box.setAlignment(Pos.CENTER);

                        edit.setFont(Font.font(
                                "Segoe UI",
                                10
                        ));
                        edit.setTextFill(Color.WHITE);
                        edit.setCursor(
                                javafx.scene.Cursor.HAND
                        );

                        edit.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        delete.setFont(Font.font(
                                "Segoe UI",
                                10
                        ));
                        delete.setTextFill(Color.WHITE);
                        delete.setCursor(
                                javafx.scene.Cursor.HAND
                        );

                        delete.setStyle(
                                "-fx-background-color: " + RED + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        edit.setOnAction(e -> {
                            Schedule item =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            editSchedule(item);
                        });

                        delete.setOnAction(e -> {
                            Schedule item =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteSchedule(item);
                        });
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty) {

                        super.updateItem(item, empty);
                        setGraphic(empty ? null : box);
                    }
                }
        );

        table.getColumns().addAll(
                time,
                route,
                bus,
                driver,
                day,
                status,
                actions
        );

        table.setItems(schedules);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );
        table.setFixedCellSize(44);
        table.setPlaceholder(
                new Label("No schedules found.")
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 12px;"
        );
    }

    private void loadSampleSchedules() {
        schedules.clear();

        schedules.addAll(
                new Schedule(
                        "07:00 AM",
                        "Mirpur → DIU",
                        "BUS-101",
                        "Rahim Ahmed",
                        "Saturday",
                        "Active"
                ),
                new Schedule(
                        "08:00 AM",
                        "Uttara → DIU",
                        "BUS-104",
                        "Karim Hasan",
                        "Saturday",
                        "Active"
                ),
                new Schedule(
                        "09:00 AM",
                        "Dhanmondi → DIU",
                        "BUS-108",
                        "Nayeem Islam",
                        "Saturday",
                        "Active"
                ),
                new Schedule(
                        "10:30 AM",
                        "Mohammadpur → DIU",
                        "BUS-112",
                        "Sabbir Hossain",
                        "Sunday",
                        "Active"
                ),
                new Schedule(
                        "12:00 PM",
                        "Badda → DIU",
                        "BUS-115",
                        "Arif Khan",
                        "Monday",
                        "Delayed"
                ),
                new Schedule(
                        "03:00 PM",
                        "DIU → Mirpur",
                        "BUS-101",
                        "Rahim Ahmed",
                        "Monday",
                        "Active"
                ),
                new Schedule(
                        "04:30 PM",
                        "DIU → Uttara",
                        "BUS-104",
                        "Karim Hasan",
                        "Tuesday",
                        "Active"
                ),
                new Schedule(
                        "05:30 PM",
                        "DIU → Dhanmondi",
                        "BUS-108",
                        "Nayeem Islam",
                        "Wednesday",
                        "Active"
                )
        );
    }

    private void filterSchedules(
            String keyword,
            String selectedDay,
            String selectedStatus) {

        String search =
                keyword == null
                        ? ""
                        : keyword.trim().toLowerCase();

        ObservableList<Schedule> result =
                FXCollections.observableArrayList();

        for (Schedule item : schedules) {

            boolean matchesSearch =
                    search.isEmpty()
                    || item.getDeparture()
                            .toLowerCase()
                            .contains(search)
                    || item.getRoute()
                            .toLowerCase()
                            .contains(search)
                    || item.getBus()
                            .toLowerCase()
                            .contains(search)
                    || item.getDriver()
                            .toLowerCase()
                            .contains(search);

            boolean matchesDay =
                    selectedDay == null
                    || selectedDay.equals("All Days")
                    || item.getDay().equals(selectedDay);

            boolean matchesStatus =
                    selectedStatus == null
                    || selectedStatus.equals("All Status")
                    || item.getStatus().equals(selectedStatus);

            if (matchesSearch
                    && matchesDay
                    && matchesStatus) {
                result.add(item);
            }
        }

        table.setItems(result);
    }

    private void addSchedule() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add Schedule");
        dialog.setHeaderText("Create New Transport Schedule");

        TextField departure = new TextField();
        departure.setPromptText("e.g. 07:30 AM");

        TextField route = new TextField();
        route.setPromptText("e.g. Mirpur → DIU");

        TextField bus = new TextField();
        bus.setPromptText("e.g. BUS-101");

        TextField driver = new TextField();
        driver.setPromptText("Driver name");

        ComboBox<String> day = new ComboBox<>();
        day.getItems().addAll(
                "Saturday",
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        );
        day.setPromptText("Select day");
        day.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll(
                "Active",
                "Delayed",
                "Cancelled"
        );
        status.setValue("Active");
        status.setMaxWidth(Double.MAX_VALUE);

        VBox box = new VBox(8);
        box.setPadding(new Insets(12));
        box.setPrefWidth(380);

        box.getChildren().addAll(
                new Label("Departure"),
                departure,
                new Label("Route"),
                route,
                new Label("Bus"),
                bus,
                new Label("Driver"),
                driver,
                new Label("Day"),
                day,
                new Label("Status"),
                status
        );

        dialog.getDialogPane().setContent(box);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.CANCEL,
                ButtonType.OK
        );

        dialog.showAndWait().ifPresent(result -> {

            if (result != ButtonType.OK) {
                return;
            }

            if (departure.getText().trim().isEmpty()
                    || route.getText().trim().isEmpty()
                    || bus.getText().trim().isEmpty()
                    || driver.getText().trim().isEmpty()
                    || day.getValue() == null) {

                showMessage(
                        "Missing Information",
                        "Please complete all schedule fields."
                );
                return;
            }

            schedules.add(
                    new Schedule(
                            departure.getText().trim(),
                            route.getText().trim(),
                            bus.getText().trim(),
                            driver.getText().trim(),
                            day.getValue(),
                            status.getValue()
                    )
            );

            table.setItems(schedules);
            table.refresh();
        });
    }

    private void editSchedule(Schedule item) {
        TextInputDialog dialog =
                new TextInputDialog(item.getStatus());

        dialog.setTitle("Edit Schedule");
        dialog.setHeaderText(
                "Update schedule status"
        );
        dialog.setContentText("Status:");

        dialog.showAndWait().ifPresent(value -> {

            if (value.equalsIgnoreCase("Active")
                    || value.equalsIgnoreCase("Delayed")
                    || value.equalsIgnoreCase("Cancelled")) {

                item.setStatus(value);
                table.refresh();

            } else {
                showMessage(
                        "Invalid Status",
                        "Use Active, Delayed or Cancelled."
                );
            }
        });
    }

    private void deleteSchedule(Schedule item) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Delete Schedule");
        alert.setHeaderText("Delete this schedule?");
        alert.setContentText(
                item.getDeparture()
                        + " - "
                        + item.getRoute()
        );

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                schedules.remove(item);
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
        button.setPadding(new Insets(10, 12, 10, 12));
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Segoe UI", 12));
        button.setCursor(javafx.scene.Cursor.HAND);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.12);" +
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
                "-fx-background-color: " + BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }

    private void setPrimaryButtonStyle(Button button) {
        button.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: " + BLUE2 + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: " + BLUE + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    private ImageView createImageView(
            String resource,
            double width,
            double height) {

        java.io.InputStream stream =
                Schedules.class.getResourceAsStream(resource);

        if (stream == null) {
            return new ImageView();
        }

        ImageView view =
                new ImageView(new Image(stream));

        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setSmooth(true);

        return view;
    }

    private void showMessage(
            String title,
            String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Schedule {

        private final String departure;
        private final String route;
        private final String bus;
        private final String driver;
        private final String day;
        private String status;

        public Schedule(
                String departure,
                String route,
                String bus,
                String driver,
                String day,
                String status) {

            this.departure = departure;
            this.route = route;
            this.bus = bus;
            this.driver = driver;
            this.day = day;
            this.status = status;
        }

        public String getDeparture() {
            return departure;
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

        public String getDay() {
            return day;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

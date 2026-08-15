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

public class Bookings extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String WHITE = "#FFFFFF";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String GREEN = "#2E7D32";
    private static final String RED = "#C62828";
    private static final String ORANGE = "#EF6C00";

    private final ObservableList<Booking> bookings =
            FXCollections.observableArrayList();

    private final TableView<Booking> table = new TableView<>();

    @Override
    public void start(Stage stage) {
        loadSampleBookings();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT + ";");

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Bookings");
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

        ImageView logo = createImageView(
                "/images/logo.png", 64, 64);

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
                logo,
                name,
                system,
                admin
        );

        VBox menu = new VBox(5);
        menu.setPadding(new Insets(10, 10, 12, 10));

        Button dashboard = createMenuButton("⌂   Dashboard");
        Button students = createMenuButton("♙   Manage Students");
        Button routes = createMenuButton("⌁   Manage Routes");
        Button buses = createMenuButton("▣   Manage Buses");
        Button allocation = createMenuButton("⇄   Bus Allocation");
        Button schedules = createMenuButton("◷   Schedules");
        Button bookingsButton = createMenuButton("▤   Bookings");
        Button reports = createMenuButton("▥   Reports");
        Button settings = createMenuButton("⚙   Settings");

        setActive(bookingsButton);

        dashboard.setOnAction(e ->
                new AdminDashboard().start(stage));

        students.setOnAction(e ->
                new ManageStudents().start(stage));

        routes.setOnAction(e ->
                new ManageRoutes().start(stage));

        buses.setOnAction(e ->
                new ManageBuses().start(stage));

        allocation.setOnAction(e ->
                showMessage(
                        "Bus Allocation",
                        "Bus Allocation module is not connected yet."
                ));

        schedules.setOnAction(e ->
                new Schedules().start(stage));

        bookingsButton.setOnAction(e ->
                setActive(bookingsButton));

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
                schedules,
                bookingsButton,
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

        Label title = new Label("Bookings");
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

        Label title = new Label("Transport Bookings");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "View and manage student transport bookings"
        );
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button add = new Button("+  Add Booking");
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
        add.setOnAction(e -> addBooking());

        heading.getChildren().addAll(
                titleBox,
                spacer,
                add
        );

        HBox filters = createFilters();

        HBox summary = createSummary();

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
                summary,
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
                "Search booking ID, student, route or bus..."
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

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll(
                "All Status",
                "Confirmed",
                "Pending",
                "Cancelled"
        );
        status.setValue("All Status");
        status.setPrefHeight(40);

        ComboBox<String> route = new ComboBox<>();
        route.getItems().addAll(
                "All Routes",
                "Mirpur",
                "Uttara",
                "Dhanmondi",
                "Mohammadpur",
                "Badda"
        );
        route.setValue("All Routes");
        route.setPrefHeight(40);

        search.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterBookings(
                                newValue,
                                route.getValue(),
                                status.getValue()
                        )
        );

        route.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterBookings(
                                search.getText(),
                                newValue,
                                status.getValue()
                        )
        );

        status.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterBookings(
                                search.getText(),
                                route.getValue(),
                                newValue
                        )
        );

        filters.getChildren().addAll(
                search,
                route,
                status
        );

        return filters;
    }

    private HBox createSummary() {
        HBox summary = new HBox(12);

        summary.getChildren().addAll(
                createSummaryCard(
                        "Total Bookings",
                        "1,243",
                        BLUE
                ),
                createSummaryCard(
                        "Confirmed",
                        "1,102",
                        GREEN
                ),
                createSummaryCard(
                        "Pending",
                        "86",
                        ORANGE
                ),
                createSummaryCard(
                        "Cancelled",
                        "55",
                        RED
                )
        );

        for (VBox card : summary.getChildren()
                .toArray(new VBox[0])) {
            HBox.setHgrow(card, Priority.ALWAYS);
        }

        return summary;
    }

    private VBox createSummaryCard(
            String title,
            String value,
            String color) {

        VBox card = new VBox(3);
        card.setPadding(new Insets(13));
        card.setMinHeight(78);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", 10));
        titleLabel.setTextFill(Color.web(MUTED));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                20
        ));
        valueLabel.setTextFill(Color.web(color));

        card.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return card;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<Booking, String> id =
                new TableColumn<>("Booking ID");
        id.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Booking, String> student =
                new TableColumn<>("Student");
        student.setCellValueFactory(
                new PropertyValueFactory<>("student")
        );

        TableColumn<Booking, String> route =
                new TableColumn<>("Route");
        route.setCellValueFactory(
                new PropertyValueFactory<>("route")
        );

        TableColumn<Booking, String> bus =
                new TableColumn<>("Bus");
        bus.setCellValueFactory(
                new PropertyValueFactory<>("bus")
        );

        TableColumn<Booking, String> date =
                new TableColumn<>("Date");
        date.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        TableColumn<Booking, String> time =
                new TableColumn<>("Time");
        time.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );

        TableColumn<Booking, String> status =
                new TableColumn<>("Status");
        status.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        TableColumn<Booking, Void> actions =
                new TableColumn<>("Actions");

        actions.setMinWidth(130);
        actions.setMaxWidth(150);

        actions.setCellFactory(column ->
                new TableCell<Booking, Void>() {

                    private final Button view =
                            new Button("View");

                    private final Button cancel =
                            new Button("Cancel");

                    private final HBox box =
                            new HBox(6, view, cancel);

                    {
                        box.setAlignment(Pos.CENTER);

                        view.setFont(Font.font(
                                "Segoe UI", 10
                        ));
                        view.setTextFill(Color.WHITE);
                        view.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        view.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        cancel.setFont(Font.font(
                                "Segoe UI", 10
                        ));
                        cancel.setTextFill(Color.WHITE);
                        cancel.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        cancel.setStyle(
                                "-fx-background-color: " + RED + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        view.setOnAction(e -> {
                            Booking item =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            viewBooking(item);
                        });

                        cancel.setOnAction(e -> {
                            Booking item =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            cancelBooking(item);
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
                id,
                student,
                route,
                bus,
                date,
                time,
                status,
                actions
        );

        table.setItems(bookings);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );
        table.setFixedCellSize(44);
        table.setPlaceholder(
                new Label("No bookings found.")
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 12px;"
        );
    }

    private void loadSampleBookings() {
        bookings.clear();

        bookings.addAll(
                new Booking(
                        "BK-1001",
                        "Mehedi Hasan",
                        "Mirpur",
                        "BUS-101",
                        "15 Aug 2026",
                        "07:00 AM",
                        "Confirmed"
                ),
                new Booking(
                        "BK-1002",
                        "Nishan Ahmed",
                        "Uttara",
                        "BUS-104",
                        "15 Aug 2026",
                        "08:00 AM",
                        "Confirmed"
                ),
                new Booking(
                        "BK-1003",
                        "Golam Robbani",
                        "Dhanmondi",
                        "BUS-108",
                        "15 Aug 2026",
                        "09:00 AM",
                        "Pending"
                ),
                new Booking(
                        "BK-1004",
                        "Mahbub Hasan",
                        "Mohammadpur",
                        "BUS-112",
                        "15 Aug 2026",
                        "10:30 AM",
                        "Confirmed"
                ),
                new Booking(
                        "BK-1005",
                        "Abdur Rahim",
                        "Badda",
                        "BUS-115",
                        "15 Aug 2026",
                        "12:00 PM",
                        "Cancelled"
                ),
                new Booking(
                        "BK-1006",
                        "Arafat Islam",
                        "Mirpur",
                        "BUS-101",
                        "15 Aug 2026",
                        "03:00 PM",
                        "Confirmed"
                )
        );
    }

    private void filterBookings(
            String keyword,
            String selectedRoute,
            String selectedStatus) {

        String search =
                keyword == null
                        ? ""
                        : keyword.trim().toLowerCase();

        ObservableList<Booking> result =
                FXCollections.observableArrayList();

        for (Booking booking : bookings) {

            boolean matchesSearch =
                    search.isEmpty()
                    || booking.getId()
                            .toLowerCase()
                            .contains(search)
                    || booking.getStudent()
                            .toLowerCase()
                            .contains(search)
                    || booking.getRoute()
                            .toLowerCase()
                            .contains(search)
                    || booking.getBus()
                            .toLowerCase()
                            .contains(search);

            boolean matchesRoute =
                    selectedRoute == null
                    || selectedRoute.equals("All Routes")
                    || booking.getRoute()
                            .equals(selectedRoute);

            boolean matchesStatus =
                    selectedStatus == null
                    || selectedStatus.equals("All Status")
                    || booking.getStatus()
                            .equals(selectedStatus);

            if (matchesSearch
                    && matchesRoute
                    && matchesStatus) {
                result.add(booking);
            }
        }

        table.setItems(result);
    }

    private void addBooking() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add Booking");
        dialog.setHeaderText(
                "Create New Transport Booking"
        );

        TextField id = new TextField();
        id.setPromptText("Booking ID");

        TextField student = new TextField();
        student.setPromptText("Student name");

        TextField route = new TextField();
        route.setPromptText("Route");

        TextField bus = new TextField();
        bus.setPromptText("Bus number");

        TextField date = new TextField();
        date.setPromptText("e.g. 15 Aug 2026");

        TextField time = new TextField();
        time.setPromptText("e.g. 07:00 AM");

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll(
                "Confirmed",
                "Pending",
                "Cancelled"
        );
        status.setValue("Confirmed");
        status.setMaxWidth(Double.MAX_VALUE);

        VBox box = new VBox(7);
        box.setPadding(new Insets(12));
        box.setPrefWidth(380);

        box.getChildren().addAll(
                new Label("Booking ID"),
                id,
                new Label("Student"),
                student,
                new Label("Route"),
                route,
                new Label("Bus"),
                bus,
                new Label("Date"),
                date,
                new Label("Time"),
                time,
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

            if (id.getText().trim().isEmpty()
                    || student.getText().trim().isEmpty()
                    || route.getText().trim().isEmpty()
                    || bus.getText().trim().isEmpty()
                    || date.getText().trim().isEmpty()
                    || time.getText().trim().isEmpty()) {

                showMessage(
                        "Missing Information",
                        "Please complete all booking fields."
                );
                return;
            }

            bookings.add(
                    new Booking(
                            id.getText().trim(),
                            student.getText().trim(),
                            route.getText().trim(),
                            bus.getText().trim(),
                            date.getText().trim(),
                            time.getText().trim(),
                            status.getValue()
                    )
            );

            table.setItems(bookings);
            table.refresh();
        });
    }

    private void viewBooking(Booking booking) {
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle("Booking Details");
        alert.setHeaderText(
                "Booking " + booking.getId()
        );

        alert.setContentText(
                "Student: " + booking.getStudent()
                + "\nRoute: " + booking.getRoute()
                + "\nBus: " + booking.getBus()
                + "\nDate: " + booking.getDate()
                + "\nTime: " + booking.getTime()
                + "\nStatus: " + booking.getStatus()
        );

        alert.showAndWait();
    }

    private void cancelBooking(Booking booking) {
        if ("Cancelled".equals(booking.getStatus())) {
            showMessage(
                    "Booking",
                    "This booking is already cancelled."
            );
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Cancel Booking");
        alert.setHeaderText(
                "Cancel booking " + booking.getId() + "?"
        );

        alert.setContentText(
                booking.getStudent()
                        + " - "
                        + booking.getRoute()
        );

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                booking.setStatus("Cancelled");
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
                Bookings.class.getResourceAsStream(resource);

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

    public static class Booking {

        private final String id;
        private final String student;
        private final String route;
        private final String bus;
        private final String date;
        private final String time;
        private String status;

        public Booking(
                String id,
                String student,
                String route,
                String bus,
                String date,
                String time,
                String status) {

            this.id = id;
            this.student = student;
            this.route = route;
            this.bus = bus;
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getStudent() {
            return student;
        }

        public String getRoute() {
            return route;
        }

        public String getBus() {
            return bus;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
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

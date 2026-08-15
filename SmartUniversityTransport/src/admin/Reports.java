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

public class Reports extends Application {

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

    private final ObservableList<RouteReport> reports =
            FXCollections.observableArrayList();

    private final TableView<RouteReport> table =
            new TableView<>();

    @Override
    public void start(Stage stage) {
        loadSampleReports();

        BorderPane root = new BorderPane();
        root.setStyle(
                "-fx-background-color: " + LIGHT + ";"
        );

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Reports"
        );
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(205);
        sidebar.setStyle(
                "-fx-background-color: " + BLUE + ";"
        );

        VBox brand = new VBox(4);
        brand.setPadding(new Insets(20, 18, 18, 18));
        brand.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = createImageView(
                "/images/logo.png",
                64,
                64
        );

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

        Button dashboard = createMenuButton(
                "⌂   Dashboard"
        );
        Button students = createMenuButton(
                "♙   Manage Students"
        );
        Button routes = createMenuButton(
                "⌁   Manage Routes"
        );
        Button buses = createMenuButton(
                "▣   Manage Buses"
        );
        Button allocation = createMenuButton(
                "⇄   Bus Allocation"
        );
        Button schedules = createMenuButton(
                "◷   Schedules"
        );
        Button bookings = createMenuButton(
                "▤   Bookings"
        );
        Button reportsButton = createMenuButton(
                "▥   Reports"
        );
        Button settings = createMenuButton(
                "⚙   Settings"
        );

        setActive(reportsButton);

        dashboard.setOnAction(e ->
                new AdminDashboard().start(stage)
        );

        students.setOnAction(e ->
                new ManageStudents().start(stage)
        );

        routes.setOnAction(e ->
                new ManageRoutes().start(stage)
        );

        buses.setOnAction(e ->
                new ManageBuses().start(stage)
        );

        allocation.setOnAction(e ->
                showMessage(
                        "Bus Allocation",
                        "Bus Allocation module is not connected yet."
                )
        );

        schedules.setOnAction(e ->
                new Schedules().start(stage)
        );

        bookings.setOnAction(e ->
                new Bookings().start(stage)
        );

        reportsButton.setOnAction(e ->
                setActive(reportsButton)
        );

        settings.setOnAction(e ->
                new Settings().start(stage));

        menu.getChildren().addAll(
                dashboard,
                students,
                routes,
                buses,
                allocation,
                schedules,
                bookings,
                reportsButton,
                settings
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button logout = createMenuButton(
                "⇥   Logout"
        );

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
        topBar.setPadding(
                new Insets(0, 20, 0, 20)
        );
        topBar.setMinHeight(62);

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label menuIcon = new Label("☰");
        menuIcon.setFont(
                Font.font("Segoe UI", 20)
        );
        menuIcon.setTextFill(
                Color.web(DARK)
        );

        Label title = new Label("Reports");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                14
        ));
        title.setTextFill(
                Color.web(DARK)
        );

        Region spacer = new Region();
        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification = new Label("♧");
        notification.setFont(
                Font.font("Segoe UI", 20)
        );
        notification.setTextFill(
                Color.web(BLUE)
        );

        VBox userBox = new VBox(1);
        userBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label user = new Label("Admin User");
        user.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                11
        ));
        user.setTextFill(
                Color.web(DARK)
        );

        Label role = new Label("Super Admin");
        role.setFont(
                Font.font("Segoe UI", 10)
        );
        role.setTextFill(
                Color.web(MUTED)
        );

        userBox.getChildren().addAll(
                user,
                role
        );

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

        content.setPadding(
                new Insets(24)
        );
        content.setFillWidth(true);

        HBox heading = new HBox(12);
        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(3);

        Label title = new Label(
                "Transport Reports"
        );

        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(
                Color.web(DARK)
        );

        Label subtitle = new Label(
                "Review transportation performance and booking activity"
        );

        subtitle.setFont(
                Font.font("Segoe UI", 12)
        );
        subtitle.setTextFill(
                Color.web(MUTED)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer = new Region();
        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button generate = new Button(
                "Generate Report"
        );

        generate.setMinWidth(150);
        generate.setPrefHeight(40);
        generate.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));
        generate.setTextFill(
                Color.WHITE
        );
        generate.setCursor(
                javafx.scene.Cursor.HAND
        );

        setPrimaryButtonStyle(generate);

        generate.setOnAction(e ->
                generateReport()
        );

        heading.getChildren().addAll(
                titleBox,
                spacer,
                generate
        );

        HBox summary = createSummaryCards();

        HBox filters = createFilters();

        VBox tableCard = new VBox(10);

        tableCard.setPadding(
                new Insets(16)
        );

        tableCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        createTable();

        tableCard.getChildren().add(
                table
        );

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
                summary,
                filters,
                tableCard
        );

        VBox.setVgrow(
                tableCard,
                Priority.ALWAYS
        );

        return content;
    }

    private HBox createSummaryCards() {
        HBox row = new HBox(12);

        row.getChildren().addAll(
                createSummaryCard(
                        "Total Trips",
                        "486",
                        BLUE
                ),
                createSummaryCard(
                        "Total Bookings",
                        "1,243",
                        GREEN
                ),
                createSummaryCard(
                        "Bus Utilization",
                        "82%",
                        ORANGE
                ),
                createSummaryCard(
                        "Cancelled Trips",
                        "24",
                        RED
                )
        );

        for (VBox card :
                row.getChildren().toArray(
                        new VBox[0])) {

            HBox.setHgrow(
                    card,
                    Priority.ALWAYS
            );
        }

        return row;
    }

    private VBox createSummaryCard(
            String title,
            String value,
            String color) {

        VBox card = new VBox(3);

        card.setPadding(
                new Insets(13)
        );

        card.setMinHeight(78);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        Label titleLabel = new Label(
                title
        );

        titleLabel.setFont(
                Font.font("Segoe UI", 10)
        );
        titleLabel.setTextFill(
                Color.web(MUTED)
        );

        Label valueLabel = new Label(
                value
        );

        valueLabel.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                20
        ));

        valueLabel.setTextFill(
                Color.web(color)
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return card;
    }

    private HBox createFilters() {
        HBox filters = new HBox(10);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );
        filters.setMinHeight(42);

        TextField search = new TextField();

        search.setPromptText(
                "Search route or bus..."
        );

        search.setPrefHeight(40);
        search.setMaxWidth(
                Double.MAX_VALUE
        );

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 8 12 8 12;"
        );

        HBox.setHgrow(
                search,
                Priority.ALWAYS
        );

        ComboBox<String> period =
                new ComboBox<>();

        period.getItems().addAll(
                "This Week",
                "This Month",
                "Last Month",
                "This Semester"
        );

        period.setValue(
                "This Month"
        );

        period.setPrefHeight(40);

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "All Status",
                "Good",
                "Needs Attention"
        );

        status.setValue(
                "All Status"
        );

        status.setPrefHeight(40);

        search.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterReports(
                                newValue,
                                status.getValue()
                        )
        );

        status.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        filterReports(
                                search.getText(),
                                newValue
                        )
        );

        filters.getChildren().addAll(
                search,
                period,
                status
        );

        return filters;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<RouteReport, String> route =
                new TableColumn<>("Route");

        route.setCellValueFactory(
                new PropertyValueFactory<>(
                        "route"
                )
        );

        TableColumn<RouteReport, String> trips =
                new TableColumn<>("Trips");

        trips.setCellValueFactory(
                new PropertyValueFactory<>(
                        "trips"
                )
        );

        TableColumn<RouteReport, String> bookings =
                new TableColumn<>("Bookings");

        bookings.setCellValueFactory(
                new PropertyValueFactory<>(
                        "bookings"
                )
        );

        TableColumn<RouteReport, String> capacity =
                new TableColumn<>("Capacity Used");

        capacity.setCellValueFactory(
                new PropertyValueFactory<>(
                        "capacity"
                )
        );

        TableColumn<RouteReport, String> utilization =
                new TableColumn<>("Utilization");

        utilization.setCellValueFactory(
                new PropertyValueFactory<>(
                        "utilization"
                )
        );

        TableColumn<RouteReport, String> status =
                new TableColumn<>("Status");

        status.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        TableColumn<RouteReport, Void> action =
                new TableColumn<>("Action");

        action.setMinWidth(110);
        action.setMaxWidth(130);

        action.setCellFactory(column ->
                new TableCell<RouteReport, Void>() {

                    private final Button view =
                            new Button("View");

                    {
                        view.setFont(
                                Font.font(
                                        "Segoe UI",
                                        10
                                )
                        );

                        view.setTextFill(
                                Color.WHITE
                        );

                        view.setCursor(
                                javafx.scene.Cursor.HAND
                        );

                        view.setStyle(
                                "-fx-background-color: "
                                + BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        view.setOnAction(e -> {

                            RouteReport item =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            viewReport(item);
                        });
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty) {

                        super.updateItem(
                                item,
                                empty
                        );

                        setGraphic(
                                empty
                                        ? null
                                        : view
                        );
                    }
                }
        );

        table.getColumns().addAll(
                route,
                trips,
                bookings,
                capacity,
                utilization,
                status,
                action
        );

        table.setItems(reports);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(44);

        table.setPlaceholder(
                new Label(
                        "No report data found."
                )
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 12px;"
        );
    }

    private void loadSampleReports() {
        reports.clear();

        reports.addAll(
                new RouteReport(
                        "Mirpur",
                        "112",
                        "318",
                        "450",
                        "71%",
                        "Good"
                ),
                new RouteReport(
                        "Uttara",
                        "98",
                        "276",
                        "400",
                        "69%",
                        "Good"
                ),
                new RouteReport(
                        "Dhanmondi",
                        "86",
                        "241",
                        "350",
                        "69%",
                        "Good"
                ),
                new RouteReport(
                        "Mohammadpur",
                        "74",
                        "198",
                        "300",
                        "66%",
                        "Good"
                ),
                new RouteReport(
                        "Badda",
                        "62",
                        "143",
                        "250",
                        "57%",
                        "Needs Attention"
                ),
                new RouteReport(
                        "Gulshan",
                        "54",
                        "67",
                        "200",
                        "34%",
                        "Needs Attention"
                )
        );
    }

    private void filterReports(
            String keyword,
            String selectedStatus) {

        String search =
                keyword == null
                        ? ""
                        : keyword.trim()
                                .toLowerCase();

        ObservableList<RouteReport> result =
                FXCollections.observableArrayList();

        for (RouteReport item : reports) {

            boolean matchesSearch =
                    search.isEmpty()
                    || item.getRoute()
                            .toLowerCase()
                            .contains(search);

            boolean matchesStatus =
                    selectedStatus == null
                    || selectedStatus.equals(
                            "All Status"
                    )
                    || item.getStatus()
                            .equals(selectedStatus);

            if (matchesSearch
                    && matchesStatus) {

                result.add(item);
            }
        }

        table.setItems(result);
    }

    private void generateReport() {
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(
                "Report Generated"
        );

        alert.setHeaderText(
                "Transport report is ready"
        );

        alert.setContentText(
                "The current report has been generated "
                + "for frontend preview.\n\n"
                + "Total routes: "
                + reports.size()
                + "\nTotal trips: 486"
                + "\nTotal bookings: 1,243"
        );

        alert.showAndWait();
    }

    private void viewReport(
            RouteReport report) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(
                "Route Report"
        );

        alert.setHeaderText(
                report.getRoute()
        );

        alert.setContentText(
                "Trips: "
                + report.getTrips()
                + "\nBookings: "
                + report.getBookings()
                + "\nCapacity Used: "
                + report.getCapacity()
                + "\nUtilization: "
                + report.getUtilization()
                + "\nStatus: "
                + report.getStatus()
        );

        alert.showAndWait();
    }

    private Button createMenuButton(
            String text) {

        Button button = new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);
        button.setMinHeight(42);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(10, 12, 10, 12)
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
                        "-fx-background-color: "
                        + "rgba(255,255,255,0.12);" +
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

    private void setActive(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }

    private void setPrimaryButtonStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: "
                + BLUE + ";" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: "
                        + BLUE2 + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: "
                        + BLUE + ";" +
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
                Reports.class.getResourceAsStream(
                        resource
                );

        if (stream == null) {
            return new ImageView();
        }

        ImageView view =
                new ImageView(
                        new Image(stream)
                );

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
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class RouteReport {

        private final String route;
        private final String trips;
        private final String bookings;
        private final String capacity;
        private final String utilization;
        private final String status;

        public RouteReport(
                String route,
                String trips,
                String bookings,
                String capacity,
                String utilization,
                String status) {

            this.route = route;
            this.trips = trips;
            this.bookings = bookings;
            this.capacity = capacity;
            this.utilization = utilization;
            this.status = status;
        }

        public String getRoute() {
            return route;
        }

        public String getTrips() {
            return trips;
        }

        public String getBookings() {
            return bookings;
        }

        public String getCapacity() {
            return capacity;
        }

        public String getUtilization() {
            return utilization;
        }

        public String getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

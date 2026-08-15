package admin;

import javafx.application.Application;
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
import javax.swing.SwingUtilities;

public class AdminDashboard extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String WHITE = "#FFFFFF";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String GREEN = "#2E7D32";
    private static final String ORANGE = "#EF6C00";
    private static final String PURPLE = "#6A1B9A";

    private BorderPane root;
    private Stage currentStage;
    private Button dashboardButton;
    private VBox content;

    @Override
    public void start(Stage stage) {
        currentStage = stage;

        root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT + ";");

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createDashboardContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle("Smart University Transport System - Admin Dashboard");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createDashboardContent() {
        content = new VBox(16);
        content.setPadding(new Insets(22));
        content.setFillWidth(true);

        Label title = new Label("Dashboard");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label("Overview of today's university transport operations");
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        VBox heading = new VBox(3, title, subtitle);

        HBox cards = createStatCards();

        VBox bookingCard = createBookingCard();
        VBox notificationCard = createNotificationCard();

        HBox lowerArea = new HBox(14, bookingCard, notificationCard);
        HBox.setHgrow(bookingCard, Priority.ALWAYS);
        notificationCard.setMinWidth(270);
        notificationCard.setPrefWidth(320);

        VBox.setVgrow(lowerArea, Priority.ALWAYS);

        /*
         * Keep the dashboard comfortable when the window is resized.
         * The notification panel becomes slightly narrower on smaller
         * windows while the booking table keeps the remaining space.
         */
        content.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();

            if (width < 820) {
                notificationCard.setPrefWidth(270);
                notificationCard.setMinWidth(250);
                lowerArea.setSpacing(10);
            } else {
                notificationCard.setPrefWidth(320);
                notificationCard.setMinWidth(270);
                lowerArea.setSpacing(14);
            }
        });

        content.getChildren().addAll(heading, cards, lowerArea);

        return content;
    }

    private HBox createStatCards() {
        HBox cards = new HBox(14);
        cards.setFillHeight(true);

        VBox studentsCard = createStatCard(
                "♙", "Total Students", "2,548",
                "+12 this week", BLUE2);

        VBox busesCard = createStatCard(
                "▣", "Total Buses", "68",
                "8 on route", GREEN);

        VBox bookingsCard = createStatCard(
                "▤", "Today's Bookings", "1,243",
                "+8% from yesterday", ORANGE);

        VBox seatsCard = createStatCard(
                "♿", "Available Seats", "862",
                "Across all routes", PURPLE);

        cards.getChildren().addAll(
                studentsCard, busesCard, bookingsCard, seatsCard);

        for (VBox card : cards.getChildren().toArray(new VBox[0])) {
            HBox.setHgrow(card, Priority.ALWAYS);
            card.setMaxWidth(Double.MAX_VALUE);
        }

        return cards;
    }

    private VBox createBookingCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        Label title = new Label("Bookings vs Bus Allocation (Today)");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Current allocation status by route");
        subtitle.setFont(Font.font("Segoe UI", 11));
        subtitle.setTextFill(Color.web(MUTED));

        TableView<RouteData> table = createRouteTable();

        VBox heading = new VBox(2, title, subtitle);

        card.getChildren().addAll(heading, table);
        VBox.setVgrow(table, Priority.ALWAYS);

        return card;
    }

    private VBox createNotificationCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        Label title = new Label("Recent Notifications");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label("Latest transport activity");
        subtitle.setFont(Font.font("Segoe UI", 11));
        subtitle.setTextFill(Color.web(MUTED));

        VBox list = new VBox();
        list.setFillWidth(true);

        list.getChildren().addAll(
                createNotification("New booking on Mirpur Route", "10:20 AM"),
                createNotification("Bus allocation updated for Uttara", "09:45 AM"),
                createNotification("Driver assigned to Bus 15", "09:30 AM"),
                createNotification("Schedule updated for Dhanmondi", "09:10 AM"),
                createNotification("New student registered", "08:55 AM")
        );

        card.getChildren().addAll(
                new VBox(2, title, subtitle),
                list
        );

        return card;
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(210);
        sidebar.setStyle("-fx-background-color: " + BLUE + ";");

        VBox brand = new VBox(4);
        brand.setAlignment(Pos.CENTER_LEFT);
        brand.setPadding(new Insets(18, 18, 14, 18));

        ImageView logoImage = createImageView("/images/logo.png", 58, 58);

        Label brandName = new Label("Smart University");
        brandName.setTextFill(Color.WHITE);
        brandName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        Label brandSub = new Label("Transport System");
        brandSub.setTextFill(Color.web("#DCEBFF"));
        brandSub.setFont(Font.font("Segoe UI", 10));

        Label admin = new Label("ADMIN PANEL");
        admin.setTextFill(Color.web("#BBD6F7"));
        admin.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));

        brand.getChildren().addAll(logoImage, brandName, brandSub, admin);

        VBox menu = new VBox(5);
        menu.setPadding(new Insets(8, 10, 10, 10));

        dashboardButton = createMenuButton("⌂   Dashboard");
        Button students = createMenuButton("♙   Manage Students");
        Button routes = createMenuButton("⌁   Manage Routes");
        Button buses = createMenuButton("▣   Manage Buses");
        Button allocation = createMenuButton("⇄   Bus Allocation");
        Button schedules = createMenuButton("◷   Schedules");
        Button bookings = createMenuButton("▤   Bookings");
        Button reports = createMenuButton("▥   Reports");
        Button settings = createMenuButton("⚙   Settings");

        setActiveMenuButton(dashboardButton);

        dashboardButton.setOnAction(e -> showDashboard());

        students.setOnAction(e -> {
            ManageStudents page = new ManageStudents();
            page.start(stage);
        });

        routes.setOnAction(e -> {
            ManageRoutes page = new ManageRoutes();
            page.start(stage);
        });

        buses.setOnAction(e -> {
            ManageBuses page = new ManageBuses();
            page.start(stage);
        });

        allocation.setOnAction(e -> showMessage(
                "Bus Allocation",
                "Bus Allocation is currently disabled while the allocation module is being integrated."));

        schedules.setOnAction(e -> {
            Schedules page = new Schedules();
            page.start(stage);
        });

        bookings.setOnAction(e -> {
            Bookings page = new Bookings();
            page.start(stage);
        });

        reports.setOnAction(e -> {
            Reports page = new Reports();
            page.start(stage);
        });

        settings.setOnAction(e -> {
            Settings page = new Settings();
            page.start(stage);
        });

        menu.getChildren().addAll(
                dashboardButton,
                students,
                routes,
                buses,
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

            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                stage.close();

                SwingUtilities.invokeLater(() ->
                        new authentication.RoleSelectionFrame().setVisible(true)
                );
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

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(44);
        button.setMinHeight(44);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(10, 12, 10, 12));
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Segoe UI", 12));
        button.setCursor(javafx.scene.Cursor.HAND);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;");

        button.setOnMouseEntered(e -> {
            if (button != dashboardButton) {
                button.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.12);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;");
            }
        });

        button.setOnMouseExited(e -> {
            if (button != dashboardButton) {
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;");
            }
        });

        return button;
    }

    private void setActiveMenuButton(Button active) {
        if (dashboardButton != null && dashboardButton != active) {
            dashboardButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 7px;" +
                    "-fx-cursor: hand;");
        }

        active.setStyle(
                "-fx-background-color: " + BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;");
    }

    private void showDashboard() {
        setActiveMenuButton(dashboardButton);
        root.setCenter(createDashboardContent());
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(14);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 22, 0, 22));
        topBar.setMinHeight(64);
        topBar.setPrefHeight(64);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;");

        Label menuIcon = new Label("☰");
        menuIcon.setFont(Font.font("Segoe UI", 20));
        menuIcon.setTextFill(Color.web(DARK));

        Label pageName = new Label("Admin Dashboard");
        pageName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        pageName.setTextFill(Color.web(DARK));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label notification = new Label("♧");
        notification.setFont(Font.font("Segoe UI", 20));
        notification.setTextFill(Color.web(BLUE));
        notification.setCursor(javafx.scene.Cursor.HAND);
        notification.setOnMouseClicked(e -> showMessage(
                "Notifications",
                "You have 5 recent transport notifications."));

        VBox userText = new VBox(1);
        userText.setAlignment(Pos.CENTER_RIGHT);

        Label user = new Label("Admin User");
        user.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
        user.setTextFill(Color.web(DARK));

        Label role = new Label("Super Admin");
        role.setFont(Font.font("Segoe UI", 10));
        role.setTextFill(Color.web(MUTED));

        userText.getChildren().addAll(user, role);

        topBar.getChildren().addAll(
                menuIcon,
                pageName,
                spacer,
                notification,
                userText
        );

        return topBar;
    }

    private VBox createStatCard(
            String icon,
            String title,
            String value,
            String subtitle,
            String color) {

        VBox card = new VBox(6);
        card.setPadding(new Insets(16));
        card.setMinHeight(118);
        card.setMaxWidth(Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        Label iconLabel = new Label(icon);
        iconLabel.setMinSize(44, 44);
        iconLabel.setPrefSize(44, 44);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setTextFill(Color.WHITE);
        iconLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 19));

        iconLabel.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-background-radius: 9px;");

        VBox text = new VBox(2);

        Label cardTitle = new Label(title);
        cardTitle.setFont(Font.font("Segoe UI", 11));
        cardTitle.setTextFill(Color.web(MUTED));

        Label number = new Label(value);
        number.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        number.setTextFill(Color.web(DARK));

        Label sub = new Label(subtitle);
        sub.setFont(Font.font("Segoe UI", 10));
        sub.setTextFill(Color.web(GREEN));

        text.getChildren().addAll(cardTitle, number, sub);

        HBox row = new HBox(11, iconLabel, text);
        row.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().add(row);

        return card;
    }

    private TableView<RouteData> createRouteTable() {
        TableView<RouteData> table = new TableView<>();

        table.setPlaceholder(new Label("No route data available."));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setFixedCellSize(42);
        table.setPrefHeight(300);
        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 11px;"
        );

        TableColumn<RouteData, String> route =
                new TableColumn<>("Route");
        route.setCellValueFactory(
                new PropertyValueFactory<>("route"));

        TableColumn<RouteData, String> bookings =
                new TableColumn<>("Bookings");
        bookings.setCellValueFactory(
                new PropertyValueFactory<>("bookings"));

        TableColumn<RouteData, String> required =
                new TableColumn<>("Required Buses");
        required.setCellValueFactory(
                new PropertyValueFactory<>("required"));

        TableColumn<RouteData, String> allocated =
                new TableColumn<>("Allocated Buses");
        allocated.setCellValueFactory(
                new PropertyValueFactory<>("allocated"));

        TableColumn<RouteData, String> difference =
                new TableColumn<>("Difference");
        difference.setCellValueFactory(
                new PropertyValueFactory<>("difference"));

        TableColumn<RouteData, String> status =
                new TableColumn<>("Status");
        status.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        table.getColumns().addAll(
                route,
                bookings,
                required,
                allocated,
                difference,
                status
        );

        table.getItems().addAll(
                new RouteData(
                        "Mirpur", "417", "9", "9", "0", "✓ Good"),

                new RouteData(
                        "Dhanmondi", "371", "8", "10", "+2", "✓ Good"),

                new RouteData(
                        "Mohammadpur", "289", "6", "6", "0", "✓ Good"),

                new RouteData(
                        "Uttara", "222", "5", "4", "-1",
                        "⚠ Need 1 More"),

                new RouteData(
                        "Badda", "180", "4", "3", "-1",
                        "⚠ Need 1 More")
        );

        return table;
    }

    private HBox createNotification(String message, String time) {
        HBox box = new HBox(8);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10, 0, 10, 0));

        Label dot = new Label("•");
        dot.setTextFill(Color.web(BLUE));
        dot.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));

        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Segoe UI", 11));
        messageLabel.setTextFill(Color.web(DARK));
        messageLabel.setWrapText(true);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label timeLabel = new Label(time);
        timeLabel.setFont(Font.font("Segoe UI", 10));
        timeLabel.setTextFill(Color.web("#888888"));

        box.getChildren().addAll(
                dot,
                messageLabel,
                spacer,
                timeLabel
        );

        return box;
    }

    private ImageView createImageView(
            String resource,
            double width,
            double height) {

        java.io.InputStream stream =
                AdminDashboard.class.getResourceAsStream(resource);

        if (stream == null) {
            return new ImageView();
        }

        Image image = new Image(stream);

        ImageView view = new ImageView(image);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setSmooth(true);

        return view;
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class RouteData {

        private String route;
        private String bookings;
        private String required;
        private String allocated;
        private String difference;
        private String status;

        public RouteData(
                String route,
                String bookings,
                String required,
                String allocated,
                String difference,
                String status) {

            this.route = route;
            this.bookings = bookings;
            this.required = required;
            this.allocated = allocated;
            this.difference = difference;
            this.status = status;
        }

        public String getRoute() {
            return route;
        }

        public String getBookings() {
            return bookings;
        }

        public String getRequired() {
            return required;
        }

        public String getAllocated() {
            return allocated;
        }

        public String getDifference() {
            return difference;
        }

        public String getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
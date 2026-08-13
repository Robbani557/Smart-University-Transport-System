package admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
public class AdminDashboard extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String WHITE = "#FFFFFF";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String GREEN = "#2E7D32";
    private static final String ORANGE = "#EF6C00";
    private static final String PURPLE = "#6A1B9A";

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + LIGHT + ";"
        );

        // ==========================================
        // LEFT SIDEBAR
        // ==========================================

        VBox sidebar = createSidebar(stage);

        root.setLeft(sidebar);

        // ==========================================
        // TOP BAR
        // ==========================================

        HBox topBar = createTopBar();

        root.setTop(topBar);

        // ==========================================
        // MAIN CONTENT
        // ==========================================

        VBox content = new VBox(15);

        content.setPadding(
                new Insets(20)
        );

        // Page title
        Label title = new Label("Dashboard");

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

        // ==========================================
        // STAT CARDS
        // ==========================================

        HBox cards = new HBox(12);

        VBox studentsCard = createStatCard(
                "♙",
                "Total Students",
                "2,548",
                "+12 this week",
                BLUE2
        );

        VBox busesCard = createStatCard(
                "▣",
                "Total Buses",
                "68",
                "8 on route",
                GREEN
        );

        VBox bookingsCard = createStatCard(
                "▤",
                "Today's Bookings",
                "1,243",
                "+8% from yesterday",
                ORANGE
        );

        VBox seatsCard = createStatCard(
                "♿",
                "Available Seats",
                "862",
                "Across all routes",
                PURPLE
        );

        cards.getChildren().addAll(
                studentsCard,
                busesCard,
                bookingsCard,
                seatsCard
        );

        // ==========================================
        // BOOKING TABLE
        // ==========================================

        VBox bookingCard = new VBox(10);

        bookingCard.setPadding(
                new Insets(15)
        );

        bookingCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        Label bookingTitle =
                new Label(
                        "Bookings vs Bus Allocation (Today)"
                );

        bookingTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        15
                )
        );

        TableView<RouteData> table =
                createRouteTable();

        bookingCard.getChildren().addAll(
                bookingTitle,
                table
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        // ==========================================
        // NOTIFICATIONS
        // ==========================================

        VBox notificationCard =
                new VBox(10);

        notificationCard.setPadding(
                new Insets(15)
        );

        notificationCard.setPrefWidth(330);

        notificationCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        Label notificationTitle =
                new Label(
                        "Recent Notifications"
                );

        notificationTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        15
                )
        );

        notificationCard.getChildren().add(
                notificationTitle
        );

        notificationCard.getChildren().addAll(

                createNotification(
                        "New booking on Mirpur Route",
                        "10:20 AM"
                ),

                createNotification(
                        "Bus allocation updated for Uttara",
                        "09:45 AM"
                ),

                createNotification(
                        "Driver assigned to Bus 15",
                        "09:30 AM"
                ),

                createNotification(
                        "Schedule updated for Dhanmondi",
                        "09:10 AM"
                ),

                createNotification(
                        "New student registered",
                        "08:55 AM"
                )
        );

        // ==========================================
        // LOWER AREA
        // ==========================================

        HBox lowerArea =
                new HBox(12);

        lowerArea.getChildren().addAll(
                bookingCard,
                notificationCard
        );

        HBox.setHgrow(
                bookingCard,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                lowerArea,
                Priority.ALWAYS
        );

        // ==========================================
        // ADD TO CONTENT
        // ==========================================

        content.getChildren().addAll(
                title,
                cards,
                lowerArea
        );

        // ==========================================
        // ROOT
        // ==========================================

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
                "Smart University Transport System - Admin Dashboard"
        );

        stage.setScene(scene);

        stage.show();
    }

    // ==================================================
    // MAIN METHOD
    // ==================================================

    public static void main(String[] args) {

        launch(args);

    }

    // ==================================================
    // SIDEBAR
    // ==================================================

    private VBox createSidebar(Stage stage) {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(180);

        sidebar.setStyle(
                "-fx-background-color: " + BLUE + ";"
        );

        // Logo
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

        // Navigation
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

        // Active Dashboard
        dashboard.setStyle(
                "-fx-background-color: " + BLUE2 + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;"
        );

        // ==========================================
        // NAVIGATION
        // ==========================================

        students.setOnAction(e -> {

            ManageStudents studentsPage =
                    new ManageStudents();

            studentsPage.start(stage);

        });

            routes.setOnAction(e -> {

                ManageRoutes routesPage = new ManageRoutes();
                routesPage.start(stage);

    });

        buses.setOnAction(e -> {

            ManageBuses busesPage = new ManageBuses();
            busesPage.start(stage);

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
                    "Schedules module."
            );

        });

        bookings.setOnAction(e -> {

            showMessage(
                    "Bookings",
                    "Bookings module."
            );

        });

        reports.setOnAction(e -> {

            showMessage(
                    "Reports",
                    "Reports module."
            );

        });

        settings.setOnAction(e -> {

            showMessage(
                    "Settings",
                    "Settings module."
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

    // ==================================================
    // MENU BUTTON
    // ==================================================

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

    // ==================================================
    // TOP BAR
    // ==================================================

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
                "-fx-border-color: " + BORDER + ";"
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

    // ==================================================
    // STAT CARD
    // ==================================================

    private VBox createStatCard(
            String icon,
            String title,
            String value,
            String subtitle,
            String color
    ) {

        VBox card =
                new VBox(5);

        card.setPadding(
                new Insets(14)
        );

        card.setPrefHeight(100);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        // Icon
        Label iconLabel =
                new Label(icon);

        iconLabel.setMinSize(
                45,
                45
        );

        iconLabel.setPrefSize(
                45,
                45
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setTextFill(
                Color.WHITE
        );

        iconLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        20
                )
        );

        iconLabel.setStyle(
                "-fx-background-color: " +
                color +
                ";" +
                "-fx-background-radius: 8px;"
        );

        // Text
        VBox text =
                new VBox(2);

        Label cardTitle =
                new Label(title);

        cardTitle.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        Label number =
                new Label(value);

        number.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        21
                )
        );

        Label sub =
                new Label(subtitle);

        sub.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        sub.setTextFill(
                Color.web(GREEN)
        );

        text.getChildren().addAll(
                cardTitle,
                number,
                sub
        );

        HBox row =
                new HBox(
                        10,
                        iconLabel,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        card.getChildren().add(
                row
        );

        return card;
    }

    // ==================================================
    // ROUTE TABLE
    // ==================================================

    private TableView<RouteData> createRouteTable() {

        TableView<RouteData> table =
                new TableView<>();

        TableColumn<RouteData, String> route =
                new TableColumn<>("Route");

        route.setCellValueFactory(
                new PropertyValueFactory<>(
                        "route"
                )
        );

        TableColumn<RouteData, String> bookings =
                new TableColumn<>("Bookings");

        bookings.setCellValueFactory(
                new PropertyValueFactory<>(
                        "bookings"
                )
        );

        TableColumn<RouteData, String> required =
                new TableColumn<>(
                        "Required Buses"
                );

        required.setCellValueFactory(
                new PropertyValueFactory<>(
                        "required"
                )
        );

        TableColumn<RouteData, String> allocated =
                new TableColumn<>(
                        "Allocated Buses"
                );

        allocated.setCellValueFactory(
                new PropertyValueFactory<>(
                        "allocated"
                )
        );

        TableColumn<RouteData, String> difference =
                new TableColumn<>(
                        "Difference"
                );

        difference.setCellValueFactory(
                new PropertyValueFactory<>(
                        "difference"
                )
        );

        TableColumn<RouteData, String> status =
                new TableColumn<>(
                        "Status"
                );

        status.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        table.getColumns().addAll(
                route,
                bookings,
                required,
                allocated,
                difference,
                status
        );

        // Sample data
        table.getItems().addAll(

                new RouteData(
                        "Mirpur",
                        "417",
                        "9",
                        "9",
                        "0",
                        "✓ Good"
                ),

                new RouteData(
                        "Dhanmondi",
                        "371",
                        "8",
                        "10",
                        "+2",
                        "✓ Good"
                ),

                new RouteData(
                        "Mohammadpur",
                        "289",
                        "6",
                        "6",
                        "0",
                        "✓ Good"
                ),

                new RouteData(
                        "Uttara",
                        "222",
                        "5",
                        "4",
                        "-1",
                        "⚠ Need 1 More"
                ),

                new RouteData(
                        "Badda",
                        "180",
                        "4",
                        "3",
                        "-1",
                        "⚠ Need 1 More"
                )
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(38);

        return table;
    }

    // ==================================================
    // NOTIFICATION
    // ==================================================

    private HBox createNotification(
            String message,
            String time
    ) {

        HBox box =
                new HBox();

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(
                        10,
                        0,
                        10,
                        0
                )
        );

        Label messageLabel =
                new Label(message);

        messageLabel.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        timeLabel.setTextFill(
                Color.GRAY
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                messageLabel,
                spacer,
                timeLabel
        );

        return box;
    }

    // ==================================================
    // MESSAGE
    // ==================================================

    private void showMessage(
            String title,
            String message
    ) {

        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // ==================================================
    // ROUTE DATA CLASS
    // ==================================================

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
                String status
        ) {

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
}
package admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Settings extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String GREEN = "#2E7D32";
    private static final String RED = "#C62828";

    private TextField nameField;
    private TextField emailField;
    private TextField usernameField;
    private PasswordField currentPassword;
    private PasswordField newPassword;
    private PasswordField confirmPassword;
    private CheckBox bookingNotifications;
    private CheckBox scheduleNotifications;
    private CheckBox systemNotifications;
    private ComboBox<String> defaultPeriod;
    private ComboBox<String> defaultStatus;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT + ";");

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Settings"
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
                "/images/logo.png", 64, 64
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
                logo, name, system, admin
        );

        VBox menu = new VBox(5);
        menu.setPadding(new Insets(10, 10, 12, 10));

        Button dashboard = createMenuButton("⌂   Dashboard");
        Button students = createMenuButton("♙   Manage Students");
        Button routes = createMenuButton("⌁   Manage Routes");
        Button buses = createMenuButton("▣   Manage Buses");
        Button allocation = createMenuButton("⇄   Bus Allocation");
        Button schedules = createMenuButton("◷   Schedules");
        Button bookings = createMenuButton("▤   Bookings");
        Button reports = createMenuButton("▥   Reports");
        Button settingsButton = createMenuButton("⚙   Settings");

        setActive(settingsButton);

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

        bookings.setOnAction(e ->
                new Bookings().start(stage));

        reports.setOnAction(e ->
                new Reports().start(stage));

        settingsButton.setOnAction(e ->
                setActive(settingsButton));

        menu.getChildren().addAll(
                dashboard,
                students,
                routes,
                buses,
                allocation,
                schedules,
                bookings,
                reports,
                settingsButton
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
                brand, menu, spacer, logout
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

        Label title = new Label("Settings");
        title.setFont(Font.font(
                "Segoe UI", FontWeight.BOLD, 14
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
                "Segoe UI", FontWeight.BOLD, 11
        ));
        user.setTextFill(Color.web(DARK));

        Label role = new Label("Super Admin");
        role.setFont(Font.font("Segoe UI", 10));
        role.setTextFill(Color.web(MUTED));

        userBox.getChildren().addAll(user, role);

        topBar.getChildren().addAll(
                menuIcon, title, spacer,
                notification, userBox
        );

        return topBar;
    }

    private VBox createContent() {
        VBox content = new VBox(18);
        content.setPadding(new Insets(24));
        content.setFillWidth(true);

        VBox heading = new VBox(3);

        Label title = new Label("Settings");
        title.setFont(Font.font(
                "Segoe UI", FontWeight.BOLD, 24
        ));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Manage your administrator profile and application preferences"
        );
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        heading.getChildren().addAll(title, subtitle);

        VBox profile = createProfileCard();
        VBox security = createSecurityCard();
        VBox notifications = createNotificationCard();
        VBox preferences = createPreferenceCard();

        HBox actions = new HBox(12);
        actions.setAlignment(Pos.CENTER_RIGHT);

        Button reset = new Button("Reset");
        reset.setPrefHeight(40);
        reset.setMinWidth(105);
        reset.setFont(Font.font(
                "Segoe UI", FontWeight.BOLD, 13
        ));
        reset.setCursor(javafx.scene.Cursor.HAND);
        reset.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        Button save = new Button("Save Settings");
        save.setPrefHeight(40);
        save.setMinWidth(150);
        save.setFont(Font.font(
                "Segoe UI", FontWeight.BOLD, 13
        ));
        save.setTextFill(Color.WHITE);
        save.setCursor(javafx.scene.Cursor.HAND);
        setPrimaryButtonStyle(save);

        reset.setOnAction(e -> resetSettings());
        save.setOnAction(e -> saveSettings());

        actions.getChildren().addAll(reset, save);

        VBox cards = new VBox(16);
        cards.getChildren().addAll(
                profile,
                security,
                notifications,
                preferences
        );

        ScrollPane scroll = new ScrollPane(cards);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );
        scroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        VBox.setVgrow(scroll, Priority.ALWAYS);

        content.getChildren().addAll(
                heading, scroll, actions
        );

        return content;
    }

    private VBox createProfileCard() {
        VBox card = createCard();

        Label title = sectionTitle("Administrator Profile");
        Label subtitle = sectionSubtitle(
                "Update the information displayed in the admin panel."
        );

        nameField = createTextField("Full name");
        nameField.setText("Admin User");

        emailField = createTextField("Email address");
        emailField.setText("admin@diu.edu.bd");

        usernameField = createTextField("Username");
        usernameField.setText("admin");

        usernameField.setEditable(false);
        usernameField.setOpacity(0.75);

        GridPane grid = createTwoColumnGrid();

        grid.add(fieldBox("Full Name", nameField), 0, 0);
        grid.add(fieldBox("Email", emailField), 1, 0);
        grid.add(fieldBox("Username", usernameField), 0, 1);

        card.getChildren().addAll(
                title, subtitle, grid
        );

        return card;
    }

    private VBox createSecurityCard() {
        VBox card = createCard();

        Label title = sectionTitle("Security");
        Label subtitle = sectionSubtitle(
                "Change the administrator password."
        );

        currentPassword = createPasswordField(
                "Current password"
        );
        newPassword = createPasswordField(
                "New password"
        );
        confirmPassword = createPasswordField(
                "Confirm new password"
        );

        GridPane grid = createTwoColumnGrid();

        grid.add(
                fieldBox("Current Password", currentPassword),
                0, 0
        );
        grid.add(
                fieldBox("New Password", newPassword),
                1, 0
        );
        grid.add(
                fieldBox(
                        "Confirm Password",
                        confirmPassword
                ),
                0, 1
        );

        card.getChildren().addAll(
                title, subtitle, grid
        );

        return card;
    }

    private VBox createNotificationCard() {
        VBox card = createCard();

        Label title = sectionTitle(
                "Notification Preferences"
        );
        Label subtitle = sectionSubtitle(
                "Choose which admin notifications you want to receive."
        );

        bookingNotifications =
                new CheckBox("Booking notifications");
        bookingNotifications.setSelected(true);

        scheduleNotifications =
                new CheckBox("Schedule notifications");
        scheduleNotifications.setSelected(true);

        systemNotifications =
                new CheckBox("System notifications");
        systemNotifications.setSelected(true);

        VBox options = new VBox(
                10,
                bookingNotifications,
                scheduleNotifications,
                systemNotifications
        );

        card.getChildren().addAll(
                title, subtitle, options
        );

        return card;
    }

    private VBox createPreferenceCard() {
        VBox card = createCard();

        Label title = sectionTitle(
                "Application Preferences"
        );
        Label subtitle = sectionSubtitle(
                "Set default filters used throughout the admin panel."
        );

        defaultPeriod = new ComboBox<>();
        defaultPeriod.getItems().addAll(
                "This Week",
                "This Month",
                "Last Month",
                "This Semester"
        );
        defaultPeriod.setValue("This Month");
        defaultPeriod.setMaxWidth(Double.MAX_VALUE);
        defaultPeriod.setPrefHeight(40);

        defaultStatus = new ComboBox<>();
        defaultStatus.getItems().addAll(
                "All Status",
                "Active",
                "Pending",
                "Cancelled"
        );
        defaultStatus.setValue("All Status");
        defaultStatus.setMaxWidth(Double.MAX_VALUE);
        defaultStatus.setPrefHeight(40);

        GridPane grid = createTwoColumnGrid();

        grid.add(
                fieldBox(
                        "Default Report Period",
                        defaultPeriod
                ),
                0, 0
        );

        grid.add(
                fieldBox(
                        "Default Status Filter",
                        defaultStatus
                ),
                1, 0
        );

        card.getChildren().addAll(
                title, subtitle, grid
        );

        return card;
    }

    private VBox createCard() {
        VBox card = new VBox(8);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );
        return card;
    }

    private GridPane createTwoColumnGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(14);

        ColumnConstraints first =
                new ColumnConstraints();
        first.setPercentWidth(50);

        ColumnConstraints second =
                new ColumnConstraints();
        second.setPercentWidth(50);

        grid.getColumnConstraints().addAll(
                first, second
        );

        return grid;
    }

    private VBox fieldBox(
            String labelText,
            Control control) {

        Label label = new Label(labelText);
        label.setFont(Font.font(
                "Segoe UI", FontWeight.BOLD, 11
        ));
        label.setTextFill(Color.web(DARK));

        if (control instanceof Region) {
            ((Region) control).setMaxWidth(
                    Double.MAX_VALUE
            );
        }

        VBox box = new VBox(5);
        box.getChildren().addAll(
                label, control
        );

        return box;
    }

    private TextField createTextField(
            String prompt) {

        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-padding: 8 10 8 10;"
        );
        return field;
    }

    private PasswordField createPasswordField(
            String prompt) {

        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-padding: 8 10 8 10;"
        );
        return field;
    }

    private Label sectionTitle(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                16
        ));
        label.setTextFill(Color.web(DARK));
        return label;
    }

    private Label sectionSubtitle(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(
                "Segoe UI", 11
        ));
        label.setTextFill(Color.web(MUTED));
        return label;
    }

    private void saveSettings() {
        String newPasswordValue =
                newPassword.getText();
        String confirmValue =
                confirmPassword.getText();

        if (!newPasswordValue.isEmpty()
                || !confirmValue.isEmpty()
                || !currentPassword.getText().isEmpty()) {

            if (currentPassword.getText().isEmpty()
                    || newPasswordValue.isEmpty()
                    || confirmValue.isEmpty()) {

                showMessage(
                        "Security",
                        "Complete all password fields before changing the password."
                );
                return;
            }

            if (!newPasswordValue.equals(
                    confirmValue)) {

                showMessage(
                        "Security",
                        "New password and confirmation do not match."
                );
                return;
            }

            if (newPasswordValue.length() < 6) {
                showMessage(
                        "Security",
                        "New password must contain at least 6 characters."
                );
                return;
            }
        }

        showMessage(
                "Settings Saved",
                "Your settings have been saved for this frontend session."
        );

        currentPassword.clear();
        newPassword.clear();
        confirmPassword.clear();
    }

    private void resetSettings() {
        nameField.setText("Admin User");
        emailField.setText("admin@diu.edu.bd");
        usernameField.setText("admin");

        currentPassword.clear();
        newPassword.clear();
        confirmPassword.clear();

        bookingNotifications.setSelected(true);
        scheduleNotifications.setSelected(true);
        systemNotifications.setSelected(true);

        defaultPeriod.setValue("This Month");
        defaultStatus.setValue("All Status");
    }

    private Button createMenuButton(
            String text) {

        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(42);
        button.setMinHeight(42);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(
                new Insets(10, 12, 10, 12)
        );
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font(
                "Segoe UI", 12
        ));
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

    private void setPrimaryButtonStyle(
            Button button) {

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
                Settings.class.getResourceAsStream(
                        resource
                );

        if (stream == null) {
            return new ImageView();
        }

        ImageView view = new ImageView(
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

        Alert alert = new Alert(
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

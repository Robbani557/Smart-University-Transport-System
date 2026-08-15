package admin;

import data.AppData;
import data.TransportData;
import model.Route;

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

public class ManageRoutes extends Application {

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String GREEN = "#2E7D32";
    private static final String ORANGE = "#EF6C00";
    private static final String RED = "#C62828";

    private final TableView<Route> table =
            new TableView<>();

    private final ObservableList<Route> routes =
            FXCollections.observableArrayList();

    private final TransportData data =
            AppData.getTransportData();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle(
                "-fx-background-color: " + LIGHT + ";"
        );

        root.setLeft(createSidebar(stage));
        root.setTop(createTopBar());
        root.setCenter(createContent());

        Scene scene =
                new Scene(root, 1200, 760);

        stage.setTitle(
                "Smart University Transport System - Manage Routes"
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
        sidebar.setStyle(
                "-fx-background-color: " + BLUE + ";"
        );

        VBox brand = new VBox(4);
        brand.setPadding(new Insets(18));
        brand.setAlignment(Pos.CENTER_LEFT);

        ImageView logo =
                createImageView(
                        "/images/logo.png",
                        64,
                        64
                );

        Label name =
                new Label("Smart University");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                15
        ));

        Label system =
                new Label("Transport System");
        system.setTextFill(
                Color.web("#DCEBFF")
        );
        system.setFont(
                Font.font("Segoe UI", 10)
        );

        Label admin =
                new Label("ADMIN PANEL");
        admin.setTextFill(
                Color.web("#BBD6F7")
        );
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
        menu.setPadding(
                new Insets(8, 10, 10, 10)
        );

        Button dashboard =
                createMenuButton("⌂   Dashboard");
        Button students =
                createMenuButton("♙   Manage Students");
        Button routesButton =
                createMenuButton("⌁   Manage Routes");
        Button buses =
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

        setActive(routesButton);

        dashboard.setOnAction(e ->
                new AdminDashboard().start(stage));

        students.setOnAction(e ->
                new ManageStudents().start(stage));

        routesButton.setOnAction(e ->
                setActive(routesButton));

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

        settings.setOnAction(e ->
                new Settings().start(stage));

        menu.getChildren().addAll(
                dashboard,
                students,
                routesButton,
                buses,
                allocation,
                schedules,
                bookings,
                reports,
                settings
        );

        Region spacer = new Region();
        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button logout =
                createMenuButton("⇥   Logout");

        logout.setOnAction(e -> {
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to log out?",
                    ButtonType.YES,
                    ButtonType.NO
            );

            alert.setTitle("Logout");
            alert.setHeaderText(null);

            if (alert.showAndWait()
                    .orElse(ButtonType.NO)
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

        Label page = new Label("Manage Routes");
        page.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                14
        ));
        page.setTextFill(
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
                page,
                spacer,
                notification,
                userBox
        );

        return topBar;
    }

    private VBox createContent() {
        VBox content = new VBox(16);
        content.setPadding(
                new Insets(20)
        );
        content.setFillWidth(true);

        HBox heading = new HBox(12);
        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(3);

        Label title =
                new Label("Manage Routes");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(
                Color.web(DARK)
        );

        Label subtitle =
                new Label(
                        "Add, search, edit and remove transport routes"
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

        Region headingSpacer =
                new Region();

        HBox.setHgrow(
                headingSpacer,
                Priority.ALWAYS
        );

        Button addRoute =
                new Button("+  Add Route");

        addRoute.setPrefHeight(40);
        addRoute.setMinWidth(125);
        addRoute.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));
        addRoute.setTextFill(
                Color.WHITE
        );
        addRoute.setCursor(
                javafx.scene.Cursor.HAND
        );

        setPrimaryButtonStyle(addRoute);

        addRoute.setOnAction(e ->
                addRoute());

        heading.getChildren().addAll(
                titleBox,
                headingSpacer,
                addRoute
        );

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(
                Pos.CENTER_LEFT
        );

        TextField search = new TextField();
        search.setPromptText(
                "Search by route name..."
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

        Label count = new Label();
        count.setMinWidth(100);
        count.setAlignment(
                Pos.CENTER_RIGHT
        );
        count.setFont(
                Font.font("Segoe UI", 11)
        );
        count.setTextFill(
                Color.web(MUTED)
        );

        createTable();
        loadRoutes();

        updateCount(
                count,
                routes.size()
        );

        search.textProperty().addListener(
                (obs, oldValue, newValue) -> {
                    searchRoute(newValue);

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

        VBox tableCard =
                new VBox(10);

        tableCard.setPadding(
                new Insets(14)
        );

        tableCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

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
                searchBar,
                tableCard
        );

        return content;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<Route, String> name =
                new TableColumn<>("Route");

        name.setCellValueFactory(
                new PropertyValueFactory<>(
                        "routeName"
                )
        );
        name.setMinWidth(150);

        TableColumn<Route, Number> students =
                new TableColumn<>(
                        "Students"
                );

        students.setCellValueFactory(
                new PropertyValueFactory<>(
                        "totalStudents"
                )
        );
        students.setMinWidth(100);

        TableColumn<Route, Number> capacity =
                new TableColumn<>(
                        "Bus Capacity"
                );

        capacity.setCellValueFactory(
                new PropertyValueFactory<>(
                        "busCapacity"
                )
        );
        capacity.setMinWidth(110);

        TableColumn<Route, Number> required =
                new TableColumn<>(
                        "Required Buses"
                );

        required.setCellValueFactory(
                new PropertyValueFactory<>(
                        "requiredBuses"
                )
        );
        required.setMinWidth(120);

        TableColumn<Route, Number> allocated =
                new TableColumn<>(
                        "Allocated"
                );

        allocated.setCellValueFactory(
                new PropertyValueFactory<>(
                        "allocatedBuses"
                )
        );
        allocated.setMinWidth(95);

        TableColumn<Route, String> status =
                new TableColumn<>("Status");

        status.setCellValueFactory(
                cell -> new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getStatus()
                )
        );
        status.setMinWidth(130);

        TableColumn<Route, Void> action =
                new TableColumn<>("Actions");

        action.setMinWidth(145);
        action.setMaxWidth(160);

        action.setCellFactory(column ->
                new TableCell<Route, Void>() {

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(6);

                    {
                        edit.setFont(
                                Font.font(
                                        "Segoe UI",
                                        10
                                )
                        );
                        edit.setTextFill(
                                Color.WHITE
                        );
                        edit.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        edit.setStyle(
                                "-fx-background-color: " +
                                BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        delete.setFont(
                                Font.font(
                                        "Segoe UI",
                                        10
                                )
                        );
                        delete.setTextFill(
                                Color.WHITE
                        );
                        delete.setCursor(
                                javafx.scene.Cursor.HAND
                        );
                        delete.setStyle(
                                "-fx-background-color: " +
                                RED + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        edit.setOnAction(e -> {
                            Route route =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            editRoute(route);
                        });

                        delete.setOnAction(e -> {
                            Route route =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteRoute(route);
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

                        setGraphic(
                                empty
                                        ? null
                                        : box
                        );
                    }
                }
        );

        table.getColumns().addAll(
                name,
                students,
                capacity,
                required,
                allocated,
                status,
                action
        );

        table.setItems(routes);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(42);

        table.setPlaceholder(
                new Label("No routes found.")
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;"
        );
    }

    private void loadRoutes() {
        routes.clear();
        routes.addAll(
                data.getRoutes()
        );
    }

    private void searchRoute(
            String keyword
    ) {
        if (keyword == null ||
                keyword.trim().isEmpty()) {

            table.setItems(routes);
            return;
        }

        String search =
                keyword.trim().toLowerCase();

        ObservableList<Route> result =
                FXCollections.observableArrayList();

        for (Route route : routes) {
            String name =
                    safe(route.getRouteName());

            if (name.toLowerCase()
                    .contains(search)) {

                result.add(route);
            }
        }

        table.setItems(result);
    }

    private void addRoute() {
        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle("Add Route");
        dialog.setHeaderText(
                "Add New Route"
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Route Name"
        );

        TextField students =
                new TextField();

        students.setPromptText(
                "Total Students"
        );

        TextField capacity =
                new TextField();

        capacity.setPromptText(
                "Bus Capacity"
        );

        TextField allocated =
                new TextField();

        allocated.setPromptText(
                "Allocated Buses"
        );
        allocated.setText("0");

        VBox form = new VBox(8);

        form.setPadding(
                new Insets(12)
        );

        form.setPrefWidth(360);

        form.getChildren().addAll(
                new Label("Route Name"),
                name,
                new Label("Total Students"),
                students,
                new Label("Bus Capacity"),
                capacity,
                new Label("Allocated Buses"),
                allocated
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

                    if (result != ButtonType.OK) {
                        return;
                    }

                    String routeName =
                            name.getText().trim();

                    if (routeName.isEmpty()) {
                        showMessage(
                                "Missing Information",
                                "Please enter a route name."
                        );
                        return;
                    }

                    int studentCount;

                    int busCapacity;

                    int allocatedBuses;

                    try {
                        studentCount =
                                Integer.parseInt(
                                        students
                                                .getText()
                                                .trim()
                                );

                        busCapacity =
                                Integer.parseInt(
                                        capacity
                                                .getText()
                                                .trim()
                                );

                        allocatedBuses =
                                Integer.parseInt(
                                        allocated
                                                .getText()
                                                .trim()
                                );

                    } catch (
                            NumberFormatException ex
                    ) {

                        showMessage(
                                "Invalid Number",
                                "Students, capacity and allocated buses must be whole numbers."
                        );

                        return;
                    }

                    if (studentCount < 0
                            || busCapacity <= 0
                            || allocatedBuses < 0) {

                        showMessage(
                                "Invalid Values",
                                "Students and allocated buses cannot be negative, and bus capacity must be greater than zero."
                        );

                        return;
                    }

                    for (Route existing :
                            data.getRoutes()) {

                        if (safe(
                                existing.getRouteName()
                        ).equalsIgnoreCase(
                                routeName
                        )) {

                            showMessage(
                                    "Duplicate Route",
                                    "A route with this name already exists."
                            );

                            return;
                        }
                    }

                    Route route =
                            new Route(
                                    routeName,
                                    studentCount,
                                    busCapacity,
                                    allocatedBuses
                            );

                    data.getRoutes().add(
                            route
                    );

                    loadRoutes();
                    table.setItems(routes);
                    table.refresh();
                });
    }

    private void editRoute(
            Route route
    ) {
        TextField name =
                new TextField(
                        route.getRouteName()
                );

        TextField students =
                new TextField(
                        String.valueOf(
                                route.getTotalStudents()
                        )
                );

        TextField capacity =
                new TextField(
                        String.valueOf(
                                route.getBusCapacity()
                        )
                );

        TextField allocated =
                new TextField(
                        String.valueOf(
                                route.getAllocatedBuses()
                        )
                );

        VBox form = new VBox(8);

        form.setPadding(
                new Insets(12)
        );

        form.setPrefWidth(360);

        form.getChildren().addAll(
                new Label("Route Name"),
                name,
                new Label("Total Students"),
                students,
                new Label("Bus Capacity"),
                capacity,
                new Label("Allocated Buses"),
                allocated
        );

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle("Edit Route");
        dialog.setHeaderText(
                "Edit " + route.getRouteName()
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

                    if (result != ButtonType.OK) {
                        return;
                    }

                    String routeName =
                            name.getText().trim();

                    if (routeName.isEmpty()) {
                        showMessage(
                                "Invalid Information",
                                "Route name cannot be empty."
                        );
                        return;
                    }

                    int studentCount;
                    int busCapacity;
                    int allocatedBuses;

                    try {
                        studentCount =
                                Integer.parseInt(
                                        students
                                                .getText()
                                                .trim()
                                );

                        busCapacity =
                                Integer.parseInt(
                                        capacity
                                                .getText()
                                                .trim()
                                );

                        allocatedBuses =
                                Integer.parseInt(
                                        allocated
                                                .getText()
                                                .trim()
                                );

                    } catch (
                            NumberFormatException ex
                    ) {

                        showMessage(
                                "Invalid Number",
                                "Students, capacity and allocated buses must be whole numbers."
                        );

                        return;
                    }

                    if (studentCount < 0
                            || busCapacity <= 0
                            || allocatedBuses < 0) {

                        showMessage(
                                "Invalid Values",
                                "Please enter valid non-negative values and a positive bus capacity."
                        );

                        return;
                    }

                    for (Route existing :
                            data.getRoutes()) {

                        if (existing != route
                                && safe(
                                        existing
                                                .getRouteName()
                                ).equalsIgnoreCase(
                                        routeName
                                )) {

                            showMessage(
                                    "Duplicate Route",
                                    "A route with this name already exists."
                            );

                            return;
                        }
                    }

                    route.setRouteName(
                            routeName
                    );

                    route.setTotalStudents(
                            studentCount
                    );

                    route.setBusCapacity(
                            busCapacity
                    );

                    route.setAllocatedBuses(
                            allocatedBuses
                    );

                    table.refresh();
                });
    }

    private void deleteRoute(
            Route route
    ) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle(
                "Delete Route"
        );

        alert.setHeaderText(
                "Delete " +
                route.getRouteName() +
                "?"
        );

        alert.setContentText(
                "This route will be removed from the shared transport data."
        );

        alert.showAndWait()
                .ifPresent(result -> {

                    if (result == ButtonType.OK) {
                        data.getRoutes()
                                .remove(route);

                        routes.remove(route);

                        table.refresh();
                    }
                });
    }

    private Button createMenuButton(
            String text
    ) {
        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);
        button.setMinHeight(42);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        button.setTextFill(
                Color.WHITE
        );

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

    private void setActive(
            Button button
    ) {
        button.setStyle(
                "-fx-background-color: " +
                BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }

    private void setPrimaryButtonStyle(
            Button button
    ) {
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
                ManageRoutes.class
                        .getResourceAsStream(
                                resource
                        );

        if (stream == null) {
            return new ImageView();
        }

        Image image =
                new Image(stream);

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
                number +
                " route" +
                (number == 1 ? "" : "s")
        );
    }

    private String safe(
            String value
    ) {
        return value == null
                ? ""
                : value;
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

    public static void main(
            String[] args
    ) {
        launch(args);
    }
}

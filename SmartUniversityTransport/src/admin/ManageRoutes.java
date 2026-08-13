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
public class ManageRoutes extends Application {

    private final TableView<Route> table = new TableView<Route>();

    private final ObservableList<Route> routeList =
            FXCollections.observableArrayList();

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String RED = "#C62828";
    private static final String GREEN = "#2E7D32";

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + LIGHT + ";"
        );

        // ==========================================
        // SIDEBAR
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

        // ==========================================
        // TITLE
        // ==========================================

        HBox heading = new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Manage Routes");

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

        Button addRoute =
                new Button("+ Add Route");

        addRoute.setPrefWidth(120);
        addRoute.setPrefHeight(38);

        addRoute.setTextFill(
                Color.WHITE
        );

        addRoute.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        addRoute.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        addRoute.setOnAction(
                e -> showAddRouteDialog()
        );

        heading.getChildren().addAll(
                title,
                headingSpacer,
                addRoute
        );

        // ==========================================
        // SEARCH
        // ==========================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search route..."
        );

        searchField.setPrefWidth(300);
        searchField.setPrefHeight(38);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        searchRoute(newValue)
        );

        // ==========================================
        // TABLE
        // ==========================================

        createTable();

        loadRoutes();

        VBox tableBox =
                new VBox();

        tableBox.setPadding(
                new Insets(15)
        );

        tableBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        tableBox.getChildren().add(table);

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
                "Smart University Transport System - Manage Routes"
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
                "-fx-background-color: " + BLUE + ";"
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
        routes.setStyle(
                "-fx-background-color: " + BLUE2 + ";" +
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

            // Current page

        });

        buses.setOnAction(e -> {

            showMessage(
                    "Manage Buses",
                    "Manage Buses page will open here."
            );

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

    // ==========================================
    // CREATE TABLE
    // ==========================================

   private void createTable() {

    TableColumn<Route, String> id =
            new TableColumn<Route, String>("Route ID");

    id.setCellValueFactory(
            new PropertyValueFactory<Route, String>("id")
    );

    TableColumn<Route, String> routeName =
            new TableColumn<Route, String>("Route Name");

    routeName.setCellValueFactory(
            new PropertyValueFactory<Route, String>("routeName")
    );

    TableColumn<Route, String> startPoint =
            new TableColumn<Route, String>("Start Point");

    startPoint.setCellValueFactory(
            new PropertyValueFactory<Route, String>("startPoint")
    );

    TableColumn<Route, String> endPoint =
            new TableColumn<Route, String>("End Point");

    endPoint.setCellValueFactory(
            new PropertyValueFactory<Route, String>("endPoint")
    );

    TableColumn<Route, String> distance =
            new TableColumn<Route, String>("Distance");

    distance.setCellValueFactory(
            new PropertyValueFactory<Route, String>("distance")
    );

    TableColumn<Route, String> status =
            new TableColumn<Route, String>("Status");

    status.setCellValueFactory(
            new PropertyValueFactory<Route, String>("status")
    );

    TableColumn<Route, Void> action =
            new TableColumn<Route, Void>("Action");

    action.setCellFactory(
            column -> new TableCell<Route, Void>() {

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

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(box);
                    }
                }
            }
    );

    table.getColumns().addAll(
            id,
            routeName,
            startPoint,
            endPoint,
            distance,
            status,
            action
    );

    table.setItems(routeList);

    table.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY
    );

    table.setFixedCellSize(40);
}

    // ==========================================
    // LOAD ROUTES
    // ==========================================

    private void loadRoutes() {

        routeList.clear();

        routeList.addAll(

                new Route(
                        "R001",
                        "Mirpur - University",
                        "Mirpur",
                        "University Campus",
                        "12 km",
                        "Active"
                ),

                new Route(
                        "R002",
                        "Uttara - University",
                        "Uttara",
                        "University Campus",
                        "15 km",
                        "Active"
                ),

                new Route(
                        "R003",
                        "Dhanmondi - University",
                        "Dhanmondi",
                        "University Campus",
                        "8 km",
                        "Active"
                ),

                new Route(
                        "R004",
                        "Mohammadpur - University",
                        "Mohammadpur",
                        "University Campus",
                        "10 km",
                        "Active"
                ),

                new Route(
                        "R005",
                        "Badda - University",
                        "Badda",
                        "University Campus",
                        "11 km",
                        "Inactive"
                ),

                new Route(
                        "R006",
                        "Gulshan - University",
                        "Gulshan",
                        "University Campus",
                        "9 km",
                        "Active"
                )
        );
    }

    // ==========================================
    // SEARCH
    // ==========================================

    private void searchRoute(
            String keyword
    ) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            table.setItems(
                    routeList
            );

            return;
        }

        String search =
                keyword.toLowerCase();

        ObservableList<Route> result =
                FXCollections.observableArrayList();

        for (Route route : routeList) {

            if (
                    route.getId()
                            .toLowerCase()
                            .contains(search)

                    ||

                    route.getRouteName()
                            .toLowerCase()
                            .contains(search)

                    ||

                    route.getStartPoint()
                            .toLowerCase()
                            .contains(search)

                    ||

                    route.getEndPoint()
                            .toLowerCase()
                            .contains(search)
            ) {

                result.add(route);
            }
        }

        table.setItems(result);
    }

    // ==========================================
    // ADD ROUTE
    // ==========================================

    private void showAddRouteDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "Add Route"
        );

        dialog.setHeaderText(
                "Add New Transport Route"
        );

        TextField id =
                new TextField();

        id.setPromptText(
                "Route ID"
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Route Name"
        );

        TextField start =
                new TextField();

        start.setPromptText(
                "Start Point"
        );

        TextField end =
                new TextField();

        end.setPromptText(
                "End Point"
        );

        TextField distance =
                new TextField();

        distance.setPromptText(
                "Distance e.g. 10 km"
        );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Inactive"
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
                new Label("Route ID"),
                id,

                new Label("Route Name"),
                name,

                new Label("Start Point"),
                start,

                new Label("End Point"),
                end,

                new Label("Distance"),
                distance,

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
                                id.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                name.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                start.getText()
                                        .trim()
                                        .isEmpty()

                                ||

                                end.getText()
                                        .trim()
                                        .isEmpty()
                        ) {

                            showMessage(
                                    "Error",
                                    "Please fill all required fields."
                            );

                            return;
                        }

                        Route newRoute =
                                new Route(
                                        id.getText(),
                                        name.getText(),
                                        start.getText(),
                                        end.getText(),
                                        distance.getText(),
                                        status.getValue()
                                );

                        routeList.add(
                                newRoute
                        );

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // EDIT ROUTE
    // ==========================================

    private void editRoute(
            Route route
    ) {

        TextField name =
                new TextField(
                        route.getRouteName()
                );

        TextField start =
                new TextField(
                        route.getStartPoint()
                );

        TextField end =
                new TextField(
                        route.getEndPoint()
                );

        TextField distance =
                new TextField(
                        route.getDistance()
                );

        ComboBox<String> status =
                new ComboBox<String>();

        status.getItems().addAll(
                "Active",
                "Inactive"
        );

        status.setValue(
                route.getStatus()
        );

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(15)
        );

        box.getChildren().addAll(
                new Label("Route Name"),
                name,

                new Label("Start Point"),
                start,

                new Label("End Point"),
                end,

                new Label("Distance"),
                distance,

                new Label("Status"),
                status
        );

        Dialog<ButtonType> dialog =
                new Dialog<ButtonType>();

        dialog.setTitle(
                "Edit Route"
        );

        dialog.setHeaderText(
                "Edit " + route.getId()
        );

        dialog.getDialogPane()
                .setContent(box);

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

                        route.setRouteName(
                                name.getText()
                        );

                        route.setStartPoint(
                                start.getText()
                        );

                        route.setEndPoint(
                                end.getText()
                        );

                        route.setDistance(
                                distance.getText()
                        );

                        route.setStatus(
                                status.getValue()
                        );

                        table.refresh();
                    }
                });
    }

    // ==========================================
    // DELETE ROUTE
    // ==========================================

    private void deleteRoute(
            Route route
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle(
                "Delete Route"
        );

        alert.setHeaderText(
                "Delete " + route.getRouteName() + "?"
        );

        alert.setContentText(
                "Are you sure you want to delete this route?"
        );

        alert.showAndWait()
                .ifPresent(result -> {

                    if (result ==
                            ButtonType.OK) {

                        routeList.remove(
                                route
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
    // ROUTE MODEL CLASS
    // ==========================================

    public static class Route {

        private String id;
        private String routeName;
        private String startPoint;
        private String endPoint;
        private String distance;
        private String status;

        public Route(
                String id,
                String routeName,
                String startPoint,
                String endPoint,
                String distance,
                String status
        ) {

            this.id = id;
            this.routeName = routeName;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.distance = distance;
            this.status = status;
        }

        public String getId() {

            return id;
        }

        public String getRouteName() {

            return routeName;
        }

        public String getStartPoint() {

            return startPoint;
        }

        public String getEndPoint() {

            return endPoint;
        }

        public String getDistance() {

            return distance;
        }

        public String getStatus() {

            return status;
        }

        public void setRouteName(
                String routeName
        ) {

            this.routeName = routeName;
        }

        public void setStartPoint(
                String startPoint
        ) {

            this.startPoint = startPoint;
        }

        public void setEndPoint(
                String endPoint
        ) {

            this.endPoint = endPoint;
        }

        public void setDistance(
                String distance
        ) {

            this.distance = distance;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}

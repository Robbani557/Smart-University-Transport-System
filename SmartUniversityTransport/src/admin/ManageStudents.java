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
import data.AppData;
import model.Route;
import model.Student;

public class ManageStudents extends Application {

    private final TableView<Student> table = new TableView<>();
    private final ObservableList<Student> students =
            FXCollections.observableArrayList();

    private static final String BLUE = "#1565C0";
    private static final String BLUE2 = "#1976D2";
    private static final String LIGHT = "#F5F7FA";
    private static final String WHITE = "#FFFFFF";
    private static final String BORDER = "#E0E0E0";
    private static final String DARK = "#212121";
    private static final String MUTED = "#667085";
    private static final String RED = "#C62828";

    private Stage currentStage;
    private Button studentsButton;

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
                "Smart University Transport System - Manage Students");
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
        menu.setPadding(new Insets(8, 10, 10, 10));

        Button dashboard = createMenuButton("⌂   Dashboard");
        studentsButton = createMenuButton("♙   Manage Students");
        Button routes = createMenuButton("⌁   Manage Routes");
        Button buses = createMenuButton("▣   Manage Buses");
        Button allocation = createMenuButton("⇄   Bus Allocation");
        Button schedules = createMenuButton("◷   Schedules");
        Button bookings = createMenuButton("▤   Bookings");
        Button reports = createMenuButton("▥   Reports");
        Button settings = createMenuButton("⚙   Settings");

        setActive(studentsButton);

        dashboard.setOnAction(e -> {
            new AdminDashboard().start(stage);
        });

        studentsButton.setOnAction(e -> {
            setActive(studentsButton);
        });

        routes.setOnAction(e -> {
            new ManageRoutes().start(stage);
        });

        buses.setOnAction(e -> {
            new ManageBuses().start(stage);
        });

        allocation.setOnAction(e -> showMessage(
                "Bus Allocation",
                "Bus Allocation page will open here."));

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
                studentsButton,
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

        Label page = new Label("Manage Students");
        page.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        page.setTextFill(Color.web(DARK));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label notification = new Label("♧");
        notification.setFont(Font.font("Segoe UI", 20));
        notification.setTextFill(Color.web(BLUE));
        notification.setCursor(javafx.scene.Cursor.HAND);

        VBox userBox = new VBox(1);
        userBox.setAlignment(Pos.CENTER_RIGHT);

        Label user = new Label("Admin User");
        user.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
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

        Label title = new Label("Manage Students");
        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                24
        ));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Add, search, edit and remove student records"
        );
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web(MUTED));

        titleBox.getChildren().addAll(title, subtitle);

        Region headingSpacer = new Region();
        HBox.setHgrow(headingSpacer, Priority.ALWAYS);

        Button addStudent = new Button("+  Add Student");
        addStudent.setPrefHeight(40);
        addStudent.setMinWidth(135);
        addStudent.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));
        addStudent.setTextFill(Color.WHITE);
        addStudent.setCursor(javafx.scene.Cursor.HAND);

        addStudent.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        addStudent.setOnMouseEntered(e ->
                addStudent.setStyle(
                        "-fx-background-color: " + BLUE2 + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        addStudent.setOnMouseExited(e ->
                addStudent.setStyle(
                        "-fx-background-color: " + BLUE + ";" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );

        addStudent.setOnAction(e -> addStudent());

        heading.getChildren().addAll(
                titleBox,
                headingSpacer,
                addStudent
        );

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText(
                "Search by student ID, name, department or phone..."
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
        count.setFont(Font.font("Segoe UI", 11));
        count.setTextFill(Color.web(MUTED));
        count.setMinWidth(110);
        count.setAlignment(Pos.CENTER_RIGHT);

        createTable();
        loadStudents();
        updateCount(count, students.size());

        search.textProperty().addListener(
                (obs, oldValue, newValue) -> {
                    searchStudent(newValue);
                    updateCount(count, table.getItems().size());
                }
        );

        searchBar.getChildren().addAll(search, count);

        VBox tableCard = new VBox(10);
        tableCard.setPadding(new Insets(14));
        tableCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        tableCard.getChildren().add(table);
        VBox.setVgrow(table, Priority.ALWAYS);
        VBox.setVgrow(tableCard, Priority.ALWAYS);

        content.getChildren().addAll(
                heading,
                searchBar,
                tableCard
        );

        VBox.setVgrow(tableCard, Priority.ALWAYS);

        return content;
    }

    private void createTable() {
        table.getColumns().clear();

        TableColumn<Student, String> id =
                new TableColumn<>("Student ID");
        id.setCellValueFactory(
                new PropertyValueFactory<>("studentId")
        );
        id.setMinWidth(110);

        TableColumn<Student, String> name =
                new TableColumn<>("Name");
        name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        name.setMinWidth(170);

        TableColumn<Student, String> department =
                new TableColumn<>("Department");
        department.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );
        department.setMinWidth(130);

        TableColumn<Student, String> route =
                new TableColumn<>("Route");
        route.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getRoute() == null
                                ? "Unassigned"
                                : cell.getValue().getRoute().getRouteName()
                )
        );
        route.setMinWidth(140);

        TableColumn<Student, Void> action =
                new TableColumn<>("Actions");
        action.setMinWidth(120);
        action.setMaxWidth(140);

        action.setCellFactory(column ->
                new TableCell<Student, Void>() {

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(6);

                    {
                        edit.setFont(Font.font("Segoe UI", 10));
                        edit.setTextFill(Color.WHITE);
                        edit.setCursor(javafx.scene.Cursor.HAND);
                        edit.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        delete.setFont(Font.font("Segoe UI", 10));
                        delete.setTextFill(Color.WHITE);
                        delete.setCursor(javafx.scene.Cursor.HAND);
                        delete.setStyle(
                                "-fx-background-color: " + RED + ";" +
                                "-fx-background-radius: 5px;"
                        );

                        edit.setOnAction(e -> {
                            Student student =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            editStudent(student);
                        });

                        delete.setOnAction(e -> {
                            Student student =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteStudent(student);
                        });

                        box.setAlignment(Pos.CENTER);
                        box.getChildren().addAll(edit, delete);
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
                name,
                department,
                route,
                action
        );

        table.setItems(students);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );
        table.setFixedCellSize(42);
        table.setPlaceholder(
                new Label("No students found.")
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;"
        );
    }

    private void loadStudents() {
        students.clear();

        for (Student student : AppData.getTransportData().getStudents()) {
            if (student != null) {
                students.add(student);
            }
        }
    }

    private void searchStudent(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            table.setItems(students);
            return;
        }

        String search = keyword.trim().toLowerCase();

        ObservableList<Student> result =
                FXCollections.observableArrayList();

        for (Student student : students) {
            String id = student.getStudentId() == null
                    ? ""
                    : student.getStudentId();

            String name = student.getName() == null
                    ? ""
                    : student.getName();

            String department = student.getDepartment() == null
                    ? ""
                    : student.getDepartment();

            String route = student.getRoute() == null
                    ? ""
                    : student.getRoute().getRouteName();

            if (id.toLowerCase().contains(search)
                    || name.toLowerCase().contains(search)
                    || department.toLowerCase().contains(search)
                    || route.toLowerCase().contains(search)) {
                result.add(student);
            }
        }

        table.setItems(result);
    }

    private void addStudent() {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Add Student");
        dialog.setHeaderText("Add New Student");

        TextField id = new TextField();
        id.setPromptText("Student ID");

        TextField name = new TextField();
        name.setPromptText("Student Name");

        ComboBox<String> department = new ComboBox<>();
        department.getItems().addAll(
                "CSE", "EEE", "ETE", "BBA", "CE", "ME"
        );
        department.setPromptText("Department");
        department.setMaxWidth(Double.MAX_VALUE);

        ComboBox<Route> route = new ComboBox<>();
        route.getItems().addAll(
                AppData.getTransportData().getRoutes()
        );
        route.setPromptText("Route");
        route.setMaxWidth(Double.MAX_VALUE);

        route.setConverter(new javafx.util.StringConverter<Route>() {
            @Override
            public String toString(Route value) {
                return value == null ? "" : value.getRouteName();
            }

            @Override
            public Route fromString(String value) {
                return AppData.getTransportData().findRoute(value);
            }
        });

        VBox box = new VBox(8);
        box.setPadding(new Insets(12));
        box.setPrefWidth(360);

        box.getChildren().addAll(
                new Label("Student ID"),
                id,
                new Label("Name"),
                name,
                new Label("Department"),
                department,
                new Label("Route"),
                route
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

            String studentId = id.getText().trim();

            if (studentId.isEmpty()
                    || name.getText().trim().isEmpty()
                    || department.getValue() == null
                    || route.getValue() == null) {

                showMessage(
                        "Missing Information",
                        "Please complete all student fields."
                );
                return;
            }

            for (Student existing :
                    AppData.getTransportData().getStudents()) {

                if (existing.getStudentId() != null
                        && existing.getStudentId()
                                .equalsIgnoreCase(studentId)) {

                    showMessage(
                            "Duplicate Student ID",
                            "A student with this ID already exists."
                    );
                    return;
                }
            }

            Student student = new Student(
                    studentId,
                    name.getText().trim(),
                    department.getValue(),
                    route.getValue()
            );

            AppData.getTransportData().addStudent(student);

            loadStudents();
            table.setItems(students);
            table.refresh();
        });
    }

    private void editStudent(Student student) {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Edit Student");
        dialog.setHeaderText(
                "Edit " + student.getStudentId()
        );

        TextField name = new TextField(student.getName());

        ComboBox<String> department = new ComboBox<>();
        department.getItems().addAll(
                "CSE", "EEE", "ETE", "BBA", "CE", "ME"
        );
        department.setValue(student.getDepartment());
        department.setMaxWidth(Double.MAX_VALUE);

        ComboBox<Route> route = new ComboBox<>();
        route.getItems().addAll(
                AppData.getTransportData().getRoutes()
        );
        route.setValue(student.getRoute());
        route.setMaxWidth(Double.MAX_VALUE);

        route.setConverter(new javafx.util.StringConverter<Route>() {
            @Override
            public String toString(Route value) {
                return value == null ? "" : value.getRouteName();
            }

            @Override
            public Route fromString(String value) {
                return AppData.getTransportData().findRoute(value);
            }
        });

        VBox box = new VBox(8);
        box.setPadding(new Insets(12));
        box.setPrefWidth(360);

        box.getChildren().addAll(
                new Label("Name"),
                name,
                new Label("Department"),
                department,
                new Label("Route"),
                route
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

            if (name.getText().trim().isEmpty()
                    || department.getValue() == null
                    || route.getValue() == null) {

                showMessage(
                        "Missing Information",
                        "Please complete all student fields."
                );
                return;
            }

            student.setName(name.getText().trim());
            student.setDepartment(department.getValue());
            student.setRoute(route.getValue());

            table.refresh();
        });
    }

    private void deleteStudent(Student student) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Delete Student");
        alert.setHeaderText("Delete Student?");
        alert.setContentText(
                student.getName() +
                " will be deleted."
        );

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                students.remove(student);
                AppData.getTransportData()
                        .getStudents()
                        .remove(student);
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

        button.setOnMouseEntered(e -> {
            if (button != studentsButton) {
                button.setStyle(
                        "-fx-background-color: rgba(255,255,255,0.12);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {
            if (button != studentsButton) {
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        return button;
    }

    private void setActive(Button active) {
        active.setStyle(
                "-fx-background-color: " + BLUE2 + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }

    private ImageView createImageView(
            String resource,
            double width,
            double height) {

        java.io.InputStream stream =
                ManageStudents.class
                        .getResourceAsStream(resource);

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

    private void updateCount(
            Label count,
            int number) {

        count.setText(number + " student"
                + (number == 1 ? "" : "s"));
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

    public static void main(String[] args) {
        launch(args);
    }
}
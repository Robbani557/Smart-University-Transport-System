package admin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + LIGHT + ";"
        );

        // =========================
        // SIDEBAR
        // =========================

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(180);

        sidebar.setStyle(
                "-fx-background-color: " + BLUE + ";"
        );

        Label logo = new Label("🚌 SUTS");

        logo.setTextFill(Color.WHITE);
        logo.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        18
                )
        );

        Label admin = new Label("Admin Panel");

        admin.setTextFill(Color.WHITE);
        admin.setFont(
                Font.font("Segoe UI", 10)
        );

        VBox logoBox = new VBox(
                2,
                logo,
                admin
        );

        logoBox.setPadding(
                new Insets(15)
        );

        VBox menu = new VBox(5);

        menu.setPadding(
                new Insets(10)
        );

        Button dashboard =
                createMenuButton("⌂   Dashboard");

        Button manageStudents =
                createMenuButton(
                        "♙   Manage Students"
                );

        Button manageRoutes =
                createMenuButton(
                        "⌁   Manage Routes"
                );

        Button manageBuses =
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
        manageStudents.setStyle(
                "-fx-background-color: " + BLUE2 + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-text-fill: white;"
        );

        menu.getChildren().addAll(
                dashboard,
                manageStudents,
                manageRoutes,
                manageBuses,
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

        sidebar.getChildren().addAll(
                logoBox,
                menu,
                spacer,
                logout
        );

        // =========================
        // TOP BAR
        // =========================

        HBox topBar = new HBox();

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

        Label hamburger =
                new Label("☰");

        hamburger.setFont(
                Font.font("Segoe UI", 20)
        );

        Region topSpacer =
                new Region();

        HBox.setHgrow(
                topSpacer,
                Priority.ALWAYS
        );

        Label notification =
                new Label("♧");

        notification.setFont(
                Font.font("Segoe UI", 18)
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
                hamburger,
                topSpacer,
                notification,
                user
        );

        // =========================
        // MAIN CONTENT
        // =========================

        VBox content =
                new VBox(15);

        content.setPadding(
                new Insets(20)
        );

        // Heading
        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Manage Students");

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

        Button addStudent =
                new Button("+ Add Student");

        addStudent.setPrefWidth(125);
        addStudent.setPrefHeight(38);

        addStudent.setTextFill(
                Color.WHITE
        );

        addStudent.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        addStudent.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 6px;" +
                "-fx-cursor: hand;"
        );

        addStudent.setOnAction(
                e -> addStudent()
        );

        heading.getChildren().addAll(
                title,
                headingSpacer,
                addStudent
        );

        // =========================
        // SEARCH
        // =========================

        TextField search =
                new TextField();

        search.setPromptText(
                "Search student..."
        );

        search.setPrefWidth(280);
        search.setPrefHeight(38);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );

        // =========================
        // TABLE
        // =========================

        createTable();

        loadStudents();

        search.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        searchStudent(newValue)
        );

        VBox tableBox =
                new VBox(table);

        tableBox.setPadding(
                new Insets(15)
        );

        tableBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        VBox.setVgrow(
                tableBox,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                heading,
                search,
                tableBox
        );

        VBox.setVgrow(
                tableBox,
                Priority.ALWAYS
        );

        root.setLeft(sidebar);
        root.setTop(topBar);
        root.setCenter(content);

        Scene scene =
                new Scene(
                        root,
                        1200,
                        760
                );

        stage.setTitle(
                "Smart University Transport System - Manage Students"
        );

        stage.setScene(scene);
        stage.show();
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        launch(args);

    }

    // =========================
    // MENU BUTTON
    // =========================

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

    // =========================
    // TABLE
    // =========================

    private void createTable() {

        TableColumn<Student, String> id =
                new TableColumn<>("ID");

        id.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Student, String> name =
                new TableColumn<>("Name");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Student, String> department =
                new TableColumn<>("Department");

        department.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        TableColumn<Student, String> phone =
                new TableColumn<>("Phone");

        phone.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        TableColumn<Student, Void> action =
                new TableColumn<>("Action");

        action.setCellFactory(
                column -> new TableCell<Student, Void>() {

                    private final Button edit =
                            new Button("✎");

                    private final Button delete =
                            new Button("▣");

                    private final HBox box =
                            new HBox(5);

                    {
                        edit.setStyle(
                                "-fx-background-color: #1565C0;" +
                                "-fx-text-fill: white;"
                        );

                        delete.setStyle(
                                "-fx-background-color: #C62828;" +
                                "-fx-text-fill: white;"
                        );

                        edit.setOnAction(
                                e -> {

                                    Student s =
                                            getTableView()
                                                    .getItems()
                                                    .get(getIndex());

                                    editStudent(s);
                                }
                        );

                        delete.setOnAction(
                                e -> {

                                    Student s =
                                            getTableView()
                                                    .getItems()
                                                    .get(getIndex());

                                    deleteStudent(s);
                                }
                        );

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
                                empty ? null : box
                        );
                    }
                }
        );

        table.getColumns().addAll(
                id,
                name,
                department,
                phone,
                action
        );

        table.setItems(
                students
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setFixedCellSize(38);
    }

    // =========================
    // LOAD DATA
    // =========================

    private void loadStudents() {

        students.clear();

        students.addAll(

                new Student(
                        "ST001",
                        "Mehedi Hasan",
                        "CSE",
                        "01700000000"
                ),

                new Student(
                        "ST002",
                        "Nishan Ahmed",
                        "ETE",
                        "01800000000"
                ),

                new Student(
                        "ST003",
                        "Golam Robbani",
                        "CSE",
                        "01770000000"
                ),

                new Student(
                        "ST004",
                        "Mahbub Hasan",
                        "BBA",
                        "01900000000"
                ),

                new Student(
                        "ST005",
                        "Abdur Rahim",
                        "EEE",
                        "01600000000"
                ),

                new Student(
                        "ST006",
                        "Arafat Islam",
                        "CSE",
                        "01780000000"
                )
        );
    }

    // =========================
    // SEARCH
    // =========================

    private void searchStudent(
            String keyword
    ) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            table.setItems(
                    students
            );

            return;
        }

        String search =
                keyword.toLowerCase();

        ObservableList<Student> result =
                FXCollections.observableArrayList();

        for (Student s : students) {

            if (
                    s.getId()
                            .toLowerCase()
                            .contains(search)

                    ||

                    s.getName()
                            .toLowerCase()
                            .contains(search)

                    ||

                    s.getDepartment()
                            .toLowerCase()
                            .contains(search)

                    ||

                    s.getPhone()
                            .contains(search)
            ) {

                result.add(s);
            }
        }

        table.setItems(result);
    }

    // =========================
    // ADD STUDENT
    // =========================

    private void addStudent() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Student"
        );

        dialog.setHeaderText(
                "Add New Student"
        );

        TextField id =
                new TextField();

        id.setPromptText(
                "Student ID"
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Student Name"
        );

        ComboBox<String> department =
                new ComboBox<>();

        department.getItems().addAll(
                "CSE",
                "EEE",
                "ETE",
                "BBA",
                "CE",
                "ME"
        );

        department.setPromptText(
                "Department"
        );

        TextField phone =
                new TextField();

        phone.setPromptText(
                "Phone Number"
        );

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(15)
        );

        box.getChildren().addAll(
                new Label("Student ID"),
                id,
                new Label("Name"),
                name,
                new Label("Department"),
                department,
                new Label("Phone"),
                phone
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

                        students.add(
                                new Student(
                                        id.getText(),
                                        name.getText(),
                                        department.getValue(),
                                        phone.getText()
                                )
                        );

                        table.refresh();
                    }
                });
    }

    // =========================
    // EDIT
    // =========================

    private void editStudent(
            Student student
    ) {

        TextInputDialog dialog =
                new TextInputDialog(
                        student.getName()
                );

        dialog.setTitle(
                "Edit Student"
        );

        dialog.setHeaderText(
                "Change Student Name"
        );

        dialog.showAndWait()
                .ifPresent(
                        name -> {

                            if (!name.isEmpty()) {

                                student.setName(
                                        name
                                );

                                table.refresh();
                            }
                        }
                );
    }

    // =========================
    // DELETE
    // =========================

    private void deleteStudent(
            Student student
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle(
                "Delete Student"
        );

        alert.setHeaderText(
                "Delete Student?"
        );

        alert.setContentText(
                student.getName()
                        + " will be deleted."
        );

        alert.showAndWait()
                .ifPresent(
                        result -> {

                            if (result ==
                                    ButtonType.OK) {

                                students.remove(
                                        student
                                );
                            }
                        }
                );
    }

    // =========================
    // STUDENT CLASS
    // =========================

    public static class Student {

        private String id;
        private String name;
        private String department;
        private String phone;

        public Student(
                String id,
                String name,
                String department,
                String phone
        ) {

            this.id = id;
            this.name = name;
            this.department = department;
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public String getPhone() {
            return phone;
        }

        public void setName(
                String name
        ) {

            this.name = name;
        }
    }
}
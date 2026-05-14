package studentManagementSystem;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class StudentManagementDashboard extends Application {

    private BorderPane root;
    private StackPane contentArea;
    private final ManagementSystem managementSystem = new ManagementSystem();

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        VBox sidebar = buildSidebar();
        HBox topBar = buildTopBar();

        contentArea = new StackPane();
        contentArea.setPadding(new Insets(25));
        contentArea.setStyle("-fx-background-color: #f8fafc;");

        root.setLeft(sidebar);
        root.setTop(topBar);
        root.setCenter(contentArea);

        showInContent(buildHomePage());

        Scene scene = new Scene(root, 1180, 720);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buildSidebar() {
        VBox sidebar = new VBox(16);
        sidebar.setPadding(new Insets(22));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: #0f172a;");

        Label title = new Label("SMS Menu");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", 24));
        title.setStyle("-fx-font-weight: bold;");

        Label subtitle = new Label("Student Management");
        subtitle.setTextFill(Color.web("#cbd5e1"));
        subtitle.setFont(Font.font("Arial", 13));

        VBox header = new VBox(4, title, subtitle);

        Button btnHome = createSidebarButton("Home");
        Button btnView = createSidebarButton("View Students");
        Button btnAdd = createSidebarButton("Add Student");
        Button btnRemove = createSidebarButton("Remove Student");
        Button btnDL = createSidebarButton("Deep Learning");

        btnHome.setOnAction(e -> showInContent(buildHomePage()));
        btnView.setOnAction(e -> showInContent(buildViewStudentsPage()));
        btnAdd.setOnAction(e -> showInContent(buildAddStudentPage()));
        btnRemove.setOnAction(e -> showInContent(buildRemoveStudentPage()));
        btnDL.setOnAction(e -> showInContent(buildDeepLearningPage()));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label footer = new Label("Clean version");
        footer.setTextFill(Color.web("#94a3b8"));
        footer.setFont(Font.font("Arial", 11));

        sidebar.getChildren().addAll(
                header,
                new Separator(),
                btnHome,
                btnView,
                btnAdd,
                btnRemove,
                btnDL,
                spacer,
                footer
        );

        return sidebar;
    }

    private HBox buildTopBar() {
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 24, 18, 24));
        topBar.setStyle("-fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-width: 0 0 1 0;");

        Label title = new Label("Student Management System");
        title.setFont(Font.font("Arial", 28));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #0f172a;");

        topBar.getChildren().add(title);
        return topBar;
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(190);
        button.setPrefHeight(42);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: #1e293b;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 10;" +
                            "-fx-cursor: hand;"
            );
            animateScale(button, 1.03);
        });

        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 10;" +
                            "-fx-cursor: hand;"
            );
            animateScale(button, 1.0);
        });

        return button;
    }

    private void animateScale(Button button, double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(140), button);
        st.setToX(scale);
        st.setToY(scale);
        st.play();
    }

    private void showInContent(Node node) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(node);

        FadeTransition ft = new FadeTransition(Duration.millis(320), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private VBox createPageBox(String titleText, String subtitleText) {
        VBox page = new VBox(18);
        page.setPadding(new Insets(25));
        page.setMaxWidth(860);
        page.setAlignment(Pos.TOP_LEFT);
        page.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: #dbe4f0;" +
                        "-fx-border-radius: 18;"
        );

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", 26));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #0f172a;");

        Label subtitle = new Label(subtitleText);
        subtitle.setFont(Font.font("Arial", 15));
        subtitle.setTextFill(Color.web("#475569"));

        page.getChildren().addAll(title, subtitle);
        return page;
    }

    private TextField createTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(40);
        tf.setMaxWidth(360);
        tf.setStyle(
                "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #cbd5e1;" +
                        "-fx-padding: 0 12 0 12;" +
                        "-fx-font-size: 14px;"
        );
        return tf;
    }

    private Button createActionButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefWidth(170);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> animateScale(button, 1.03));
        button.setOnMouseExited(e -> animateScale(button, 1.0));

        return button;
    }

    private VBox buildHomePage() {
        VBox page = createPageBox(
                "Welcome",
                "This focuses only on student management and a proper deep learning panel."
        );

        VBox card1 = createInfoCard("Student Records", "View, add, and remove students from the system.");
        VBox card2 = createInfoCard("Deep Learning Module", "Use this section later for dataset loading, training, and result display.");
        VBox card3 = createInfoCard("Clean Scope", "No brain image, no tuition, and no fake AI predictions.");

        HBox row = new HBox(18, card1, card2, card3);
        row.setAlignment(Pos.CENTER_LEFT);

        page.getChildren().add(row);
        return page;
    }

    private VBox createInfoCard(String titleText, String bodyText) {
        VBox card = new VBox(10);
        card.setPrefWidth(240);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: #f8fafc;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #dbe4f0;" +
                        "-fx-border-radius: 14;"
        );

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", 18));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #1e293b;");

        Label body = new Label(bodyText);
        body.setWrapText(true);
        body.setFont(Font.font("Arial", 14));
        body.setTextFill(Color.web("#475569"));

        card.getChildren().addAll(title, body);
        return card;
    }

    private VBox buildViewStudentsPage() {
        VBox page = createPageBox("View Students", "All students currently stored in the system.");

        TextArea studentArea = new TextArea();
        studentArea.setEditable(false);
        studentArea.setWrapText(true);
        studentArea.setPrefHeight(430);
        studentArea.setStyle(
                "-fx-control-inner-background: #f8fafc;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #cbd5e1;"
        );

        List<Student> students = managementSystem.getStudents();

        if (students == null || students.isEmpty()) {
            studentArea.setText("No students available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append("Student ID: ").append(student.getId()).append("\n");
                sb.append("Name: ").append(student.getName()).append("\n");
                sb.append("GPA: ").append(student.getGpa()).append("\n");
                sb.append("--------------------------------------------------\n");
            }
            studentArea.setText(sb.toString());
        }

        page.getChildren().add(studentArea);
        return page;
    }

    private VBox buildAddStudentPage() {
        VBox page = createPageBox("Add Student", "Enter the student information below.");

        TextField idField = createTextField("Enter student ID");
        TextField nameField = createTextField("Enter student name");
        TextField gpaField = createTextField("Enter GPA");

        Label feedback = new Label();
        feedback.setFont(Font.font("Arial", 14));

        Button saveButton = createActionButton("Save Student", "#2563eb");

        saveButton.setOnAction(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String gpaText = gpaField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || gpaText.isEmpty()) {
                feedback.setText("Please complete all fields.");
                feedback.setTextFill(Color.RED);
                return;
            }

            try {
                double gpa = Double.parseDouble(gpaText);
                managementSystem.addStudent(id, name, gpa);

                feedback.setText("Student added successfully.");
                feedback.setTextFill(Color.web("#16a34a"));

                idField.clear();
                nameField.clear();
                gpaField.clear();
            } catch (NumberFormatException ex) {
                feedback.setText("GPA must be a valid number.");
                feedback.setTextFill(Color.RED);
            }
        });

        page.getChildren().addAll(idField, nameField, gpaField, saveButton, feedback);
        return page;
    }

    private VBox buildRemoveStudentPage() {
        VBox page = createPageBox("Remove Student", "Remove a student by entering the student ID.");

        TextField idField = createTextField("Enter student ID");
        Label feedback = new Label();
        feedback.setFont(Font.font("Arial", 14));

        Button removeButton = createActionButton("Delete Student", "#dc2626");

        removeButton.setOnAction(e -> {
            String id = idField.getText().trim();

            if (id.isEmpty()) {
                feedback.setText("Please enter a student ID.");
                feedback.setTextFill(Color.RED);
                return;
            }

            boolean removed = managementSystem.removeStudent(id);

            if (removed) {
                feedback.setText("Student removed successfully.");
                feedback.setTextFill(Color.web("#16a34a"));
            } else {
                feedback.setText("Student ID not found.");
                feedback.setTextFill(Color.RED);
            }

            idField.clear();
        });

        page.getChildren().addAll(idField, removeButton, feedback);
        return page;
    }

    private VBox buildDeepLearningPage() {
        VBox page = createPageBox(
                "Deep Learning Processing",
                "Run a real DL4J model using your student dataset."
        );

        Label info = new Label("This trains a real neural network on Generated_Student_Datas.csv.");
        info.setFont(Font.font("Arial", 15));
        info.setTextFill(Color.web("#475569"));

        TextArea processArea = new TextArea();
        processArea.setEditable(false);
        processArea.setWrapText(true);
        processArea.setPrefHeight(380);
        processArea.setStyle(
                "-fx-control-inner-background: #f8fafc;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #cbd5e1;"
        );
        processArea.setText("Ready to train the student model...");

        Button trainButton = createActionButton("Train Student Model", "#2563eb");

        trainButton.setOnAction(e -> {
            processArea.setText("Training in progress...");
            DeepLearningService service = new DeepLearningService();
            String result = service.runTraining();
            processArea.setText(result);
        });

        page.getChildren().addAll(info, trainButton, processArea);
        return page;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
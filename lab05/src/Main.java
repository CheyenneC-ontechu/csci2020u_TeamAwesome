import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        TableView<StudentRecord> tableView = new TableView<>();
        DataSource d = new DataSource();
        ObservableList<StudentRecord> list = d.getAllMarks();

        TableColumn SIDCol = new TableColumn("SID");
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn assignmentsCol = new TableColumn("Assignments");
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn midtermCol = new TableColumn("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn finalExamCol = new TableColumn("Final Exam");
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        TableColumn finalMarkCol = new TableColumn("Final Mark");
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        TableColumn letterGradeCol = new TableColumn("Letter Grade");
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        tableView.getColumns().addAll(SIDCol, assignmentsCol, midtermCol, finalExamCol, finalMarkCol, letterGradeCol);

        Pane pane = new Pane();
        pane.getChildren().add(tableView);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Lab 05 Solutions");
        primaryStage.setScene(scene);
        primaryStage.show();
        tableView.setItems(DataSource.getAllMarks());
    }


    public static void main(String[] args) {
        launch(args);
    }
}


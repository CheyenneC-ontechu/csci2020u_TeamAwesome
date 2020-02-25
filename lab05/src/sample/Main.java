package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.sql.DataSource;
import javax.swing.text.Element;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        TableView tableView = new TableView();

        TableColumn SIDCol = new TableColumn("SID");
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn assignmentsCol = new TableColumn("Assignments");
        TableColumn midtermCol = new TableColumn("Midterm");
        TableColumn finalExamCol = new TableColumn("Final Exam");
        TableColumn finalMarkCol = new TableColumn("Final Mark");
        TableColumn letterGradeCol = new TableColumn("Letter Grade");

        tableView.getColumns().addAll(SIDCol, assignmentsCol, midtermCol, finalExamCol, finalMarkCol, letterGradeCol);

        Pane pane = new Pane();
        pane.getChildren().add(tableView);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Lab 05 Solutions");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

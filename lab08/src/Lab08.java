import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static java.lang.System.exit;


public class Lab08 extends Application {
    DataSource d = new DataSource();
    ObservableList<StudentRecord> marks = d.getAllMarks();
    String currentFilename = "file.csv";
    TableView<StudentRecord> tableView = new TableView<>();
    @Override
    public void start(Stage primaryStage) throws Exception{

        TableColumn SIDCol = new TableColumn("SID");
        SIDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("studentID"));

        TableColumn assignmentsCol = new TableColumn("Assignments");
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("assignments"));

        TableColumn midtermCol = new TableColumn("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("midterm"));

        TableColumn finalExamCol = new TableColumn("Final Exam");
        finalExamCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("finalExam"));

        TableColumn finalMarkCol = new TableColumn("Final Mark");
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("finalMark"));

        TableColumn letterGradeCol = new TableColumn("Letter Grade");
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("letterGrade"));

        tableView.getColumns().addAll(SIDCol, assignmentsCol, midtermCol, finalExamCol, finalMarkCol, letterGradeCol);

        Button btAdd = new Button("Add");
        TextField txtSID = new TextField();
        TextField txtAssign = new TextField();
        TextField txtMid = new TextField();
        TextField txtFinal = new TextField();

        btAdd.setOnAction(e -> {
            StudentRecord studentRecord = new StudentRecord(txtSID.getText(), Float.parseFloat(txtAssign.getText()),
                    Float.parseFloat(txtMid.getText()), Float.parseFloat(txtFinal.getText()));
            marks.add(studentRecord);
            tableView.getItems().add(studentRecord);
        });

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().addAll(menuFile);

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");

        menuFile.getItems().addAll(newItem,openItem,saveItem,saveAsItem,exitItem);

        newItem.setOnAction(e -> {
            for ( int i = 0; i<tableView.getItems().size(); i++) {
                tableView.getItems().clear();
            }
        });

        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            currentFilename = selectedFile.toString();
            try {
                load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveItem.setOnAction(e -> {
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveAsItem.setOnAction(e-> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showSaveDialog(primaryStage);
            currentFilename = selectedFile.toString();
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        exitItem.setOnAction(e -> {
            exit(1);
        });


        GridPane pane = new GridPane();

        pane.add(menuBar,0,0,4,1);

        pane.add(tableView,0,1,4,1);

        pane.add(new Label("SID"),0,2);
        pane.add(txtSID,1,2);
        pane.add(new Label("Assignment"),2,2);
        pane.add(txtAssign,3,2);
        pane.add(new Label("Midterm"),0,3);
        pane.add(txtMid,1,3);
        pane.add(new Label("Final"),2,3);
        pane.add(txtFinal,3,3);
        pane.add(btAdd,1,4);


        pane.setHgap(10);
        pane.setVgap(10);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Lab 05 Solutions");
        primaryStage.setScene(scene);
        primaryStage.show();
        tableView.setItems(DataSource.getAllMarks());
    }

    public void save() throws IOException {
        Writer writer = null;
        try {
            File file = new File(currentFilename);
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord studentRecord : marks) {

                String text = studentRecord.getStudentID() + "," + studentRecord.getAssignments() + ","
                        + studentRecord.getMidterm() + "," + studentRecord.getFinalExam() + "\n";



                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }
    public void load() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFilename));

        while (bufferedReader.readLine() != null) {
            String[] data = bufferedReader.readLine().split(",");
            StudentRecord s = new StudentRecord(data[0], Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3]));
            marks.add(s);
            tableView.getItems().add(s);

        }
        bufferedReader.close();
    }



    public static void main(String[] args) {
        launch(args);
    }
}


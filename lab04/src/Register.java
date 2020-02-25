import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 20, 50, 20));

        //create textfields
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        TextField fullName = new TextField();
        TextField email = new TextField();
        TextField phoneNum = new TextField();
        DatePicker dob = new DatePicker();

        //add textfields and labels
        pane.add(new Label("Username:"), 0, 0);
        pane.add(username, 2, 0);
        pane.add(new Label("Password:"), 0, 1);
        pane.add(password, 2, 1);
        pane.add(new Label("Full name:"), 0, 2);
        pane.add(fullName, 2, 2);
        pane.add(new Label("E-Mail address:"), 0, 3);
        pane.add(email, 2, 3);
        pane.add(new Label("Phone number:"), 0, 4);
        pane.add(phoneNum, 2, 4);
        pane.add(new Label("Date of birth"), 0, 5);
        pane.add(dob, 2, 5);

        Button registerBt = new Button("Register");
        pane.add(registerBt, 2,6);


        //if button is pressed
        registerBt.setOnAction(value -> {
            //print input to console
            System.out.println(username.getText());
            System.out.println(password.getText());
            System.out.println(fullName.getText());
            System.out.println(email.getText());

            System.out.print("(");
            System.out.print(phoneNum.getText().substring(0,3));
            System.out.print(")-");
            System.out.print(phoneNum.getText().substring(3,6));
            System.out.print("-");
            System.out.println(phoneNum.getText().substring(6,10));

            System.out.println(dob.getValue());

            //clear fields
            username.setText("");
            password.setText("");
            fullName.setText("");
            email.setText("");
            phoneNum.setText("");
            dob.setValue(null);
        });

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Lab 4 Solution");
        stage.show();
    }
}

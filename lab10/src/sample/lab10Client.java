package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class lab10Client extends Application {
    DataInputStream fromServer = null;
    DataOutputStream toServer = null;
    Socket socket = null;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setHgap(10);

        TextField username = new TextField();
        TextField message = new TextField();

        Button btSend = new Button("send");
        Button btExit = new Button("Exit");

        pane.add(new Label("Username:"), 0,0);
        pane.add(username,1,0);
        pane.add(new Label("Message:"), 0,1);
        pane.add(message,1,1);
        pane.add(btSend, 0,2);
        pane.add(btExit,0,3);

        stage.setScene(new Scene(pane));
        stage.setTitle("Lab 10 Client");
        stage.show();

        btSend.setOnAction(e -> {
            try {
                String chat = username.getText() + ": " + message.getText() + "\n";
                toServer.writeUTF(chat);
                toServer.flush();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btExit.setOnAction(e -> {
            try {
                socket.close();
                System.exit(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        try {
            socket = new Socket("localhost", 5000);

            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());


        } catch (SocketException e) {
            e.printStackTrace();;
        }

    }
}

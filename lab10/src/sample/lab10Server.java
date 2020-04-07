package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class lab10Server extends Application {
    TextArea ta = new TextArea();
    private int clientNo = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Button btExit = new Button("Exit");

        GridPane pane = new GridPane();
        pane.add(ta,0,0,3,2);
        pane.add(btExit,0,2);

        stage.setScene(new Scene(pane));
        stage.setTitle("Lab 10 Server");
        stage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);

                while (true) {
                    Socket socket = serverSocket.accept();

                    // Increment clientNo
                    clientNo++;
                    Platform.runLater( () -> {
                        // Display the client number
                        ta.appendText("Starting thread for client " + clientNo +
                                " at " + new Date() + '\n');

                        // Find the client's host name, and IP address
                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText("Client " + clientNo + "'s host name is "
                                + inetAddress.getHostName() + "\n");
                        ta.appendText("Client " + clientNo + "'s IP Address is "
                                + inetAddress.getHostAddress() + "\n");
                    });

                    btExit.setOnAction(e -> {
                        try {
                            socket.close();
                            System.exit(1);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    new Thread(new HandleAClient(socket)).start();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream fromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream toClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    String chat = fromClient.readUTF();

                    Platform.runLater(() -> {
                        ta.appendText(chat);

                    });
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}




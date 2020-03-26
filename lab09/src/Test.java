import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test extends Application {
    Canvas canvas = new Canvas(400,400);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5,5,5,5));
        pane.getChildren().add(canvas);
        stage.setScene(new Scene(pane));
        stage.setTitle("lab09");
        stage.show();

        gc.setStroke(Color.BLACK);
        gc.strokeLine(5,395,400,395);
        gc.strokeLine(5,395,5,5);

        ArrayList<Float> list1= new ArrayList<Float>();
        for (int i=0; i < 1000; i++) {
            Random random = new Random();
            list1.add(random.nextFloat()*200);
            System.out.println(list1.get(i));
        }
        gc.setStroke(Color.BLUE);
        for (int i = 0; i < list1.size()-1; i++) {
            gc.strokeLine(i,list1.get(i), i+1, list1.get(i+1));
        }
    }
}

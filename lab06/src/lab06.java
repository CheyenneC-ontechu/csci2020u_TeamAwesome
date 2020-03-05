import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class lab06 extends Application {
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(800,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        double max1 = 363153.7;
        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            gc.fillRect(10+(i*40),350 + (max1-avgHousingPricesByYear[i])/5000,
                    15,avgHousingPricesByYear[i]/5000);
        }
        gc.setFill(Color.BLUE);
        double max2 = 1613246.3;
        for (int i = 0; i < avgCommercialPricesByYear.length; i++) {
            gc.fillRect(25+(i*40),100 + (max2-avgCommercialPricesByYear[i])/5000,
                    15,avgCommercialPricesByYear[i]/5000);
        }

        int total = 0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            total += purchasesByAgeGroup[i];
        }

        double arc = total/360;
        double startAngle = 0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            gc.setFill(pieColours[i]);
            double angle = purchasesByAgeGroup[i] / arc;
            gc.fillArc(450, 50, 270, 270, startAngle, angle, ArcType.ROUND);
            startAngle += angle;
        }



        Pane pane = new Pane();
        pane.getChildren().add(canvas);

        stage.setScene(new Scene(pane));
        stage.setTitle("lab 06");
        stage.show();

    }



}

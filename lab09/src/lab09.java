import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lab09 extends Application {
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

        String symbol1 = "MSFT";
        String symbol2 = "ABC";
        ArrayList<Float> stock1 = downloadStockPrices(symbol1);
        ArrayList<Float> stock2 = downloadStockPrices(symbol2);

        drawLinePlot(stock1,stock2);

    }

    public ArrayList<Float> downloadStockPrices(String symbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol="
                +symbol +"&apikey=4B9ADTHJVJNA8NZ2";
        String dataToParse = "";
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Float> stock = new ArrayList<>();
        JsonObject object = null;
        String dash = "-";

        try {
            URL urlAPI = new URL(url);
            URLConnection urlConnection = urlAPI.openConnection();

            Scanner scanner = new Scanner(urlConnection.getInputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dataToParse += line;
                if (line.contains(dash)) {
                    dates.add(line.substring(9,19));
                }

            }
            dates.remove(0);
            scanner.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        object = parser.parse(dataToParse).getAsJsonObject();

        if (object != null) {
            JsonObject MTS = object.get("Monthly Time Series").getAsJsonObject();
            //JsonObject date = MTS.get("2020-02-28").getAsJsonObject();
            //System.out.println(date);
            for (String s : dates) {
                JsonObject date = MTS.get(s).getAsJsonObject();
                stock.add(date.get("4. close").getAsFloat());
            }


        }
        return stock;

    }
    public void drawLinePlot(List<Float> stock1, List<Float> stock2) {
        gc.setStroke(Color.BLACK);
        gc.strokeLine(5,canvas.getHeight()-5,canvas.getHeight(),canvas.getHeight()-5);
        gc.strokeLine(5,canvas.getHeight()-5,5,5);

        plotLine(stock1, Color.RED);
        plotLine(stock2, Color.BLUE);

    }
    public void plotLine(List<Float> stock, Color color) {
        gc.setStroke(color);
        for (int i = 0; i < stock.size()-1; i++) {
            gc.strokeLine(i+5,stock.get(i), i+5+1, stock.get(i+1));
        }
    }
}

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class homeController{
    @FXML private Label title;
    @FXML private VBox home;
    @FXML private LineChart samplesChart;
    @FXML private NumberAxis xAxis,yAxis;

    @FXML
    private void switchToValues() throws IOException { //method to switch scenes
        Scene valuesScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/values.fxml")));
        Main.switchToScene(valuesScene);
    }

    public void initialize() {
        home.getStylesheets().add("css/home_stylesheet.css");

        samplesChart.setAnimated(false);

        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(Main.function.getUpper()); //sets x bounds to match integration bounds
        xAxis.setLowerBound(Main.function.getLower());
        xAxis.setTickUnit(1);

        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(Main.function.getMax()); //sets y bounds to match max/min within integration region
        yAxis.setLowerBound(Main.function.getMin());
        yAxis.setTickUnit(1);

        XYChart.Series insideSamples = new XYChart.Series();
        insideSamples.setName("Random Samples Inside the Area of Integration");

        XYChart.Series outsideSamples = new XYChart.Series();
        outsideSamples.setName("Random Samples Outside the Area of Integration");

        XYChart.Series trueValues = new XYChart.Series();
        trueValues.setName("Function Values");

        for (double i=Main.function.getLower();i<=Main.function.getUpper()+0.1;i+=0.1) { //calculate function values
            trueValues.getData().add(new XYChart.Data(i, Main.function.evaluateAt(i)));
        }

        samplesChart.getData().addAll(insideSamples,outsideSamples,trueValues);

        //timeline setup found at https://stackoverflow.com/questions/62963502/how-do-i-update-a-xychart-in-realtime-in-java-using-javafx
        Timeline timeline = new Timeline();
        graphAnimation animation = new graphAnimation();

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event ->  {
            Point point = animation.nextStep(); //generate new random point
            if (point.isWithin()) { //plotting inside points
                //replacement position formula
                int nextIndex = animation.getInsideCount()-500*(animation.getInsideCount()/500);

                //if the total number of points generated so far is at or below 500, add the point to the series
                if (animation.getInsideCount()<=500) insideSamples.getData().add(new XYChart.Data(point.getX(),point.getY()));

                //if the total number of points generated so far is above 500, begin replacement of old points
                else insideSamples.getData().set(nextIndex,new XYChart.Data(point.getX(),point.getY()));
            } else { //plotting outside points
                //same logic as above
                int nextIndex = animation.getOutsideCount()-500*(animation.getOutsideCount()/500);

                if (animation.getOutsideCount()<=500) outsideSamples.getData().add(new XYChart.Data(point.getX(),point.getY()));

                else outsideSamples.getData().set(nextIndex,new XYChart.Data(point.getX(),point.getY()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
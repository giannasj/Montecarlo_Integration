import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
public class SamplesController {

    @FXML
    private LineChart samplesChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private void switchToValues(ActionEvent event) throws IOException {
        Parent valuesScene = FXMLLoader.load(getClass().getResource("value.fxml"));
        Main.switchToScene(valuesScene);
    }
    public void initialize() throws IOException {

        samplesChart.getStylesheets().add("samples_stylesheet.css");
        samplesChart.setAnimated(false);

        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(Main.function.getUpper());
        xAxis.setLowerBound(Main.function.getLower());
        xAxis.setTickUnit(1);

        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(Main.function.getMax());
        yAxis.setLowerBound(Main.function.getMin());
        yAxis.setTickUnit(1);

        XYChart.Series insideSamples = new XYChart.Series();
        insideSamples.setName("Random Samples Inside the Area of Integration");

        XYChart.Series outsideSamples = new XYChart.Series();
        outsideSamples.setName("Random Samples Outside the Area of Integration");

        XYChart.Series trueValues = new XYChart.Series();
        trueValues.setName("Function Values");

        for (double i=Main.function.getLower();i<=Main.function.getUpper()+0.1;i+=0.1) {
            trueValues.getData().add(new XYChart.Data(i, Main.function.evaluateAt(i)));
        }

        samplesChart.getData().addAll(insideSamples,outsideSamples,trueValues);

        Timeline timeline = new Timeline();
        myAnimation animation = new myAnimation(Main.function,Main.numberOfConsecutiveSamples);
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Main.delay), event ->  {
                myPoint point = animation.nextStep();
                if (point.isWithin()) {
                    if (animation.isDone()) insideSamples.getData().remove(0);
                    insideSamples.getData().add(new XYChart.Data(point.x,point.y));
                } else {
                    if (animation.isDone()) outsideSamples.getData().remove(0);
                    outsideSamples.getData().add(new XYChart.Data(point.x,point.y));
                }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

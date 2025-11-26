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
import javafx.scene.control.Label;
import javafx.util.Duration;
public class ValueController {

    @FXML
    private LineChart valuesChart; 

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label label;

    @FXML
    private void switchToSamples(ActionEvent event) throws IOException {
        Parent samplesScene = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.switchToScene(samplesScene);
    }

    public void initialize() {
        valuesChart.getStylesheets().add("values_stylesheet.css");
        valuesChart.setAnimated(false);

        XYChart.Series value = new XYChart.Series();
        value.setName("Calculated Value");

        valuesChart.getData().add(value);

        Timeline timeline = new Timeline();
        myAnimation animation = new myAnimation(Main.function,Main.numberOfConsecutiveSamples);
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Main.delay), event ->  {
                myPoint point = animation.nextStep();
                double calculatedValue = Main.function.totalArea()*animation.netSampleCount/animation.samplesGenerated;
                if (animation.isDone()) value.getData().remove(0);
                value.getData().add(new XYChart.Data(animation.samplesGenerated,calculatedValue));
                label.setText("Calculated Value: "+String.valueOf(calculatedValue));

                xAxis.setAutoRanging(false);
                xAxis.setUpperBound(animation.samplesGenerated);
                xAxis.setLowerBound((animation.samplesGenerated<500)?0:animation.samplesGenerated-500);
                xAxis.setTickUnit(50);

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

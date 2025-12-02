import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.util.Duration;
public class valuesController {

    @FXML private LineChart valuesChart; 

    @FXML private NumberAxis xAxis,yAxis;

    @FXML private Label estValue;

    @FXML
    private void switchToHome() throws IOException { //method to switch scenes
        Scene samplesScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/home.fxml")));
        Main.switchToScene(samplesScene);
    }

    public void initialize() {
        valuesChart.getStylesheets().add("css/values_stylesheet.css");
        valuesChart.setAnimated(false);

        XYChart.Series value = new XYChart.Series();
        value.setName("Calculated Value");

        valuesChart.getData().add(value);

        //timeline setup found at https://stackoverflow.com/questions/62963502/how-do-i-update-a-xychart-in-realtime-in-java-using-javafx

        Timeline timeline = new Timeline();
        graphAnimation animation = new graphAnimation();
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event ->  { //change keyframe duration here
                animation.nextStep();
                /*finding the calculated value:
                first, the net inside count is found. this is the number of 'positive' 'inside' points minus the number of
                'negative' 'inside' points. This is due to the fact that an area below the y-axis is treated as negative. 
                Alternatively, the positive and negative areas could be calcualted separately and then subtracted. However, 
                the method I used was much simpler to code. Next, the net inside count is divided by the total count to find 
                its proportion out of all of the points, which is then multiplied by the total area of the 'box'.
                */
                double calculatedValue = Main.function.totalArea()*animation.getNetInsideCount()/(double)animation.getCount();
                estValue.setText("Estimated Value: "+(int)(100*calculatedValue)/100.0);

                //if total count is less than or equal to 500, add the data to the series
                if (animation.getCount()<=500) {
                    value.getData().add(new XYChart.Data(animation.getCount(),calculatedValue));
                } 
                //otherwise, replace the old data according to the nextIndex formula
                else{
                    int nextIndex = animation.getCount()-500*(animation.getCount()/500);
                    value.getData().set(nextIndex,new XYChart.Data(animation.getCount(),calculatedValue));
                }

                xAxis.setAutoRanging(false);
                //if the count is below 500, set the upper bound to 500. Otherwise, set it to the number of samples (points) generated
                xAxis.setUpperBound((animation.getCount()<500)?500:animation.getCount());
                //if the count is below 500, set the lower bound to 0. Otherwise, set it to be 500 behind the upper bound.
                xAxis.setLowerBound((animation.getCount()<500)?0:animation.getCount()-500);
                xAxis.setTickUnit(50);

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

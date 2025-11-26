import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private static Stage primaryStage;
    public static Function function = new Function(-3,3); 
    public static int numberOfConsecutiveSamples = 500;
    public static int delay = 5;

    public static void switchToScene(Parent root) throws IOException {
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Main.primaryStage=primaryStage;
        Parent samplesScene = FXMLLoader.load(getClass().getResource("sample.fxml"));
        
        primaryStage.setTitle("MonteCarlo Integration");
        primaryStage.setScene(new Scene(samplesScene));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
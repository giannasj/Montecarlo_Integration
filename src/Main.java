import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage currentStage;
    public static String file = "fxml/home.fxml";
    public static Function function=new Function(-3,3); //definition for the bounds of the function
    //to change the function definition, go to the Function.java file and look through the comments

    public static void switchToScene(Scene newScene) { //method to switch to new scene
        currentStage.setScene(newScene);
        currentStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.currentStage=stage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(file)));
        
        currentStage.setTitle("Montecarlo Integration");
        currentStage.setScene(scene);
        currentStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

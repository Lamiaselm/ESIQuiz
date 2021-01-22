package GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Test extends Application {
    public static ArrayList<Stage> tab_Stage=new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root =  FXMLLoader.load(Test.class.getResource("/GUI/login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ESIQuiz");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
     Test.tab_Stage.add(primaryStage);

        tab_Stage.add(primaryStage);
    }
    public static void main(String...args){
        Application.launch(Test.class, (java.lang.String[])null);
    }
    public static void vider_stage(){
        for (Stage s :tab_Stage) {
            s.close();
        }
    }
}









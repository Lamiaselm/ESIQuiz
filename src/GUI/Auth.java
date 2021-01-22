package GUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;





public class Auth {


    public static boolean verifier(String username, String mdp) {
        String str1 = "lamia";
        String str2 = "selmane";
       // System.out.println(username);
      //  System.out.println(mdp);
        if (username.equals(str1) && mdp.equals(str2)) {

            return true;

        } else return false;


    }

    public static void loadStage(String fxml) {
        try {

            Parent root = FXMLLoader.load(Auth.class.getResource(fxml));
            Scene scene =new Scene(root, 800, 569);
            Stage stage = new Stage();
            stage.setX(300);
            stage.setY(100);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

           Test.tab_Stage.add(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class apprenant_ctr   {


    @FXML private Button prfl;
    @FXML private Button csltqz;
    @FXML private Button dcx;









    public void deconexion (ActionEvent event )
    {
        if (event.getSource()== this.dcx){
          //  Test.vider_stage();
           // loadStage("/GUI/login.fxml");

            try {
                AnchorPane root =  FXMLLoader.load(Test.class.getResource("/GUI/login.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage=new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("ESIQuiz");
                primaryStage.show();
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public void ConsulterQuiz (ActionEvent event )
    {
        if (event.getSource()== this.csltqz){
          //  Test.vider_stage();
          //  loadStage("/GUI/compteApprenant.fxml");

            try {
                AnchorPane root =  FXMLLoader.load(Test.class.getResource("/GUI/compteApprenant.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage=new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("ESIQuiz");
                primaryStage.show();
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

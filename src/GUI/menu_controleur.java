package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class menu_controleur implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public  Button btnf1;
    public  Button btnf2;
    public  Button btnf3;
    public  Button btnf4;
    public  Button dcx;



    public void menu_formateur (ActionEvent event)
    {
        if (event.getSource()== this.btnf1){
           // Test.vider_stage();
            try {
                AnchorPane root =  FXMLLoader.load(Test.class.getResource("/GUI/creerFormation.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage=new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("ESIQuiz");
                primaryStage.show();
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
          //  loadStage("/GUI/creerFormation.fxml");







    }
    public void doconnexion (ActionEvent event )
    {
        if (event.getSource()== this.dcx){
          //  Test.vider_stage();
            //loadStage("/GUI/login.fxml");

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
}


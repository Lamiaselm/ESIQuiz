package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class login_controleur implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public TextField field_log =new TextField();
    @FXML
    public PasswordField mdp=new PasswordField ();
    public  Button login;
    BufferedReader in = null;
    String ligne ;

    public  void login_formateur()
    {
        String username = this.field_log.getText();
        String mdps =this.mdp.getText();
        if(GUI.Auth.verifier(username,mdps)) {
            //GUI.Test.vider_stage();
           // loadStage("menu.fxml");
            System.out.println("TRUE");

                // Test.vider_stage();
                try {
                    AnchorPane root =  FXMLLoader.load(Test.class.getResource("/GUI/menu.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage=new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("ESIQuiz");
                    primaryStage.show();
                } catch (Exception ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        else{

            ObjectInputStream inApp=null;
            Formation form;
            HashMap<String,Apprenant> apprenants= new HashMap<String,Apprenant>();
            Boolean fek=false;
            Date date= new Date();
            Apprenant p= new Apprenant("","",date,"","","","");
            try {
                inApp = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Appreants.dat"))));
                try {

                    form=(Formation)inApp.readObject();
                    apprenants=form.getApprenants();

                    for (Apprenant value : apprenants.values()) {

                        //System.out.println(value.getLogin()+" "+value.getMotDePass());
                        if((value.getLogin().equals(username))&&(value.getMotDePass().equals(mdps))){
                            fek=true;
                            p=value;

                            break;

                        }

                    }

                    if(fek==true){
                        Stage window= new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        window.setTitle("Compte Appreants");
                        Parent root= new Parent() {
                        };
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("compteApprenant.fxml"));
                        try{
                            root = loader.load();}
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        CompteApprenant cApp= loader.getController();
                        cApp.init(p);
                        window.setScene(new Scene(root, 800, 700));
                        window.showAndWait();
                        cApp.getData();
                    }else {


                        try {
                            Stage stage = (Stage) this.field_log.getScene().getWindow();

                            stage.close();

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

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                inApp.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void entrer (ActionEvent event)
    {
        if (event.getSource()== this.login){


            this.login_formateur();
        }
    }
}


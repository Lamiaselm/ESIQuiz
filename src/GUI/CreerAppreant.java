package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerAppreant implements Initializable {

    @FXML private TextField nomApp;
    @FXML private TextField prenomApp;
    @FXML private TextArea adresseApp;
    @FXML private DatePicker dateNaissanceApp;
    @FXML private TextField matriculeApp;
    @FXML private Button retour;
    private Apprenant apprenant;

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void init(Apprenant a){

        this.apprenant=a;

        this.nomApp.setText(a.getNom());
        this.prenomApp.setText(a.getPrenom());
        this.adresseApp.setText(a.getAdresse());

        Instant instant = Instant.ofEpochMilli(a.getDateNaissance().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        this.dateNaissanceApp.setValue(localDate);

        this.matriculeApp.setText(a.getMatricule());
    }
    @FXML Apprenant getData(){
        String n=this.nomApp.getText();
        String p=this.prenomApp.getText();
        String m=this.matriculeApp.getText();
        String a=this.adresseApp.getText();

        LocalDate localDate=this.dateNaissanceApp.getValue();
        Instant instant=Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date datedb=Date.from(instant);
        String login=n.toLowerCase()+"_"+p.toLowerCase();

        String motDePass=m;

        this.apprenant.setMatricule(m);
        this.apprenant.setAdresse(a);
        this.apprenant.setNom(n);
        this.apprenant.setPrenom(p);
        this.apprenant.setDateNaissance(datedb);
        this.apprenant.setLogin(login);
        this.apprenant.setMotDePass(motDePass);
        Stage stage = (Stage) nomApp.getScene().getWindow();
        stage.close();
        return this.apprenant;

    }
    public void retour (ActionEvent event )
    {
        if (event.getSource()== this.retour){
            //  Test.vider_stage();
            //  loadStage("/GUI/compteApprenant.fxml");

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
    }
}

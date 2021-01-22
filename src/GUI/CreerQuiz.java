package GUI;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerQuiz implements Initializable {
    @FXML private TextField nomQuiz;
    @FXML private DatePicker dateDebut;
    @FXML private DatePicker dateFin;
    @FXML private TableView<Question> tabQuestions;
    @FXML private TableColumn<Question,String> type;
    @FXML private TableColumn<Question,String> enon;
    @FXML private TableColumn<Question,Integer>num;
    @FXML private Button ajouter;
    @FXML private Button supprimer;
    @FXML private Button retour;
    @FXML private Button ok;
    private ObservableList<Notion> listNotions= FXCollections.observableArrayList();
    private ObservableList<Question> listQuestions= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enon.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        enon.setCellFactory(TextFieldTableCell.<Question> forTableColumn());

        type.setCellValueFactory(new PropertyValueFactory<>("TypeQuestion"));
        type.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        num.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Integer>(tabQuestions.getItems().indexOf(column.getValue())+1));

        tabQuestions.setItems(listQuestions);
    }
    @FXML private void supprimerQuestion(){
        ObservableList<Question> questionsSelected=tabQuestions.getSelectionModel().getSelectedItems();
        for(Question q:questionsSelected){
            if (listQuestions.isEmpty() ) break;
            if(listQuestions.contains(q)) listQuestions.remove(q);
        }

        raffrichir();
    }
    private void raffrichir(){
        tabQuestions.getColumns().get(0).setVisible(false);
        tabQuestions.getColumns().get(0).setVisible(true);
    }
    @FXML void ajouterQuestion()throws Exception{

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouveau Quiz");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("choisirQuestion.fxml"));
        Parent root = loader.load();
        ChoisirQuestion cC=loader.getController();
        ArrayList<Question> list= new ArrayList<Question>();

        for(Question q: this.listQuestions){
            list.add(q);
        }


        window.setScene(new Scene(root, 400, 300));
        System.out.println(list.size());
        cC.init(this.listNotions,list);
        window.showAndWait();
        ObservableList<Question> f=cC.getData();
        this.listQuestions=f;
        tabQuestions.setItems(this.listQuestions);


        this.raffrichir();

    }
    public void initNotion(ObservableList<Notion> list){


        listNotions=list;

    }
    @FXML public Quiz getData(){
        String s=this.nomQuiz.getText();
        LocalDate localDatedb=this.dateDebut.getValue();
        Instant instantdb=Instant.from(localDatedb.atStartOfDay(ZoneId.systemDefault()));
        Date datedb=Date.from(instantdb);

        LocalDate localDatefn=this.dateFin.getValue();
        Instant instantfn=Instant.from(localDatefn.atStartOfDay(ZoneId.systemDefault()));
        Date datefn=Date.from(instantfn);

        ArrayList<Question> list=new ArrayList<Question>();
        for(Question q: this.listQuestions){
            list.add(q);
        }

        Quiz quiz=new Quiz(s,datedb,datefn,list);
        Stage stage = (Stage) nomQuiz.getScene().getWindow();
        stage.close();
        return quiz;

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
    public void init(Quiz q){
        this.nomQuiz.setText(q.getNom());
        Instant instant = Instant.ofEpochMilli(q.getDateOuvert().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        this.dateDebut.setValue(localDate);

        Instant instant2 = Instant.ofEpochMilli(q.getDateExpir().getTime());
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        LocalDate localDate2 = localDateTime2.toLocalDate();
        this.dateFin.setValue(localDate2);
        ObservableList<Question> list=FXCollections.observableArrayList();
        for(Question question:q.getQuestions()){
            list.add(question);
        }
        this.listQuestions=list;
        this.tabQuestions.setItems(this.listQuestions);

    }

}

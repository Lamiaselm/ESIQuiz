package GUI;

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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerNotion implements Initializable {
    @FXML private TextField nomNotion;
    @FXML private TableView<Question> tabQuestions;
    @FXML private TableColumn<Question,String>enon;
    @FXML private TableColumn<Question,String> type;
    @FXML private Button ajouterQCM;
    @FXML private Button ajouterQO;
    @FXML private Button ajouterQCU;
    @FXML private Button retour;
    private ObservableList<Question> listQuestions= FXCollections.observableArrayList();
    private Notion notion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        enon.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        enon.setCellFactory(TextFieldTableCell.<Question> forTableColumn());

        type.setCellValueFactory(new PropertyValueFactory<>("TypeQuestion"));
        type.setCellFactory(TextFieldTableCell.<Question> forTableColumn());



        tabQuestions.setRowFactory( tv -> {
            TableRow<Question> row = new TableRow<Question>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Question rowData = row.getItem();
                    String m=rowData.getEnonce();
                    for(Question q :listQuestions){
                        if(q.getEnonce()==m){
                            if(q.getTypeQuestion()=="QCM"){
                                //QCM
                                Stage window= new Stage();
                                window.initModality(Modality.APPLICATION_MODAL);
                                window.setTitle("Nouveau QCM");
                                Parent root= new Parent() {
                                };
                                FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQCM.fxml"));
                                try{
                                    root = loader.load();}
                                catch(Exception e){
                                    e.printStackTrace();
                                }

                                CreerQCM cQCM=loader.getController();
                                cQCM.init((QCM)q);
                                window.setScene(new Scene(root, 400, 300));
                                window.showAndWait();
                                QCM qcm=cQCM.getData();
                                qcm.setId_notion(0);
                                qcm.setIdQuestion(q.getIdQuestion());
                                this.listQuestions.set(this.listQuestions.indexOf((QCM)q),qcm);
                                tabQuestions.getColumns().get(0).setVisible(false);
                                tabQuestions.getColumns().get(0).setVisible(true);

                            }else{
                                if(q.getTypeQuestion()=="QCU"){
                                    //QCU
                                    Stage window= new Stage();
                                    window.initModality(Modality.APPLICATION_MODAL);
                                    window.setTitle("Nouveau QCU");
                                    Parent root= new Parent() {
                                    };
                                    FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQCU.fxml"));
                                    try{
                                        root = loader.load();}
                                    catch(Exception e){
                                        e.printStackTrace();
                                    }

                                    CreerQCU cQCU=loader.getController();
                                    cQCU.init((QCU)q);
                                    window.setScene(new Scene(root, 400, 300));
                                    window.showAndWait();
                                    QCU qcu=cQCU.getData();
                                    qcu.setId_notion(0);
                                    qcu.setIdQuestion(q.getIdQuestion());
                                    this.listQuestions.set(this.listQuestions.indexOf((QCU)q),qcu);
                                    tabQuestions.getColumns().get(0).setVisible(false);
                                    tabQuestions.getColumns().get(0).setVisible(true);
                                }else{
                                    //QO
                                    Stage window= new Stage();
                                    window.initModality(Modality.APPLICATION_MODAL);
                                    window.setTitle(" QO");
                                    Parent root= new Parent() {
                                    };
                                    FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQO.fxml"));
                                    try{
                                        root = loader.load();}
                                    catch(Exception e){
                                        e.printStackTrace();
                                    }

                                    creerQO cQO=loader.getController();
                                    cQO.init((QO)q);
                                    window.setScene(new Scene(root, 400, 300));
                                    window.showAndWait();
                                    QO qo=cQO.getData();
                                    qo.setId_notion(0);
                                    qo.setIdQuestion(q.getIdQuestion());
                                    this.listQuestions.set(this.listQuestions.indexOf((QO)q),qo);
                                    tabQuestions.getColumns().get(0).setVisible(false);
                                    tabQuestions.getColumns().get(0).setVisible(true);
                                }
                            }

                            break;
                        }
                    }
                }
            });
            return row ;
        });



        tabQuestions.setItems(this.listQuestions);
    }

    @FXML private void supprimerQuestion(){
        ObservableList<Question> questionsSelected=tabQuestions.getSelectionModel().getSelectedItems();
        questionsSelected.forEach(listQuestions::remove);
        tabQuestions.getColumns().get(0).setVisible(false);
        tabQuestions.getColumns().get(0).setVisible(true);
    }
    @FXML private void addQCM() throws Exception{
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouveau QCM");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQCM.fxml"));
        Parent root = loader.load();
        CreerQCM cQCM=loader.getController();

        window.setScene(new Scene(root, 400, 300));
        window.showAndWait();
        QCM qcm=cQCM.getData();
        qcm.setId_notion(0);
        qcm.setIdQuestion(this.listQuestions.size());
        this.listQuestions.add(qcm);
        tabQuestions.getColumns().get(0).setVisible(false);
        tabQuestions.getColumns().get(0).setVisible(true);
    }
    @FXML private void addQCU() throws Exception{
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouveau QCU");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQCU.fxml"));
        Parent root = loader.load();
        CreerQCU cQCU=loader.getController();

        window.setScene(new Scene(root, 400, 300));
        window.showAndWait();

        QCU qcu=cQCU.getData();
        qcu.setId_notion(0);
        qcu.setIdQuestion(this.listQuestions.size());
        this.listQuestions.add(qcu);
        tabQuestions.getColumns().get(0).setVisible(false);
        tabQuestions.getColumns().get(0).setVisible(true);
    }
    @FXML private void addQo() throws Exception{
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouveau QO");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQO.fxml"));
        Parent root = loader.load();
        creerQO cQO=loader.getController();

        window.setScene(new Scene(root, 400, 300));
        window.showAndWait();

        QO qo=cQO.getData();
        qo.setId_notion(0);
        qo.setIdQuestion(this.listQuestions.size());
        this.listQuestions.add(qo);
        tabQuestions.getColumns().get(0).setVisible(false);
        tabQuestions.getColumns().get(0).setVisible(true);
    }
    public void init(Notion n) {
        if(!n.getQuestion().isEmpty()) {
            for (Question q : n.getQuestion()) {
                this.listQuestions.add(q);
            }
        }
        if(n.getNotion()!=""){
            this.nomNotion.setText(n.getNotion());
        }

    }
    @FXML Notion getData(){
        String s=nomNotion.getText();
        ArrayList<Question> m = new ArrayList<Question>();
        for(Question q: this.listQuestions){
            m.add(q);
        }
        Notion n=new Notion(0,s,m);
        Stage stage = (Stage) ajouterQCM.getScene().getWindow();

        stage.close();
        return n;
    }
    public void retour(ActionEvent event) {
        if (event.getSource() == this.retour) {
            //  Test.vider_stage();
            //loadStage("/GUI/login.fxml");

            try {
                AnchorPane root = FXMLLoader.load(Test.class.getResource("/GUI/menu.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("ESIQuiz");
                primaryStage.show();
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

}

package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreerFormationCont implements Initializable {
    private Formation formation;
    @FXML private TextField nom;
    @FXML private TextArea descp;
    @FXML private DatePicker dateDebut;
    @FXML private DatePicker dateFin;
    @FXML private TableView<Notion> tabNotions;
    @FXML private TableColumn<Notion,String> nomNotion;
    @FXML private Button retour;


    //Quiz Q..........................................Q
    @FXML private TableView<Quiz> tabQuiz;
    @FXML private TableColumn<Quiz,String> nomQuiz;
    @FXML private TableColumn<Quiz, Date> dateDbQuiz;
    @FXML private TableColumn<Quiz,Date> dateFnQuiz;
    @FXML private Button ajouterQuiz;
    @FXML private Button suprimerQuiz;
    //Apprenants Q___Q
    @FXML private TableView<Apprenant> tabApprenant;
    @FXML private TableColumn<Apprenant,String> colMatricule;
    @FXML private TableColumn<Apprenant,String> colNom;
    @FXML private TableColumn<Apprenant,String> colPrenom;
    @FXML private TableColumn<Apprenant,Date> colDateN;
    @FXML private TableColumn<Apprenant,Integer> colMoy;


    private ObservableList<Quiz> listQuiz=FXCollections.observableArrayList();
    private ObservableList<Notion> listNotions= FXCollections.observableArrayList();
    private ObservableList <Apprenant>listApprenant=FXCollections.observableArrayList();





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObjectInputStream inApp;

        try {
            inApp = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Appreants.dat"))));
            try {
                HashMap<String,Apprenant> apprenants = new HashMap<String,Apprenant>();
                Formation form=(Formation)inApp.readObject();
                apprenants=form.getApprenants();
                for (Apprenant value : apprenants.values()) {
                    this.listApprenant.add(value);
                }
                this.nom.setText(form.getNom());
                this.descp.setText(form.getDescription());

                for(Quiz q:form.getListQuiz()){
                    this.listQuiz.add(q);
                }
                for(Notion n:form.getNotions()){
                    this.listNotions.add(n);
                }
                Instant instant = Instant.ofEpochMilli(form.getDateDebut().getTime());
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                LocalDate localDate = localDateTime.toLocalDate();
                this.dateDebut.setValue(localDate);

                Instant instant2 = Instant.ofEpochMilli(form.getDateFin().getTime());
                LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
                LocalDate localDate2 = localDateTime2.toLocalDate();
                this.dateFin.setValue(localDate2);


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            inApp.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nomNotion.setCellValueFactory(new PropertyValueFactory<>("notion"));
        nomNotion.setCellFactory(TextFieldTableCell.<Notion> forTableColumn());
        tabNotions.setRowFactory( tv -> {
            TableRow<Notion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Notion rowData = row.getItem();
                    Stage window= new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Modifier Notion");
                    Parent root= new Parent() {
                    };
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("creerNotion.fxml"));
                    try{
                        root = loader.load();}
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    CreerNotion cNotion=loader.getController();
                    cNotion.init((Notion)rowData);
                    window.setScene(new Scene(root, 800, 700));
                    window.showAndWait();
                    Notion notion =cNotion.getData();
                    this.listNotions.set(this.listNotions.indexOf(rowData),notion);
                    this.raffrichir();

                }
            });
            return row ;
        });



        tabNotions.setItems(this.listNotions);

        nomQuiz.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomQuiz.setCellFactory(TextFieldTableCell.<Quiz> forTableColumn());
        dateDbQuiz.setCellValueFactory(new PropertyValueFactory<>("dateOuvert"));
        dateFnQuiz.setCellValueFactory(new PropertyValueFactory<>("dateExpir"));

        tabQuiz.setRowFactory( tv -> {
            TableRow<Quiz> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Quiz rowData = row.getItem();
                    Stage window= new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("");
                    Parent root= new Parent() {
                    };
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQuiz.fxml"));
                    try{
                        root = loader.load();}
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    CreerQuiz cQuiz=loader.getController();
                    cQuiz.init((Quiz)rowData);
                    window.setScene(new Scene(root, 800, 700));
                    window.showAndWait();
                    Quiz quiz =cQuiz.getData();
                    this.listQuiz.set(this.listNotions.indexOf(rowData),quiz);
                    this.raffrichir();

                }
            });
            return row ;
        });

        tabQuiz.setItems(this.listQuiz);

        //-----APPRENANT
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNom.setCellFactory(TextFieldTableCell.<Apprenant> forTableColumn());
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colPrenom.setCellFactory(TextFieldTableCell.<Apprenant> forTableColumn());
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colMatricule.setCellFactory(TextFieldTableCell.<Apprenant> forTableColumn());
        colDateN.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        //colDateN.setCellFactory(DateCell.<Apprenant> forTableColumn());
        colMoy.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        tabApprenant.setRowFactory( tv -> {
            TableRow<Apprenant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Apprenant rowData = row.getItem();
                    Stage window= new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Apprenant");
                    Parent root= new Parent() {
                    };
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("creerAppreant.fxml"));
                    try{
                        root = loader.load();}
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    CreerAppreant cApprenant=loader.getController();
                    cApprenant.init((Apprenant)rowData);
                    window.setScene(new Scene(root, 800, 700));
                    window.showAndWait();
                    Apprenant apprenant =cApprenant.getData();
                    this.listApprenant.set(listApprenant.indexOf(rowData),apprenant);
                    this.raffrichir();

                }
            });
            return row ;
        });


        tabApprenant.setItems(listApprenant);


    }

    @FXML private void supprimerNotion(){
        ObservableList<Notion> questionsSelected=tabNotions.getSelectionModel().getSelectedItems();
        questionsSelected.forEach(listNotions::remove);
        this.raffrichir();
    }
    private void raffrichir(){
        tabNotions.getColumns().get(0).setVisible(false);
        tabNotions.getColumns().get(0).setVisible(true);
        tabQuiz.getColumns().get(0).setVisible(false);
        tabQuiz.getColumns().get(0).setVisible(true);
        tabApprenant.getColumns().get(0).setVisible(false);
        tabApprenant.getColumns().get(0).setVisible(true);

    }
    @FXML private void ajouterNotion() throws Exception{
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouvelle Notion");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerNotion.fxml"));

        Parent root = loader.load();
        CreerNotion cNotion=loader.getController();

        window.setScene(new Scene(root, 800, 700));
        window.showAndWait();
        Notion notion=cNotion.getData();
        this.listNotions.add(notion);
        this.raffrichir();
    }

    @FXML void addQuiz() throws Exception{
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerQuiz.fxml"));
        Parent root = loader.load();
        CreerQuiz cQ=loader.getController();
        window.setScene(new Scene(root, 800, 700));
        cQ.initNotion(listNotions);
        window.showAndWait();
        this.listQuiz.add(cQ.getData());
        tabQuiz.setItems(listQuiz);
        this.raffrichir();
    }
    @FXML void addApprenant() throws Exception{

        Date d=new Date();
        Apprenant a = new Apprenant("","",d,"","","","");
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouveau Apprenant");
        Parent root= new Parent() {
        };
        FXMLLoader loader=new FXMLLoader(getClass().getResource("creerAppreant.fxml"));
        try{
            root = loader.load();}
        catch(Exception e){
            e.printStackTrace();
        }

        CreerAppreant cApprenant=loader.getController();
        cApprenant.setApprenant(a);
        window.setScene(new Scene(root, 800, 700));
        window.showAndWait();
        Apprenant apprenant =cApprenant.getData();
        this.listApprenant.add(apprenant);
        this.raffrichir();
    }

    @FXML void deleApprenat(){
        ObservableList<Apprenant> questionsSelected=tabApprenant.getSelectionModel().getSelectedItems();
        questionsSelected.forEach(listApprenant::remove);
        this.raffrichir();

    }

    @FXML public void getData(){
        ArrayList<Quiz> qui= new ArrayList<Quiz>();
        for(Quiz n:this.listQuiz){
            Date today= new Date();
            int f= today.compareTo(n.getDateOuvert());
            if(f>-1){
                for(Apprenant un: this.listApprenant){
                  if(!un.getQuizPasEncSoumis().contains(n)){
                    un.getQuizPasEncSoumis().add(n);}
                }
            }
            qui.add(n);

        }

        ObjectOutputStream inApp=null;
        HashMap<String,Apprenant> apprenants= new HashMap<String,Apprenant>();
        String nomFormation= nom.getText();

        LocalDate localDatedb=this.dateDebut.getValue();
        Instant instantdb=Instant.from(localDatedb.atStartOfDay(ZoneId.systemDefault()));
        Date datedb=Date.from(instantdb);

        LocalDate localDatefn=this.dateFin.getValue();
        Instant instantfn=Instant.from(localDatefn.atStartOfDay(ZoneId.systemDefault()));
        Date datefn=Date.from(instantfn);

        String ds= this.descp.getText();
        ArrayList<Notion> not= new ArrayList<Notion>();
        for(Notion n:this.listNotions){
            not.add(n);
        }




        for( Apprenant a : this.listApprenant){
            apprenants.put(a.getMatricule(),a);
        }

        try {
            inApp = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Appreants.dat"),false)));

            Formation formation=new Formation(nomFormation,ds,datedb,datefn);
            formation.setApprenants(apprenants);
            formation.setNotions(not);
            formation.setListQuiz(qui);
            inApp.writeObject(formation);

            if(inApp!=null) inApp.close();

            Stage stage = (Stage) this.nom.getScene().getWindow();

            stage.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

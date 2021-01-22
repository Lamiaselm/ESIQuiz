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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChoisirQuestion implements Initializable {
    @FXML private TableView<Notion> tabNotions;
    @FXML private TableColumn<Notion,String> nomNotion;
    private ObservableList<Notion> listNotions= FXCollections.observableArrayList();
    private ArrayList<Question> questSelected=new ArrayList<Question>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //NOM NOTION

        nomNotion.setCellValueFactory(new PropertyValueFactory<>("notion"));
        nomNotion.setCellFactory(TextFieldTableCell.<Notion> forTableColumn());

        tabNotions.setRowFactory( tv -> {
            TableRow<Notion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Notion rowData = row.getItem();
                    Stage window= new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("nb Question");
                    Parent root=new Parent(){};
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("nbQuestion.fxml"));
                    try{
                        root = loader.load();}
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    NbQuestion cNb=loader.getController();
                    ArrayList<Question> list=rowData.getQuestion();
                    //the problem is here---
                    questSelected.forEach(list::remove);
                    cNb.setListQuestions(list);
                    //----------------------
                    cNb.setListSelect(questSelected);


                    window.setScene(new Scene(root, 400, 300));
                    window.showAndWait();
                    this.questSelected=cNb.getData();

                    this.raffrichir();

                }
            });
            return row ;
        });

        tabNotions.setItems(this.listNotions);

    }
    private void raffrichir(){
        tabNotions.getColumns().get(0).setVisible(false);
        tabNotions.getColumns().get(0).setVisible(true);
    }
    @FXML  public ObservableList<Question> getData()
    {
        ObservableList<Question> list= FXCollections.observableArrayList();
        for(Question q:questSelected)
        {
            list.add(q);
        }

        Stage stage = (Stage) this.tabNotions.getScene().getWindow();

        stage.close();
        return list;
    }

    public void init(ObservableList<Notion> listNotions,ArrayList<Question> quests){

        for (Notion n: listNotions){
            this.listNotions.add(n);
        }
        tabNotions.setItems(this.listNotions);
        this.questSelected=quests;

    }

}

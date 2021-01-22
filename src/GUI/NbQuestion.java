package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NbQuestion implements Initializable {
    @FXML private Button ok;
    @FXML private TableView<Question> tabQuesiton;
    @FXML private TableColumn<Question,String> typeCol;
    @FXML private TableColumn<Question,String> enonCol;

    private ObservableList<Question> listQuestions= FXCollections.observableArrayList();
    private ObservableList<Question> listSelect= FXCollections.observableArrayList();
    //GETTER SETTER

    public ArrayList<Question> getListQuestions() {
        ArrayList<Question> list=new ArrayList<Question>();
        for(Question q: listQuestions){
            list.add(q);
        }
        return list;
    }

    public void setListQuestions(ArrayList<Question> listQuestions) {
        ObservableList list=FXCollections.observableArrayList();
        for(Question q:listQuestions){
            this.listQuestions.add(q);
        }
        this.tabQuesiton.setItems(this.listQuestions);

    }

    public ArrayList<Question> getListSelect() {
        ArrayList<Question> list=new ArrayList<Question>();
        for(Question q: listSelect){
            list.add(q);
        }
        return list;
    }

    public void setListSelect(ArrayList<Question> listSelect) {
        ObservableList list=FXCollections.observableArrayList();
        for(Question q:listSelect){
            list.add(q);
        }
        this.listSelect = list;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabQuesiton.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        enonCol.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        enonCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());

        typeCol.setCellValueFactory(new PropertyValueFactory<>("TypeQuestion"));
        typeCol.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        init();

        tabQuesiton.setItems(this.listQuestions);
    }
    @FXML ArrayList<Question> getData(){
        ObservableList<Question> select=this.tabQuesiton.getSelectionModel().getSelectedItems();
        this.listQuestions.forEach(listSelect::remove);
        for(Question q:select){
            listSelect.add(q);
        }
        Stage stage = (Stage) tabQuesiton.getScene().getWindow();
        stage.close();
        ArrayList<Question> questo=new ArrayList<Question>();
        for(Question q: listSelect){
            questo.add(q);
        }
        return questo;
    }
    private void init(){
        if(!listSelect.isEmpty()) {
            for (Question q : listSelect) {

                if (listQuestions.contains(q)) {
                    this.tabQuesiton.getSelectionModel().select(q);
                }
            }
        }
    }



}

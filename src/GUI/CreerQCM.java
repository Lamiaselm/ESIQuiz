package GUI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreerQCM implements Initializable {

    @FXML private TextArea enonce;
    @FXML private TableView<Option> choix;
    @FXML private TableColumn<Option,String>option;
    @FXML private TableColumn<Option, Boolean> vv;
    @FXML private Button ajouter;
    @FXML private Button ok;
    private ObservableList<Option> listOptions=FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choix.setEditable(true);
        option.setCellValueFactory(new PropertyValueFactory<>("choix"));
        option.setCellFactory(TextFieldTableCell.<Option> forTableColumn());

        option.setOnEditCommit((TableColumn.CellEditEvent<Option, String> event) -> {
            TablePosition<Option, String> pos = event.getTablePosition();

            String newchoix = event.getNewValue();

            int row = pos.getRow();
            Option op = event.getTableView().getItems().get(row);

            op.setChoix(newchoix);
        });

        vv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Option, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Option, Boolean> param) {
                Option person = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isCor());


                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        person.setCor(newValue);
                    }
                });
                return booleanProp;
            }
        });
        vv.setCellFactory(new Callback<TableColumn<Option, Boolean>, TableCell<Option, Boolean>>() {
            @Override
            public TableCell<Option, Boolean> call(TableColumn<Option, Boolean> p) {
                CheckBoxTableCell<Option, Boolean> cell = new CheckBoxTableCell<Option, Boolean>();
                return cell;
            }
        });

        choix.setItems(this.listOptions);

    }
    public void init(QCM qcm){
        if(qcm.getEnonce()!=""){
            this.enonce.setText(qcm.getEnonce());
        }
        if(!qcm.getProposition().isEmpty()) {
            for (Option o : qcm.getProposition()) {
                listOptions.add(o);
            }
        }
    }

    @FXML
    public void ajouterOption(){
        Option option=new Option(".....",false);

        this.listOptions.add(option);
        choix.getColumns().get(0).setVisible(false);
        choix.getColumns().get(0).setVisible(true);
    }
    @FXML public void supprimerOption(){
        ObservableList<Option> optionsSelected=choix.getSelectionModel().getSelectedItems();
        optionsSelected.forEach(listOptions::remove);
        choix.getColumns().get(0).setVisible(false);
        choix.getColumns().get(0).setVisible(true);
    }
    @FXML public QCM getData(){

        String s=enonce.getText();
        ArrayList<Option> op =new ArrayList<Option>();
        for(Option o:listOptions){
            op.add(o);
        }
        QCM qcm= new QCM(0,s,"QCM",0,op);

        Stage stage = (Stage) ok.getScene().getWindow();

        stage.close();

        return qcm;


    }
}




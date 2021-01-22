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

public class RepondreQCM implements Initializable {
    @FXML private Label ennonce;
    @FXML private TableView <Option> tabOption;
    @FXML private TableColumn<Option,String> colChoix;
    @FXML private TableColumn<Option, Boolean> colVV;
    @FXML private Button suivant;
    @FXML private Button precedent;
    private QCM qcm;
    private ObservableList<Option> listOptions= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        tabOption.setEditable(true);
        colChoix.setCellValueFactory(new PropertyValueFactory<>("choix"));
        colChoix.setCellFactory(TextFieldTableCell.<Option> forTableColumn());

        colVV.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Option, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Option, Boolean> param) {
                Option option = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(option.getSelected());


                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        qcm.setRepondre(true);
                        option.setSelected(newValue);
                    }
                });
                return booleanProp;
            }
        });
        colVV.setCellFactory(new Callback<TableColumn<Option, Boolean>, TableCell<Option, Boolean>>() {
            @Override
            public TableCell<Option, Boolean> call(TableColumn<Option, Boolean> p) {
                CheckBoxTableCell<Option, Boolean> cell = new CheckBoxTableCell<Option, Boolean>();
                return cell;
            }
        });
        this.tabOption.setItems(this.listOptions);
    }

    public void init(QCM qcm){
        this.qcm=qcm;
        this.ennonce.setText(this.qcm.getEnonce());
        ObservableList<Option> options=FXCollections.observableArrayList();

        for(Option option: this.qcm.getProposition()) {
            options.add(option);
        }



        this.listOptions = options;
        this.tabOption.setItems(listOptions);

    }
    @FXML public QCM getData(){
        ArrayList<Option> list= new ArrayList<Option>();
        for(Option option:this.listOptions){
            if (option.getSelected()) list.add(option);
        }
        this.qcm.setReponses(list);
        Stage stage = (Stage) ennonce.getScene().getWindow();

        stage.close();
        return this.qcm;

    }

       /* private void houda(){
            Option h1=new Option("HAHAHAbitch",false);
            Option h2=new Option("let's make houda great again",true);
            Option h3=new Option("KURIKAITSU",true);
            this.listOptions.addAll(h1,h2,h3);
        }*/
}

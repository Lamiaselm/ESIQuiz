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
import java.util.TreeSet;

public class RepondreQCU implements Initializable {
    @FXML private Label ennonce;
    @FXML private TableView<Option> tabOption;
    @FXML private TableColumn<Option,String> colChoix;
    @FXML private TableColumn<Option, Boolean> colVV;
    @FXML private Button suivant;
    @FXML private Button precedent;
    private QCU qcu;
    private ObservableList<Option> listOptions= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeSet<Integer>n= new TreeSet<Integer>();

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
                        if((newValue==true)&&(oldValue==false)){

                            for(Option a:listOptions){
                                a.setSelected(false);
                            }
                            tabOption.getColumns().get(0).setVisible(false);
                            tabOption.getColumns().get(0).setVisible(true);
                        }
                        qcu.setRepondre(true);
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


    public void init(QCU qcu){
        this.qcu=qcu;
        this.ennonce.setText(this.qcu.getEnonce());
        ObservableList<Option> options=FXCollections.observableArrayList();
        ArrayList<Option> optioon= new ArrayList<Option>(this.qcu.getProposition());

        for(Option option: optioon) {


            options.add(option);
        }

        this.listOptions = options;
        this.tabOption.setItems(listOptions);

    }
    @FXML public QCU getData(){
        for(Option op: listOptions){
            if(op.getSelected()){
                this.qcu.setReponse(op);
            }
        }

        Stage stage = (Stage) ennonce.getScene().getWindow();

        stage.close();
        return this.qcu;
    }
}

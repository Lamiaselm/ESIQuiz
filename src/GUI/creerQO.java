package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class creerQO implements Initializable {
    @FXML private TextArea enonce;
    @FXML private TextField motCle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML public QO getData(){

        String s=enonce.getText();
        String m=motCle.getText();
        QO qo= new QO(0,s,"QO",0,m);

        Stage stage = (Stage) enonce.getScene().getWindow();

        stage.close();

        return qo;
    }
    public void init(QO qo){
        if(qo.getEnonce()!=""){
            this.enonce.setText(qo.getEnonce());
        }
        if(!qo.getMot().equals("")) {
            this.motCle.setText(qo.getMot());
        }
    }
}

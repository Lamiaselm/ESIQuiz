package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RepondreQO implements Initializable {
    @FXML
    private Label ennonce;
    @FXML
    private TextArea  rep;
    private QO qo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void init(QO qo){
        this.qo=qo;
        this.ennonce.setText(qo.getEnonce());
        if(qo.isRepondre()){
            this.rep.setText(qo.getReponse());
        }

    }

    @FXML public QO getData(){
        this.qo.setReponse(this.rep.getText());
        if(!(this.rep.getText()==""))this.qo.setRepondre(true);
        Stage stage = (Stage) rep.getScene().getWindow();

        stage.close();

        return this.qo;

    }

}

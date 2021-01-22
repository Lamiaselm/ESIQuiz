package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CreerQuesion implements Initializable {
    @FXML private RadioButton qcm;
    @FXML private RadioButton qcu;
    @FXML private RadioButton qo;
    private ToggleGroup typeQuestion;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.typeQuestion= new ToggleGroup();
        this.qcm.setToggleGroup(typeQuestion);
        this.qcu.setToggleGroup(typeQuestion);
        this.qo.setToggleGroup(typeQuestion);
    }
}

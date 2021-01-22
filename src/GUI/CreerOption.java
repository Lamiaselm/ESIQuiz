package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreerOption implements Initializable {
    @FXML public TextArea context ;
    @FXML public Checkbox vrais;

    public Option nouveauChoix(){
        String choix=context.getText();
        boolean selectionnee=vrais.getState();
        Option option =new Option(choix,selectionnee);
        return option;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

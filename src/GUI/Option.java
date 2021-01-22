package GUI;

import java.io.Serializable;

public class Option implements Serializable {
    private  String choix;
    private  Boolean cor;
    private Boolean selected=false;

    public Option(String choix, Boolean cor) {
        this.choix = choix;
        this.cor = cor;

    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public Boolean isCor() {
        return cor;
    }

    public void setCor(Boolean cor) {
        this.cor = cor;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

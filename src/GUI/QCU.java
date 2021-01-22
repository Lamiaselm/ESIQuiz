package GUI;
import java.util.*;
public class QCU extends Question {
    private ArrayList<Option> proposition=new ArrayList<Option>();
    private Option reponse;


    public QCU(int id,String enonce, String typeQuestion, int id_notion, ArrayList<Option> proposition) {
        super(id,enonce, typeQuestion, id_notion);
        this.proposition = proposition;
    }


    public ArrayList<Option> getProposition() {
        return proposition;
    }

    public void setProposition(ArrayList<Option> proposition) {
        this.proposition = proposition;
    }

    public Option getReponse() {
        return reponse;
    }

    public void setReponse(Option reponse) {
        this.reponse = reponse;
    }


    @Override
    public Double evaluer(int N) {
        Double m=1.0;
        Double j=0.0;
        m=m*100/N;
        if(this.reponse.isCor())  return m;
        else{
            return 0.0;
        }


    }

}

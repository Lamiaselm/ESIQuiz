package GUI;
import java.util.*;
public class QCM extends Question{
    private ArrayList<Option> proposition=new ArrayList<Option>();
    private ArrayList<Option> reponses =new ArrayList<Option>();

    public QCM(int id,String enonce, String typeQuestion, int id_notion, ArrayList<Option> proposition) {
        super(id,enonce, typeQuestion, id_notion);
        this.proposition = proposition;
    }

    public ArrayList<Option> getProposition() {
        return proposition;
    }

    public void setProposition(ArrayList<Option> proposition) {
        this.proposition = proposition;
    }

    public ArrayList<Option> getReponses() {
        return reponses;
    }

    public void setReponses(ArrayList<Option> reponses) {
        this.reponses = reponses;
    }

    @Override
    public Double evaluer(int N) {
        Double m=0.0;
        int p=proposition.size();
        for (Option o: reponses)
        {
            if(o.isCor()){m=m+1.0/p;}
            else{m=m-1.0/p;}
        }
        for(Option t: proposition){
            if(t.isCor()){
                if(!this.reponses.contains(t)){m=m-1.0/p;}
            }else{
                if(!this.reponses.contains(t)){m=m+1.0/p;}
            }
        }
        if(m<0) m=0.0;
        return m*100.0/N;
    }
}

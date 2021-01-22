package GUI;
import java.io.Serializable;
import java.util.*;
public class Notion implements Serializable {
    private int idNotion;
    private String notion;
    private static int idDerniereNotion;
    private ArrayList<Question> question=new ArrayList<Question>();

    public Notion(int idNotion, String notion, ArrayList<Question> question) {
        this.idNotion = idNotion;
        this.notion = notion;
        this.question = question;
    }

    public int getIdNotion() {
        return idNotion;
    }

    public void setIdNotion(int idNotion) {
        this.idNotion = idNotion;
    }

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        this.notion = notion;
    }

    public static int getIdDerniereNotion() {
        return idDerniereNotion;
    }

    public static void setIdDerniereNotion(int idDerniereNotion) {
        Notion.idDerniereNotion = idDerniereNotion;
    }

    public ArrayList<Question> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<Question> question) {
        this.question = question;
    }

    public void supprimerQuestion(int pos){
        this.question.remove(pos);

    }
    public void ajouterQuestion(Question q){
        this.question.add(q);
    }
    public void modifierQuestion(int pos,Question q){
        this.question.remove(pos);
        this.question.add(q);
    }
}

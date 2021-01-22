package GUI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Quiz implements Serializable {
    private String nom;
    private Date dateOuvert;
    private Date dateExpir;
    private ArrayList<Question> questions=new ArrayList <Question>();
    private double tauxAccomplissment;
    private double tauxReussite;
    private Boolean submit=false;


    public Quiz(String nom, Date dateOuvert, Date dateExpir, ArrayList<Question> questions) {
        this.nom = nom;
        this.dateOuvert = dateOuvert;
        this.dateExpir = dateExpir;
        this.questions = questions;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateOuvert() {
        return dateOuvert;
    }

    public void setDateOuvert(Date dateOuvert) {
        this.dateOuvert = dateOuvert;
    }

    public Date getDateExpir() {
        return dateExpir;
    }

    public void setDateExpir(Date dateExpir) {
        this.dateExpir = dateExpir;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public double getTauxAccomplissment() {
        return tauxAccomplissment;
    }

    public void setTauxAccomplissment(float tauxAccomplissment) {
        this.tauxAccomplissment = tauxAccomplissment;
    }

    public double getTauxReussite() {
        return tauxReussite;
    }

    public void setTauxReussite(float tauxReussite) {
        this.tauxReussite = tauxReussite;
    }

    public Boolean getSubmit() {
        return submit;
    }

    public void setSubmit(Boolean submit) {
        this.submit = submit;
    }

    public double evaluer(){
        double m=0;
        int N=this.questions.size();
        for (Question q: this.questions){
            m=m+q.evaluer(N);

        }
        this.tauxReussite=m;
        return m;
    }
    public double calculTauxAccomplissement(){
        double m=questions.size();
        double tmp=0;
        for(Question q:questions){
            if (q.isRepondre()==true){

                tmp=tmp+1/m;

            }
        }
        this.tauxAccomplissment=tmp;

        return tmp;
    }

}

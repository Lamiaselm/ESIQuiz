package GUI;
import java.util.*;
public class Apprenant extends User implements Comparable <Apprenant>{
    private String matricule;
    private double moyenne;

    private ArrayList<Quiz> quizPasEncSoumis=new ArrayList<Quiz>();

    public Apprenant(String nom, String prenom, Date dateNaissance, String adresse, String login, String motDePass, String matricule) {
        super(nom, prenom, dateNaissance, adresse, login, motDePass);
        this.matricule = matricule;

    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public ArrayList<Quiz> getQuizPasEncSoumis() {
        return quizPasEncSoumis;
    }

    public void setQuizPasEncSoumis(ArrayList<Quiz> quizPasEncSoumis) {
        this.quizPasEncSoumis = quizPasEncSoumis;
    }

    @Override
    public int hashCode()
    {
        return matricule.hashCode();
    }


    @Override
    public boolean equals(Object a) {
        if(a== null) return false;
        if (!(a instanceof Apprenant)) return false;

        return (this.matricule==((Apprenant)a).matricule);
    }

    @Override
    public String toString() {
        return (this.getNom()+" "+this.getPrenom()+" "+this.getAdresse()+" "+this.getDateNaissance()+" "+this.matricule+" "+this.moyenne);
    }
    @Override
    public int compareTo(Apprenant o) {
        if (this.moyenne==(o.getMoyenne())) return 0;
        else{
            if (this.moyenne > (o.getMoyenne())) return 1;
            else {
                return -1;
            }
        }
    }

    public void ajouterQuiz(Quiz q){
        this.quizPasEncSoumis.add(q);
    }

    public double calculeMoyenne(){
        double m=0;
        for (Quiz q: quizPasEncSoumis)
        {
            if(q.getSubmit()) m=m+q.getTauxReussite();
        }
        m=m/quizPasEncSoumis.size();
        this.moyenne=m;
        return m;

    }
}

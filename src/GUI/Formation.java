package GUI;

import java.io.Serializable;
import java.util.*;
public class Formation implements Serializable {
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList <Notion> notions=new ArrayList <Notion>();
    private HashMap <String,Apprenant> apprenants=new HashMap<String,Apprenant>();
    private ArrayList<Quiz> listQuiz =new ArrayList <Quiz>();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public ArrayList<Notion> getNotions() {
        return notions;
    }

    public void setNotions(ArrayList<Notion> notions) {
        this.notions = notions;
    }

    public HashMap<String, Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(HashMap<String, Apprenant> apprenants) {
        this.apprenants = apprenants;
    }

    public ArrayList<Quiz> getListQuiz() {
        return listQuiz;
    }

    public void setListQuiz(ArrayList<Quiz> listQuiz) {
        this.listQuiz = listQuiz;
    }

    public Formation(String nom, String description, Date dateDebut, Date dateFin) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    public void ajouterNotion(Notion n){
        this.notions.add(n);
    }
    public void ajouterApprenant(Apprenant a,String matricule){
        this.apprenants.put(matricule,a);
    }
    public void ajouterQuiz(Quiz q){
        this.listQuiz.add(q);
    }
    public ArrayList<Apprenant> ordrCroissant()
    {
        ArrayList<Apprenant> listApprenants= new ArrayList<Apprenant>(this.apprenants.values());
        Collections.sort(listApprenants,Collections.reverseOrder());
        return listApprenants;

    }
    public void ajouterQuizApprenants(int idQuiz){
        for(Map.Entry<String,Apprenant>a: apprenants.entrySet())
        {
            Apprenant ap=a.getValue();
            String apk=a.getKey();
            ap.ajouterQuiz(this.listQuiz.get(idQuiz));
            this.apprenants.put(apk,ap);
        }
    }
}

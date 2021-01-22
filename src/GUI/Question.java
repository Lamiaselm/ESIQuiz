package GUI;

import java.io.Serializable;

public abstract class Question implements Serializable {
    private int idQuestion;
    private String enonce;
    private String TypeQuestion;
    private int id_notion;
    private boolean repondre=false;

    public Question(int idQuestion,String enonce, String typeQuestion, int id_notion) {
        this.idQuestion=idQuestion;
        this.enonce = enonce;
        TypeQuestion = typeQuestion;
        this.id_notion = id_notion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public String getTypeQuestion() {
        return TypeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        TypeQuestion = typeQuestion;
    }

    public int getId_notion() {
        return id_notion;
    }

    public void setId_notion(int id_notion) {
        this.id_notion = id_notion;
    }

    public boolean isRepondre() {
        return repondre;
    }

    public void setRepondre(boolean repondre) {
        this.repondre = repondre;
    }
    public abstract Double evaluer(int N);

    @Override
    public boolean equals(Object obj) {
        if(obj== null) return false;
        if (!(obj instanceof Question)) return false;
        return (this.enonce==((Question) obj).getEnonce());
    }
}

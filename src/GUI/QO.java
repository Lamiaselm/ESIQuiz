package GUI;
public class QO extends Question{
    private String mot;
    private String reponse;

    public QO(int id,String enonce, String typeQuestion, int id_notion, String mot) {
        super(id,enonce, typeQuestion, id_notion);
        this.mot = mot;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public Double evaluer(int N) {

        int lol=reponse.indexOf(this.mot);
        if (lol==-1) return 0.0;
        else return 100.0/N;
    }
}

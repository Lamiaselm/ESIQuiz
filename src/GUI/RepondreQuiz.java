package GUI;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RepondreQuiz implements Initializable {

    @FXML private Button suivant;
    @FXML private Button precedent;
    @FXML private ProgressBar progress;
    @FXML private Button submit;
    private ObservableList<Question> listQuestion= FXCollections.observableArrayList();
    private IntegerProperty pos= new SimpleIntegerProperty(0);
    private DoubleProperty ppf= new SimpleDoubleProperty(0);
    private DoubleProperty ppff= new SimpleDoubleProperty(0);

    private Quiz quiz;
    public int getPos() {
        return pos.get();
    }

    public IntegerProperty posProperty() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos.set(pos);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setDisable(true);
        ppff.setValue((this.listQuestion.size()*1.0));
        ppf.bind(pos.divide(ppff));

        this.progress.progressProperty().bind(ppf);

        suivant.disableProperty().bind(pos.greaterThanOrEqualTo(listQuestion.size()));
        precedent.disableProperty().bind(pos.lessThanOrEqualTo(0));



        suivant.setOnAction((event) -> {



            if(getPos()==-1){
                setPos(0);
            }
            Question k =this.listQuestion.get(pos.get());

            if(k.getTypeQuestion()=="QCM"){
                repQCM((QCM)k);
            }else{
                if(k.getTypeQuestion()=="QCU"){
                    repQCU((QCU)k);
                }else{
                    repQO((QO)k);
                }
            }

            this.setPos(getPos()+1);


            if(quiz.calculTauxAccomplissement()==1.0){
                submit.setDisable(false);
            }
        });
        precedent.setOnAction((event) -> {

            if(getPos()==this.listQuestion.size()){
                setPos(this.listQuestion.size()-1);
            }
            if(getPos()==-1){
                setPos(0);
            }

            Question k =this.listQuestion.get(pos.get());

            if(k.getTypeQuestion()=="QCM"){
                repQCM((QCM)k);
            }else{
                if(k.getTypeQuestion()=="QCU"){
                    repQCU((QCU)k);
                }else{
                    repQO((QO)k);
                }
            }

            this.setPos(getPos()-1);

            if(quiz.calculTauxAccomplissement()==1.0){
                submit.setDisable(false);
            }
        });




    }

    public void repQCM(QCM qcm) {
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Repondre Quiz");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RepondreQCM.fxml"));
        Parent root= new Parent() {
        };
        try{
            root = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        RepondreQCM rQCM=loader.getController();
        rQCM.init(qcm);
        window.setScene(new Scene(root, 600, 700));
        window.showAndWait();
        QCM qcmm=rQCM.getData();
        qcmm.setRepondre(true);
        this.listQuestion.set(this.listQuestion.indexOf(qcm),qcmm);


    }

    public void repQCU(QCU qcu){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Repondre Quiz");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RepondreQCU.fxml"));

        Parent root= new Parent() {
        };
        try{
            root = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        RepondreQCU rQCU=loader.getController();
        rQCU.init(qcu);
        window.setScene(new Scene(root, 600, 700));
        window.showAndWait();
        QCU qcuu=rQCU.getData();
        qcuu.setRepondre(true);

        this.listQuestion.set(this.listQuestion.indexOf(qcu),qcuu);


    }

    public void repQO(QO qo){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Repondre Quiz");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RepondreQO.fxml"));

        Parent root= new Parent() {
        };
        try{
            root = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        RepondreQO rQO=loader.getController();
        rQO.init(qo);
        window.setScene(new Scene(root, 600, 700));
        window.showAndWait();
        QO qoo=rQO.getData();

        qoo.setRepondre(true);
        this.listQuestion.set(this.listQuestion.indexOf(qo),qoo);
    }
    public void init(Quiz quiz){
        this.quiz=quiz;
        for(Question q:quiz.getQuestions()){
            this.listQuestion.add(q);

        }
        suivant.disableProperty().bind(pos.greaterThanOrEqualTo(listQuestion.size()));

        if(quiz.calculTauxAccomplissement()==1.0) this.submit.setDisable(false);
    }

    @FXML public void submitQuiz(){
        quiz.setSubmit(true);
        quiz.evaluer();
        getData();
    }

    @FXML  public Quiz getData(){
        ArrayList<Question> questions=new ArrayList<Question>();
        for(Question q: this.listQuestion){
            questions.add(q);

        }
        this.quiz.setQuestions(questions);
        quiz.calculTauxAccomplissement();
        Stage stage = (Stage) suivant.getScene().getWindow();

        stage.close();
        return this.quiz;
    }

}

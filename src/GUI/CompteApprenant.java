package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CompteApprenant implements Initializable {

        private Apprenant apprenant;
        @FXML
        private Label bienvenu;
        @FXML
        private TableView<Quiz> tableQuiz;
        @FXML
        private TableColumn<Quiz, String> nomColumn;
        @FXML
        private TableColumn<Quiz, Integer> tauxRColumn;
        @FXML
        private TableColumn<Quiz, Integer> tauxAColumn;
        @FXML
        private TableColumn<Quiz, Date> dateDbQuiz;
        @FXML
        private TableColumn<Quiz, Date> dateFnQuiz;
        @FXML
        private Button deconexion;
        @FXML
        private TextField nom;
        @FXML
        private TextField prenom;
        @FXML
        private TextField adress;
        @FXML
        private TextField login;
        @FXML
        private TextField matricule;
        @FXML
        private TextField motDePasse;
        @FXML
        private DatePicker dateNaissance;
        @FXML


        ObservableList<Quiz> listQuiz = FXCollections.observableArrayList();


        @Override
        public void initialize(URL location, ResourceBundle resources) {

            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tauxRColumn.setCellValueFactory((new PropertyValueFactory<>("tauxReussite")));
            nomColumn.setCellFactory(TextFieldTableCell.<Quiz>forTableColumn());
            dateDbQuiz.setCellValueFactory(new PropertyValueFactory<>("dateOuvert"));
            dateFnQuiz.setCellValueFactory(new PropertyValueFactory<>("dateExpir"));
            tauxAColumn.setCellValueFactory(new PropertyValueFactory<>("tauxAccomplissment"));


            tableQuiz.setRowFactory(tv -> {
                TableRow<Quiz> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {

                        Quiz rowData = row.getItem();
                        Date date = new Date();
                        int lawl = date.compareTo(rowData.getDateOuvert());
                        if ((!rowData.getSubmit()) && (lawl <= 0)) {
                            Stage window = new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            window.setTitle("Quiz " + rowData.getNom());
                            Parent root = new Parent() {
                            };
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("repondreQuiz.fxml"));
                            try {
                                root = loader.load();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            RepondreQuiz cQuiz = loader.getController();
                            cQuiz.init((Quiz) rowData);
                            window.setScene(new Scene(root, 400, 300));
                            window.showAndWait();
                            Quiz quizz = cQuiz.getData();
                            this.listQuiz.set(this.listQuiz.indexOf(rowData), quizz);
                            tableQuiz.getColumns().get(0).setVisible(false);
                            tableQuiz.getColumns().get(0).setVisible(true);
                        }
                    }
                });
                return row;
            });

            this.tableQuiz.setItems(this.listQuiz);
        }

        void init(Apprenant apprenant) {
            this.apprenant = apprenant;
            if (!apprenant.getQuizPasEncSoumis().isEmpty()) {

                for (Quiz a : this.apprenant.getQuizPasEncSoumis()) {
                    this.listQuiz.add(a);
                }
            }

            this.tableQuiz.setItems(listQuiz);
            this.matricule.setText(apprenant.getMatricule());
            this.nom.setText(apprenant.getNom());
            this.prenom.setText(apprenant.getPrenom());
            this.adress.setText(apprenant.getAdresse());
            this.login.setText(apprenant.getLogin());
            this.motDePasse.setText(apprenant.getMotDePass());

            Instant instant = Instant.ofEpochMilli(apprenant.getDateNaissance().getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDate localDate = localDateTime.toLocalDate();
            this.dateNaissance.setValue(localDate);

            bienvenu.setText("Bienvnu " + apprenant.getLogin() + "!");

        }

        @FXML
        void getData() {
            String nom = this.nom.getText();
            String prenom = this.prenom.getText();

            String adress = this.adress.getText();

            LocalDate localDatedb = this.dateNaissance.getValue();
            Instant instantdb = Instant.from(localDatedb.atStartOfDay(ZoneId.systemDefault()));
            Date daten = Date.from(instantdb);
            String motDePass = this.motDePasse.getText();
            this.apprenant.setNom(nom);
            this.apprenant.setPrenom(prenom);
            this.apprenant.setAdresse(adress);
            this.apprenant.setDateNaissance(daten);
            this.apprenant.setMotDePass(motDePass);

            ArrayList<Quiz> list = new ArrayList<Quiz>();
            for (Quiz q : this.listQuiz) {
                list.add(q);
            }
            this.apprenant.setQuizPasEncSoumis(list);

            ObjectInputStream inApp = null;
            ObjectOutputStream outApp = null;
            Formation form;
            HashMap<String, Apprenant> apprenants = new HashMap<String, Apprenant>();

            try {
                inApp = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Appreants.dat"))));
                try {

                    form = (Formation) inApp.readObject();
                    apprenants = form.getApprenants();
                    apprenants.remove(this.matricule.getText());
                    apprenants.put(this.matricule.getText(), apprenant);
                    form.setApprenants(apprenants);
                    outApp = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Appreants.dat"), false)));
                    outApp.writeObject(form);
                    if (inApp != null) outApp.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                inApp.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) this.login.getScene().getWindow();

            stage.close();
        }


    }


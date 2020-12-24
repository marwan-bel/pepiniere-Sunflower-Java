/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


import Entities.GestionServices;
import service.ServicePep;
import utils.PostFile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author shmhi
 */
public class AddServiceController {

    @FXML
    private JFXComboBox<String> Servicesatet;
    @FXML
    private JFXComboBox<String> ServiceCity;
    @FXML
    private JFXComboBox<String> servicetype;
    @FXML
    private JFXTextField ServiceName;

    @FXML
    private JFXTextField Serviceemail;
    @FXML
    private JFXTextField Serviceprice;
    @FXML
    private JFXButton addService;
    @FXML
    private JFXTextArea servicedescrip;
    File f;

    GestionServices s;
    @FXML
    private ImageView simageview;
    @FXML
    private JFXButton ServiceImage;
    @FXML
    private JFXRadioButton Daily;
    @FXML
    private ToggleGroup bb;
    @FXML
    private JFXRadioButton Monthly;
    @FXML
    private JFXRadioButton Weekly;

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
        private boolean isValidEmail()
{
      Pattern p = Pattern.compile("[_A-Za-z0-9][A-Za-z0-9._]*?@[_A-Za-z0-9]+([.][_A-Za-z]+)+");
        Matcher m = p.matcher(Serviceemail.getText());
    if (m.find() && m.group().equals(Serviceemail.getText()))
    {
      
        return true ;
    } 

 
    return false;
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (ServiceName.getText() == null || ServiceName.getText().length() == 0) {
            errorMessage += "No valid  name!\n";
        }
       
        if (!isValidEmail()) {
            errorMessage += "No valid Mail Forment!\n";
        }
        if (servicedescrip.getText() == null || servicedescrip.getText().length() == 0) {
            errorMessage += "No valid description !\n";
        }
        if (f == null && s.getImage() == null) {
            errorMessage += "No valid photo ( please upload a photo !)\n";
        }
        if (Serviceprice.getText() == null || Serviceprice.getText().length() == 0) {
            errorMessage += "No valid prix !\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Float.parseFloat(Serviceprice.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid  prix (must be a Float)!\n";
            }
        }
        if (!Monthly.isSelected() && !Daily.isSelected() && !Weekly.isSelected()) {
            errorMessage += "No valid Etat ( select an item !)\n";
        }
        if (Servicesatet.getSelectionModel().getSelectedIndex() == -1) {
            errorMessage += "No valid state ( select an item !)\n";
        }
        if (ServiceCity.getSelectionModel().getSelectedIndex() == -1) {
            errorMessage += "No valid City ( select an item !)\n";
        }
        if (servicetype.getSelectionModel().getSelectedIndex() == -1) {
            errorMessage += "No valid Type ( select an item !)\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    public void uploadPhoto() throws MalformedURLException, Exception {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            String imageFile = selectedFile.toURI().toURL().toString();
            this.f = selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
            Image img = new Image(imageFile);
            simageview.setImage(img);
        }
    }

    private String whichcheck() {
        String l = "";
        if (Weekly.isSelected()) {
            l = "weekly";
        }
        if (Daily.isSelected()) {
            l = "daily";
        }
        if (Monthly.isSelected()) {
            l = "monthly";
        }
        return l;
    }

    private void ajot(GestionServices gts) throws Exception {

        if (isInputValid()) {
            s= new GestionServices();
            s.setNom_service(ServiceName.getText());
            s.setPrix(Integer.valueOf(Serviceprice.getText()));
            s.setEtat(whichcheck());
            s.setType(servicetype.getSelectionModel().getSelectedItem());
            s.setRegion(ServiceCity.getSelectionModel().getSelectedItem());
            s.setDescription(servicedescrip.getText());
            s.setContact(Serviceemail.getText());
            
            if (f != null) {
                s.setImage(PostFile.upload(f.getAbsolutePath()));
            }
            ServicePep sp = new ServicePep();
            sp.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText("Ajout confirmer");
            alert.showAndWait();
            ServiceName.getScene().getWindow().hide();
        }

    }

    @FXML
    private void SAjouter(ActionEvent event) throws Exception {
        GestionServices gts = new GestionServices();
        ajot(gts);
    }

    private void region() {
        ObservableList<String> listregion = FXCollections.observableArrayList(
                "Ariana",
                "Bizert",
                "Ben arous",
                "Beja",
                "Gabes",
                "Gafsa",
                "Jandouba",
                "Kassrine",
                "Kairouan",
                "Kebeli",
                "Kef",
                "Mahdia",
                "Manouba",
                "Monastir",
                "Medinin",
                "Nabeul",
                "Sfax",
                "Siliana",
                "Sidi bouzid",
                "Sousse",
                "Tataouin",
                "Touzer",
                "Tuins",
                "Zaghouan"
        );
        Servicesatet.setItems(listregion);

    }

    private void choixregion() {
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Ariana")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "Ariana Ville",
                    "Ettadhamen",
                    "Kalâat el-Andalous",
                    "La Soukra",
                    "Mnihla",
                    "Raoued",
                    "Sidi Thabet"
            );
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Bizert")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "Bizerte Nord",
                    "El Alia",
                    "Ghar El Melh",
                    "Ghezala",
                    "Joumine",
                    "Mateur",
                    "Menzel Bourguiba",
                    "Menzel Jemil",
                    "Ras Jebel",
                    "Sejnane",
                    "Tinja",
                    "Utique",
                    "Zarzouna"
            );
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Ben arous")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("ben arous", "rades", "boumhal", "hamamlanf", "morouj");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Beja")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("beja", "mjez el beb");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Gabes")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("gabes", "chneni", "ghanouch", "matouia", "tbilbou", "matmata");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Gafsa")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("gafsa", "metlaoui", "elgssour");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Jandouba")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("jandouba", "tbarka", "ain drahem");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Kassrine")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("kasrine", "sbitla");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Kairouan")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "KAIROUAN",
                    "SBIKHA",
                    "BENSALEM",
                    "AIN JELOULA",
                    "MENZEL MHIRI",
                    "SIDI SAAD",
                    "CHERARDA",
                    "OUSSELTIA",
                    "CHEBIKA",
                    "CITE EL HAJJEM",
                    "HAFFOUZ",
                    "KAIROUAN SUD",
                    "SISSEB",
                    "EL KARMA",
                    "KAIROUAN OKBA",
                    "EL BATEN",
                    "EL ALA",
                    "BIR AHMED",
                    "HAJEB EL AYOUN",
                    "NASRALLAH",
                    "KAIROUAN MEDINA",
                    "BOUHAJLA",
                    "CITE ENNASR",
                    "RAKKADA",
                    "EL BORJ",
                    "CITE IBN EL JAZZAR"
            );
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Kebeli")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("kbeli", "janoura", "souk lahad", "douz");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Kef")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("kef", "ghar dema");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Mahdia")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("mahdia", "ksour sef", "karkar", "cheba", "eljem");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Manouba")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("manouba", "jdayda");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Monastir")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("monastir", "saline", "moknin", "kasr hlel", "tbolba", "lamta", "sayada", "bouhjar", "jamel", "ksibet el madiouni");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Medinin")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("djerba", "zerziss", "medini");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Nabeul")) {
            ObservableList<String> listregion = FXCollections.observableArrayList("nabeul", "hammamet", "grambalia", "haouaria", "korba", "klibia");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Sfax")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "sfax",
                    "mahress",
                    "sakiet zit",
                    "karknah",
                    "sakiet edir",
                    "jbeniana");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Siliana")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "siliana",
                    "bargou",
                    "kessra");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Sidi bouzid")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "sidi bouzid",
                    "...");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Sousse")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "SOUSSE",
                    "BOU FICHA",
                    "HAMMAM SOUSSE",
                    "HERGLA",
                    "MESSAIDINE",
                    "EL KANAISS",
                    "EL BORJINE",
                    "BENI KELTHOUM",
                    "HAMM SOUSSE GHARBIE",
                    "KONDAR",
                    "KALAA ESSEGHIRA",
                    "AKOUDA",
                    "SOUSSE ERRIADH",
                    "MSAKEN ",
                    "SIDI EL HANI",
                    "SOUSSE BOUHSINA",
                    "ENFIDHA",
                    "SOUSSE EZZOUHOUR",
                    "MENZEL BELOUAER ",
                    "MOUREDDINE",
                    "CHEGARNIA",
                    "AIN EL GARCI",
                    "SOUSSE SOUK LAHAD",
                    "SIDI BOU ALI",
                    "KSIBET SOUSSE",
                    "CHATT MERIAM",
                    "SOUSSE KHEZAMA",
                    "SAHLOUL",
                    "SOUSSE CORNICHE",
                    "KALAA KEBIRA",
                    "KALAA KEBIRA",
                    "MSAKEN",
                    "ZAOUIET SOUSSE",
                    "EL KANTAOUI"
            );
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Tataouin")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "TATAOUINE",
                    "BIR LAHMAR",
                    "KSAR OUN",
                    "GHOUMRASSEN ",
                    "KSAR MGABLA",
                    "SMAR ",
                    "KIRCHAOU ",
                    "DOUIRET",
                    "TATAOUINE EL MAHRAJENE",
                    "REMADA",
                    "EL FERCH",
                    "KSAR DEBBEB",
                    "OUED EL GHAR",
                    "KSAR EL MOURABITINE",
                    "MAZTOURIA",
                    "DEHIBAT",
                    "KSAR EL HADADA",
                    "BENI MEHIRA ",
                    "TATAOUINE ETTAHRIR",
                    "GUERMASSA",
                    "EZZAHRA TATAOUINE",
                    "KSAR OULED SOLTAN",
                    "BIR THLATHINE",
                    "ROGBA");
            ServiceCity.getItems().clear();

            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Touzer")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "Touze",
                    "chat jrid",
                    "nafta");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Tuins")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "lac",
                    "marsa",
                    "la goulette",
                    "carthage",
                    "sidi bou said",
                    "medina",
                    "centre ville");
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }
        if (Servicesatet.getSelectionModel().getSelectedItem().equals("Zaghouan")) {
            ObservableList<String> listregion = FXCollections.observableArrayList(
                    "zagouane",
                    "zriba",
                    "jdidi"
            );
            ServiceCity.getItems().clear();
            ServiceCity.setItems(listregion);

        }

    }

    public void initialize() {
        servicetype.getItems().add("Service Agricole");
        servicetype.getItems().add("Service jardinage");
        servicetype.getItems().add("Autre Service");
        region();
        Servicesatet.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            choixregion();
        });
        whichcheck();

    }
}

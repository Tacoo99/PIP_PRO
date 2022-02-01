package client.ConfirmOrder;

import client.ChooseRent.ChooseRentController;
import client.ChooseRent.LaptopController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ConfirmOrderController implements Initializable{

    Connection con;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ConfirmOrderController() {
        con = ConnectionUtil.conDB();
    }

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Date today;
    Date date;

    @FXML
    private Label loggedUser;
    @FXML
    private Label errorLbl;
    @FXML
    private TextField txtCPU;
    @FXML
    private DatePicker txtDataDo;
    @FXML
    private TextField txtDataWypoz;
    @FXML
    private TextField txtDysk;
    @FXML
    private TextField txtEkran;
    @FXML
    private TextField txtGPU;
    @FXML
    private TextField txtGodzWypoz;
    @FXML
    private TextField txtImie;
    @FXML
    private TextField txtKod;
    @FXML
    private TextField txtMiasto;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtNazwisko;
    @FXML
    private TextField txtProducent;
    @FXML
    private TextField txtRAM;
    @FXML
    private TextField txtSN;
    @FXML
    private TextField txtUlica;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button BackMenu;
    @FXML
    private Button confirmButton;

    String SN,CurrUSer, producent, model, cpu, gpu,ram, dysk,ekran;
    String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    String successStyle = "-fx-border-color: #d4d4d4;";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        date = new Date();
        String data = dateFormat.format(date);
        System.out.println(data);
        txtDataWypoz.setText(dateFormat.format(date));


        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date hour = new Date();
        String godzina = hourFormat.format(hour);
        System.out.println(godzina);

        txtGodzWypoz.setText(godzina);

    }

    public void setSN(String sn){
        this.SN = sn;
        System.out.println(SN);
        txtSN.setText(SN);
    }
    public void setUsername(String user){
        this.CurrUSer = user;
        System.out.println(CurrUSer);
        txtUsername.setText(CurrUSer);
        loggedUser.setText(CurrUSer);
    }
    public void setProducent(String text){
        this.producent = text;
        System.out.println(producent);
        txtProducent.setText(producent);
    }
    public void setModel(String text){
        this.model = text;
        System.out.println(model);
        txtModel.setText(model);
    }
    public void setCPU(String text){
        this.cpu = text;
        System.out.println(cpu);
        txtCPU.setText(cpu);
    }
    public void setGPU(String text){
        this.gpu = text;
        System.out.println(gpu);
        txtGPU.setText(gpu);
    }
    public void setRAM(String text){
        this.ram = text;
        System.out.println(ram);
        txtRAM.setText(ram);
    }
    public void setDysk(String text){
        this.dysk = text;
        System.out.println(dysk);
        txtDysk.setText(dysk);
    }
    public void setEkran(String text){
        this.ekran = text;
        System.out.println(ekran);
        txtEkran.setText(ekran);
    }


    @FXML
    void Cancel(MouseEvent event){

        try{

            Stage stageOld = (Stage) BackMenu.getScene().getWindow();
            stageOld.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ChooseRent/Laptop.fxml"));
            Parent root = loader.load();

            LaptopController scene2Controller = loader.getController();
            scene2Controller.setUser(CurrUSer);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/images/icons8_file_settings_128px.png"));
            stage.setTitle("Panel klienta - wybierz kategorię");
            stage.show();


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void CategoryBack(MouseEvent event) {

        try{

            Stage stageOld = (Stage) BackMenu.getScene().getWindow();
            stageOld.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ChooseRent/ChooseRent.fxml"));
            Parent root = loader.load();

            ChooseRentController scene2Controller = loader.getController();
            scene2Controller.setUser(CurrUSer);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/images/icons8_file_settings_128px.png"));
            stage.setTitle("Panel klienta - wybierz kategorię");
            stage.show();


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void setLblError(){
        errorLbl.setTextFill(Color.TOMATO);
        errorLbl.setText("Uzupełnij wszystkie pola!");
    }

    private void setlblSuccess(){
        errorLbl.setTextFill(Color.GREEN);
        errorLbl.setText("Wszystko jest w porządku :)!");
    }

    @FXML
    private void confirmOrder() {

        boolean status = false;

        if (txtImie.getText().isEmpty()) {
            txtImie.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtImie.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        if (txtNazwisko.getText().isEmpty()) {
            txtNazwisko.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtNazwisko.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        if (txtMiasto.getText().isEmpty()) {
            txtMiasto.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtMiasto.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        //-----------------------------------------------------------------------

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String chosen_date = txtDataDo.getValue().toString();

        try {
            today = sdf.parse(chosen_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //------------------------------------------------------------------------

        if ( txtDataDo.getValue() == null) {
            txtDataDo.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtDataDo.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        if (txtUlica.getText().isEmpty()) {
            txtUlica.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtUlica.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        if (txtKod.getText().isEmpty()) {
            txtKod.setStyle(errorStyle);
            setLblError();
            status = false;
        } else {
            txtKod.setStyle(successStyle);
            setlblSuccess();
            status = true;
        }

        if( today.compareTo(date) > 0 ){

            status = false;
            txtDataDo.setStyle(errorStyle);
            setLblError();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił błąd");
            alert.setHeaderText(null);
            alert.setContentText("Wybrałeś złą datę!");
            alert.showAndWait();
        }



        if (status) {

            System.out.println("Ustawiam wartość 'dostepny' tego sprzętu na 0");

            String SQL = "UPDATE stock set Dostepny = 0 WHERE SN = ?";

            try {
                preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setString(1, SN);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                String st = "INSERT INTO pendingorders ( klient, sn_sprzetu, data_zamowienia, godzina_zamowienia, do_kiedy ) VALUES ( ?,?,?,?,? )";
                preparedStatement = con.prepareStatement(st);
                preparedStatement.setString(1, loggedUser.getText() );
                preparedStatement.setString(2, txtSN.getText() );
                preparedStatement.setString(3, txtDataWypoz.getText());
                preparedStatement.setString(4, txtGodzWypoz.getText());
                preparedStatement.setString(5, txtDataDo.getValue().toString());

                preparedStatement.executeUpdate();


            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Potwierdzenie zamówienia");
            alert.setHeaderText(null);
            alert.setContentText("Zamówienie zostało złożone, damy Ci znać jak tylko potwierdzimy twój zakup");

            alert.showAndWait();

        }

    }
}


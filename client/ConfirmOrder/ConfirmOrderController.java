package client.ConfirmOrder;

import client.ChooseRent.ChooseRentController;
import client.ChooseRent.LaptopController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ConfirmOrderController implements Initializable{

    @FXML
    private Label loggedUser;
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

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
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

    @FXML
    private void confirmOrder(){

        if( txtImie.getText().isEmpty() ){
            txtImie.setStyle(errorStyle);
        }
        else{
            txtImie.setStyle(successStyle);
        }

        if( txtNazwisko.getText().isEmpty() ){
            txtNazwisko.setStyle(errorStyle);
        }
        else{
            txtNazwisko.setStyle(successStyle);
        }

        if( txtMiasto.getText().isEmpty() ){
            txtMiasto.setStyle(errorStyle);
        }
        else{
            txtMiasto.setStyle(successStyle);
        }

        if( txtDataDo.getValue() != null){
            txtDataDo.setStyle(errorStyle);
        }
        else{
            txtDataDo.setStyle(successStyle);
        }

        if( txtUlica.getText().isEmpty() ){
            txtUlica.setStyle(errorStyle);
        }
        else{
            txtUlica.setStyle(successStyle);
        }

        if( txtKod.getText().isEmpty() ){
            txtKod.setStyle(errorStyle);
        }
        else{
            txtKod.setStyle(successStyle);
        }

    }


}
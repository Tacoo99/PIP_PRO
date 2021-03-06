package admin.add_device;

import product.productClass;

import java.sql.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.ConnectionUtil;


public class AddDeviceController implements Initializable {


    @FXML
    private TextField txtId;

    @FXML
    private Label lblRefresh;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lbl_close;

    @FXML
    private TableView<ObservableList> tblData;

    @FXML
    private TextField txtCPU;

    @FXML
    private TextField txtDysk;

    @FXML
    private TextField txtEkran;

    @FXML
    private TextField txtGPU;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtProducent;

    @FXML
    private TextField txtRAM;

    @FXML
    private TextField txtSN;


    @FXML
    private ComboBox<String> txtTyp;


    private double x,y;

    PreparedStatement preparedStatement;
    Connection con;

    public AddDeviceController() {
        con =  ConnectionUtil.conDB();
    }


    @FXML
    public void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


    @FXML
    public void minimize(MouseEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    public void handleCloseWindow() {
            Stage stage = (Stage) lbl_close.getScene().getWindow();
            stage.close();
    }


    @FXML
    void tblRefresh() {
        tblData.getItems().clear();
        fetRowList();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        lblRefresh.setTextFill(Color.GREEN);
        lblRefresh.setText("Od??wie??ono tabl??: "+ dtf.format(now));
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtTyp.getItems().addAll(
                "Laptop",
                "Tablet",
                "Telefon"
        );
        fetColumnList();
        fetRowList();

    }

    @FXML
    private void HandleEvents() {



        if ( txtId.getText().isEmpty() || txtSN.getText().isEmpty() || txtModel.getText().isEmpty() || txtProducent.getText().isEmpty() || txtTyp.getValue() == null
        || txtCPU.getText().isEmpty() || txtGPU.getText().isEmpty() || txtRAM.getText().isEmpty() || txtDysk.getText().isEmpty() || txtEkran.getText().isEmpty() )

        {

            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("B????d! : Podaj wszystkie dane");
        }

        else {


            tblData.getItems().clear();
            saveData();
            fetRowList();

        }
    }

    private void clearFields() {
        for (TextField textField : Arrays.asList( txtId, txtSN, txtModel, txtProducent, txtCPU, txtGPU, txtEkran, txtRAM, txtDysk )) {
            textField.clear();
        }
        txtTyp.setSelectionModel(null);
    }

    private void saveData() {

            productClass obj = new productClass();

            obj.id = txtId.getText();
            obj.sn = txtSN.getText();
            obj.producent = txtProducent.getText();
            obj.model = txtModel.getText();
            obj.typ = txtTyp.getValue();
            obj.cpu = txtCPU.getText();
            obj.gpu = txtGPU.getText();
            obj.ram = txtRAM.getText();
            obj.dysk = txtDysk.getText();
            obj.ekran = txtEkran.getText();
            obj.dostepny = "1";

        try {
            String st = "INSERT INTO stock ( ID, SN, Producent, Model, Typ, CPU, GPU, RAM, Dysk, Ekran, Dostepny) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(st);
            preparedStatement.setString(1, obj.id);
            preparedStatement.setString(2, obj.sn);
            preparedStatement.setString(3, obj.producent);
            preparedStatement.setString(4, obj.model);
            preparedStatement.setString(5, obj.typ);
            preparedStatement.setString(6, obj.cpu);
            preparedStatement.setString(7, obj.gpu);
            preparedStatement.setString(8, obj.ram);
            preparedStatement.setString(9, obj.dysk);
            preparedStatement.setString(10, obj.ekran);
            preparedStatement.setString(11,obj.dostepny);

            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Dodano pomy??lnie");


            fetRowList();
            clearFields();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
        }
    }

    String SQL = "SELECT * from stock";

    //only fetch columns
    private void fetColumnList() {

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);


            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory((Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);


            }

        } catch (Exception e) {
            System.out.println("B????d " + e.getMessage());

        }
    }

    //fetches rows and data from the list
    private void fetRowList() {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
package admin.approve_rent;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;


public class ApproveRentController {

    @FXML
    private Button approveBtn, rejectBtn;
    @FXML
    Label lbl_close;

    private String id_zamowienia;

    double x, y;
    PreparedStatement preparedStatement;

    private ObservableList<ObservableList> data;

    @FXML
    private TableView<ObservableList> tblData;

    @FXML
    public void handleExitAction() {
        Stage stage = (Stage) lbl_close.getScene().getWindow();
        stage.close();
    }

    Connection con;

    public ApproveRentController() {
        con = ConnectionUtil.conDB();
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
    public void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void getAll() {
        tblData.getItems().clear();
        data = FXCollections.observableArrayList();

        data.clear();


        String SQL = "SELECT * FROM pendingorders";

        try {

            preparedStatement = con.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tblData.getColumns().removeAll();
                tblData.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            tblData.setItems(data);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void query(String sql){

        try {

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id_zamowienia);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setAlert(Alert.AlertType type, String title, String content){

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void approve(MouseEvent event) {

        ObservableList test = tblData.getSelectionModel().getSelectedItem();

        if (!test.isEmpty()) {

            id_zamowienia = (String) test.get(2);
            System.out.println("Wybrano zamowienie o ID: " + id_zamowienia);

            if (event.getSource() == approveBtn) {
                String SQL2 = "INSERT INTO orders SELECT * FROM pendingorders WHERE sn_sprzetu = ?";
                query(SQL2);

                String SQL = "DELETE FROM pendingorders WHERE sn_sprzetu = ?";
                query(SQL);

                setAlert(Alert.AlertType.INFORMATION,"Sukces", "Zamówienie zostało potwierdzone");

            } else if (event.getSource() == rejectBtn) {
                String SQL3 = "DELETE FROM pendingorders WHERE sn_sprzetu = ?";
                query(SQL3);
                setAlert(Alert.AlertType.WARNING,"Sukces", "Zamówienie zostało odrzucone");

            }
            else {
                setAlert(Alert.AlertType.ERROR,"Wystąpił błąd!", "Wybierz zamówienie do potwierdzenia!");
            }

        }

    }

}


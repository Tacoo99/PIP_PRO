package client.OrderHistory;

import client.MainPage.MainPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderHistoryController{

    private ObservableList<ObservableList> data;
    @FXML
    private TableView<ObservableList> tblData;

    Connection con;
    PreparedStatement preparedStatement;
    String LoggedUser;


    @FXML
    private Label currUser;
    @FXML
    private TextField ordersText;

    @FXML
    private TextField sumText;

    public OrderHistoryController() {
        con = ConnectionUtil.conDB();
    }

    @FXML
    void onBackButton(MouseEvent event) {

        try{

            Node node = (Node) event.getSource();
            Stage stageHistory = (Stage) node.getScene().getWindow();
            stageHistory.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage/MainPage.fxml"));
            Parent root = loader.load();

            MainPageController scene2Controller = loader.getController();
            scene2Controller.setLogin(currUser.getText());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/images/icons8_file_settings_128px.png"));
            stage.setTitle("Panel klienta - historia zamówień");
            stage.show();


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    String SQL = "SELECT id, sn_sprzetu, data_zamowienia, godzina_zamowienia, do_kiedy FROM orders WHERE klient = ?";
    String SQL2 = "SELECT id, sn_sprzetu, data_zamowienia, godzina_zamowienia, do_kiedy FROM pendingorders WHERE klient = ?";
    int orders = 0;

    @FXML
    void getAcceptedList(){

        data = FXCollections.observableArrayList();
        clearTable();


        try {

            preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, currUser.getText());

            ResultSet rs = preparedStatement.executeQuery();


            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tblData.getColumns().addAll(col);
            }

            while (rs.next()) {
                orders++;
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

        ordersText.setText(String.valueOf(orders));

    }

    private void clearTable(){
        tblData.getItems().clear();
        data.removeAll();
        tblData.setItems(data);
    }

    int orders2 = 0;
    @FXML
    void getAwaitingList(){

        clearTable();

        data = FXCollections.observableArrayList();

        try {

            preparedStatement = con.prepareStatement(SQL2);
            preparedStatement.setString(1, currUser.getText());

            ResultSet rs = preparedStatement.executeQuery();


            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tblData.getColumns().addAll(col);
            }

            while (rs.next()) {
                orders2++;
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

        ordersText.setText(String.valueOf(orders2));
    }


    public void setLogin(String login){
        LoggedUser = login;
        currUser.setText(LoggedUser);

    }

}

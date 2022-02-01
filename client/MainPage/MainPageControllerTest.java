package client.MainPage;

import client.ChooseRent.ChooseRentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import utils.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class MainPageControllerTest {

    Connection con;

    public MainPageControllerTest() {
        con = ConnectionUtil.conDB();
    }

    @Test
    void openRent() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ChooseRent/ChooseRent.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/images/icons8_file_settings_128px.png"));
            stage.setTitle("Panel klienta - dodaj zamówienie");
            stage.show();


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    void OpenMyAccount() {
        String user = "klient";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT email, name, surname, acc_creation FROM clients WHERE login = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("surname"));
                System.out.println(resultSet.getString("acc_creation"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
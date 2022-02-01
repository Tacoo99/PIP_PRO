package client.Home;

import org.junit.jupiter.api.Test;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class Home_ClientControllerTest {

    Connection con;

    public Home_ClientControllerTest() {
        con = ConnectionUtil.conDB();
    }

    @Test
    void onLoginButtonClick() {

        String login, password;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM clients Where login = ? and password = ?";

        try {
            login = "klient";
            password = "klient";

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();


            if (!resultSet.next()) {
                System.out.println("Niepoprawny login lub hasło!");

            } else {
                System.out.println("Podane dane są prawidłowe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
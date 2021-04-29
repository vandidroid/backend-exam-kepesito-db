package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    public List<String> checkOverpopulation() {
        List<String> dinos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT breed FROM dinosaur WHERE actual - expected > 0 ORDER BY breed ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dinos.add(resultSet.getString("breed"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dinos;
    }

}

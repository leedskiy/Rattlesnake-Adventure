package rattlesnakeadventure.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        String url = "jdbc:mysql://localhost:3306/highestscore_schema";
        String user = "root";
        String pass = readPassFromCfgFile();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String readPassFromCfgFile() {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            return properties.getProperty("db.password"); // gets password from "config.properties" file
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveScore(String playerName, int playerScore) {
        String query = "INSERT INTO players_score (playername, playerscore) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, playerScore);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readTop10Players() {
        ArrayList<String> topPlayers = new ArrayList<>();

        String query = "SELECT playername, playerscore FROM players_score ORDER BY playerscore DESC LIMIT 10";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String playerName = resultSet.getString("playername");
                int playerScore = resultSet.getInt("playerscore");
                String playerInfo = playerName + " " + playerScore;
                topPlayers.add(playerInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topPlayers;
    }
}
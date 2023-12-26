package rattlesnakeadventure.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public DatabaseManager() {
        String url = "jdbc:mysql://localhost:3306/highestscore_schema";
        String user = "root";
        String pass = readPassFromCfgFile();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, playerScore);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
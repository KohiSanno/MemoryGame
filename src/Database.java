import java.sql.*;
import java.util.ArrayList;

public class Database {
    //connection used to establish a connection with the database
    private Connection conn = null;

    public Database() {
        // Constructor where the database connection is established
        connect();
    }

    private void connect() {
        // Actual connection setup code
        try {
            // MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/scores", "root", "Kohiko10-Hiro0713");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void storeHighScore(String name, int score) {
        // insert the name and score into the highscores table.
        String SQL = "INSERT INTO highscores (name, score) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // Sets the first parameter (1) in the query to the player's name.
            pstmt.setString(1, name);
            // Sets the second parameter (2) in the query to the player's score.
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Defines the getHighScores method, which returns an ArrayList of HighScore objects.
    public ArrayList<HighScore> getHighScores() {

        ArrayList<HighScore> highScores = new ArrayList<>();
        // Defines the SQL statement to retrieve the top 10 high scores.
        String SQL = "SELECT name, score FROM highscores ORDER BY score DESC LIMIT 10";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                // Constructing HighScore object with name and the highest level achieved
                highScores.add(new HighScore(rs.getString("name"), rs.getInt("score")));
            }
        } catch (SQLException ex) {
            System.out.println("getHighScores error");
            System.out.println(ex.getMessage());
        }
        return highScores;
    }
}
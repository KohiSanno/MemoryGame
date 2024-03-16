import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;

public class MemoryGame extends JFrame {
    private int currentLevelIndex = 0; //keep track of the current level index
    private GameBoard gameBoard;
    Connection conn = null;
    ResultSet rs = null;
    private JLabel timerLabel; // JLabel to display elapsed time
    private int secondsElapsed = 0; // Counter for the elapsed seconds
    private Timer timer; // Swing timer

    private JButton giveUpButton; // Declare the Give Up button
    private int highestLevelAchieved = 0;

    private Database database;

    public MemoryGame() {

        database = new Database();
        database.storeHighScore(String.valueOf(NameWindow.nameTextField), highestLevelAchieved);
        giveUpButton = new JButton("Give Up");
        giveUpButton.addActionListener(e -> giveUp());
        this.add(giveUpButton, BorderLayout.SOUTH);

        timerLabel = new JLabel("Time: 0");
        //Changing stuff
        this.add(timerLabel, BorderLayout.NORTH); // Adds the timer label to the top of the JFrame

        // Timer setup
        timer = new Timer(1000, new ActionListener() { // ActionListener that will update every second
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++; // Increment the number of seconds
                timerLabel.setText("Time: " + secondsElapsed); // Update the label
            }
        });
        timer.start(); // Start the timer when the game starts



        // Menu setup: Configures a menu item to show high scores.
        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("High Scores") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch and display high scores
                ArrayList<HighScore> highScores = database.getHighScores();
                new HighScoreWindow(highScores, MemoryGame.this); // Pass the fetched high scores to the HighScoreWindow
            }
        });


        //This block creates a menu item labeled "High Scores". When clicked, it fetches high scores from the database and displays them in a new HighScoreWindow.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options"); // Create a new menu or use an existing one
        menu.add(menuHighScores); // Add the high score menu item
        menuBar.add(menu); // Add the menu to the menu bar
        this.setJMenuBar(menuBar);


        initializeControls();


        gameBoard = new GameBoard(Level.ALL_LEVELS[0], this); // Initialize gameBoard with the first level
        add(gameBoard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Memory Game");
    }


    private void initializeControls() {
        giveUpButton = new JButton("Give Up");
        giveUpButton.addActionListener(e -> giveUp());
        this.add(giveUpButton, BorderLayout.SOUTH);

        timerLabel = new JLabel("Time: 0");
        this.add(timerLabel, BorderLayout.NORTH); // Adds the timer label to the top of the JFrame

        timer = new Timer(1000, e -> {
            secondsElapsed++; // Increment the number of seconds
            timerLabel.setText("Time: " + secondsElapsed); // Update the label
        });
        timer.start(); // Start the timer when the game starts
    }


    private void giveUp() {
        int currentLevel = currentLevelIndex + 1; // Assuming level index starts from 0
        highestLevelAchieved = Math.max(highestLevelAchieved, currentLevel);


        JOptionPane.showMessageDialog(this, "You gave up! Your highest level achieved: " + highestLevelAchieved);
        resetGame(); // Define this method to reset the game
    }

    private void resetGame() {
        database.storeHighScore(NameWindow.nameTextField.getText(), highestLevelAchieved);
        // Implement this method to reset the game to its initial state
        currentLevelIndex = 0; // Resetting level index to 0
        // Possibly reset other game state variables and update the game board
        gameBoard.setLevel(Level.ALL_LEVELS[0]); // Reset the game board to the first level
        secondsElapsed = 0; // Reset the timer
        timerLabel.setText("Time: 0"); // Reset the timer label
    }

    // Method to load the next level
    public void loadNextLevel() {
            currentLevelIndex++;
            //System.out.println(currentLevelIndex);
            if (currentLevelIndex < Level.ALL_LEVELS.length) { // Check if more levels are available

                gameBoard.setLevel(Level.ALL_LEVELS[currentLevelIndex]);
                JOptionPane.showMessageDialog(this, "Welcome to Level " + (currentLevelIndex + 1));
            } else {
                JOptionPane.showMessageDialog(this, "All levels completed! Restarting game.");
                currentLevelIndex = 0;
                gameBoard.setLevel(Level.ALL_LEVELS[currentLevelIndex]); // Reset to first level
            }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NameWindow();
            MemoryGame game = new MemoryGame();
            game.setVisible(true);
        });
    }
}

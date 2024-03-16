import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard extends JPanel {
    private Tile[] tiles;
    private MemoryGame game;
    private List<Tile> selectedTiles = new ArrayList<>();
    private boolean isProcessing = false; // To control tile selection during reveal/hide transitions

    public GameBoard(Level level, MemoryGame game) {
        this.game = game;
        setLevel(level);
    }


    public void setLevel(Level level) {
        removeAll();
        setLayout(new GridLayout(level.getGridSize(), level.getGridSize()));
//        tiles = new Tile[level.getGridSize() * level.getGridSize()];

        List<Character> chars = new ArrayList<>();
        System.out.println("The length of character array in level: " + level.getCharacters().length);
        for (char c : level.getCharacters()) {
            chars.add(c);
            chars.add(c); // Each character appears twice
        }
        Collections.shuffle(chars);
        System.out.println("Length of char list: " + chars.size());

        tiles = new Tile[chars.size()];
        for (int i = 0; i < tiles.length; i++) {
            final Tile tile = new Tile(chars.get(i));
            tile.addActionListener(e -> selectTile(tile));
            tiles[i] = tile;
            add(tile);
        }

        for (Tile tile : tiles) {
            System.out.println(tile.getContent());
        }
        revalidate();
        repaint();
    }



    private void selectTile(Tile tile) {
        if (!isProcessing && !tile.isMatched() && selectedTiles.size() < 2 && !selectedTiles.contains(tile)) {
            tile.revealContent();
            selectedTiles.add(tile);
            if (selectedTiles.size() == 2) {
                isProcessing = true; // Prevent further selections
                checkMatch();
            }
        }
    }

    private void checkCompletion() {
        for (Tile tile : tiles) {
            if (!tile.isMatched()) {
                return; // Found an unmatched tile, so not all tiles are matched yet
            }
        }
        // If this point is reached, all tiles are matched
        game.loadNextLevel(); // Proceed to next level
    }

    private void checkMatch() {
        if (selectedTiles.size() == 2) {
            Tile firstTile = selectedTiles.get(0);
            Tile secondTile = selectedTiles.get(1);

            if (firstTile.getContent() == secondTile.getContent()) {
                firstTile.setMatched(true);
                secondTile.setMatched(true);
                firstTile.revealContent(false);
                secondTile.revealContent(false);
            } else {
                Timer timer = new Timer(1000, e -> {
                    firstTile.revealContent(false);
                    secondTile.revealContent(false);
                    selectedTiles.clear(); // Clear selections after flipping back
                    isProcessing = false; // Allow further selections
                });
                timer.setRepeats(false);
                timer.start();
                return; // Return early to avoid clearing the list and resetting isProcessing too soon
            }
            selectedTiles.clear(); // Clear selections for the next try
            isProcessing = false; // Allow further selections
            checkCompletion(); // Check if the game is complete after every match attempt
        }
    }

}

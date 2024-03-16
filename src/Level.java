public class Level {
    private int gridSize;
    private char[] characters;
    private int flipLimit; // The number of tiles that can be flipped at once

    public Level(int gridSize, char[] characters, int flipLimit) {
        this.gridSize = gridSize;
        this.characters = characters;
        this.flipLimit = flipLimit;
    }

    public int getGridSize() { return gridSize; }
    public char[] getCharacters() { return characters; }
    public int getFlipLimit() { return flipLimit; }

    // Define the levels as required
    public static final Level[] ALL_LEVELS = new Level[] {
            new Level(2, new char[]{'A', 'B'}, 2),
            new Level(2, new char[]{'1','2'}, 2),
            new Level(3, new char[]{'D','E','F'}, 2),
            new Level(3, new char[]{'3','4','5'}, 2),
            new Level(3, new char[]{'X','Y','Z'}, 2),
            new Level(4, new char[]{'A','B','C','D','E','F','G','H'}, 2),
            new Level(4, new char[]{'1','2','3','4','5','6','7','8'}, 2),
            new Level(4, new char[]{'I','J','K','L','M','N','O','P'}, 2),
            new Level(4, new char[]{'1','2','3','4','5','6','7','8'}, 2),
            new Level(5, new char[]{'A','B','C','D','E'}, 5),

    };
}

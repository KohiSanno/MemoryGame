import java.util.Objects;

public class HighScore {
    private final String name;
    private final int score;

    public HighScore(String name, int score){
        this.name = name;
        this.score = score;
    }

    // Getter method for name
    public String getName() {
        return this.name;
    }

    // Getter method for score
    public int getScore() {
        return this.score;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.score);
        hash = 89 * hash + this.score;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HighScore other = (HighScore) obj;
        if (this.score != other.score) {
            return false;
        }
        return true;
    }
}


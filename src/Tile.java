import javax.swing.*;

public class Tile extends JButton {
    private char content;
    private boolean isMatched = false;

    public Tile(char content) {
        super(" ");
        this.content = content;
        addActionListener(e -> revealContent());
    }

    public void revealContent() {
        if (!isMatched) {
            setText(String.valueOf(content));
        }
    }

    public void revealContent(boolean reveal) {
        if (!isMatched) {
            setText(reveal ? String.valueOf(content) : " ");
        }
    }


    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        if (matched) {
            // disable the button to indicate it's matched
            setEnabled(false);
        }
    }

    public char getContent() {
        return content;
    }
}

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class HighScoreTableModel extends AbstractTableModel{
    private final ArrayList<HighScore> highScores;
    private final String[] colName = new String[]{ "Player Name", "Score" }; // Updated column names

    public HighScoreTableModel(ArrayList<HighScore> highScores){
        this.highScores = highScores;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return highScores.size();
    }

    @Override
    public int getColumnCount() {
        return colName.length; // Now 2, for name and score
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HighScore highScore = highScores.get(rowIndex);
        switch(columnIndex) {
            case 0: return highScore.getName(); // Player Name
            case 1: return highScore.getScore(); // Score
            default: return null;
        }
    }

    @Override
    public String getColumnName(int index) {
        return colName[index];
    }
}

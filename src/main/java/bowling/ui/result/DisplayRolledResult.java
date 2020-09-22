package bowling.ui.result;

public class DisplayRolledResult {
    private final String description;
    private final int score;

    public DisplayRolledResult(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public String getScore() {
        if (0 == score){
            return "-";
        }
        return String.valueOf(score);
    }
}

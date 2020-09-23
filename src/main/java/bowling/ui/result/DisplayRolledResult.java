package bowling.ui.result;

import bowling.domain.core.state.Score;

import static bowling.domain.core.state.Score.NOT_CALCULATED;

public class DisplayRolledResult {
    private final String description;
    private final int score;

    public DisplayRolledResult(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public static DisplayRolledResult ofNotCalculated(String description){
        return new DisplayRolledResult(description, Score.NOT_CALCULATED);
    }

    public String getDescription() {
        return description;
    }

    public String getScore() {
        if (NOT_CALCULATED == score){
            return "";
        }
        return String.valueOf(score);
    }
}

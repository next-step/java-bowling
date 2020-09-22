package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class SwingHistory {

    private static final String DELIMITER = "/";
    private static final String ZERO_SCORE = "-";
    private static final String STRIKE = "X";

    private final List<String> swingHistory;

    public SwingHistory() {
        this.swingHistory = new ArrayList<>();
    }

    public void addHistory(int score) {
        swingHistory.add(scoreToString(score));
    }

    private String scoreToString(int score) {

        if (score == Frame.MINIMAL_SCORE) {
            return ZERO_SCORE;
        }

        if (score == Frame.MAXIMUM_SCORE) {
            return STRIKE;
        }

        return String.valueOf(score);
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, swingHistory);
    }
}

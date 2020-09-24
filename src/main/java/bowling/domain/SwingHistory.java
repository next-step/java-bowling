package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class SwingHistory {

    private static final String DELIMITER = "|";
    private static final String ZERO_SCORE = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final int INT_STRIKE = 10;
    private static final int INT_ZERO_SCORE = 0;

    private final List<String> swingHistory;

    public SwingHistory() {
        this.swingHistory = new ArrayList<>();
    }

    public void addHistory(int score, int sumOfScores) {
        swingHistory.add(scoreToString(score, sumOfScores));
    }

    private String scoreToString(int score, int sumOfScores) {

        if (score == Frame.MINIMAL_SCORE) {
            return ZERO_SCORE;
        }

        if (score == Frame.MAXIMUM_SCORE) {
            return STRIKE;
        }

        if (sumOfScores == Frame.MAXIMUM_SCORE) {
            return SPARE;
        }

        return String.valueOf(score);
    }

    public int firstSwing() {

        String swing = swingHistory.get(0);

        if (swing.equals(STRIKE)) {
            return INT_STRIKE;
        }

        if (swing.equals(ZERO_SCORE)) {
            return INT_ZERO_SCORE;
        }

        return Integer.parseInt(swing);
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, swingHistory);
    }
}

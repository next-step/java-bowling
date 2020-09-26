package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class SwingHistory {

    private static final String DELIMITER = "|";
    private static final String ZERO_SCORE = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";

    private final List<Integer> originalScores;
    private final List<String> swingHistories;

    public SwingHistory() {
        this.originalScores = new ArrayList<>();
        this.swingHistories = new ArrayList<>();
    }

    public void addHistory(int score) {
        originalScores.add(score);
        swingHistories.add(scoreToString(score));
    }

    private String scoreToString(int score) {

        if (score == Frame.MINIMAL_SCORE) {
            return ZERO_SCORE;
        }

        if (score == Frame.MAXIMUM_SCORE) {
            return STRIKE;
        }

        if (sumOfScores() == Frame.MAXIMUM_SCORE) {
            return SPARE;
        }

        return String.valueOf(score);
    }

    private int sumOfScores() {
        return originalScores.stream()
                             .mapToInt(Integer::intValue)
                             .sum();
    }

    public int firstSwing() {
        return originalScores.get(0);
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, swingHistories);
    }
}

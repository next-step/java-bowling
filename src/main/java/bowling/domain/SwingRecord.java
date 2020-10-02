package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class SwingRecord {

    private static final String DELIMITER = "|";
    private static final String ZERO_SCORE = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";

    private static final int SPARE_SIZE = 2;

    private final List<Integer> originalScores;
    private final List<String> swingRecords;

    public SwingRecord() {
        this.originalScores = new ArrayList<>();
        this.swingRecords = new ArrayList<>();
    }

    public void addHistory(int score) {
        originalScores.add(score);
        swingRecords.add(scoreToString(score));
    }

    private String scoreToString(int score) {

        if (score == Swing.MINIMAL_SCORE) {
            return ZERO_SCORE;
        }

        if (isSpare()) {
            return SPARE;
        }

        if (score == Swing.MAXIMUM_SCORE) {
            return STRIKE;
        }

        return String.valueOf(score);
    }

    private boolean isSpare() {
        return originalScores.size() == SPARE_SIZE && sumOfScores() == Swing.MAXIMUM_SCORE;
    }

    private int sumOfScores() {
        return originalScores.stream()
                             .mapToInt(Integer::intValue)
                             .sum();
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, swingRecords);
    }
}

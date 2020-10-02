package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class SwingRecord {

    private static final int EMPTY = 0;
    private static final String DELIMITER = "|";
    private static final String ZERO_SCORE = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";

    private final List<String> swingRecords;

    public SwingRecord() {
        this.swingRecords = new ArrayList<>();
    }

    public void addHistory(int score) {
        swingRecords.add(scoreToString(score));
    }

    private String scoreToString(int score) {

        if (score == Swing.MINIMAL_SCORE) {
            return ZERO_SCORE;
        }

        if (score == Swing.MAXIMUM_SCORE) {
            return strikeOrSpare();
        }

        return String.valueOf(score);
    }

    private String strikeOrSpare() {

        if (swingRecords.isEmpty()) {
            return STRIKE;
        }

        return SPARE;
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, swingRecords);
    }
}

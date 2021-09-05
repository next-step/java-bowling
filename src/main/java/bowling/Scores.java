package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";
    private static final String GUTTER_SYMBOL = "-";

    private static final int GUTTER_NUMBER = 0;
    private static final int SECOND_SCORE = 2;

    List<Score> scores = new ArrayList<>();

    public void add(Score score) {
        scores.add(score);
    }

    public boolean isEnd() {
        return isMaxScore() || isFull();
    }

    public FrameResult getFrameResult() {
        if (isMaxScore()) {
            return getSpareOrStrike();
        }

        return FrameResult.MISS;
    }

    public String getScoresString() {
        FrameResult frameResult = getFrameResult();
        String result = getNumbers().stream()
                .map(this::replaceToString)
                .collect(Collectors.joining("|"));

        if (frameResult == FrameResult.STRIKE) {
            return STRIKE_SYMBOL;
        }

        if (frameResult == FrameResult.SPARE) {
            return result.substring(0, result.length() - 1) + SPARE_SYMBOL;
        }

        return result;
    }

    private String replaceToString(Integer n) {
        if (n == GUTTER_NUMBER) {
            return GUTTER_SYMBOL;
        }

        return String.valueOf(n);
    }

    private List<Integer> getNumbers() {
        return scores.stream()
                .map(Score::toInt)
                .collect(Collectors.toList());
    }

    private boolean isFull() {
        return scores.size() >= SECOND_SCORE;
    }

    private Boolean isMaxScore() {
        return scores.stream()
                .reduce(Score::sum)
                .map(Score::isMaxScore)
                .orElse(false);
    }

    private FrameResult getSpareOrStrike() {
        if (isFull()) {
            return FrameResult.SPARE;
        }

        return FrameResult.STRIKE;
    }
}

package bowling.score;

import bowling.global.exception.ScoreMaxSizeException;
import bowling.global.utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

    private static final int SCORE_MAX_VALUE = 10;
    private static final int SCORES_MAX_SIZE = 3;

    private List<Score> scores;

    private Scores() {
        this.scores = new ArrayList<>();
    }

    public static Scores newInstance() {
        return new Scores();
    }

    public void add(Score score) {
        this.scores.add(score);
        validateScoreMaxSize();
    }

    private void validateScoreMaxSize() {
        if (scores.size() > SCORES_MAX_SIZE) {
            throw new ScoreMaxSizeException(ExceptionMessage.INVALID_SCORES_MAX_SIZE);
        }
    }

    private void validateSumAllMaxIsTen() {
        if (sumAll() > SCORE_MAX_VALUE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_SCORES_SUMALL);
        }
    }

    public int getRemainingPins() {
        return SCORE_MAX_VALUE - sumAll();
    }

    public List<Score> getScores() {
        validateSumAllMaxIsTen();
        return Collections.unmodifiableList(scores);
    }

    public Score getScore(int index) {
        return scores.get(index);
    }

    public boolean isSpare() {
        if (scores.size() != 2) {
            return false;
        }
        return sumAll() == SCORE_MAX_VALUE;
    }

    private int sumAll() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                '}';
    }
}

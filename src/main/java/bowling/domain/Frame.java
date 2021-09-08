package bowling.domain;

import bowling.exception.InvalidScoreException;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    private static final String INVALID_SCORE_MESSAGE = "핀의 갯수가 10을 초과할 수 없습니다.";
    protected static final int TEN_SCORE = 10;
    protected static final int ZERO_SCORE = 0;
    protected static final String STRIKE_SYMBOL = "X";
    protected static final String SPARE_SYMBOL = "/";
    protected static final String GUTTER_SYMBOL = "-";
    protected static final String SEPARATOR_SYMBOL = "|";

    protected List<Integer> scores;

    public Frame(int score) {
        scores = new ArrayList<>();
        addScore(score);
    }

    public void addScore(int score) {
        validate(score);
        validateSumOfScore(score);
        scores.add(score);
    }

    public void validate(int score) {
        if (score < ZERO_SCORE || score > TEN_SCORE) {
            throw new InvalidScoreException(INVALID_SCORE_MESSAGE);
        }
    }

    private void validateSumOfScore(int score) {
        validate(sumOfScore() + score);
    }

    public String scoreToSymbol() {
        int firstScore = scores.get(0);
        if (scores.size() <= 1) {
            return changeScoreToSymbol(firstScore);
        }

        int secondScore = scores.get(1);
        if (sumOfScore() == 10) {
            return changeScoreToSpare(firstScore);
        }

        return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondScore);
    }

    public int sumOfScore() {
        int sumOfScore = scores.stream().mapToInt(Integer::intValue).sum();
        return sumOfScore;
    }

    protected String changeScoreToSymbol(int score) {
        if (score == ZERO_SCORE) {
            return GUTTER_SYMBOL;
        }
        if (score == TEN_SCORE) {
            return STRIKE_SYMBOL;
        }
        return score + "";
    }

    protected String changeScoreToSpare(int firstTryScore) {
        return changeScoreToSymbol(firstTryScore) + SEPARATOR_SYMBOL + SPARE_SYMBOL;
    }

    public abstract boolean isNext();
}

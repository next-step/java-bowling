package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected static final int TEN_SCORE = 10;
    protected static final int ZERO_SCORE = 0;
    protected static final String STRIKE_SYMBOL = "X";
    protected static final String SEPARATOR_SYMBOL = "|";
    protected static final String SPARE_SYMBOL = "/";
    protected static final String GUTTER_SYMBOL = "-";

    protected List<Integer> scores;

    public Frame(int score) {
        scores = new ArrayList<>();
        addScore(score);
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public String toScoreSymbol() {
        int firstScore = scores.get(0);
        if (scores.size() <= 1) {
            return changeScoreToSymbol(firstScore);
        }

        int secondScore = scores.get(1);
        if (firstScore + secondScore == 10) {
            return changeScoreToSpare(firstScore);
        }
        return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondScore);
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

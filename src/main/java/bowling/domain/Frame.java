package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected static final String SEPARATOR_SYMBOL = "|";
    protected static final int FRAME_SCORE_MAX_SIZE = 2;
    protected static final int FRAME_SCORE_MAX_SCORE = 10;
    protected List<Score> scores;

    public Frame(int score) {
        scores = new ArrayList<>();
        addScore(score);
    }

    public void addScore(int scoreNumber) {
        Score score = Score.valueOf(scoreNumber);
        Score.validateTotalScore(scores, score);
        scores.add(score);
    }

    public int frameScoresSum() {
        return Score.sumOfScores(scores);
    }

    public String frameScoreToSymbolString() {
        Score firstScore = scores.get(0);
        if (scores.size() <= 1) {
            return firstScore.changeScoreToSymbol();
        }

        Score secondScore = scores.get(1);
        if (frameScoresSum() == FRAME_SCORE_MAX_SCORE) {
            return firstScore.changeScoreToSpare();
        }

        return firstScore.changeScoreToSymbol() + SEPARATOR_SYMBOL + secondScore.changeScoreToSymbol();
    }

    public abstract boolean isNext();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}

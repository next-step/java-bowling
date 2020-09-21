package bowling.score;

import bowling.global.utils.ExceptionMessage;

import java.util.Collections;
import java.util.List;

import static bowling.global.utils.CommonConstant.*;

public abstract class Scores {

    private static final int SCORE_MAX_VALUE = 10;

    protected List<Score> scores;
    protected boolean bonus;

    protected Scores(List<Score> scores) {
        this.scores = scores;
    }

    public void add(Score score) {
        if (scores.size() < NUMBER_TWO && !bonus) {
            validateScoreIsLargethanRemainingPins(score);
        }
        this.scores.add(score);
    }

    public Score getScore() {
        if (scores.size() != NUMBER_ZERO) {
            int index = this.scores.size() - NUMBER_ONE;
            return scores.get(index);
        }
        return scores.get(scores.size());
    }

    public Score getScoreIndex(int index) {
        return Collections.unmodifiableList(scores)
                .get(index);
    }

    public int getRemainingPins() {
        return SCORE_MAX_VALUE - sumAll();
    }

    private int sumAll() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public boolean isSpare() {
        if (scores.size() != NUMBER_TWO) {
            return false;
        }
        return sumAll() == SCORE_MAX_VALUE;
    }

    public boolean isInitCount() {
        return scores.size() == NUMBER_ZERO;
    }

    public boolean isFirstCount() {
        return scores.size() == NUMBER_ONE;
    }

    public boolean isSecondCount() {
        return scores.size() == NUMBER_TWO;
    }

    public abstract boolean canPitching();

    private void validateScoreIsLargethanRemainingPins(Score score) {
        if (score.getScore() != SCORE_MAX_VALUE && score.getScore() > getRemainingPins()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_LARGE_THAN_REMAINING_PINS, getRemainingPins()));
        }
    }

    public int size() {
        return scores.size();
    }

}

package bowling.score;

import bowling.global.utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.global.utils.CommonConstant.*;

public class Scores {

    private static final int SCORE_MAX_VALUE = 10;

    private List<Score> scores;
    private boolean bonus;

    private Scores(List<Score> scores) {
        this.scores = scores;
    }

    public static Scores newInstance() {
        return new Scores(new ArrayList<>());
    }

    public void add(Score score) {
        if (scores.size() < NUMBER_TWO && !bonus) {
            validateScoreIsLargethanRemainingPins(score);
        }
        this.scores.add(score);
    }

    public int getRemainingPins() {
        return SCORE_MAX_VALUE - sumAll();
    }

    public boolean isInitCount() {
        return scores.size() == NUMBER_ZERO;
    }

    public boolean isFirstCount() {
        return scores.size() == NUMBER_ONE;
    }

    public boolean isSecondCount() {
        return scores.size() >= NUMBER_TWO;
    }

    public Score getScore() {
        if (scores.size() != NUMBER_ZERO) {
            int index = this.scores.size() - NUMBER_ONE;
            return scores.get(index);
        }
        return scores.get(scores.size());
    }

    public boolean isSpare() {
        if (scores.size() != NUMBER_TWO) {
            return false;
        }
        return sumAll() == SCORE_MAX_VALUE;
    }

    private int sumAll() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public boolean canNormalPitching() {
        if (getScore().isStrike()) {
            return false;
        }
        if (isSpare()) {
            return false;
        }
        if (isSecondCount()) {
            return false;
        }
        return (isFirstCount() || getScore().isGutter());
    }

    public boolean canFinalPitching() {
        if (scores.size() != NUMBER_ZERO && getScore().isStrike()) {
            bonus = true;
        }
        if (isInitCount() || isFirstCount()) {
            return true;
        }
        if ((getScore().isStrike() && !bonus)) {
            bonus = true;
            return true;
        }
        if (isSpare() && !bonus) {
            bonus = true;
            return true;
        }
        return false;
    }

    private void validateScoreIsLargethanRemainingPins(Score score) {
        if (score.getScore() != SCORE_MAX_VALUE && score.getScore() > getRemainingPins()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_LARGE_THAN_REMAINING_PINS, getRemainingPins()));
        }
    }

    public Score getScoreIndex(int index) {
        return Collections.unmodifiableList(scores)
                .get(index);
    }

    public int size() {
        return scores.size();
    }

}

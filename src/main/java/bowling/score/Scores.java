package bowling.score;

import bowling.global.utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

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
        if (scores.size() < 2 && !bonus) {
            validateScoreIsLargethanRemainingPins(score);
        }
        this.scores.add(score);
    }

    public int getRemainingPins() {
        return SCORE_MAX_VALUE - sumAll();
    }

    public boolean isInitCount() {
        return scores.size() == 0;
    }

    public boolean isFirstCount() {
        return scores.size() == 1;
    }

    public boolean isSecondCount() {
        return scores.size() >= 2;
    }

    public Score getScore() {
        if (scores.size() != 0) {
            int index = this.scores.size() - 1;
            return scores.get(index);
        }
        return scores.get(scores.size());
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
        if (scores.size() != 0 && getScore().isStrike()) {
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
        if (score.getScore() != 10 && score.getScore() > getRemainingPins()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_LARGE_THAN_REMAINING_PINS, getRemainingPins()));
        }
    }

}

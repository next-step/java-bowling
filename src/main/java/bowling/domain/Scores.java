package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private static final int NORMAL_ROUND_TRY_NUM = 2;
    private static final int LAST_ROUND_TRY_NUM = 3;
    private static final int FIRST_SCORE = 0;
    private static final int SECOND_SCORE = 1;
    private static final int THIRD_SCORE = 2;

    private final List<Score> scores = new ArrayList<>();

    public void add(Integer pins) {
        scores.add(new Score(pins));
    }

    public boolean isNormalRoundEnd() {
        return scores.size() == NORMAL_ROUND_TRY_NUM ||
                scores.stream().reduce(Score::sum).get().isStrike();
    }

    public boolean isLastRoundEnd() {
        return scores.size() == LAST_ROUND_TRY_NUM || (scores.size() == NORMAL_ROUND_TRY_NUM && !isBonusBall());
    }

    private boolean isBonusBall() {
        return isSecondPinSpare() || scores.get(FIRST_SCORE).isStrike() || scores.get(SECOND_SCORE).isStrike();
    }


    public boolean isSecondPinSpare() {
        return isSpare(NORMAL_ROUND_TRY_NUM, FIRST_SCORE, SECOND_SCORE);
    }

    public boolean isThirdPinSpare() {
        return isSpare(LAST_ROUND_TRY_NUM, SECOND_SCORE, THIRD_SCORE);
    }

    private boolean isSpare(int size, int before, int current) {
        if (scores.size() < size) {
            return false;
        }
        return !scores.get(current).isGutter() &&
                !scores.get(before).isStrike() &&
                scores.get(before).sum(scores.get(current)).isStrike();
    }

    public boolean containsAll(List<Score> scores) {
        return scores.containsAll(scores);
    }

    public List<Score> getScores() {
        return scores;
    }

    public int sum() {
        int sum = 0;
        for (Score score : scores) {
            sum = score.sum(sum);
        }
        return sum;
    }

    public Score getFirstScore() {
        return scores.get(0);
    }

    public boolean containsStrike() {
        return scores.stream()
                .anyMatch(Score::isStrike);
    }
}

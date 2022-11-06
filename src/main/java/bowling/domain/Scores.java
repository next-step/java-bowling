package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    public static final int MAX_SCORE = 10;
    private static final int NORMAL_ROUND_TRY_NUM = 2;
    private static final int LAST_ROUND_TRY_NUM = 3;
    private static final int FIRST_SCORE = 0;
    private static final int SECOND_SCORE = 1;
    private static final int THIRD_SCORE = 2;
    private final List<Score> scores = new ArrayList<>();

    public Scores() {

    }

    public Scores(List<Integer> scores) {
        scores.stream()
                .map(Score::new)
                .forEach(this.scores::add);
    }

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
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public Integer spareBonus() {
        return scores.get(0).sum(Scores.MAX_SCORE);
    }

    public boolean containsStrike() {
        return scores.stream()
                .anyMatch(Score::isStrike);
    }

    public boolean containsSpare() {
        return isSecondPinSpare() || isThirdPinSpare();
    }

    public boolean hasScore() {
        return this.scores.size() > 0;
    }

    public boolean hasTwoScore() {
        return this.scores.size() == 2;
    }

    public Integer doubleStrikeBonus() {
        return scores.get(FIRST_SCORE).sum(Scores.MAX_SCORE * 2);
    }

    public boolean isFirstScoreStrike() {
        if (!hasScore()) {
            return false;
        }
        return scores.get(FIRST_SCORE).isStrike();
    }
}

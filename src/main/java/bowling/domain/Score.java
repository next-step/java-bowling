package bowling.domain;

import java.util.Objects;

public class Score {

    private static final int MISS_BONUS_COUNT = 0;
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int STRIKE_BONUS_COUNT = 2;

    private int score;
    private int leftBonusCount;

    private Score(int score, int leftBonusCount){
        this.score = score;
        this.leftBonusCount = leftBonusCount;
    }

    public static Score of(int score){
        return new Score(score, MISS_BONUS_COUNT);
    }

    public static Score ofStrike(int score) {
        return new Score(score, STRIKE_BONUS_COUNT);
    }

    public static Score ofSpare(int score) {
        return new Score(score, SPARE_BONUS_COUNT);
    }

    public int getLeftBonusCount() {
        return leftBonusCount;
    }

    public int getScore() {
        return score;
    }

    public void renewScore(int score){
        this.score = score;
        reduceBonusCount();
    }

    private void reduceBonusCount() {
        this.leftBonusCount--;
        if (leftBonusCount < 0) {
            throw new IllegalArgumentException("남은 보너스 점수 획득 기회가 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                leftBonusCount == score1.leftBonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftBonusCount);
    }

}

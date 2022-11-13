package bowling.domain.score.scores;

public class BonusScores extends Scores {
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;

    @Override
    public void validateScore() {
        // 검증 필요 없음
    }

    @Override
    public boolean isNotEndScore(Scores scores) {
        if (scores.isStrike()) {
            return !this.isSizeEqual(STRIKE_BONUS_SIZE);
        }
        if (scores.isSpare()) {
            return !this.isSizeEqual(SPARE_BONUS_SIZE);
        }
        return false;
    }

    @Override
    public boolean isChanceMinusTwo() {
        throw new IllegalArgumentException();
    }


}

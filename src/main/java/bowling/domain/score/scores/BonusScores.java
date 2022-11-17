package bowling.domain.score.scores;

public class BonusScores extends Scores {
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;

    @Override
    public void validateScore() {
        throw new RuntimeException("보너스 게임은 검증이 필요 없습니다.");
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

package bowling.domain.score;

public final class Score {

    private final int score;
    private final BonusCount bonusCount;

    private Score(final int score, final BonusCount bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score of(int score, BonusCount bonusCount) {
        return new Score(score, bonusCount);
    }
}

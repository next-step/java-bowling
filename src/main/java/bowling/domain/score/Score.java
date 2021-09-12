package bowling.domain.score;

public final class Score {

    public static final int BONUS_REMAIN_COUNT_ONE = 1;
    public static final int BONUS_REMAIN_COUNT_TWO = 2;

    private final int score;
    private final int remainCount;

    private Score(final int score, final int remainCount) {
        this.score = score;
        this.remainCount = remainCount;
    }

    public static Score of(final int score, final int remainCount) {
        return new Score(score, remainCount);
    }

    public static Score ofRemainTwo(final int score) {
        return new Score(score, BONUS_REMAIN_COUNT_TWO);
    }

    public static Score ofRemainOne(final int score) {
        return new Score(score, BONUS_REMAIN_COUNT_ONE);
    }

    public boolean isRemainCount(final int remainCount) {
        return this.remainCount == remainCount;
    }

    public int sum(final int addScore) {
        return score + addScore;
    }
}

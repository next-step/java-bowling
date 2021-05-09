package bowling.domain;

public final class BonusScoreCount {

    private final int remain;

    private BonusScoreCount(final int remain) {
        this.remain = remain;
    }

    public static BonusScoreCount from(final int remain) {
        return new BonusScoreCount(remain);
    }

}

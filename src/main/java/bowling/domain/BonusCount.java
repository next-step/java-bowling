package bowling.domain;

public final class BonusCount {

    private final int remain;

    private BonusCount(final int remain) {
        this.remain = remain;
    }

    public static BonusCount from(final int remain) {
        return new BonusCount(remain);
    }

}

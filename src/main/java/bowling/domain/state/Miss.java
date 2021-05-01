package bowling.domain.state;

public final class Miss {

    private final int firstCount;
    private final int secondCount;

    private Miss(final int firstCount, final int secondCount) {
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    public static final Miss from(final int firstCount, final int secondCount) {
        return new Miss(firstCount, secondCount);
    }
}

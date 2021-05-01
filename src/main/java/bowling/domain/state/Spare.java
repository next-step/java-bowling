package bowling.domain.state;

public final class Spare extends Finish {

    private final int firstCount;
    private final int secondCount;

    private Spare(final int firstCount, final int secondCount) {
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    public static final State of(final int firstCount, final int secondCount) {
        return new Spare(firstCount, secondCount);
    }
}

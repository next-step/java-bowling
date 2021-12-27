package bowling.domain;

public class NormalScore implements Score {

    private static final int NORMAL_SCORE_TRY_COUNT = 2;
    private static final int DOWN_TRY_COUNT = 1;
    private static final int LAST_COUNT = 1;

    private final int tryCount;
    private final Pins firstScore;
    private final Pins secondScore;

    public NormalScore() {
        this(NORMAL_SCORE_TRY_COUNT, Pins.defaultPins(), Pins.defaultPins());
    }

    private NormalScore(int tryCount, Pins firstScore, Pins secondScore) {
        this.tryCount = tryCount;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    @Override
    public NormalScore firstScore(Pins pins) {
        if (tryCount != NORMAL_SCORE_TRY_COUNT) {
            throw new IllegalArgumentException("첫 번째 스코어를 진행할 수 없습니다.");
        }

        return new NormalScore(nextTryCount(pins), pins, this.secondScore);
    }

    @Override
    public NormalScore nextScore(Pins pins) {
        if (!isNextScore()) {
            throw new IllegalArgumentException("두 번째 스코어를 진행할 수 없습니다.");
        }

        return new NormalScore(nextTryCount(pins), firstScore, pins);
    }

    @Override
    public boolean isNextScore() {
        return tryCount >= LAST_COUNT;
    }

    private int nextTryCount(Pins pins) {
        int newTryCount = tryCount - DOWN_TRY_COUNT;
        if (pins.isMaxPins()) {
            newTryCount -= DOWN_TRY_COUNT;
        }
        return newTryCount;
    }

}

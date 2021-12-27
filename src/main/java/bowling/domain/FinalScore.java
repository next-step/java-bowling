package bowling.domain;

public class FinalScore implements Score {

    private static final int NORMAL_SCORE_TRY_COUNT = 3;
    private static final int DOWN_TRY_COUNT = 1;
    private static final int LAST_COUNT = 1;

    private final int tryCount;
    private final Pins firstScore;
    private final Pins secondScore;
    private final Pins lastScore;

    public FinalScore() {
        this(NORMAL_SCORE_TRY_COUNT, Pins.of(0), Pins.of(0), Pins.of(0));
    }

    private FinalScore(int tryCount, Pins firstScore, Pins secondScore, Pins lastScore) {
        this.tryCount = tryCount;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.lastScore = lastScore;
    }

    @Override
    public FinalScore firstScore(Pins pins) {
        if (tryCount != NORMAL_SCORE_TRY_COUNT) {
            throw new IllegalArgumentException("첫 번째 스코어를 진행할 수 없습니다.");
        }

        return new FinalScore(nextTryCount(pins), pins, this.secondScore, this.lastScore);
    }

    @Override
    public FinalScore nextScore(Pins pins) {
        if (!isNextScore()) {
            throw new IllegalArgumentException("두 번째 스코어를 진행할 수 없습니다.");
        }

        return new FinalScore(nextTryCount(pins), firstScore, pins, this.lastScore);
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

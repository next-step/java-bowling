package bowling.domain;

public class FinalFrame implements Frame {
    private static final int FINAL_INDEX = 9;
    private static final int PIN_COUNT_MAX = 10;
    private static final int PIN_COUNTS_EMPTY = 0;
    private static final int PIN_COUNTS_SINGLE_SIZE = 1;
    private static final String CANNOT_THROW_THIRD_BOWL = "3구를 던질수 없습니다.";

    private final PinCounts pinCounts;

    public FinalFrame() {
        this(new FinalPinCounts());
    }

    private FinalFrame(PinCounts pinCounts) {
        this.pinCounts = pinCounts;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (size() == PIN_COUNTS_EMPTY) {
            return addBowl(pinCount);
        }

        if (size() == PIN_COUNTS_SINGLE_SIZE) {
            return addBowl(pinCount);
        }

        if (isThirdThrowPossible()) {
            return addBowl(pinCount);
        }

        throw new IllegalArgumentException(CANNOT_THROW_THIRD_BOWL);
    }

    private boolean isThirdThrowPossible() {
        return pinCounts.isFirstPinCountStrike()
                || (!pinCounts.isFirstPinCountStrike() && pinCounts.totalPinCount() == PIN_COUNT_MAX);
    }

    private Frame addBowl(int pinPinCount) {
        pinCounts.knockDown(pinPinCount);
        return new FinalFrame(pinCounts);
    }

    private Frame addBowl(String firstPinCount) {
        return addBowl(Integer.parseInt(firstPinCount));
    }

    @Override
    public boolean isFinished() {
        return pinCounts.isFinished();
    }

    @Override
    public int index() {
        return FINAL_INDEX;
    }

    @Override
    public PinCounts pinCounts() {
        return pinCounts;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public Score score() {
        if (!isFinished()) {
            return Score.unCountableScore();
        }

        return Score.Miss(pinCounts.totalPinCount());
    }

    @Override
    public Score add(Score previousScore) {
        int previousScoreChance = previousScore.leftOpportunity();
        int previousScoreValue = previousScore.value();

        if (size() == PIN_COUNTS_EMPTY
                || (previousScoreChance == 2 && size() <= PIN_COUNTS_SINGLE_SIZE)) {
            return Score.unCountableScore();
        }

        if (previousScoreChance == 1) {
            return Score.Miss(pinCounts.firstPinCount().value() + previousScoreValue);
        }

        return Score.Miss(pinCounts.firstPinCount().value()
                + pinCounts.secondPinCount().value()
                + previousScoreValue);
    }
}

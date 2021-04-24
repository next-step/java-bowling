package bowling.domain;

public class FinalFrame implements Frame {
    private static final String CANNOT_THROW_THIRD_BOWL = "3구를 던질수 없습니다.";
    private static final int FINAL_INDEX = 9;

    private final PinCounts pinCounts;

    public FinalFrame() {
        this(new FinalPinCounts());
    }

    private FinalFrame(PinCounts pinCounts) {
        this.pinCounts = pinCounts;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (pinCounts.pinCounts().isEmpty()) {
            return addBowl(pinCount);
        }

        if (pinCounts.pinCounts().size() == 1) {
            return addBowl(pinCount);
        }

        if (isThirdThrowPossible()) {
            return addBowl(pinCount);
        }

        throw new IllegalArgumentException(CANNOT_THROW_THIRD_BOWL);
    }

    private boolean isThirdThrowPossible() {
        return pinCounts.pinCounts().get(0).isStrike()
                || (!pinCounts.pinCounts().get(0).isStrike() && pinCounts.totalPinCount() == 10);
    }

    public Frame throwBowl(int pinCount) {
        return throwBowl(String.valueOf(pinCount));
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
}

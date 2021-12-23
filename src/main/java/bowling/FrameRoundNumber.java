package bowling;

public class FrameRoundNumber {
    public static final FrameRoundNumber BEFORE_FINAL_FRAME_NUMBER = new FrameRoundNumber(9);
    public static final String WRONG_FRAME_NUMBER_MESSAGE = "잘못된 프레임넘버입니다.";

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;

    private final int roundNumber;

    public FrameRoundNumber(int roundNumber) {
        if (roundNumber < MIN_VALUE || roundNumber > MAX_VALUE) {
            throw new IllegalArgumentException(WRONG_FRAME_NUMBER_MESSAGE);
        }
        this.roundNumber = roundNumber;
    }

    public FrameRoundNumber next() {
        return new FrameRoundNumber(roundNumber + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FrameRoundNumber that = (FrameRoundNumber) o;

        return roundNumber == that.roundNumber;
    }

    @Override
    public int hashCode() {
        return roundNumber;
    }
}

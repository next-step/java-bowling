package domain;

import java.util.*;

public class NormalFrame {
    private static final int MAXIMUM_NUMBER_OF_ROUND_RESULTS_FOR_NORMAL_FRAME = 2;
    private static final int FIRST_TRIAL = 0;
    private static final int STRIKE_PINS = 10;
    private static final int GAP_BETWEEN_FRAME_NUMBERS = 1;

    private final int frameNumber;
    private List<Pins> roundResult;
    private NormalFrame nextNormalFrame;

    private NormalFrame(int frameNumber, List<Pins> roundResult) {
        this.frameNumber = frameNumber;
        this.roundResult = roundResult;
    }

    public static NormalFrame of(int frameNumber, List<Pins> roundResult) {
        return new NormalFrame(frameNumber, roundResult);
    }

    private NormalFrame makeNextFrame(Pins pins) {
        return of(increaseFrameNumber(), new ArrayList<>(Arrays.asList(pins)));
    }

    public NormalFrame fillFrame(Pins pins) {
        if (roundResult.isEmpty()) {
            roundResult.add(pins);
            return this;
        }
        if (isFull() || isStrike()) {
            nextNormalFrame = makeNextFrame(pins);
            return nextNormalFrame;
        }
        roundResult.add(pins);
        return this;
    }

    private boolean isFull() {
        return roundResult.size() == MAXIMUM_NUMBER_OF_ROUND_RESULTS_FOR_NORMAL_FRAME;
    }

    private boolean isStrike() {
        return roundResult.get(FIRST_TRIAL).equals(Pins.from(STRIKE_PINS));
    }

    private int increaseFrameNumber() {
        return frameNumber + GAP_BETWEEN_FRAME_NUMBERS;
    }

    public boolean isMatch(NormalFrame normalFrame) {
        return this.equals(normalFrame);
    }

    public List<Pins> getRoundResult() {
        return Collections.unmodifiableList(roundResult);
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber &&
                Objects.equals(roundResult, that.roundResult) &&
                Objects.equals(nextNormalFrame, that.nextNormalFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, roundResult, nextNormalFrame);
    }
}
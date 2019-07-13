package domain;

import java.util.*;

public class NormalFrame {

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

    public boolean isMatch(NormalFrame normalFrame) {
        return this.equals(normalFrame);
    }

    public NormalFrame fillFrame(Pins pins) {
        if (roundResult.isEmpty()) {
            roundResult.add(pins);
            return this;
        }
        if(roundResult.size() == 2 || roundResult.get(0).equals(Pins.from(10))) {
            nextNormalFrame = of(frameNumber + 1, new ArrayList<>(Arrays.asList(pins)));
            return nextNormalFrame;
        }
        roundResult.add(pins);
        return this;
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

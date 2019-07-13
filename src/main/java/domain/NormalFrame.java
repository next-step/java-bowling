package domain;

import java.util.List;

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
        roundResult.add(pins);
        if(roundResult.size() == 2 || roundResult.get(0).equals(Pins.from(10))) {
            return nextNormalFrame;
        }
        return this;
    }

    public List<Pins> getRoundResult() {
        return roundResult;
    }
}

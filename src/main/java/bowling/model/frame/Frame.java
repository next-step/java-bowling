package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Score;

public abstract class Frame {

    public static final String SEPARATOR_OF_FRAME = ",";
    private final FrameNumber frameNumber;

    Frame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    FrameNumber nextFrameNumber() {
        return frameNumber.increase();
    }

    public FrameNumber getNumber() {
        return frameNumber.self();
    }

    public static Frame initialize() {
        return NormalFrame.ofFirst();
    }
    
    public abstract Score getScore();

    public abstract Frame bowl(Pins downPins);

    public abstract String printResult();

    public abstract boolean isGameOver();

    abstract Score calculate(Score score);
}

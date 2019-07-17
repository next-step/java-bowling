package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.frame.state.Score;

public abstract class Frame {

    private final FrameNumber frameNumber;

    Frame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    FrameNumber nextFrameNumber() {
        return frameNumber.increase();
    }

    FrameNumber getNumber() {
        return frameNumber.self();
    }

    static Frame initialize() {
        return NormalFrame.ofFirst();
    }

    abstract Score getScore();

    abstract Score calculate(Score score);

    public abstract Frame bowl(Pin downPin);

    public abstract FrameResult getResult();

    public abstract boolean isGameOver();
}

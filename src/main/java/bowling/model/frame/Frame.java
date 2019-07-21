package bowling.model.frame;

import bowling.model.DownPin;
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

    boolean isEqualFrameNumber(Frame frameAfterBowling){
        return frameNumber.equals(frameAfterBowling.getNumber());
    }

    static Frame initialize() {
        return NormalFrame.ofFirst();
    }

    abstract Score getScore();

    abstract Score calculate(Score prevScore);

    public abstract Frame bowl(DownPin downPin);

    public abstract FrameResult getResult();

    public abstract boolean isGameOver();
}

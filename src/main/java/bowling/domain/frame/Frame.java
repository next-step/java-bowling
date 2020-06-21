package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;

public abstract class Frame {

    abstract Frame bowl(PinCount countOfPin);

    abstract boolean isGameOver();

    abstract int getNo();

    void addFrame(Frames frames) {
        return;
    }

    abstract FrameResult getFrameResult();
}

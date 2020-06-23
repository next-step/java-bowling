package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;

public abstract class Frame {

    abstract Frame bowl(PinCount countOfPin);

    abstract boolean isGameOver();

    abstract int getFrameNo();

    void addFrame(Frames frames) {
        if (frames.isSameCurrentFrame(this)) {
            return;
        }
        frames.add(this);
    }

    abstract FrameResult getFrameResult();
}

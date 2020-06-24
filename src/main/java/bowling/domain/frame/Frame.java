package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;

public abstract class Frame {

    public abstract void bowl(PinCount countOfPin);

    abstract Frame initNextFrame();

    abstract void addFrame(Frames frames);

    boolean isGameOver() {
        return false;
    }

    abstract int getFrameNo();

    abstract StateDtos getFrameResult();
}

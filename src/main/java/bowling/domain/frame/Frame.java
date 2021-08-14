package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.List;

public abstract class Frame {

    public abstract Score getScore();

    public boolean isBowlingFinish() {
        return false;
    }

    protected abstract Score addStateScore(Score score);

    protected abstract void hitPins(Pins pins);

    protected abstract StateData getFrameStates();

    protected void addFrame(List<Frame> frames) {
    }

}

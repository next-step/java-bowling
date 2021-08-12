package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.CommonState;

import java.util.List;

public abstract class Frame {

    public static final int START_COUNT = 1;
    public static final int END_COUNT = 10;

    protected CommonState state;

    protected Frame(CommonState state) {
        this.state = state;
    }

    public abstract Score getScore();

    protected abstract Score addBonusScore(Score score);

    public void hitPins(Pins pins) {
        this.state = state.hitPins(pins);
    }

    public StateData getFrameStates() {
        return StateData.of(state.getState());
    }

    public boolean isBowlingFinish() {
        return false;
    }

    protected void addFrame(List<Frame> frames) {

    }

}

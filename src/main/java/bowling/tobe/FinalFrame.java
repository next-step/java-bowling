package bowling.tobe;

import bowling.domain.Pin;
import bowling.domain.Pitch;
import bowling.domain.Shot;
import bowling.domain.State;

import java.util.List;

public class FinalFrame extends Frame {

    public FinalFrame() {
        this.frameNo = FINAL_FRAME;
        this.pitch = new Pitch();
    }

    @Override
    public State bowling(Pin pin) {
        // TODO
        return null;
    }

    @Override
    public List<Shot> getShotHistory() {
        // TODO
        return null;
    }
}

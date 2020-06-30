package bowling.tobe;

import bowling.domain.Pin;
import bowling.domain.Pitch;
import bowling.domain.Shot;
import bowling.domain.State;

import java.util.List;

public class NormalFrame extends Frame {

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME);
    }

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitch = new Pitch();
    }

    @Override
    public State bowling(Pin pin) {
        if (pitch.add(pin)) {
            return State.Finish;
        }
        return State.NotFinish;
    }

    @Override
    public List<Shot> getShotHistory() {
        return pitch.getShotHistory();
    }
}

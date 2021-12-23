package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.domain.value.Pins;

public class NormalFrame extends Frame{

    private static final int FINAL_FRAME_NO = 10;

    public NormalFrame(int frameNo) {

        this.frameNo = frameNo;
        states = new States(new Ready());
    }

    @Override
    public Frame bowl(int knockedDownPin) {

        State state = states.bowl(new Pins(knockedDownPin));
        states.add(state);
        if(!isFinished()) {
            return this;
        }
        return nextFrame();
    }

    private Frame nextFrame() {
        int nextFrameNo = this.frameNo + 1;
        if (nextFrameNo == FINAL_FRAME_NO) {
            return new FinalFrame(nextFrameNo);
        }
        return new NormalFrame(nextFrameNo);
    }
}

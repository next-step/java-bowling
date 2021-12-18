package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.state.Ready;
import bowling.model.state.State;
import bowling.model.state.States;

public class NormalFrame extends Frame {

    private static final int FINAL_FRAME_NO = 10;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        states = new States(new Ready());
    }

    @Override
    public Frame bowl(int knockedDownPin) {
        State state = states.last().bowl(Pins.knockedDown(knockedDownPin));
        states.add(state);
        if(!isFinish()) {
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

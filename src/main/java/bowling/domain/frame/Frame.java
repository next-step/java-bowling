package bowling.domain.frame;

import bowling.domain.state.States;

public abstract class Frame {

    protected int frameNo;
    protected States states;

    public abstract Frame bowl(int knockedDownPin);

    public String getStateMark() {
        return this.states.getMark();
    }

    public int getFrameNo() {
        return this.frameNo;
    }

    public boolean isStrike() {
        return this.states.isStrike();
    }

    public boolean isSpare() {
        return this.states.isSpare();
    }

    public boolean isNormalFrame() {
        return this instanceof NormalFrame;
    }

    public boolean isFinished() {
        return this.states.isFinished();
    }
}

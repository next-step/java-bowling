package bowling.model.frame;

public abstract class Frame {
    protected int frameNo;
    protected States states;

    public abstract Frame bowl(int knockedDownPin);

    public String getStateDesc() {
        return this.states.getDesc();
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

    public boolean isFinish() {
        return this.states.isFinish();
    }
}

package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;

public abstract class Frame {
    protected final int no;
    protected State state;
    protected Frame next;

    public Frame(int no, State state) {
        this.no = no;
        this.state = state;
    }

    public int getNo() {
        return no;
    }

    public Frame bowl(int count) {
        return bowl(PinCount.of(count));
    }

    protected State getState() {
        return state;
    }

    protected Frame getNext() {
        return next;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public abstract Score getScore();

    abstract protected  Score calculateBonusScore(Score score);

    abstract public Frame bowl(PinCount pinCount);
}

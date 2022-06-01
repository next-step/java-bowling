package bowling.domain;

import bowling.domain.state.State;
import java.util.OptionalInt;

public abstract class Frame {

    protected Frame nextFrame;
    protected Frame beforeFrame;
    protected State state = State.ofReady();

    public Frame() {
        this(null, null);
    }

    protected Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    abstract public OptionalInt scoreCalculated();

    protected Score bonusScore(Score previousScore) {
        Score score = state.addBonus(previousScore);

        if (score.isAddedAllBonus()) {
            return score;
        }

        return nextFrame.bonusScore(score);
    }

    public Frame createNext() {
        this.nextFrame = new NormalFrame(this, null);
        return nextFrame;
    }

    public void createFinal() {
        this.nextFrame = new FinalFrame(this, null);
    }

    public Frame createBefore() {
        this.beforeFrame = new NormalFrame(null, this);
        return beforeFrame;
    }

    public void shot(int hitCount) {
        state = state.bowl(hitCount);
    }

    public boolean isDone() {
        return state.isDone();
    }

    public Frame next() {
        return this.nextFrame;
    }

    public Frame before() {
        return this.beforeFrame;
    }

    protected State state() {
        return state;
    }

    public String output() {
        return state.output();
    }

}

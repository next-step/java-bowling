package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.state.State;

import java.util.Objects;
import java.util.Optional;

public abstract class Frame {
    protected final int no;
    protected State state;
    protected Frame next;
    protected Optional<Score> score = Optional.empty();

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

    public Result createResult() {
        return new Result(getScore().getValue(), state.getDesc());
    }

    public abstract Score getScore();

    abstract protected Score calculateBonusScore(Score score);

    abstract public Frame bowl(PinCount pinCount);

    @Override
    public String toString() {
        return "Frame{" +
                "no=" + no +
                ", state=" + state +
                ", score=" + score +
                '}';
    }
}

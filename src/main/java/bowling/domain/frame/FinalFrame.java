package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.state.State;

public class FinalFrame implements Frame {
    private final List<State> states;
    private TryCount tryCount;

    private FinalFrame(TryCount tryCount) {
        this.tryCount = tryCount;
        this.states = new ArrayList<>();
    }

    public static Frame init() {
        return new FinalFrame(TryCount.first());
    }

    public static Frame of(int tryCount) {
        return new FinalFrame(TryCount.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {

    }

    @Override
    public Frame next() {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isDone() {
        return false;
    }
}

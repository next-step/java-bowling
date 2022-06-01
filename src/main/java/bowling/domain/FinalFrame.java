package bowling.domain;

import bowling.domain.state.FinalState;
import java.util.OptionalInt;

public class FinalFrame extends Frame {
    private FinalState state = new FinalState();

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public OptionalInt scoreCalculated() {
        if (!isDone()) {
            return OptionalInt.empty();
        }
        return state.score().getAsOptionalInt();
    }

    @Override
    protected Score bonusScore(Score score) {
        return state.addBonus(score);
    }

    @Override
    public void shot(int hitCount) {
        state.bowl(hitCount);
    }

    @Override
    public boolean isDone() {
        return state.isDone();
    }

    @Override
    public String output() {
        return state.output();
    }
}

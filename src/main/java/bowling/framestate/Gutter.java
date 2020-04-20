package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;

import java.util.Collections;
import java.util.List;

public class Gutter implements State {

    private Gutter() {
    }

    public static Gutter newInstance() {
        return new Gutter();
    }

    @Override
    public State bowl(Pin pinCount) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createGutter();
    }

    @Override
    public FrameScore addNextAddingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Collections.emptyList());
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.emptyList();
    }
}

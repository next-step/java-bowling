package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;

import java.util.Arrays;
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
    public FrameScore sumBeforeScore(final FrameScore beforeScore) {
        return FrameScore.newInstanceWithNoLeftCount(beforeScore);
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(Pin.ofMin(), Pin.ofMin());
    }
}

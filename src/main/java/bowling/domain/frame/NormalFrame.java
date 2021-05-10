package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

public class NormalFrame extends Frame {

    private State state;
    private Frame next;

    private NormalFrame() {
        this.state = Ready.initialize();
        this.next = null;
    }

    public static Frame initialize() {
        return new NormalFrame();
    }

    @Override
    public Frame bowl(Pins fallPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score score() {
        return null;
    }
}

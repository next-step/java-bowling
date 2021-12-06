package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public class NormalFrame extends Frame {
    private State state;

    private NormalFrame() {
        this.score = Score.init();
        this.state = Ready.of();
    }

    public static Frame create() {
        return new NormalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        this.state = state.pitch(pins);

        if (isFrameOver()) {
            score = state.calculateScore();
        }
    }

    @Override
    public boolean isFrameOver() {
        return state.isFinish();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String getMark() {
        return state.getMark();
    }
}

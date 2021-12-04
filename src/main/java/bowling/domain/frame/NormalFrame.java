package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

public class NormalFrame extends Frame {
    private State state;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.framePins = FramePins.create();
        this.score = Score.init();
        this.state = Ready.of();
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void pitch(Pins pins) {
        validatePins(pins);

        changeState(pins);
    }

    private void changeState(Pins pins) {
        this.state = state.pitch(pins);
        framePins.addPins(pins);
    }

    private void validatePins(Pins pins) {
        Preconditions.checkMaximumSize(framePins.calculateTotalPins() + pins.getPins(),
                                       STRIKE_OR_SPARE_COUNT, "최대 투구수를 넘을 수 없습니다.");
    }

    @Override
    public boolean isFrameOver() {
        return state.isFinish();
    }

    @Override
    public boolean isFinalFrameOver() {
        return false;
    }

    @Override
    public void accumulateScore() {
        if (isStrike()) {
            score = Score.ofStrike();
            return;
        }

        if (isSpare()) {
            score = Score.ofSpare();
            return;
        }

        score = Score.ofMiss(framePins.calculateTotalPins());
    }

    private boolean isStrike() {
        return state instanceof Strike;
    }

    private boolean isSpare() {
        return state instanceof Spare;
    }

    @Override
    public String mark() {
        return state.mark();
    }
}

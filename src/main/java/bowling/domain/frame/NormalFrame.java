package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.pitch.PitchNumber;
import bowling.domain.pitch.NormalPitchNumber;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class NormalFrame implements Frame {
    private State state;
    private PitchNumber pitchNumber;

    private NormalFrame(PitchNumber normalPitchNumber) {
        this.pitchNumber = normalPitchNumber;
    }

    public static Frame init() {
        return new NormalFrame(NormalPitchNumber.first());
    }

    public static Frame of(int tryCount) {
        return new NormalFrame(NormalPitchNumber.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
        this.state = this.getState(pinCount);
        this.pitchNumber = NormalPitchNumber.of(pitchNumber.increase());
    }

    private State getState(int pinCount) {
        if (pitchNumber.isFirstPitch()) {
            return State.newState(BowlingPin.of(pinCount));
        }
        return State.newState(state.firstHit(), BowlingPin.of(pinCount));
    }

    @Override
    public Frame next() {
        return NormalFrame.init();
    }

    @Override
    public boolean isDone() {
        if (state instanceof Strike) {
            return true;
        }
        return pitchNumber.isLastPitch();
    }

    @Override
    public String scoreResult() {
        if (pitchNumber.isSecondPitch()) {
            return state.score();
        }
        return state.totalScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NormalFrame that = (NormalFrame)o;
        return Objects.equals(state, that.state) && Objects.equals(pitchNumber, that.pitchNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, pitchNumber);
    }
}

package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bowling.domain.pitch.PitchNumber;
import bowling.domain.pitch.FinalPitchNumber;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class FinalFrame implements Frame {
    public static final String PARTITION = "|";
    private final List<State> states;
    private PitchNumber pitchNumber;

    private FinalFrame(PitchNumber pitchNumber) {
        this.pitchNumber = pitchNumber;
        this.states = new ArrayList<>();
    }

    public static Frame init() {
        return new FinalFrame(FinalPitchNumber.first());
    }

    public static Frame of(int tryCount) {
        return new FinalFrame(FinalPitchNumber.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
        State state = this.getState(pinCount);
        this.pitchNumber = FinalPitchNumber.of(this.pitchNumber.increase());
        this.remove(state);
        this.states.add(state);
    }

    private void remove(State state) {
        if (isDone() && (state instanceof Spare || !bonusFrame())) {
            states.remove(0);
        }
    }

    private State getState(int pinCount) {
        if (pitchNumber.isFirstPitch() || bonusFrame()) {
            return State.newState(BowlingPin.of(pinCount));
        }
        return State.newState(states.get(states.size() - 1).firstHit(), BowlingPin.of(pinCount));
    }

    private boolean bonusFrame() {
        return states.stream().anyMatch(state -> state instanceof Spare || state instanceof Strike);
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isDone() {
        if (bonusFrame()) {
            return pitchNumber.isBonusPitch();
        }
        return pitchNumber.isLastPitch();
    }

    @Override
    public String scoreResult() {
        return states.stream().map(state -> {
            if (pitchNumber.isSecondPitch()
               || (!pitchNumber.isBonusPitch() && hasStrike())
               || (pitchNumber.isBonusPitch() && state instanceof Miss)) {
                return state.score();
            }
            return state.totalScore();
        }).collect(Collectors.joining(PARTITION));
    }

    private boolean hasStrike() {
        return states.stream().anyMatch(status -> status instanceof Strike);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalFrame that = (FinalFrame)o;
        return Objects.equals(states, that.states) && Objects.equals(pitchNumber, that.pitchNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, pitchNumber);
    }
}

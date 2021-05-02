package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Continue implements State{
    private static final String STATE = "CONTINUE";

    private final Pins pins;

    private Continue(Pins pins){
        this.pins = pins;
    }

    public static Continue of(Pins pins) {
        return new Continue(pins);
    }

    @Override
    public State stateAfterPitch(Pins pitch) {
        pins.isSecondPitchable(pitch);
        if(pins.isSpare(pitch)){
            return Spare.of(pins, pitch);
        }
        return Miss.of(pins, pitch);
    }

    @Override
    public FrameScore frameScore() {
        return FrameScore.of(pins.fallingPins(), FrameScore.UNSCORED_SCORE);
    }

    @Override
    public FrameScore frameScoreWithBonus(FrameScore prevFrameScore) {
        FrameScore addedFrameScore = prevFrameScore.frameScoreWithBonus(pins.fallingPins(), FrameScore.NO_TRY);
        if(prevFrameScore.hasOneTryLeft()) {
            return addedFrameScore;
        }
        return FrameScore.of(addedFrameScore.score(), FrameScore.UNSCORED_SCORE);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String state() {
        return STATE;
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(this.pins.fallingPins());
        return new StateDTO(state(),pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Continue)) return false;
        Continue continueState= (Continue) o;
        return pins.equals(continueState.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}

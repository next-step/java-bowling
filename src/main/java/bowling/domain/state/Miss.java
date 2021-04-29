package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Miss extends FinishedState{
    private static final String STATE = "MISS";
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public String state() {
        return STATE;
    }

    @Override
    public FrameScore frameScore() {
        return FrameScore.of(firstPins.fallingPins() + secondPins.fallingPins(), FrameScore.NO_TRY);
    }

    @Override
    public FrameScore frameScoreWithBonus(FrameScore prevFrameScore) {
        if(prevFrameScore.hasOneTryLeft()) {
            return prevFrameScore.frameScoreWithBonus(firstPins.fallingPins(), FrameScore.NO_TRY);
        }
        return prevFrameScore.frameScoreWithBonus(firstPins.fallingPins() + secondPins.fallingPins(), FrameScore.NO_TRY);
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(Integer.valueOf(firstPins.fallingPins()));
        pins.add(Integer.valueOf(secondPins.fallingPins()));
        return new StateDTO(state(),pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miss)) return false;
        Miss miss = (Miss) o;
        return firstPins.equals(miss.firstPins) &&
                secondPins.equals(miss.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}

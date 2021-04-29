package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spare extends FinishedState {
    private static final String STATE = "SPARE";
    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Spare of(Pins firstPins, Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    @Override
    public String state() {
        return STATE;
    }

    @Override
    public FrameScore frameScore() {
        return FrameScore.of(Pins.MAX_PINS, FrameScore.ONE_TRY);
    }

    @Override
    public FrameScore frameScoreWithBonus(FrameScore prevFrameScore) {
        if(prevFrameScore.hasOneTryLeft()) {
            return prevFrameScore.frameScoreWithBonus(firstPins.fallingPins(), FrameScore.NO_TRY);
        }
        return prevFrameScore.frameScoreWithBonus(Pins.MAX_PINS, FrameScore.NO_TRY);
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
        System.out.println("@@@@@@@@@@@@");
        if (this == o) return true;
        if (!(o instanceof Spare)) return false;
        Spare spare = (Spare) o;
        return firstPins.equals(spare.firstPins) &&
                secondPins.equals(spare.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}

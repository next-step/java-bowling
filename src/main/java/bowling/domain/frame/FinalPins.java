package bowling.domain.frame;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalPins implements Pins {

    private static final int MAX_PIN = 10;
    private final List<Integer> downPins;
    private final Pins pins;

    private FinalPins(Pins pins) {
        this.pins = pins;
        this.downPins = new ArrayList<>();
    }

    public static Pins newInstance() {
        Pins bonusPins = new FinalPins(new NormalPins());
        return bonusPins;
    }

    @Override
    public void down(int downPin) {
        validate(downPin);

        if (this.pins.hasTurn()) {
            this.pins.down(downPin);
            return;
        }

        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.pins.hasTurn()) {
            return true;
        }

        if (this.downPins.size() < getBonusCount()) {
            return true;
        }

        return false;
    }

    @Override
    public PinsState getPinsState() {
        PinsState pinsState = this.pins.getPinsState();

        List<ScoreType> strikes = this.downPins.stream()
            .filter(integer -> integer.equals(MAX_PIN))
            .map(integer -> ScoreType.STRIKE)
            .collect(Collectors.toList());

        List<Integer> downPins = new ArrayList<>(pinsState.getDownPins());
        downPins.addAll(this.downPins);

        List<ScoreType> scoreTypes = new ArrayList<>(pinsState.getScoreTypes());
        scoreTypes.addAll(strikes);

        return new PinsState(downPins, scoreTypes);
    }

    private void validate(int downPin) {
        if (downPin < 0 || downPin > MAX_PIN) {
            throw new IllegalArgumentException("invalid downPin");
        }

        if (!hasTurn()) {
            throw new IllegalStateException("invalid state");
        }
    }

    private int getBonusCount() {
        PinsState pinsState = this.pins.getPinsState();
        if (pinsState.hasStrike()) {
            return 2;
        }
        if (pinsState.hasSpare()) {
            return 1;
        }
        return 0;
    }
}

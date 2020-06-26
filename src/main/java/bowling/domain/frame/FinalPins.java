package bowling.domain.frame;

import bowling.domain.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<ScoreType> getScoreType() {
        return this.pins.getScoreType();
    }

    @Override
    public List<Integer> getDownPins() {
        List<Integer> downPins =  new ArrayList<>(this.pins.getDownPins());
        downPins.addAll(this.downPins);
        return downPins;
    }

    @Override
    public int sum() {
        return this.pins.sum() + this.downPins.stream().reduce(0, Integer::sum);
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
        return getScoreType().map(ScoreType::getBonusBowlCount).orElse(0);
    }
}

package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinalFramePins implements Pins {

    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;
    private final List<Integer> downPins;
    private final Pins pins;

    private FinalFramePins(Pins pins) {
        this.pins = pins;
        this.downPins = new ArrayList<>();
    }

    public static Pins create() {
        return new FinalFramePins(new NormalPins());
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

        return this.downPins.size() < getBonusCount();
    }

    @Override
    public ScoreType getScoreType() {
        return this.pins.getScoreType();
    }

    @Override
    public List<Integer> getDownPins() {
        List<Integer> downPins = new ArrayList<>(this.pins.getDownPins());
        downPins.addAll(this.downPins);
        return downPins;
    }

    @Override
    public int sum() {
        return this.pins.sum() + this.downPins.stream().reduce(0, Integer::sum);
    }

    private void validate(int downPin) {
        if (downPin < MIN_PIN || downPin > MAX_PIN) {
            throw new IllegalArgumentException("0보다 작거나 10보다 클 수 없습니다.");
        }

        if (!hasTurn()) {
            throw new IllegalStateException("이미 다 던졌습니다.");
        }
    }

    private int getBonusCount() {
        return getScoreType().getBonusBowlCount();
    }
}

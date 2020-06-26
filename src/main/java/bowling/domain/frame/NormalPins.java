package bowling.domain.frame;

import bowling.domain.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalPins implements Pins {

    private static final int PIN_COUNT = 10;
    private static final int MAX_ROUND = 2;

    private final List<Integer> downPins = new ArrayList<>();

    @Override
    public void down(int downPin) {
        validate(downPin);
        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        if (sumOfDownPin() == 10) {
            return false;
        }

        return true;
    }

    @Override
    public Optional<ScoreType> getScoreType() {
        if (isStrike()) {
            return Optional.ofNullable(ScoreType.STRIKE);
        }

        if (isSpare()) {
            return Optional.ofNullable(ScoreType.SPARE);
        }

        if (!hasTurn()) {
            return Optional.ofNullable(ScoreType.MISS);
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> getDownPins() {
        return new ArrayList<>(this.downPins);
    }

    @Override
    public int sum() {
        return this.downPins.stream().reduce(0, Integer::sum);
    }

    private void validate(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("max down limit");
        }

        if (downPin < 0) {
            throw new IllegalArgumentException("invalid downPin");
        }

        if (sumOfDownPin() + downPin > PIN_COUNT) {
            throw new IllegalArgumentException("invalid downPin");
        }
    }

    private int sumOfDownPin() {
        return this.downPins.stream().reduce(0, Integer::sum);
    }

    private boolean isSpare() {
        if (this.downPins.size() == 2 && sumOfDownPin() == 10) {
            return true;
        }

        return false;
    }

    private boolean isStrike() {
        if (this.downPins.size() == 1 && this.downPins.get(0) == 10) {
            return true;
        }

        return false;
    }
}

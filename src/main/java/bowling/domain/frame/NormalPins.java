package bowling.domain.frame;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public PinsState getPinsState() {
        if (isStrike()) {
            return new PinsState(new ArrayList<>(this.downPins), Arrays.asList(ScoreType.STRIKE));
        }

        if (isSpare()) {
            return new PinsState(new ArrayList<>(this.downPins), Arrays.asList(ScoreType.SPARE));
        }

        if (this.downPins.size() == MAX_ROUND) {
            return new PinsState(new ArrayList<>(this.downPins), Arrays.asList(ScoreType.MISS));
        }

        return new PinsState(new ArrayList<>(this.downPins), Collections.EMPTY_LIST);
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

package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalPins implements Pins{
    private static final int FINAL_MAX_SIZE = 3;

    private final List<Pin> pins;

    private FinalPins() {
        pins = new ArrayList<>();
    }

    private FinalPins(List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins init() {
        return new FinalPins();
    }

    public Pins first(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        return new FinalPins(pins);
    }

    public FinalPins next(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        validMaxPins();
        return new FinalPins(pins);
    }

    private void validMaxPins() {
        // Final 은 기준이 다름.
        if (accumulatedPins() > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private int accumulatedPins() {
        int[] total = {0};
        pins.forEach(pin -> total[FIRST_INDEX] = pin.accumulated(total[FIRST_INDEX]));
        return total[FIRST_INDEX];
    }

    public boolean isEnd() {
        return pins.size() == ofFinalSize();
    }

    private int ofFinalSize() {
        ScoreRule finalRule = scoreRule();
        if (ScoreRule.STRIKE.equals(finalRule)
                || ScoreRule.STRIKE.equals(finalRule)) {
            return FINAL_MAX_SIZE;
        }
        return MAX_SIZE;
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(accumulatedPins(), (pins.size() < MAX_SIZE));
    }
}

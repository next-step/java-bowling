package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPins implements Pins{
    private List<Pin> pins;

    private NormalPins() {
        pins = new ArrayList<>();
    }

    private NormalPins(List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins init() {
        return new NormalPins();
    }

    public Pins first(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        return new NormalPins(pins);
    }

    public Pins next(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        validMaxPins();
        return new NormalPins(pins);
    }

    public boolean isEnd() {
        return isStrike() || pins.size() == NORMAL_PINS_MAX_SIZE;
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(accumulatedPins(), (pins.size() < NORMAL_PINS_MAX_SIZE));
    }

    @Override
    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }

    private void validMaxPins() {
        if (accumulatedPins() > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private int accumulatedPins() {
        int[] total = { 0 };
        pins.forEach(pin -> total[0] = pin.accumulated(total[0]));
        return total[0];
    }

    private boolean isStrike() {
        return ScoreRule.STRIKE == pins.get(FIRST_INDEX).scoreRule(IS_FIRST);
    }
}

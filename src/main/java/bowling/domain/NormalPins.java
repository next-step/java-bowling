package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPins implements Pins{
    private static final String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    private static final int MAX_PINS = 10;
    private static final int NORMAL_PINS_MAX_SIZE = 2;
    private static final int FIRST_INDEX = 0;
    private static final boolean IS_FIRST = true;

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
        return pins.stream()
                .mapToInt(Pin::pin)
                .sum();
    }

    private boolean isStrike() {
        return ScoreRule.STRIKE == pins.get(FIRST_INDEX).scoreRule(IS_FIRST);
    }
}

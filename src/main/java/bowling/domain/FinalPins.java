package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FinalPins implements Pins{
    private static final int FINAL_PINS_MAX_SIZE = 3;
    private static final int SKIP_INDEX_ONE = 1;
    private static final int SKIP_INDEX_ZERO = 0;

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

    public Pins next(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        validMaxPins();
        return new FinalPins(pins);
    }

    private void validMaxPins() {
        if (accumulatedPins() > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private int accumulatedPins() {
        int[] total = {0};
        IntStream.range(0, pins.size())
                .skip(skipSize())
                .limit(PINS_MAX_SIZE)
                .forEach(index -> total[FIRST_INDEX] = pins.get(index).accumulated(total[FIRST_INDEX]));
        return total[FIRST_INDEX];
    }

    private long skipSize() {
        return pins.get(FIRST_INDEX).isStrike() ? SKIP_INDEX_ONE : SKIP_INDEX_ZERO;
    }

    public boolean isEnd() {
        return pins.size() == finalSize();
    }

    private int finalSize() {
        ScoreRule finalRule = scoreRule();
        if (ScoreRule.STRIKE.equals(finalRule)
                || ScoreRule.STRIKE.equals(finalRule)) {
            return FINAL_PINS_MAX_SIZE;
        }
        return PINS_MAX_SIZE;
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(accumulatedPins(), (pins.size() < PINS_MAX_SIZE));
    }

    @Override
    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }
}

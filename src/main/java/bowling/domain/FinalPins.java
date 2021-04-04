package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FinalPins implements Pins{
    private static final int FINAL_PINS_MAX_SIZE = 3;
    private static final int MINUS_SIZE_ONE = 1;

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
        IntStream.range(FIRST_INDEX, pins.size())
                .skip(skipSize())
                .limit(NORMAL_PINS_MAX_SIZE)
                .forEach(index -> total[FIRST_INDEX] = pins.get(index).accumulated(total[FIRST_INDEX]));
        return total[FIRST_INDEX];
    }

    private long skipSize() {
        int result;
        for(result = FIRST_INDEX; result < pins.size() - MINUS_SIZE_ONE; result++) {
            if (!pins.get(result).isStrike()) {
                break;
            };
        }
        return result;
    }

    public boolean isEnd() {
        return pins.size() == finalSize();
    }

    private int finalSize() {
        ScoreRule finalRule = scoreRule();
        if (ScoreRule.STRIKE.equals(finalRule)
                || ScoreRule.SPARE.equals(finalRule)) {
            return FINAL_PINS_MAX_SIZE;
        }
        return NORMAL_PINS_MAX_SIZE;
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(accumulatedPins(), (pins.size() < NORMAL_PINS_MAX_SIZE));
    }

    @Override
    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }
}

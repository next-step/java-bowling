package bowling;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.CommonConstans.*;

public class FinalPins implements Pins {


    private final List<Pin> pins;

    private FinalPins() {
        this(new ArrayList<>());
    }

    private FinalPins(List<Pin> pins) {
        this.pins = new ArrayList<>(pins);
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
        Pin.accumulatedPins(pins);
        return new FinalPins(pins);
    }

    public boolean isEnd() {
        return pins.size() == finalSize();
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(Pin.accumulatedPins(pins), (pins.size() < NORMAL_PINS_MAX_SIZE));
    }

    @Override
    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }

    @Override
    public Score score() {
        int sum = pins.stream()
                .mapToInt(Pin::pin)
                .sum();

        return Score.of(sum);
    }

    @Override
    public BonusChance bonusChance() {
        return BonusChance.of(CommonConstans.ZERO);
    }

    private int finalSize() {
        ScoreRule finalRule = scoreRule();
        if (pins.get(ZERO).isStrike()
                || ScoreRule.SPARE == finalRule) {
            return FINAL_PINS_MAX_SIZE;
        }
        return NORMAL_PINS_MAX_SIZE;
    }
}

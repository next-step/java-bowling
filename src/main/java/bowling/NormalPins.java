package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.CommonConstans.*;

public class NormalPins implements Pins {


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

    @Override
    public Score score() {
        return Score.of(accumulatedPins());
    }

    private int accumulatedPins() {
        return pins.stream()
                .mapToInt(Pin::pin)
                .sum();
    }

    @Override
    public BonusChance bonusChance() {
        return BonusChance.of(scoreRule().bonusChance);
    }

    private void validMaxPins() {
        if (accumulatedPins() > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private boolean isStrike() {
        return ScoreRule.STRIKE == pins.get(ZERO)
                .scoreRule(IS_FIRST);
    }
}

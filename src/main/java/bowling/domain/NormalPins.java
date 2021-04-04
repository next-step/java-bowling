package bowling.domain;

import java.util.ArrayList;
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

    public NormalPins next(int countOfDownPin) {
        pins.add(Pin.of(countOfDownPin));
        validMaxPins();
        return new NormalPins(pins);
    }

    private void validMaxPins() {
        if (accumulatedPins() > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private int accumulatedPins() {
        int[] total = {0};
        pins.forEach(pin -> total[FIRST_INDEX] = pin.accumulated(total[FIRST_INDEX]));
        return total[FIRST_INDEX];
    }

    // 스트라이크 혹은 사이즈 2 일 떄 종료
    public boolean isEnd() {
        return isStrike() || pins.size() == 2;
    }

    private boolean isStrike() {
        return ScoreRule.STRIKE.equals(pins.get(FIRST_INDEX).ofScoreRule(IS_FIRST));
    }

    public ScoreRule scoreRule() {
        return ScoreRule.of(accumulatedPins(), (pins.size() == 1));
    }
}

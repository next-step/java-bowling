package bowling.domain.state;

import bowling.domain.Pins;

public class FirstBowl extends RunningState {
    private static final String GUTTER_SYMBOL = "-";

    private final Pins first;

    private FirstBowl(Pins first) {
        this.first = first;
    }

    public static ThrowingState create(Pins pins) {
        return new FirstBowl(pins);
    }

    @Override
    public ThrowingState bowl(Pins pins) {
        validate(pins);
        if (first.isSpare(pins)) {
            return Spare.create(first, pins);
        }
        return Miss.create(first, pins);
    }

    private void validate(Pins second) {
        if (!first.isValidSum(second)) {
            throw new IllegalArgumentException(String.format("first(%s), second(%s)의 핀의 합은 %d를 넘을 수 없습니다.", first, second, Pins.MAX_PINS));
        }
    }

    @Override
    public String symbol() {
        if (first.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(first.getValue());
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}

package bowling.domain.state;

import bowling.domain.Pins;

public class Spare extends EndedState {
    private static final String SPARE_SYMBOL = "/";

    private final Pins first;
    private final Pins second;

    private Spare(Pins first, Pins second) {
        validate(first, second);
        this.first = first;
        this.second = second;
    }

    private void validate(Pins first, Pins second) {
        if (!first.isSpare(second)) {
            throw new IllegalArgumentException(String.format("first(%s), second(%s)는 spare 상태가 아닙니다.", first, second));
        }
    }

    public static ThrowingState create(Pins first, Pins second) {
        return new Spare(first, second);
    }

    @Override
    public String symbol() {
        return String.format("%s|%s", first.getValue(), SPARE_SYMBOL);
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}

package bowling.domain.state;

import bowling.domain.Pins;

public class Spare extends EndedState {
    private static final String SPARE_SYMBOL = "/";

    private final Pins first;
    private final Pins second;

    private Spare(Pins first, Pins second) {
        this.first = first;
        this.second = second;
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

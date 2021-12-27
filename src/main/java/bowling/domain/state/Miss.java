package bowling.domain.state;

import bowling.domain.Pins;

public class Miss extends EndedState {
    private static final String FRAME_STATE_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pins first;
    private final Pins second;

    private Miss(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

    public static ThrowingState create(Pins first, Pins second) {
        return new Miss(first, second);
    }

    @Override
    public String symbol() {
        return String.format(FRAME_STATE_FORMAT, first.getValue(), secondValue());
    }

    private String secondValue() {
        if (second.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(second.getValue());
    }
}

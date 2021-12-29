package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Miss extends EndedState {
    private static final String FRAME_STATE_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pins first;
    private final Pins second;

    private Miss(Pins first, Pins second) {
        validate(first, second);
        this.first = first;
        this.second = second;
    }

    private void validate(Pins first, Pins second) {
        if (!first.isMiss(second)) {
            throw new IllegalArgumentException(String.format("first(%s), second(%s)는 miss 상태가 아닙니다.", first, second));
        }
    }

    public static ThrowingState create(Pins first, Pins second) {
        return new Miss(first, second);
    }

    @Override
    public String symbol() {
        return String.format(FRAME_STATE_FORMAT, formatValue(first), formatValue(second));
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public Score score() {
        return first.score(second);
    }

    @Override
    public Score scoreAfter(Score previousScore) {
        previousScore = previousScore.bowl(first.score());
        if (previousScore.hasFinalScore()) {
            return previousScore;
        }
        return previousScore.bowl(second.score());
    }

    private String formatValue(Pins pin) {
        if (pin.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pin.getValue());
    }
}

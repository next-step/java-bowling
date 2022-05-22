package bowling.domain.pin;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class FinalExtraPins {
    private static final int SPARE_SIZE = 1;
    private static final int STRIKE_SIZE = 2;

    private final List<Pin> extraPins = new ArrayList<>();

    public void addSpareExtra(Pin no) {
        if (extraPins.size() == SPARE_SIZE) {
            throw new IllegalStateException();
        }
        extraPins.add(no);
    }

    public void addStrikeExtra(Pin no) {
        validateStrikeExtra(no);
        extraPins.add(no);
    }

    private void validateStrikeExtra(Pin no) {
        if (extraPins.isEmpty() || first().isStrike()) {
            return;
        }

        if (extraPins.size() == STRIKE_SIZE) {
            throw new IllegalStateException();
        }
        if (!first().isMissWith(no) && !first().isSpareWith(no)) {
            throw new IllegalStateException();
        }
    }

    public Score spareScore(Score beforeScore) {
        if (extraPins.isEmpty()) {
            return beforeScore;
        }
        return beforeScore.addedScore(first());
    }

    public Score strikeScore(Score beforeScore) {
        if (extraPins.size() != STRIKE_SIZE) {
            return beforeScore;
        }
        return beforeScore.addedScore(first())
                .addedScore(second());
    }

    public String expression(State state) {
        String expression = state.expression();
        if (extraPins.isEmpty()) {
            return expression;
        }

        State firstExtraState = new Ready().bowl(first());
        if (extraPins.size() == SPARE_SIZE) {
            return expression + "|" + firstExtraState.expression();
        }
        if (firstExtraState instanceof Strike) {
            State secondExtraState = new Ready().bowl(second());
            return expression + "|" + firstExtraState.expression() + "|" + secondExtraState.expression();
        }
        return expression + "|" + firstExtraState.bowl(second()).expression();
    }

    private Pin first() {
        return extraPins.get(0);
    }

    private Pin second() {
        return extraPins.get(1);
    }
}

package bowling.game;

import bowling.game.vo.Pin;

import java.util.stream.Stream;

public enum State {
    STRIKE("X", (pin, beforePin) -> pin.isMaxCount()),
    SPARE("/", (pin, beforePin) -> Pin.add(pin, beforePin).isMaxCount()),
    GUTTER("-", (pin, beforePin) -> pin.isMinCount()),
    MISS("MISS", (pin, beforePin) -> !pin.isMinCount() && !pin.isMaxCount());

    private final String symbol;
    private final StateStrategy stateStrategy;

    State(final String symbol, final StateStrategy stateStrategy) {
        this.symbol = symbol;
        this.stateStrategy = stateStrategy;
    }

    public static State findState(Pin pin, Pin beforePin, boolean isNotFirstPitch) {
        State findState = Stream.of(values())
                .filter(state -> state.stateStrategy.isCorrectState(pin, beforePin))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        if (isNotFirstPitch && findState.isStrike()) {
            return SPARE;
        }

        return findState;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isStrike() {
        return this.equals(STRIKE);
    }

    public boolean isSpare() {
        return this.equals(SPARE);
    }

    public boolean isMiss() {
        return this.equals(MISS);
    }

    private interface StateStrategy {
        boolean isCorrectState(Pin pin, Pin beforePin);
    }
}

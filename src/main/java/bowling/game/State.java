package bowling.game;

import java.util.stream.Stream;

public enum State {
    STRIKE("X", (pinCount, beforeCount) -> pinCount == 10),
    SPARE("/", (pinCount, beforeCount) -> pinCount + beforeCount == 10),
    GUTTER("-", (pinCount, beforeCount) -> pinCount == 0),
    MISS("MISS", (pinCount, beforeCount) -> pinCount != 0 && pinCount != 10);

    private final String symbol;
    private final StateStrategy stateStrategy;

    State(final String symbol, final StateStrategy stateStrategy) {
        this.symbol = symbol;
        this.stateStrategy = stateStrategy;
    }

    public static State findState(int pinCount, int beforeCount, boolean isNotFirstPitch) {
        State findState = Stream.of(values())
                .filter(state -> state.stateStrategy.isCorrectState(pinCount, beforeCount))
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
        boolean isCorrectState(int pinCount, int beforeCount);
    }
}

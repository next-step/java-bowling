package bowling.domain.turn;

import bowling.exception.BowlingException;

public enum TurnState {
    FIRST, SECOND, THIRD;

    private static final String NOT_FOUND = "상태를 찾을 수 없습니다.";

    public TurnState getNextTurnState() {
        if (this.equals(TurnState.FIRST)) {
            return TurnState.SECOND;
        }

        if (this.equals(TurnState.SECOND)) {
            return TurnState.THIRD;
        }

        throw new BowlingException(NOT_FOUND);
    }
}

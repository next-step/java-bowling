package bowling.domain.frame;

import bowling.domain.Turn;

import java.util.Arrays;
import java.util.LinkedList;

enum FrameState {
    FIRST_TURN(0),
    SECOND_TURN(1),
    COMPLETED(2);

    private final int matchesTurnSize;

    FrameState(final int matchesTurnSize) {
        this.matchesTurnSize = matchesTurnSize;
    }

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public static FrameState of(LinkedList<Turn> turns) {
        if (!turns.isEmpty() && turns.getFirst().isAllClear()) {
            return COMPLETED;
        }

        int turnSize = turns.size();

        //noinspection
        return Arrays.stream(values())
                .filter(iFrameState -> iFrameState.matchesTurnSize == turnSize)
                .findFirst()
                .orElse(COMPLETED);
    }
}

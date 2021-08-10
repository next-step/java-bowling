package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.domain.Turn;
import bowling.exception.BowlFailureException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Frame {
    protected final LinkedList<Turn> turns;
    private final int currentFrameNumber;

    protected Frame(int frameNumber) {
        this.turns = new LinkedList<>();
        this.currentFrameNumber = frameNumber;
    }

    public void bowl(final TurnScore score) {
        if (isCompleted()) {
            throw new BowlFailureException();
        }
        turns.add(new Turn(score));
    }

    public boolean isCompleted() {
        return FrameState.of(turns).isCompleted();
    }

    public FrameScoreGrade frameScoreGrade() {
        if (!FrameState.of(turns).isCompleted()) {
            return FrameScoreGrade.EMPTY;
        }
        return FrameScoreGrade.of(turns.getFirst(), turns.getLast());
    }

    public TurnScores scores() {
        return turns.stream()
                .map(Turn::value)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(), TurnScores::new
                        )
                );
    }

    public int currentFrameNumber() {
        return currentFrameNumber;
    }

    public Frame nextFrame() {
        int nextFrameNumber = nextFrameNumber();

        return nextFrameNumber == Frames.MAX_FRAME_NUMBER ?
                new FinalFrame() : new Frame(nextFrameNumber);
    }

    private int nextFrameNumber() {
        return currentFrameNumber + 1;
    }

    private enum FrameState {
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

            //noinspection OptionalGetWithoutIsPresent
            return Arrays.stream(values())
                    .filter(iFrameState -> iFrameState.matchesTurnSize == turnSize)
                    .findFirst().get();
        }
    }
}

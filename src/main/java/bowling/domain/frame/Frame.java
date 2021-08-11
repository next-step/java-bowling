package bowling.domain.frame;

import bowling.domain.score.framescore.*;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.domain.Turn;
import bowling.exception.BowlFailureException;
import bowling.util.Pagination;

import java.util.*;
import java.util.stream.Collectors;

public class Frame {
    private static final int FIRST_FRAME_NUMBER = 1;

    protected final LinkedList<Turn> turns;

    protected Pagination<Frame> pagination;

    protected Frame() {
        this.turns = new LinkedList<>();
    }

    public static Frame firstFrame() {
        Frame newFrame = new Frame();
        newFrame.pagination = new Pagination<>(
                FIRST_FRAME_NUMBER, newFrame, Pagination.empty()
        );
        return newFrame;
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

    public boolean isCompletedFirstTurn() {
        return turns.size() >= 1;
    }

    public FrameScore frameScore() {
        if (!FrameState.of(turns).isCompleted()) {
            return new InProgress(turnScores());
        }

        Turn firstTurn = turns.getFirst();
        if (firstTurn.isAllClear()) {
            return Strike.of(pagination);
        }

        Turn secondTurn = turns.getLast();
        if (firstTurn.union(secondTurn).isAllClear()) {
            return Spare.of(pagination);
        }

        if (firstTurn.isGutter() && secondTurn.isGutter()) {
            return Miss.instance();
        }

        return new FrameScore(turnScores());
    }

    public TurnScores turnScores() {
        return turns.stream()
                .map(Turn::value)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(), TurnScores::new
                        )
                );
    }

    public int currentFrameNumber() {
        return pagination.currentPageNumber();
    }

    public Frame newNextFrame() {
        int nextFrameNumber = pagination.nextPageNumber();

        Frame nextInstance = nextFrameNumber == Frames.MAX_FRAME_NUMBER ?
                new FinalFrame() : new Frame();
        nextInstance.pagination = pagination.newNextPage(nextInstance);

        return nextInstance;
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

            //noinspection
            return Arrays.stream(values())
                    .filter(iFrameState -> iFrameState.matchesTurnSize == turnSize)
                    .findFirst()
                    .orElse(COMPLETED);
        }
    }
}

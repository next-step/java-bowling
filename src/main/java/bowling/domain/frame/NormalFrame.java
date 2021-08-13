package bowling.domain.frame;

import bowling.domain.Turn;
import bowling.domain.framescore.*;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;
import bowling.exception.InvalidFrameStateException;
import bowling.util.Pagination;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {
    private static final int FIRST_FRAME_NUMBER = 1;

    protected final LinkedList<Turn> turns;
    protected Pagination<Frame> pagination;

    protected NormalFrame() {
        this.turns = new LinkedList<>();
    }

    public static Frame firstFrame() {
        NormalFrame newFrame = new NormalFrame();
        newFrame.pagination = new Pagination<>(
                FIRST_FRAME_NUMBER, newFrame, Pagination.empty()
        );
        return newFrame;
    }

    @Override
    public void bowl(final TurnScore score) {
        if (isCompleted()) {
            throw new InvalidFrameStateException(InvalidFrameStateException.ErrorCode.BOWL_FAILURE);
        }
        turns.add(new Turn(score));
    }

    @Override
    public TurnScores turnScores() {
        return turns.stream()
                .map(Turn::value)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(), TurnScores::new
                        )
                );
    }

    @Override
    public FrameScore frameScore() {
        if (!isCompleted()) {
            return Normal.of(turnScores(), false);
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

        return Normal.of(turnScores(), true);
    }

    @Override
    public boolean isCompleted() {
        return FrameState.of(turns).isCompleted();
    }

    public Frame newNextFrame() {
        int nextFrameNumber = pagination.nextPageNumber();

        Frame nextFrame =
                nextFrameNumber == Frames.MAX_FRAME_NUMBER ?
                        new FinalFrame() : new NormalFrame();

        Pagination<Frame> nextPagination = pagination.newNextPage(nextFrame);
        if (nextFrame instanceof NormalFrame) {
            ((NormalFrame) nextFrame).pagination = nextPagination;
        }
        return nextFrame;
    }

    @Override
    public int currentFrameNumber() {
        return pagination.currentPageNumber();
    }
}

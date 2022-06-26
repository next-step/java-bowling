package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateEnum;

public class NormalFrame extends AbstractFrame {

    public NormalFrame() {
        super();
    }

    @Override
    public void bowl(int countOfFallenPins) {
        if (fullFrameState.firstBowl(countOfFallenPins)) {
            return;
        }
        fullFrameState.secondBowl(countOfFallenPins);
    }

    @Override
    public boolean capableOfAdditionalBowling() {
        return fullFrameState.capableOfAdditionalBowling();
    }

    @Override
    public int getScore() {
        Score score = getResultScore();

        if (score.canCalculateScore()) {
            return score.getScore();
        }

        return calculateAdditionalScore(score);
    }

    private Score getResultScore() {
        if (StateEnum.isFinished(getFirstHalfFrameState())) {
            return getFirstHalfFrameState().getScore();
        }

        if (StateEnum.isFinished(getSecondHalfFrameState()) || (StateEnum.isGutter(getFirstHalfFrameState()) && StateEnum.isGutter(getSecondHalfFrameState()) )) {
            return getSecondHalfFrameState().getScore();
        }
        return new Score();
    }


    private int calculateAdditionalScore(Score beforeScore) {
        Score beforeBowl = null;
        if (incalculable(next.getFirstHalfFrameState())) {
            return NOT_COMPLETED_CALCULATION;
        }

        if (beforeScore.getLeft() == CALCULATE_ONCE) {
            beforeBowl = beforeScore.bowl(next.fallenPinsCountOfFirstFrame());
        }

        if (beforeScore.getLeft() == CALCULATE_TWICE) {
            beforeBowl = calculateTwice(beforeScore);
        }

        if (beforeBowl == null) {
            return next.getScore();
        }

        return beforeBowl.getScore();
    }

    private Score calculateTwice(Score beforeScore) {
        Score beforeBowl = beforeScore.bowl(next.fallenPinsCountOfFirstFrame());
        return calculateSecondAdditionalScore(beforeBowl);
    }

    private Score calculateSecondAdditionalScore(Score beforeBowl) {
        if (StateEnum.isReady(next.getSecondHalfFrameState())) {
            return calculateAfterNextFrame(beforeBowl);
        }
        return bowl(beforeBowl, next.getSecondHalfFrameState(), next.fallenPinsCountOfSecondFrame());
    }

    private Score calculateAfterNextFrame(Score beforeBowl) {
        if (afterNext() != null) {
            return bowl(beforeBowl, afterNext().getFirstHalfFrameState(), afterNext().fallenPinsCountOfFirstFrame());
        }
        return bowl(beforeBowl, next.getSecondHalfFrameState(), next.fallenPinsCountOfSecondFrame());
    }

    private boolean incalculable(State state) {
        return StateEnum.isReady(state);
    }

    private Score bowl(Score beforeBowl, State state, int countOfPins) {
        if (incalculable(state)) {
            return new Score();
        }
        return beforeBowl.bowl(countOfPins);
    }
}

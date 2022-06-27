package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.StateEnum;

import java.util.Optional;

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
        return calculateAdditionalScore(calculateAdditionalScoreOnSpare());
    }

    private Score calculateAdditionalScoreOnSpare() {
        if (StateEnum.isFinished(getFirstHalfFrameState())) {
            return getFirstHalfFrameState().getScore();
        }

        if (StateEnum.isFinished(getSecondHalfFrameState()) || (StateEnum.isGutter(getFirstHalfFrameState()) && StateEnum.isGutter(getSecondHalfFrameState()) )) {
            return getSecondHalfFrameState().getScore();
        }
        return new Score();
    }


    private int calculateAdditionalScore(Score beforeScore) {
        if (beforeScore.canCalculateScore()) {
            return beforeScore.getScore();
        }

        Score resultScore = null;
        if (StateEnum.isSpare(getSecondHalfFrameState())) {
            resultScore = calculateAdditionalScoreOnSpare(beforeScore);
        }

        if (StateEnum.isStrike(getFirstHalfFrameState())) {
            resultScore = calculateAdditionalScoreOnStrike(beforeScore);
        }

        return Optional.ofNullable(resultScore).orElseGet(Score::new).getScore();
    }

    private Score calculateAdditionalScoreOnSpare(Score beforeScore) {
        if (StateEnum.isReady(next.getFirstHalfFrameState())) {
            return Score.incompleteCalculation();
        }
        return getSecondHalfFrameState().calculateAdditionalScore(beforeScore, next.fallenPinsCountOfFirstFrame());
    }

    private Score calculateAdditionalScoreOnStrike(Score beforeScore) {
        if (StateEnum.isReady(next.getFirstHalfFrameState())) {
            return Score.incompleteCalculation();
        }

        Score resultScore;
        if (StateEnum.isStrike(next.getFirstHalfFrameState())) {
            resultScore = getFirstHalfFrameState().calculateAdditionalScore(beforeScore, next.fallenPinsCountOfFirstFrame());
            return calculateSecondAdditionalScore(resultScore);
        }

        if (StateEnum.isReady(next.getSecondHalfFrameState())) {
            return Score.incompleteCalculation();
        }

        resultScore = getFirstHalfFrameState().calculateAdditionalScore(beforeScore, next.fallenPinsCountOfFirstFrame());
        return getFirstHalfFrameState().calculateAdditionalScore(resultScore, next.fallenPinsCountOfSecondFrame());
    }

    private Score calculateSecondAdditionalScore(Score resultScore) {
        if (StateEnum.isReady(afterNext().getFirstHalfFrameState())) {
            return Score.incompleteCalculation();
        }

        if (afterNext() != null) {
            return getFirstHalfFrameState().calculateAdditionalScore(resultScore, afterNext().fallenPinsCountOfFirstFrame());
        }
        return getFirstHalfFrameState().calculateAdditionalScore(resultScore, next.fallenPinsCountOfSecondFrame());
    }
}

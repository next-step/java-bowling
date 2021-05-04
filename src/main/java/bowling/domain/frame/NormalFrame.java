package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.frame.state.State;

public class NormalFrame extends Frame {
    protected Frame nextFrame;

    @Override
    public boolean isAvailable() {
        return stateHistory.getLatestState().hasNext();
    }

    @Override
    public State bowl(int inputScore) {
        State currentState = stateHistory.getLatestState();
        currentState.bowl(inputScore);

        State nextState = currentState.nextState();
        stateHistory.addState(nextState);

        if (nextState.hasNext()) {
            return currentState;
        }
        return nextState;
    }

    @Override
    public Score getScore() {
        State latestState = stateHistory.getLatestState();
        Score score = latestState.calculateScore();

        if (score.canCalculate() || nextFrame == null) {
            return score;
        }
        return nextFrame.additionalScore(score);
    }

    @Override
    protected Score additionalScore(Score score) {
        stateHistory.calculateScore(score);

        if (score.canCalculate() || nextFrame == null) {
            return score;
        }
        return nextFrame.additionalScore(score);
    }

    @Override
    public Frame createFrame(boolean isFinalFrame) {
        if (isFinalFrame) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }
        nextFrame = new NormalFrame();
        return nextFrame;
    }
}

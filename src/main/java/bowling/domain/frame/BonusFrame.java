package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.frame.state.FirstPitch;
import bowling.domain.frame.state.State;

public class BonusFrame extends NormalFrame {
    private int availability;

    public BonusFrame(int availability) {
        this.availability = availability;
    }

    @Override
    public boolean isAvailable() {
        if (availability > 0) {
            return true;
        }
        return false;
    }

    @Override
    public State bowl(int inputScore) {
        --availability;
        State currentState = stateHistory.getLatestState();
        currentState.bowl(inputScore);

        State nextState = currentState.nextState();
        stateHistory.addState(nextState);

        if (nextState.getStateValue() == 10) {
            stateHistory.addState(new FirstPitch());
        }

        if (nextState.hasNext()) {
            return currentState;
        }
        return nextState;
    }

    @Override
    protected Score additionalScore(Score score) {
        stateHistory.calculateScore(score);

        return score;
    }
}

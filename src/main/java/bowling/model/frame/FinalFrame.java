package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.Ready;
import bowling.model.state.Spare;
import bowling.model.state.State;
import bowling.model.state.Strike;

import java.util.List;

public class FinalFrame extends AbstractFrame {

    public static final int MAX_TRY_NUMBER = 3;
    private int roundNumber = 0;

    public FinalFrame(int number) {
        super(number);
    }

    @Override
    public void bowl(Pin pin) {
        super.bowl(pin);
        roundNumber++;
        if (getCurrentState().isFinished()) {
            states.add(new Ready());
        }
    }

    @Override
    public boolean isFinished() {
        if (getFirstState() instanceof Strike || getFirstState() instanceof Spare) {
            return roundNumber == MAX_TRY_NUMBER;
        }

        return getFirstState().isFinished();
    }

    private State getFirstState() {
        return states.get(0);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    public List<State> getStates() {
        return states;
    }
}

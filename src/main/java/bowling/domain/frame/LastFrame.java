package bowling.domain.frame;

import java.util.LinkedList;

import bowling.domain.BowlingGame;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;

public class LastFrame implements Frame {
    private static final String ALREADY_ENDED_FRAME_EXCEPTION_MESSAGE = "이미 종료된 프레임입니다.";

    private final LinkedList<State> states = new LinkedList<>();

    private int bowlCount = 0;

    public LastFrame() {
        states.add(new Ready());
    }

    @Override
    public void bowl(int falledPins) {
        if (isFinish()) {
            throw new IllegalStateException(ALREADY_ENDED_FRAME_EXCEPTION_MESSAGE);
        }

        State currentState = getCurrentState();
        bowlCount += 1;

        if (currentState.isFinish()) {
            states.add(new Ready().bowl(falledPins));
            return;
        }

        states.removeLast();
        states.push(currentState.bowl(falledPins));
    }

    @Override
    public int getFrameNumber() {
        return BowlingGame.LAST_FRAME;
    }

    @Override
    public boolean isFinish() {
        return hasNoBonusBowl() || bowlCount == 3;
    }

    private State getCurrentState() {
        return states.getLast();
    }

    private boolean hasNoBonusBowl() {
        return bowlCount == 2 && !getCurrentState().canBonusBowl();
    }
}

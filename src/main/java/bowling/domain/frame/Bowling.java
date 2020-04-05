package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.Objects;

public class Bowling {
    private static final int START_FRAME_NUMBER = 1;
    private static final int READY_FRAME = 0;

    private LinkedList<State> states;
    private Player player;

    public Bowling() {
        states = new LinkedList<>();
    }

    public Bowling(LinkedList<State> states, Player player) {
        this.states = states;
        this.player = player;
    }

    public void bowl(Pin fallenPins) {
        if (states.size() == READY_FRAME) {
            State ready = new Ready().bowl(fallenPins);
            ready.frame(START_FRAME_NUMBER);
            states.add(ready);
            return;
        }
        State state = states.getLast();
        states.add(createStateByBowling(state, fallenPins));
    }

    private State createStateByBowling(State state, Pin fallenPins) {
        if (state.isFinish()) {
            return createStateByFinalBowling(state, fallenPins);
        }
        State state1 = state.bowl(fallenPins);
        state1.frame(state.getFrame().getFrameNumber());
        return state1;
    }

    private State createStateByFinalBowling(State state, Pin fallenPins) {
        if (state.getFrame().getFrameNumber() == 10) {
            return state.bowl(fallenPins);
        }
        State ready = new Ready().bowl(fallenPins);
        ready.frame(state.getFrame().getFrameNumber() + 1);
        return ready;
    }

    public LinkedList<State> getStates() {
        LinkedList<State> statesBeforeCalculation = new LinkedList<>(this.states);
        LinkedList<State> states = new LinkedList<>();

        while (statesBeforeCalculation.size() != READY_FRAME) {
            states.add(calculate(statesBeforeCalculation));
        }
        return states;
    }

    private State calculate(LinkedList<State> states) {
        State first = states.removeFirst();
        for (State state : states) {
            first.renewScore(calculateByScore(first, state));
        }
        return first;
    }

    private Score calculateByScore(State first, State loopState) {
        if (Objects.nonNull(first.getScore()) && !first.getScore().isCalculation()) {
            return loopState.calculateByBeforeScore(first.getScore());
        }
        return first.getScore();
    }

    public int getFrameNumberLast() {
        if (states.size() == READY_FRAME) {
            return START_FRAME_NUMBER;
        }

        State state = states.getLast();
        if (state.isFinish()) {
            return state.getFrame().getFrameNumber() + 1;
        }
        return state.getFrame().getFrameNumber();
    }

    public String getName() {
        return player.getName();
    }

}

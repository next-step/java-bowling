package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.Objects;

public class Bowling {

    private LinkedList<State> states;
    private Player player;
    private int frameNumber = 1;

    public Bowling() {
        states = new LinkedList<>();
    }

    public Bowling(Player player) {
        this.player = player;
    }

    public void bowl(int fallenPins) {
        if (states.size() == 0) {
            State ready = new Ready().bowl(fallenPins);
            ready.frame(frameNumber);
            states.add(ready);
            return;
        }
        State state = states.getLast();
        states.add(createStateByBowling(state, fallenPins));
    }

    private State createStateByBowling(State state, int fallenPins) {
        if (state.isFinish()) {
            return createStateByFinalBowling(state, fallenPins);
        }
        return state.bowl(fallenPins);
    }

    private State createStateByFinalBowling(State state, int fallenPins) {
        if (frameNumber == 10) {
            return state.bowl(fallenPins);
        }
        State ready = new Ready().bowl(fallenPins);
        Frame2 frame = ready.frame(frameNumber + 1);
        increaseFrame(frame.getFrameNumber());
        return ready;
    }

    public LinkedList<State> getStates() {
        LinkedList<State> statesBeforeCalculation = new LinkedList<>(this.states);
        LinkedList<State> states = new LinkedList<>();

        while (statesBeforeCalculation.size() != 0) {
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

    private void increaseFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

}

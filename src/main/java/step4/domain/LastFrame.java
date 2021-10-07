package step4.domain;

import java.util.LinkedList;
import java.util.stream.Collectors;
import step4.domain.state.FirstBowl;
import step4.domain.state.Ready;
import step4.domain.state.State;

public class LastFrame implements Frame {
    private LinkedList<State> states = new LinkedList<>();

    public LastFrame(int i) {
        states.add(new Ready());
    }

    @Override
    public String calculateScoreFromNextFrame(Score state) {
        return null;
    }

    @Override
    public void throwBowl(int falledPins) {
        State currentState = states.getLast();

        if (currentState.isFinish()) {
            states.add(new FirstBowl(falledPins));
            return;
        }

        states.removeLast();
        states.add(currentState.throwBowl(falledPins));
    }

    @Override
    public Frame createFrame(int i) {
        return null;
    }

    @Override
    public String getScore() {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int round() {
        return 0;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public String getSymbol() {
        return states.stream()
            .map(state -> state.getSymbol())
            .collect(Collectors.joining("|"));
    }
}

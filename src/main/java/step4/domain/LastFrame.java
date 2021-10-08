package step4.domain;

import java.util.LinkedList;
import java.util.stream.Collectors;
import step4.domain.state.FirstBowl;
import step4.domain.state.Ready;
import step4.domain.state.State;

public class LastFrame implements Frame {
    private LinkedList<State> states = new LinkedList<>();
    private int turn = 0;

    public LastFrame(int i) {
        states.add(new Ready());
    }

    public LinkedList<State> getStates() {
        return states;
    }

    @Override
    public String calculateScoreFromNextFrame(Score beforeScore) {
        for (State state : states) {
            beforeScore = state.calculateScore(beforeScore);
            if (beforeScore.canCalculate()) {
                return beforeScore.getScore();
            }
        }
        return beforeScore.getScore();
    }

    @Override
    public void throwBowl(int falledPins) {
        turn ++;
        State currentState = states.getLast();

        if (currentState.isFinish()) {
            states.add(new Ready().throwBowl(falledPins));
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
        return states.getLast().getScore();
    }

    @Override
    public boolean isFinish() {
        return states.getLast().isFinish();
    }

    @Override
    public int round() {
        return 10;
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

    @Override
    public boolean isGameEnd() {
        if (turn == 3 || secondTurnIsNotSpareOrStrike()) {
            return true;
        }
        return false;
    }

    private boolean secondTurnIsNotSpareOrStrike() {
        if (turn == 2) {
            System.out.println(states.get(1).getSymbol());
            System.out.println(Integer.parseInt(states.get(1).getScore()));
            return Integer.parseInt(states.get(1).getScore()) != 10;
        }
        return false;
    }
}

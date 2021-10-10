package step4.domain;

import java.util.LinkedList;
import java.util.stream.Collectors;
import step4.domain.state.Ready;
import step4.domain.state.State;
import step4.exception.NeedAdditionalFrameException;

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
    public Score calculateScoreFromNextFrame(Score beforeScore) {
        Score score = beforeScore;

        for (State state : states) {
            score = state.calculateScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        throw new NeedAdditionalFrameException();

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
    public Score getScore() {
        int newScore = states.stream().map(state -> state.score().getScore()).reduce(0, Integer::sum);
        return new Score(newScore, 0);
    }

    @Override
    public boolean isFinish() {
        return isGameEnd();
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
            .map(State::getSymbol)
            .collect(Collectors.joining("|"));
    }

    @Override
    public boolean isGameEnd() {
        return turn == 3 || secondTurnIsNotSpareOrStrike();
    }

    private boolean secondTurnIsNotSpareOrStrike() {
        if (turn == 2) {
            return states.size() == 1 && states.get(0).getScore() != 10;
        }
        return false;
    }
}

package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.FirstPitch;
import bowling.domain.state.Pins;
import bowling.domain.state.State;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private final LinkedList<State> states;
    private int frameCount;

    public FinalFrame(LinkedList<State> states) {
        this.states = states;
    }

    public FinalFrame(int frameCount) {
        this.states = new LinkedList<>();
        this.states.add(new FirstPitch());
        this.frameCount = frameCount;
    }

    @Override
    public List<Pins> getPinsList() {
        return states.stream()
            .map(State::getPins)
            .collect(Collectors.toList());
    }

    @Override
    public int getFrameCount() {
        return frameCount;
    }

    @Override
    public Score addScore(Score score) {
        for (State state : states) {
            score = state.addNextScore(score);
        }
        return score;
    }

    @Override
    public Score getScore() {
        Iterator<State> iterator = states.iterator();
        Score score = iterator.next().getScore();
        while (iterator.hasNext()) {
            State state = iterator.next();
            score = state.addNextScore(score);
        }
        return score;
    }

    public boolean isGameEnd() {
        return getScore().isFinished();
    }


    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    public Frame bowl(int count) {
        State currentState = states.getLast();
        if (currentState.isFinished()) {
            currentState = new FirstPitch();
            states.add(currentState);
        }

        states.removeLast();
        currentState = currentState.bowl(count);
        states.add(currentState);
        return this;
    }
}

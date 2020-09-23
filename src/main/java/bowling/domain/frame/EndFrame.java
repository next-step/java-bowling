package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class EndFrame implements Frame {

    private final LinkedList<State> states = new LinkedList<>(Arrays.asList(Ready.of()));
    private final EndFrameCount count = EndFrameCount.of();

    public static Frame of() {
        return new EndFrame();
    }

    @Override
    public Frame bowl(Pin felledPin) {
        count.increment();
        bowlAndSet(felledPin);
        if (states.getLast().isEnd()) {
            states.add(Ready.of());
        }
        return this;
    }

    private void bowlAndSet(Pin felledPin) {
        State current = states.getLast();
        states.set(states.indexOf(current), current.bowl(felledPin));
    }

    @Override
    public boolean isEnd() {
        State firstState = states.getFirst();
        return firstState.isFrameFinish(firstState) || count.isMax();
    }

    @Override
    public Score calculateScore(Score baseScore, Frames frames) {
        Score lastScore = frames.getFrame(frames.getLastIndex()).getScore(frames);
        return lastScore;
    }

    @Override
    public Score getScore(Frames frames) {
        if (!isEnd()) {
            return Score.ofPending();
        }
        return states.stream()
                .map(State::getScore)
                .reduce(Score::add)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public Index getIndex() {
        return Index.of(Index.MAX_INDEX);
    }

    public LinkedList<State> getStates() {
        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndFrame endFrame = (EndFrame) o;
        return Objects.equals(states, endFrame.states) &&
                Objects.equals(count, endFrame.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, count);
    }
}

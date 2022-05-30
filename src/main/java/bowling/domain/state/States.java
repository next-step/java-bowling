package bowling.domain.state;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.view.OutputView.COLUMN;

public class States {
    private static final int MAX_BOWLS = 3;
    private static final int INITIAL_BOWLS = 1;

    private final List<State> states;

    public States(List<State> states) {
        validate(states);
        this.states = new ArrayList<>(states);
    }

    private void validate(List<State> states) {
        if (states == null) {
            throw new IllegalArgumentException("states는 null 일 수 없습니다.");
        }
    }

    public void bowl(Pin pin) {
        if (isEmpty()) {
            states.add(State.ready().bowl(pin));
            return;
        }
        states.add(last().bowl(pin));
    }

    public boolean isDone() {
        if (isEmpty() || states.size() == INITIAL_BOWLS) {
            return false;
        }

        if (states.size() == MAX_BOWLS) {
            return true;
        }

        return second().isNotTen() && first().isNotTen();
    }

    private State last() {
        return states.get(states.size() - 1);
    }

    private State first() {
        return states.get(0);
    }

    private State second() {
        if (states.size() > INITIAL_BOWLS) {
            return states.get(1);
        }
        return State.ready();
    }


    public static States initialize() {
        return new States(Collections.emptyList());
    }

    @Override
    public String toString() {
        return states.stream()
                .map(State::toSimpleString)
                .collect(Collectors.joining(COLUMN));
    }

    public Score score() {
        if (!isDone()) {
            return Score.unScorable();
        }

        Score score = Score.scorable();

        for (State state : states) {
            score = score.add(state.simpleScore());
        }

        return score;
    }


    public Score score(Score score) {
        if (isEmpty()) {
            return score;
        }

        Score nextScore = first().score(score);

        if (nextScore.canScore()) {
            return nextScore;
        }

        State second = second();

        if (second == null) {
            return nextScore;
        }

        return second.score(nextScore);
    }

    private boolean isEmpty() {
        return states.isEmpty();
    }
}

package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.State;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Last implements State {
    private static final int MAX_SIZE = 3;

    private LinkedList<State> states;

    public Last() {
        states = new LinkedList<>();
    }

    @Override
    public State roll(int count) {
        if (states.isEmpty()) {
            states.add(State.from(count));
            return this;
        }

        State current = states.getLast();

        if (current.isFinish()) {
            states.add(Finish.from(current, count));
            return this;
        }

        states.removeLast();
        states.add(current.roll(count));

        return this;
    }

    @Override
    public List<String> getValue() {
        return states.stream()
                .flatMap(state -> state.getValue().stream())
                .collect(Collectors.toList());

    }

    @Override
    public boolean isFinish() {
        if (states.isEmpty()) {
            return false;
        }

        State last = states.getLast();
        State first = states.getFirst();
        int size = states.size();

        if (size == 1 && !(last instanceof Open) && last.isFinish()) {
            return false;
        }

        if (size == 2 && first instanceof Strike && last instanceof Strike) {
            return false;
        }

        if (size == 2 && first instanceof Spare && last instanceof Playing) {
            return true;
        }

        if (size == MAX_SIZE) {
            return true;
        }

        return states.getLast().isFinish();
    }

    @Override
    public Score getScore() {
        return Score.ofOpen(
                states.stream()
                        .mapToInt(state -> state.getScore().toInt())
                        .sum()
        );
    }

    @Override
    public Score sumScore(Score before) {
        states.stream()
                .forEach(state -> {
                    if (before.canNextSum()) {
                        before.sum(state.getScore());
                    }
                });

        return before;
    }
}


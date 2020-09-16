package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.State;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Last extends Finished {

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

        State lastState = states.getLast();

        if (!lastState.isFinish()) {
            return false;
        }

        return !getCurrentScore().canNextSum();
    }

    private Score getCurrentScore() {
        State state = states.getFirst();
        Score score = Score.of(0, 0);

        if (!state.isFinish()) {
            return score;
        }

        score = state.getScore();

        for (int index = 1; index < states.size(); index++) {
            score = states.get(index).sumScore(score);
        }

        return score;
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


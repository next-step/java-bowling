package bowling_step3.domain.Frame;

import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.State;
import bowling_step3.exception.PitchOverBoundException;

import java.util.Objects;

public class NormalFrame extends Frame {

    private final int index;
    private State state;

    public NormalFrame(int index) {
        this.index = index;
        this.state = new Ready();
    }

    @Override
    public NormalFrame next() {
        return new NormalFrame(index + 1);
    }

    public static NormalFrame init() {
        return new NormalFrame(DEFAULT);
    }

    private void validate() {
        if (isFinish()) {
            throw new PitchOverBoundException();
        }
    }

    public String getKnockDownExpression() {
        return state.toString();
    }

    @Override
    public void pitch(int countOfKnockDown) {
        validate();
        this.state = state.pitch(countOfKnockDown);

        if (!isFinish()) {
            return;
        }
        this.score = state.getScore();
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public boolean hasScore() {
        return Objects.nonNull(score) && score.isFinishedCalculate();
    }

    @Override
    public void calculateScore(int index, int count) {
        if (this.index == index || score.isFinishedCalculate()) {
            return;
        }

        score.add(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return index == frame.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}


package bowling.model;

import java.util.Objects;

public class NormalRound implements Round{
    private State state;
    private Score score;

    public NormalRound() {
        this.state = new Ready();
    }

    public NormalRound(State state) {
        this.state = state;
    }

    @Override
    public State bowl(int countOfPin) {
        this.state = this.state.bowl(countOfPin);
        return this.state;
    }

    @Override
    public Round next(State state) {
        return new NormalRound(state);
    }

    @Override
    public int calcMaxTryCount() {
        if (state instanceof Strike) {
            return -1;
        }

        return 0;
    }

    public boolean isLastRound(int tryCount) {
        if (state instanceof Strike || tryCount == 3) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalRound that = (NormalRound) o;
        return Objects.equals(state, that.state) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, score);
    }
}

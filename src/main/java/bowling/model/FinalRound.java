package bowling.model;

import java.util.Objects;

public class FinalRound implements Round{
    private static int bonusCount = 1;

    private State state;
    private Score score;

    public FinalRound() {
        this.state = new Ready();
    }

    public FinalRound(State state) {
        this.state = state;
    }

    @Override
    public State bowl(int countOfPin) {
        this.state = this.state.bowl(countOfPin);
        return this.state;
    }

    @Override
    public Round next(State state) {
        return new FinalRound(state);
    }

    @Override
    public int calcMaxTryCount() {
        if (bonusCount == 0) {
            return 0;
        }

        if (state instanceof Strike || state instanceof Spare) {
            bonusCount -= 1;
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRound that = (FinalRound) o;
        return Objects.equals(state, that.state) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, score);
    }
}

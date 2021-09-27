package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.model.Score.*;

public class FinalRound implements Round{
    private static int bonusCount = 1;

    private State state;
    private List<Score> scores;

    public FinalRound() {
        this.state = new Ready();
    }

    public FinalRound(State state, List<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public State bowl(int countOfPin) {
        this.state = this.state.bowl(countOfPin);
        calculateScore(countOfPin);
        this.scores.add(createScore(countOfPin));
        return this.state;
    }

    @Override
    public Round next(State state, List<Score> scores) {
        return new FinalRound(state, scores);
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
    public List<Score> calculateScore(int countOfPin) {
        List<Score> list = new ArrayList<>();
        for (Score score : scores) {
            if (score.canCalculateScore()) {
                System.out.println(score.getScore());
            }else {
                list.add(score.bowl(countOfPin));
            }

            //score.output();
        }

        this.scores = list;
        return this.scores;
    }

    @Override
    public Score createScore(int countOfPin) {
        if (state instanceof Strike) {
            return ofStrike();
        }

        if (state instanceof Spare) {
            return ofSpare();
        }

        return ofMiss(countOfPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRound that = (FinalRound) o;
        return Objects.equals(state, that.state) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, scores);
    }
}

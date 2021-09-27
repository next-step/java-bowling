package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.model.Score.*;

public class NormalRound implements Round{
    private State state;
    private List<Score> scores;

    public NormalRound() {
        this.state = new Ready();
        this.scores = new ArrayList<>();
    }

    public NormalRound(State state, List<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public State bowl(int countOfPin) {
        state = state.bowl(countOfPin);
        scores.add(createScore(countOfPin));
        return this.state;
    }

    @Override
    public Round next(State state, List<Score> scores) {
        return new NormalRound(state, scores);
    }

    @Override
    public int calcMaxTryCount() {
        if (state instanceof Strike) {
            return -1;
        }

        return 0;
    }

    @Override
    public List<Score> calculateScore(int countOfPin) {
        List<Score> list = new ArrayList<>();
        System.out.println(scores.size());
        for (Score score : scores) {
            if (score.canCalculateScore()) {
                System.out.println(score.getScore());
            }else {
                list.add(score.bowl(countOfPin));
            }

        }

        this.scores = list;
        this.scores.add(createScore(countOfPin));


        for (Score score : scores) {

            //score.output();
        }
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
        return Objects.equals(state, that.state) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, scores);
    }
}

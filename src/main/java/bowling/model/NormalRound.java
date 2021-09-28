package bowling.model;

import bowling.CannotBowlException;

import java.util.*;

import static bowling.model.Score.*;

public class NormalRound implements Round{
    private State state;
    private LinkedList<Score> scores;

    public NormalRound() {
        this.state = new Ready();
        this.scores = new LinkedList<>();
        this.scores.add(new Score());
    }

    public NormalRound(State state, LinkedList<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        this.state = state.bowl(countOfPin);

        calculateScore(countOfPin);

        return state;
    }

    public NormalRound next() {
        return new NormalRound(new Ready(), nextScore());
    }

    @Override
    public int calcMaxTryCount() {
        if (state instanceof Strike) {
            return -1;
        }

        return 0;
    }

    @Override
    public void calculateScore(int countOfPin) {
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.remove().bowl(countOfPin);

            if (score.canCalculateScore()) {
                System.out.println(score.getScore());
            }else{
                scores.add(score);
            }
        }
    }

    @Override
    public LinkedList<Score> nextScore() {
        LinkedList<Score> nextScore = new LinkedList<>();

        if (state instanceof Strike) {
            nextScore.add(ofStrike());
        }

        if (state instanceof Spare) {
            nextScore.add(ofSpare());
        }

        nextScore.add(new Score());

        return nextScore;
    }

    public boolean isLastRound(int tryCount) {
        if (state instanceof Strike || tryCount == 3) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFinish() {
        if (state instanceof Strike || !(state instanceof FirstBowl)) {
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

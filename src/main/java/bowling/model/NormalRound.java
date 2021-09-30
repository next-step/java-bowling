package bowling.model;

import bowling.CannotBowlException;
import java.util.*;

import static bowling.model.Score.*;

public class NormalRound implements Round{
    private static final int BEFORE_FINAL_FRAME = 9;

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

    @Override
    public Round next(int frameNo) {
        if (frameNo == BEFORE_FINAL_FRAME) {
            return new FinalRound(new Ready(), nextScore());
        }
        return new NormalRound(new Ready(), nextScore());
    }

    public LinkedList<Score> nextScore() {
        LinkedList<Score> scores = new LinkedList<>();
        scores.addAll(this.scores);
        scores.add(new Score());

        return scores;
    }

    @Override
    public void calculateScore(int countOfPin) {
        int size = scores.size();

        for (int i = 0; i < size; i++) {
            scores.add(scores.remove().bowl(countOfPin));
        }

        if (state instanceof Strike) {
            scores.removeLast();
            scores.add(ofStrike());
        }

        if (state instanceof Spare) {
            scores.removeLast();
            scores.add(ofSpare());
        }
    }

    @Override
    public boolean isFinish() {
        if (state instanceof Strike || !(state instanceof FirstBowl)) {
            return true;
        }

        return false;
    }

    @Override
    public List<Integer> getScore() {
        List<Integer> calculatedScore = new ArrayList<>();
        int size = scores.size();

        for (int i = 0; i < size; i++) {
            Score score = scores.remove();

            if (score.canCalculateScore()) {
                calculatedScore.add(score.getScore());
            }else{
                scores.add(score);
            }
        }

        return calculatedScore;
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

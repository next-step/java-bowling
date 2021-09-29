package bowling.model;

import bowling.CannotBowlException;
import java.util.*;

import static bowling.model.Score.*;

public class NormalRound implements Round{
    private static final int FINAL_ROUND = 10;

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

    public Round next(int frameNo) {
        if (frameNo == FINAL_ROUND) {
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
    public int calcMaxTryCount() {
        if (state instanceof Strike) {
            return -1;
        }

        return 0;
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
        int totalScore = 0;
        int size = scores.size();

        for (int i = 0; i < size; i++) {
            Score score = scores.remove();

            if (score.canCalculateScore()) {
                totalScore += score.getScore();
                calculatedScore.add(totalScore);
            }else{
                scores.add(score.sum(totalScore));
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

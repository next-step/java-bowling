package bowling.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.model.Score.*;

import bowling.CannotBowlException;

public class FinalRound implements Round{
    private State state;
    private LinkedList<Score> scores;

    public FinalRound() {
        this.state = new Ready();
        this.scores = new LinkedList<>();
        this.scores.add(new Score());
    }

    public FinalRound(State state, LinkedList<Score> scores) {
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
    public boolean isFinish() {
        if (state instanceof Miss || state instanceof ThirdStrike || state instanceof ThirdSpare) {
            return true;
        }

        return false;
    }

    @Override
    public List<Integer> getScore() {
        return scores.stream()
                .filter(Score::canCalculateScore)
                .map(Score::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public Round next(int frameNo) {
        return new FinalRound();
    }

    @Override
    public void calculateScore(int countOfPin) {
        for (int i = 0; i < scores.size(); i++) {
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

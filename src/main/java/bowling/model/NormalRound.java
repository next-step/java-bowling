package bowling.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NormalRound extends AbstractRound {
    private static final int BEFORE_FINAL_FRAME = 9;

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
    public Round next(int frameNo) {
        if (frameNo == BEFORE_FINAL_FRAME) {
            return new FinalRound(new Ready(), nextScore());
        }

        return new NormalRound(new Ready(), nextScore());
    }

    public LinkedList<Score> nextScore() {
        LinkedList<Score> scores = new LinkedList<>(this.scores);
        scores.add(new Score());

        return scores;
    }

    @Override
    public List<Integer> getScore() {
        List<Integer> calculatedScore = new ArrayList<>();
        int size = scores.size();

        for (int i = 0; i < size; i++) {
            saveCalculatedScore(calculatedScore);
        }

        return calculatedScore;
    }

    private void saveCalculatedScore(List<Integer> calculatedScore) {
        Score score = scores.remove();
        if (score.canCalculateScore()) {
            calculatedScore.add(score.getScore());
        }

        if (!score.canCalculateScore()) {
            scores.add(score);
        }
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

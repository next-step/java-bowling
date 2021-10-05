package bowling.model;

import bowling.model.state.Ready;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NormalFrame extends AbstractFrame {
    private static final int BEFORE_FINAL_FRAME = 9;

    public NormalFrame() {
        this.state = new Ready();
        this.scores = new LinkedList<>();
        this.scores.add(new Score());
    }

    public NormalFrame(State state, LinkedList<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public Frame next(int frameNo) {
        if (frameNo == BEFORE_FINAL_FRAME) {
            return new FinalFrame(new Ready(), nextScore());
        }

        return new NormalFrame(new Ready(), nextScore());
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
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, scores);
    }
}

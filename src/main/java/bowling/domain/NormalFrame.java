package bowling.domain;

import java.util.ArrayList;
import java.util.Objects;

public class NormalFrame implements Frame {
    public static final Score MAX_SCORE = new Score(10);
    private final Scores scores;
    private FrameResult result;

    public NormalFrame() {
        this.scores = new Scores(new ArrayList<>());
    }

    public NormalFrame(Scores scores) {
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }

    @Override
    public boolean end() {
        if (scores.sum().equals(MAX_SCORE) || scores.size() == 2) {
            result = FrameResult.match(scores);
            return true;
        }
        return false;
    }

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return Objects.equals(scores, frame.scores) && result == frame.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, result);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                ", result=" + result +
                '}';
    }

    public FrameResult getResult() {
        return result;
    }
}

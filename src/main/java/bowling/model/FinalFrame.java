package bowling.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bowling.model.state.Ready;

public class FinalFrame extends AbstractFrame {
    public FinalFrame() {
        this.state = new Ready();
        this.scores = new LinkedList<>();
        this.scores.add(new Score());
    }

    public FinalFrame(State state, LinkedList<Score> scores) {
        this.state = state;
        this.scores = scores;
    }

    @Override
    public List<Integer> getScore() {
        return scores.stream()
                .filter(Score::canCalculateScore)
                .map(Score::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public Frame next(int frameNo) {
        return new FinalFrame();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, scores);
    }
}

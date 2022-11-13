package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Scores {
    private final List<Score> scores;

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public Scores(Integer... score) {
        this(Arrays.stream(score)
                .map(Score::new)
                .collect(Collectors.toList()));
    }

    public Score sum() {
        return scores.stream()
                .reduce(Score::add)
                .stream()
                .findFirst()
                .orElseGet(() -> new Score(0));
    }

    public int size() {
        return scores.size();
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                '}';
    }

    public List<Score> getScores() {
        return scores;
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}

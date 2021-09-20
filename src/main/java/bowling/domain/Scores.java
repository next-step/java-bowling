package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scores {

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void roll(final Score score) {
        this.scores.add(score);
    }

    public int downPins() {
        return this.scores.stream()
                .map(Score::getNumberOfPins)
                .reduce(0, Integer::sum);
    }

    public List<Score> elements() {
        return Collections.unmodifiableList(scores);
    }

    public int size() {
        return elements().size();
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                '}';
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

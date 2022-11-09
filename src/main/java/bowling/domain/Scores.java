package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scores {

    private final List<Score> scores;

    public Scores(Score... scores) {
        this.scores = new ArrayList<>();
        this.scores.addAll(List.of(scores));
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public boolean isEmpty() {
        return this.scores.isEmpty();
    }

    public Score first() {
        return this.scores.get(0);
    }

    public Score second() {
        return this.scores.get(1);
    }

    public Score third() {
        return this.scores.get(2);
    }
    
    public int size() {
        return this.scores.size();
    }

    public int sum() {
        return this.scores.stream()
                .mapToInt(Score::value)
                .sum();
    }

    public List<Score> scores() {
        return Collections.unmodifiableList(this.scores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores1 = (Scores) o;
        return Objects.equals(this.scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}

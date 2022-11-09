package bowling.domain;

import java.util.List;
import java.util.Objects;

public class Scores {

    private final List<Score> scores;

    public Scores(Score... scores) {
        this.scores = List.of(scores);
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

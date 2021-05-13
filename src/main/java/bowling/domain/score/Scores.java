package bowling.domain.score;

import bowling.exception.ScoreListNullPointerException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class Scores {

    private final List<Score> scores;

    public Scores(final List<Score> scores) {
        validateNull(scores);
        this.scores = scores;
    }

    private final void validateNull(final List<Score> scores) {
        if (Objects.isNull(scores)) {
            throw new ScoreListNullPointerException();
        }
    }

    public final Stream<Score> stream() {
        return scores.stream();
    }

    public final int sum() {
        return scores.stream()
                .mapToInt(Score::score)
                .sum();
    }

    public final int size() {
        return scores.size();
    }
}

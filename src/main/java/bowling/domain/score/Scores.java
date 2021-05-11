package bowling.domain.score;

import java.util.List;

public final class Scores {

    private final List<Score> scores;

    public static Scores of(final List<Score> scores) {
        return new Scores(scores);
    }

    private Scores(final List<Score> scores) {
        this.scores = scores;
    }


}

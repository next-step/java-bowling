package step4.domain;

import java.util.List;

public class Scores {
    private final List<Score> scores;

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> scores() {
        return scores;
    }
}

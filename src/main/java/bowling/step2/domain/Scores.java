package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final List<Score> scores;

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> scores() {
        return new ArrayList<>(scores);
    }
}

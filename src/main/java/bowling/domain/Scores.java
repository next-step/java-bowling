package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(final List<Score> scores) {
        this.scores = scores;
    }

    public void roll(final Score score) {
        this.scores.add(score);
    }

    public Score first() {
        return elements().get(0);
    }

    public Score second() {
        return elements().get(1);
    }

    public List<Score> elements() {
        return Collections.unmodifiableList(scores);
    }

    public int size() {
        return elements().size();
    }
}

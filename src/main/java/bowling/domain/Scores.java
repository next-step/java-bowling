package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void roll(final Score score) {
        this.scores.add(score);
    }

    public int knockedDownPins() {
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
}

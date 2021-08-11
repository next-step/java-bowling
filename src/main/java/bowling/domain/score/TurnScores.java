package bowling.domain.score;

import java.util.Iterator;
import java.util.List;

public class TurnScores implements Iterable<Score> {
    private final List<Score> scores;

    public TurnScores(final List<Score> scores) {
        this.scores = scores;
    }

    public Score first() {
        return scores.get(0);
    }

    public int size() {
        return scores.size();
    }

    @Override
    public Iterator<Score> iterator() {
        return scores.iterator();
    }
}

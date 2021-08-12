package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TurnScores implements Iterable<TurnScore> {
    private final List<TurnScore> scores;

    public TurnScores(final List<TurnScore> scores) {
        this.scores = scores;
    }

    public static TurnScores empty() {
        return new TurnScores(Collections.emptyList());
    }

    public TurnScore first() {
        return scores.get(0);
    }

    public TurnScore last() {
        return scores.get(scores.size() - 1);
    }

    public Score sum() {
        int sumValue = scores.stream().mapToInt(
                Score::value
        ).sum();

        return new Score(sumValue);
    }


    public TurnScores union(TurnScores turnScores) {
        List<TurnScore> newScores = new ArrayList<>(scores);
        newScores.addAll(turnScores.scores);

        return new TurnScores(newScores);
    }

    public int size() {
        return scores.size();
    }

    @Override
    public Iterator<TurnScore> iterator() {
        return scores.iterator();
    }
}

package bowling.domain.score;

import java.util.*;

public class TurnScores implements Iterable<TurnScore> {
    private final List<TurnScore> scores;

    private Score sumScoreCache;

    public TurnScores(final List<TurnScore> scores) {
        this.scores = scores;
    }

    public static TurnScores empty() {
        return new TurnScores(Collections.emptyList());
    }

    public boolean isEmpty() {
        return scores.isEmpty();
    }

    public TurnScore first() {
        return scores.get(0);
    }

    public TurnScore last() {
        return scores.get(scores.size() - 1);
    }

    public Score sum() {
        if (Objects.isNull(sumScoreCache)) {
            registerSumScoreCache();
        }
        return sumScoreCache;
    }

    private synchronized void registerSumScoreCache() {
        if (Objects.isNull(sumScoreCache)) {
            sumScoreCache = new Score(
                    scores.stream().mapToInt(Score::value).sum()
            );
        }
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

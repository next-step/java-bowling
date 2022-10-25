package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Bowling {
    private static final int MAX_TRIES = 2;
    private static final int MAX_SCORE_SUM = 10;

    private final List<Score> scores;

    Bowling(List<Score> scores) {
        this.scores = scores;

        if (totalScore() > MAX_SCORE_SUM) {
            throw new IllegalArgumentException(String.format("스코어 합은 %s 이하여야 합니다.", MAX_SCORE_SUM));
        }
    }

    public static Bowling init() {
        return new Bowling(List.of());
    }

    public Bowling bowl(Score score) {
        List<Score> result = new ArrayList<>(scores);
        result.add(score);
        return new Bowling(result);
    }

    public boolean isStrike() {
        if (tries() == 1) {
            return scores.get(0).isMax();
        }

        return false;
    }

    public boolean isSpare() {
        if (triesDone()) {
            return totalScore() == MAX_SCORE_SUM;
        }

        return false;
    }

    public boolean isFinished() {
        return isStrike() || triesDone();
    }

    public int tries() {
        return scores.size();
    }

    public List<Score> getScores() {
        return unmodifiableList(scores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bowling)) return false;

        Bowling bowling = (Bowling) o;

        return Objects.equals(scores, bowling.scores);
    }

    @Override
    public int hashCode() {
        return scores != null ? scores.hashCode() : 0;
    }

    private boolean triesDone() {
        return tries() == MAX_TRIES;
    }

    private int totalScore() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }
}

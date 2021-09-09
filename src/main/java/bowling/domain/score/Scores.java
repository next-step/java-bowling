package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Scores {
    protected List<Score> scores = new ArrayList<>();

    public void add(int score) {
        validate();
        if (firstTurn()) {
            scores.add(Score.of(score));
            return;
        }
        Score before = scores.get(size() - 1);
        scores.add(Score.of(before, score));
    }

    private void validate() {
        if (done()) {
            throw new IllegalStateException("현재 프레임에서 투구를 모두 마쳤습니다.");
        }
    }

    public abstract boolean done();

    protected boolean firstTurn() {
        return size() == 0;
    }

    private int size() {
        return scores.size();
    }

    public List<Score> getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        return scores.stream().map(Score::getDisplay).collect(Collectors.joining("|"));
    }
}

package bowling.domain.frame;


import java.util.List;
import java.util.Objects;

public abstract class Scores {
    protected List<Score> scores;

    public abstract boolean isFinished();

    public abstract void addScore(int score) throws Exception;

    public abstract List<Score> getScores();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
            말티푸 실버그레이
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores = (Scores) o;
        return Objects.equals(this.scores, scores.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                '}';
    }
}

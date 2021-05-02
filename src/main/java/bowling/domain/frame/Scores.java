package bowling.domain.frame;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Scores {
    protected List<Score> scores;

    public abstract boolean isFinished();

    public abstract void addScore(int score) throws Exception;

    public abstract List<Score> getScores();

    public List<Score> transSpareScores() {
        if (scores.contains(Score.SPARE)) {
            List<Score> result = new ArrayList<>(scores);
            int spareIndex = result.indexOf(Score.SPARE);
            result.set(spareIndex, Score.valueOf(Score.SPARE.getScore() - result.get(spareIndex - 1).getScore()));
            return result;
        }
        return this.scores;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
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

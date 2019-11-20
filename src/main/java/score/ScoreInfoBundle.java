package score;

import java.util.List;
import java.util.Objects;

public class ScoreInfoBundle {
    private static final int FIRST = 0;
    private final List<ScoreInfo> scoreInfoBundle;

    public ScoreInfoBundle(List<ScoreInfo> scoreInfoBundle) {
        this.scoreInfoBundle = scoreInfoBundle;
    }

    public int size() {
        return scoreInfoBundle.size();
    }

    public ScoreInfo getFirst() {
        return scoreInfoBundle.get(FIRST);
    }

    public void add(ScoreInfo scoreInfo) {
        scoreInfoBundle.add(scoreInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreInfoBundle that = (ScoreInfoBundle) o;
        return Objects.equals(scoreInfoBundle, that.scoreInfoBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreInfoBundle);
    }

    @Override
    public String toString() {
        return "ScoreInfoBundle{" +
                "scoreInfoBundle=" + scoreInfoBundle +
                '}';
    }
}

package score;

import java.util.List;
import java.util.Objects;

public class ScoreInfoBundle {
    private final List<ScoreInfo> scoreInfoBundle;

    public ScoreInfoBundle(List<ScoreInfo> scoreInfoBundle) {
        this.scoreInfoBundle = scoreInfoBundle;
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
}

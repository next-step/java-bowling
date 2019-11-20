package score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ScoreInfoBundle {
    private static final int FIRST = 0;
    private static final int MAX = 10;
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
        validate();
    }

    private void validate() {
        Integer reduce = scoreInfoBundle.stream()
                .map(ScoreInfo::getScore)
                .reduce(0, Integer::sum);

        if (reduce > MAX) {
            throw new IllegalArgumentException("두 점수의 합이 10점을 넘었습니다.");
        }
    }

    public boolean isStrike() {
        return scoreInfoBundle.stream()
                .anyMatch(ScoreInfo::isStrike);
    }

    public List<ScoreInfo> getScoreInfoBundle() {
        return Collections.unmodifiableList(scoreInfoBundle);
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

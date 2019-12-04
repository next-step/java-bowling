package score;

import score.framescore.FrameScore;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ScoreInfoBundle {
    private static final int MAX = 10;

    private final List<ScoreInfo> scoreInfoBundle;

    public ScoreInfoBundle(List<ScoreInfo> scoreInfoBundle) {
        this.scoreInfoBundle = scoreInfoBundle;
    }

    public int size() {
        return scoreInfoBundle.size();
    }

    public boolean isStrikeOrSpareOfLast() {
        ScoreInfo last = getLast();
        return last.isStrike() || last.isSpare();
    }

    public boolean isNormalOfLast() {
        return !isStrikeOrSpareOfLast();
    }

    public ScoreInfo nextScore(int score){
        return getLast().nextScore(score);
    }

    private ScoreInfo getLast() {
        return scoreInfoBundle.get(scoreInfoBundle.size() - 1);
    }

    public void add(ScoreInfo scoreInfo) {
        scoreInfoBundle.add(scoreInfo);
        validate();
    }

    public void addOnLast(ScoreInfo scoreInfo) {
        scoreInfoBundle.add(scoreInfo);
    }

    private void validate() {
        Integer reduce = scoreInfoBundle.stream()
                .map(ScoreInfo::getScore)
                .reduce(0, Integer::sum);

        if (reduce > MAX) {
            throw new IllegalArgumentException("두 점수의 합이 10점을 넘었습니다.");
        }
    }

    public boolean hasStrike() {
        return scoreInfoBundle.stream()
                .anyMatch(ScoreInfo::isStrike);
    }

    public List<ScoreInfo> getScoreInfoBundle() {
        return Collections.unmodifiableList(scoreInfoBundle);
    }

    public Status getStatus() {
        if (scoreInfoBundle.isEmpty()) {
            return Status.NONE;
        }
        return getLast().getStatus();
    }

    public int getSum() {
        return scoreInfoBundle.stream()
                .mapToInt(ScoreInfo::getScore)
                .sum();
    }

    public FrameScore addScore(FrameScore frameScore) {
        for (ScoreInfo scoreInfo : scoreInfoBundle) {
            frameScore = addScore(frameScore, scoreInfo);
        }
        return frameScore;
    }

    private FrameScore addScore(FrameScore before, ScoreInfo scoreInfo) {
        if (before.getAddCount() > 0) {
            return before.addScore(scoreInfo.getScore());
        }
        return before;
    }

    public boolean isEmpty() {
        return scoreInfoBundle.isEmpty();
    }

    public boolean hasOne() {
        return scoreInfoBundle.size() == 1;
    }

    public boolean hasTwo() {
        return scoreInfoBundle.size() == 2;
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

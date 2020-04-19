package bowling;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FrameScore {
    private final Score score;
    private final LeftScoreCount leftScoreCount;

    private FrameScore(final Score score, final LeftScoreCount leftScoreCount) {
        validateNull(score, leftScoreCount);
        this.score = score;
        this.leftScoreCount = leftScoreCount;
    }

    private void validateNull(final Score score, final LeftScoreCount leftScoreCount) {
        if (Objects.isNull(score) || Objects.isNull(leftScoreCount)) {
            throw new IllegalArgumentException("score and LeftScoreCount must be not null.");
        }
    }

    public static FrameScore newInstance(final Score score, final LeftScoreCount leftScoreCount) {
        return new FrameScore(score, leftScoreCount);
    }

    public static FrameScore createReady() {
        return new FrameScore(Score.ofZeroPins(), LeftScoreCount.of(2));
    }

    public static FrameScore createMiss(final Score score) {
        return new FrameScore(score, LeftScoreCount.of(0));
    }

    public static FrameScore createSpare() {
        return new FrameScore(Score.ofAllPins(), LeftScoreCount.of(1));
    }

    public static FrameScore createStrike() {
        return new FrameScore(Score.ofAllPins(), LeftScoreCount.of(2));
    }

    public Score getScore() {
        if (!canCalculateSelfScore()) {
            throw new IllegalStateException();
        }
        return score;
    }

    public boolean canCalculateSelfScore() {
        return leftScoreCount.isEqualTo(0);
    }

    public FrameScore addingUp(final List<Score> scores) {
        if(canCalculateSelfScore()) {
            return this;
        }

        return getAddingUpFrameScore(scores);
    }

    private FrameScore getAddingUpFrameScore(final List<Score> scores) {
        LeftScoreCount updateLeftScoreCount = LeftScoreCount.of(leftScoreCount);
        Iterator<Score> scoreIterator = scores.iterator();
        Score addingUpScore = Score.ofZeroPins();

        while(!updateLeftScoreCount.isEqualTo(0) && scoreIterator.hasNext()) {
            addingUpScore = addingUpScore.add(scoreIterator.next());
            updateLeftScoreCount = updateLeftScoreCount.minus();
        }

        return FrameScore.newInstance(score.add(addingUpScore), updateLeftScoreCount);
    }
}

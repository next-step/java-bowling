package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;

import java.util.Objects;

public class LastFrameInfo implements FrameInfo{
    private ScoreType scoreType;
    private final LastFrameBowlCount lastFrameBowlCount;

    public LastFrameInfo(ScoreType scoreType) {
        this.scoreType = scoreType;
        this.lastFrameBowlCount = new LastFrameBowlCount();
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        this.scoreType = scoreType.bowl(fallenPin);

        if (scoreType instanceof Strike || scoreType instanceof Spare) {
            lastFrameBowlCount.maxCountThree();
        }

        lastFrameBowlCount.bowl();
        return this.scoreType;
    }

    @Override
    public String scoreResult() {
        return scoreType.scoreResult();
    }

    public boolean isFrameEnd() {
        return scoreType.isFrameEnd();
    }

    public boolean bowlingGameEnd() {
        return lastFrameBowlCount.bowlingGameEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrameInfo that = (LastFrameInfo) o;
        return Objects.equals(scoreType, that.scoreType) && Objects.equals(lastFrameBowlCount, that.lastFrameBowlCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreType, lastFrameBowlCount);
    }
}

package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.Score;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrameInfo implements FrameInfo {
    private static final int MAX_NORMAL_FRAME = 9;

    private final int frameNo;
    private ScoreType scoreType;

    public NormalFrameInfo(int frameNo) {
        this.frameNo = frameNo;
        this.scoreType = new None();
    }

    public NormalFrameInfo(int frameNo, ScoreType scoreType) {
        this.frameNo = frameNo;
        this.scoreType = scoreType;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        this.scoreType = scoreType.bowl(fallenPin);
        return this.scoreType;
    }

    @Override
    public String scoreResult() {
        return scoreType.scoreResult();
    }

    public boolean isFrameEnd() {
        return scoreType.isFrameEnd();
    }

    public Score score() {
        return scoreType.score();
    }

    public Score calculate(Score beforeScore) {
        return scoreType.calculate(beforeScore);
    }

    public boolean nextFrameIsLastFrame() {
        return frameNo == MAX_NORMAL_FRAME;
    }

    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameInfo normalFrameInfo = (NormalFrameInfo) o;
        return frameNo == normalFrameInfo.frameNo && Objects.equals(scoreType, normalFrameInfo.scoreType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, scoreType);
    }
}

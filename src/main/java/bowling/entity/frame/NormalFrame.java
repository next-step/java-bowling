package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final int frameNo;
    private ScoreType scoreType;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.scoreType = new None();
    }

    public NormalFrame(int frameNo, ScoreType scoreType) {
        this.frameNo = frameNo;
        this.scoreType = scoreType;
    }

    public NormalFrame pinResult(Pin fallenPin) {
        scoreType = scoreType.pinResult(fallenPin);

        if (scoreType.isFrameEnd()) {
            return new NormalFrame(frameNo + 1);
        }
        return this;
    }

    @Override
    public boolean isFrameEnd() {
        return scoreType.isFrameEnd();
    }

    @Override
    public String scoreResult() {
        return scoreType.scoreResult();
    }

    @Override
    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo && Objects.equals(scoreType, that.scoreType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, scoreType);
    }
}

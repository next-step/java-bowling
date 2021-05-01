package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrameInfo implements FrameInfo{
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
    public ScoreType pinResult(Pin fallenPin) {
        this.scoreType = scoreType.pinResult(fallenPin);
        return this.scoreType;
    }

    public boolean nextFrameIsLastFrame() {
        return frameNo == MAX_NORMAL_FRAME;
    }

    public int frameNo(){
        return frameNo;
    }

    @Override
    public String scoreResult() {
        return scoreType.scoreResult();
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

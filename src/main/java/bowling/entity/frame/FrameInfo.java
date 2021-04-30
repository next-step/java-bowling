package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class FrameInfo {
    private static final int MAX_NORMAL_FRAME = 9;

    private final int frameNo;
    private ScoreType scoreType;

    public FrameInfo(int frameNo, ScoreType scoreType) {
        this.frameNo = frameNo;
        this.scoreType = scoreType;
    }

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

    public String scoreResult() {
        return scoreType.scoreResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameInfo frameInfo = (FrameInfo) o;
        return frameNo == frameInfo.frameNo && Objects.equals(scoreType, frameInfo.scoreType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, scoreType);
    }
}

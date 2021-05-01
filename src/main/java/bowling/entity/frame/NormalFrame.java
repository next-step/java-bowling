package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final NormalFrameInfo normalFrameInfo;
    private Frame nextFrame;

    public NormalFrame(int frameNo) {
        this.normalFrameInfo = new NormalFrameInfo(frameNo);
    }

    public NormalFrame(int frameNo, ScoreType scoreType) {
        this.normalFrameInfo = new NormalFrameInfo(frameNo, scoreType);
    }

    public Frame pinResult(Pin fallenPin) {

        if (normalFrameInfo.pinResult(fallenPin).isFrameEnd()) {
            nextFrame = nextFrame();
            return nextFrame;
        }

        return this;
    }

    private Frame nextFrame() {
        if (normalFrameInfo.nextFrameIsLastFrame()) {
            return new LastFrame();
        }
        return new NormalFrame(normalFrameInfo.frameNo() + 1);
    }

    public BowlingBoard bowlingBoard() {
        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard);
        return bowlingBoard;
    }

    @Override
    public boolean bowlingGameEnd() {
        return false;
    }

    public void addFrameResult(BowlingBoard bowlingBoard) {

        String scoreResult = normalFrameInfo.scoreResult();

        if (!(scoreResult.equals(""))) {
            bowlingBoard.addResult(new NormalFrameResult(scoreResult));
        }

        if (nextFrame != null) {
            nextFrame.addFrameResult(bowlingBoard);
        }
    }

    @Override
    public int frameNo() {
        return normalFrameInfo.frameNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(normalFrameInfo, that.normalFrameInfo) && Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalFrameInfo, nextFrame);
    }
}

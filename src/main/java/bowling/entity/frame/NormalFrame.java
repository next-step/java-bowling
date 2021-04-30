package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final int frameNo;
    private ScoreType scoreType;
    private Frame nextFrame;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.scoreType = new None();
    }

    public NormalFrame(int frameNo, ScoreType scoreType) {
        this.frameNo = frameNo;
        this.scoreType = scoreType;
    }

    public Frame pinResult(Pin fallenPin) {
        scoreType = scoreType.pinResult(fallenPin);

        if (scoreType.isFrameEnd()) {
            nextFrame = nextFrame();
            return nextFrame;
        }

        return this;
    }

    private Frame nextFrame() {
        if (frameNo == 9) {
            return new LastFrame();
        }
        return new NormalFrame(frameNo + 1);
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
        if (!(scoreType instanceof None)) {
            bowlingBoard.addResult(new NormalFrameResult(scoreType.scoreResult()));
        }

        if (nextFrame != null) {
            nextFrame.addFrameResult(bowlingBoard);
        }
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

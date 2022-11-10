package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.status.Status;

import java.util.Objects;
import java.util.Optional;

public class NormalFrame extends Frame {
    public static final int LAST_FRAME_NO = 9;

    private Frame nextFrame;

    public NormalFrame(int newFrameNo) {
        this.frameNo = newFrameNo;
    }

    @Override
    public Frame bowl(Pin pin) {
        status = status.bowl(pin);
        if (isFinished()) {
            nextFrame = nextFrame();
        }
        return this;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Boolean isFinished() {
        return status.isFinished();
    }

    @Override
    public Boolean isFinalFrame() {
        return false;
    }

    public Frame nextFrame() {
        if (frameNo == LAST_FRAME_NO && isFinished()) {
            return FinalFrame.init();
        }
        if (isFinished()) {
            return new NormalFrame(frameNo + 1);
        }
        return this;
    }

    @Override
    public Score getScore() {
        Score score = status.getScore();
        if (score.canCalculate()) {
            return score;
        }
        return nextFrame.addScore(score);
    }

    @Override
    public Score addScore(Score preScore) {
        Score score = preScore.addScore(status.getScore());
        if (score.canCalculate()) {
            return score;
        }

        return Optional.ofNullable(nextFrame)
                .map(nextFrame -> nextFrame.addScore(score))
                .orElse(preScore);
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, status);
    }
}

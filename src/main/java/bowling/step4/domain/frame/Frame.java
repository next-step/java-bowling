package bowling.step4.domain.frame;

import bowling.step4.domain.score.Score;

public abstract class Frame {

    protected int frameNo;

    public Frame(int frameNo) {
        validateFrameNo(frameNo);
        this.frameNo = frameNo;
    }

    public int getframeNo() {
        return frameNo;
    }

    protected abstract void validateFrameNo(int frameNo);

    public abstract Frame pitch(int pitch);

    public abstract boolean isGameEnd();

    public abstract Score getScore();

    public abstract Score calculateAdditionalScore(Score beforScore);
}

package game.bowling.domain;

import game.bowling.domain.status.FinalFirstThrow;
import game.bowling.domain.status.Status;

/**
 * Created by yusik on 2019/11/20.
 */
public class FinalFrame implements Frame {

    private final int frameNo;
    private Status status;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        status = new FinalFirstThrow();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void bowl(int score) {
        status = status.bowl(score);
    }

    @Override
    public boolean isFinish() {
        return status.isFinal();
    }

    @Override
    public Score getScore() {
        return status.getScore();
    }
}

package game.bowling.domain;

import game.bowling.domain.status.FirstThrow;
import game.bowling.domain.status.Status;

/**
 * Created by yusik on 2019/11/20.
 */
public class NormalFrame implements Frame {

    private final int frameNo;
    private Status status;

    private NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        status = new FirstThrow();
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }

    public NormalFrame next() {
        return new NormalFrame(this.frameNo + 1);
    }

    public FinalFrame last() {
        return new FinalFrame(this.frameNo + 1);
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public Score getScore() {
        return status.getScore();
    }

    @Override
    public void bowl(int score) {
        status = status.bowl(score);
    }

    public boolean isFinish() {
        return status.isFinal();
    }
}

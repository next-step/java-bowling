package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.status.*;

public class NormalFrame implements Frame {
    public static final int FRAME_MAX_SCORE = 10;

    public int index;
    public Score score;
    public boolean isEnd;
    public NormalFrame nextFrame;
    public FrameStatus status;

    public NormalFrame(int index, int score) {
        this.status = new Ready(false);
        this.index = index;
        bowl(score);
    }

    public static NormalFrame first(int score) {
        return new NormalFrame(0, score);
    }

    public NormalFrame next(int score) {
        if (isEnd) {
            nextFrame = new NormalFrame(index + 1, score);
            return nextFrame;
        }

        bowl(score);
        return this;
    }

    public void bowl(int score) {
        this.status = status.bowl(score);
        this.isEnd = isEndCondition(score);
    }

    public boolean isEnd() {
        return isEnd;
    }


    @Override
    public boolean isEndCondition(int score) {
        return this.status instanceof Strike ||
                this.status instanceof Spare ||
                this.status instanceof Miss;
    }

    public FrameStatus getStatus() {
        return status;
    }
}

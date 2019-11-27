package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.status.FrameStatus;
import bowling.domain.status.Ready;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int FRAME_MAX_SCORE = 10;

    public int index;
    public List<Integer> scores = new ArrayList<>();
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
        this.scores.add(score);
        this.isEnd = isEndCondition(score);
        this.status = status.bowl(score);
    }

    @Override
    public boolean hasSize(int size) {
        return scores.size() == size;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public boolean isEnd() {
        return isEnd;
    }


    @Override
    public boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == FRAME_MAX_SCORE;
    }

    public FrameStatus getStatus() {
        return status;
    }
}

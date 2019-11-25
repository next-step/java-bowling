package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int FRAME_MAX_SCORE = 10;
    public static final int SPARE_CHECK_SIZE = 2;

    public int index;
    public List<Integer> scores = new ArrayList<>();
    public boolean isEnd;
    public boolean isSpare;

    public NormalFrame(int index, int score) {
        this.index = index;
        addScore(score);
    }

    public static NormalFrame first(int score) {
        return new NormalFrame(0, score);
    }

    public NormalFrame next(int score) {
        if (isEnd) {
            return new NormalFrame(index + 1, score);
        }

        addScore(score);
        return this;
    }

    public void addScore(int score) {
        this.scores.add(score);
        this.isEnd = isEndCondition(score);
        checkIsSpare();
    }

    @Override
    public void checkIsSpare() {
        if (scores.size() != SPARE_CHECK_SIZE) {
            return;
        }

        isSpare = scores.get(0) + scores.get(1) == FRAME_MAX_SCORE;
    }

    @Override
    public boolean hasSize(int size) {
        return scores.size() == size;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isEnd() {
        return isEnd;
    }


    @Override
    public boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == FRAME_MAX_SCORE;
    }

}

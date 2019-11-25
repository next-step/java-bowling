package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int FRAME_MAX_SCORE = 10;
    public static final int SPARE_CHECK_SIZE = 2;

    public List<Integer> scores = new ArrayList<>();
    public boolean isEnd;
    public boolean isSpare;

    public void addScore(int score) {
        this.scores.add(score);
        this.isEnd = isEndCondition(score);
        checkIsSpare();
    }

    public void checkIsSpare() {
        if (scores.size() != SPARE_CHECK_SIZE) {
            return;
        }

        isSpare = scores.get(0) + scores.get(1) == FRAME_MAX_SCORE;
    }

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
        return hasNotStrikeOrSpare() || hasSize(3);
    }

    private boolean hasNotStrikeOrSpare() {
        return hasSize(2) && this.scores.get(0) + scores.get(1) < FRAME_MAX_SCORE;
    }

    public boolean hasNotFinalFrame() {
        return this.hasSize(0);
    }

}

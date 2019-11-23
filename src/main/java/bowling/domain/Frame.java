package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Frame {

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

    public abstract boolean isEndCondition(int score);

    public void checkIsSpare() {
        if (scores.size() != SPARE_CHECK_SIZE) {
            return;
        }

        isSpare = scores.get(0) + scores.get(1) == FRAME_MAX_SCORE;
    }

    public boolean hasSize(int size) {
        return scores.size() == size;
    }

    public abstract String getScoreFormat();

    public String findScoreFormat(int index) {
        int score = this.scores.get(index);

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }
        return String.valueOf(score);
    }

    public String findSecondScoreFormat() {
        int score = this.scores.get(1);
        if (isSpare) {
            return FrameType.SPARE.getCode();
        }

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }

        return String.valueOf(score);
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
}

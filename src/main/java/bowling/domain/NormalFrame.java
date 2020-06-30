package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private List<Integer> scores;
    private int remain;

    public NormalFrame() {
        this.scores = new ArrayList<>();
        remain = TOTAL_PIN_COUNT;
    }

    @Override
    public List<Shot> getResult() {
        int remain = TOTAL_PIN_COUNT;
        List<Shot> shots = new ArrayList<>();
        for (int i = IntegerUtils.ZERO; i < scores.size(); i++) {
            shots.add(Shot.of(i == IntegerUtils.ZERO, scores.get(i), remain - scores.get(i)));
            remain -= scores.get(i);
        }
        return shots;
    }

    @Override
    public boolean bowling(int score) {
        addScore(score);
        return isFrameFinish();
    }

    private void addScore(int score) {
        validateAddScore(score);

        remain -= score;
        scores.add(score);
    }

    private void validateAddScore(int score) {
        if (remain - score < IntegerUtils.ZERO) {
            throw new IllegalArgumentException("Max pin count per frame is " + TOTAL_PIN_COUNT);
        }

        if (scores.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }
    }

    private boolean isFrameFinish() {
        return remain == IntegerUtils.ZERO || scores.size() == MAX_THROW_COUNT;
    }
}

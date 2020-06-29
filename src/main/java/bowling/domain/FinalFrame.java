package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private List<Integer> scores;
    private int remain;

    public FinalFrame() {
        this.scores = new ArrayList<>();
        this.remain = TOTAL_PIN_COUNT;
    }

    @Override
    public List<ResultType> getResult() {
        int remain = TOTAL_PIN_COUNT;
        List<ResultType> resultTypes = new ArrayList<>();
        for (int i = IntegerUtils.ZERO; i < scores.size(); i++) {
            resultTypes.add(ResultType.of(i == IntegerUtils.ZERO, scores.get(i), remain - scores.get(i)));
            remain -= scores.get(i);
        }
        return resultTypes;
    }

    @Override
    public boolean bowling(int score) {
        if (isBonusFrame()) {
            scores.add(score);
            return true;
        }
        addScore(score);
        return isFrameFinish();
    }

    private boolean isBonusFrame() {
        if (remain == IntegerUtils.ZERO && scores.size() <= MAX_THROW_COUNT) {
            return true;
        }
        return false;
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
        return scores.size() == MAX_THROW_COUNT && remain > IntegerUtils.ZERO;
    }
}

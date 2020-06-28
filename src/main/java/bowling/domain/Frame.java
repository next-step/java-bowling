package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public static final int TOTAL_PIN_COUNT = 10;
    public static final int MAX_THROW_COUNT = 2;
    private List<Integer> pins;
    private int remain;

    public Frame() {
        this.pins = new ArrayList<>();
        remain = TOTAL_PIN_COUNT;
    }

    public String getResult() {
        return "";
    }

    public ResultType getResultType() {
        return ResultType.of(this);
    }

    public void addScore(int score) {
        if (remain - score < IntegerUtils.ZERO) {
            throw new IllegalArgumentException("Max pin count per frame is " + TOTAL_PIN_COUNT);
        }

        if (pins.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }

        remain -= score;
        pins.add(score);
    }

    public int getThrowCount() {
        return pins.size();
    }

    public int getRemain() {
        return remain;
    }
}

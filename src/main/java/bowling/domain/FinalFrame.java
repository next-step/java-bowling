package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private List<Integer> pins;
    private int remain;

    public FinalFrame() {
        this.pins = new ArrayList<>();
        this.remain = TOTAL_PIN_COUNT;
    }

    @Override
    public List<ResultType> getResult() {
        int remain = TOTAL_PIN_COUNT;
        List<ResultType> resultTypes = new ArrayList<>();
        for (int i = IntegerUtils.ZERO; i < pins.size(); i++) {
            resultTypes.add(ResultType.of(i == IntegerUtils.ZERO, pins.get(i), remain - pins.get(i)));
            remain -= pins.get(i);
        }
        return resultTypes;
    }

    @Override
    public boolean addScore(int score) {
        if (isBonusFrame()) {
            pins.add(score);
            return true;
        }

        if (remain - score < IntegerUtils.ZERO) {
            throw new IllegalArgumentException("Max pin count per frame is " + TOTAL_PIN_COUNT);
        }

        if (pins.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }

        remain -= score;
        pins.add(score);

        return pins.size() == MAX_THROW_COUNT && remain > IntegerUtils.ZERO;
    }

    private boolean isBonusFrame() {
        if (remain == IntegerUtils.ZERO && pins.size() <= MAX_THROW_COUNT) {
            return true;
        }
        return false;
    }
}

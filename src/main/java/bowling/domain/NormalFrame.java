package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int TOTAL_PIN_COUNT = 10;
    public static final int MAX_THROW_COUNT = 2;
    private List<Integer> pins;
    private int remain;

    public NormalFrame() {
        this.pins = new ArrayList<>();
        remain = TOTAL_PIN_COUNT;
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
        if (remain - score < IntegerUtils.ZERO) {
            throw new IllegalArgumentException("Max pin count per frame is " + TOTAL_PIN_COUNT);
        }

        if (pins.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }

        remain -= score;
        pins.add(score);

        return remain == IntegerUtils.ZERO || pins.size() == MAX_THROW_COUNT;
    }
}

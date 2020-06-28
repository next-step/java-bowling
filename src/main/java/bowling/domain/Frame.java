package bowling.domain;

import bowling.common.IntegerUtils;
import org.hibernate.type.IntegerType;

public class Frame {
    public static final int TOTAL_PIN_COUNT = 10;
    public static final int MAX_THROW_COUNT = 2;
    private int throwCount;
    private int remain;

    public Frame() {
        throwCount = IntegerType.ZERO;
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

        if (throwCount + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }

        throwCount++;
        remain -= score;
    }

    public int getThrowCount() {
        return throwCount;
    }

    public int getRemain() {
        return remain;
    }
}

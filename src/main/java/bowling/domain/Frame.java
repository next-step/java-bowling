package bowling.domain;

import org.hibernate.type.IntegerType;

public class Frame {
    private static final int TOTAL_PIN_COUNT = 10;
    private static final int MAX_THROW_COUNT = 2;
    private int throwCount;
    private int remain;

    public Frame() {
        throwCount = IntegerType.ZERO;
        remain = TOTAL_PIN_COUNT;
    }

    public String getResult() {
        if (throwCount == 1 && remain == 0) {
            return "strike";
        }
        if (throwCount == 2 && remain == 0) {
            return "spare";
        }
        if (throwCount == 2 && remain == TOTAL_PIN_COUNT) {
            return "gutter";
        }
        if (throwCount == 2 && remain > 0) {
            return "miss";
        }
        throw new NotFinishedFrameException("Frame is not finished");
    }

    public void addScore(int score) {
        if (remain - score < 0) {
            throw new IllegalArgumentException("Max pin count per frame is " + TOTAL_PIN_COUNT);
        }

        if (throwCount + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }

        throwCount++;
        remain-=score;
    }
}

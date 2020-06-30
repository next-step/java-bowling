package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Score {
    public static final int MAX_THROW_COUNT = 2;
    private final List<Pin> scores;
    private int remain;

    public Score() {
        this.scores = new ArrayList<>();
        this.remain = Pin.MAX_COUNT;
    }

    public void add(Pin pin) {
        validateThrow(pin);

        scores.add(pin);
        remain -= pin.getCount();
    }

    private void validateThrow(Pin pin) {
        if (remain - pin.getCount() < 0) {
            throw new IllegalArgumentException("remain can not less than 0");
        }

        if (scores.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }
    }

    public int getRemain() {
        return remain;
    }
}

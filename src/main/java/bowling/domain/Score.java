package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private final List<Pin> scores;
    private int remain;

    public Score() {
        this.scores = new ArrayList<>();
        this.remain = Pin.MAX_COUNT;
    }

    public void add(Pin pin) {
        if (remain - pin.getCount() < 0) {
            throw new IllegalArgumentException("remain can not less than 0");
        }
        scores.add(pin);
        remain -= pin.getCount();
    }

    public int getRemain() {
        return remain;
    }
}

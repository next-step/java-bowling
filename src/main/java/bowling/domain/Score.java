package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class Score {
    public static final int MAX_THROW_COUNT = 2;
    private final List<Pin> scores;
    private final List<Shot> shotHistory;
    private int remain;

    public Score() {
        this.scores = new ArrayList<>();
        this.shotHistory = new ArrayList<>();
        this.remain = Pin.MAX_COUNT;
    }

    public boolean add(Pin pin) {
        validateThrow(pin);

        insertShotHistory(pin);

        scores.add(pin);
        remain -= pin.getCount();

        return scores.size() == MAX_THROW_COUNT;
    }

    private void validateThrow(Pin pin) {
        if (remain - pin.getCount() < 0) {
            throw new IllegalArgumentException("remain can not less than 0");
        }

        if (scores.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }
    }

    private void insertShotHistory(Pin pin) {
        shotHistory.add(Shot.of(scores.size() == IntegerUtils.ZERO, pin.getCount(), remain - pin.getCount()));
    }

    public List<Shot> getShotHistory() {
        return shotHistory;
    }

    public int getRemain() {
        return remain;
    }
}

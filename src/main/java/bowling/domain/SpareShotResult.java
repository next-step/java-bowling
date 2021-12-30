package bowling.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import bowling.engine.Shot;

public enum SpareShotResult implements Shot {
    SPARE_ONE(1, "/"),
    SPARE_TWO(2, "/"),
    SPARE_THREE(3, "/"),
    SPARE_FOUR(4, "/"),
    SPARE_FIVE(5, "/"),
    SPARE_SIX(6, "/"),
    SPARE_SEVEN(7, "/"),
    SPARE_EIGHT(8, "/"),
    SPARE_NINE(9, "/"),
    SPARE_TEN(10, "/");

    private static final Map<Integer, Shot> spareScoreMap = new HashMap<>();
    static {
        Arrays.stream(values())
                .forEach(SpareShotResult::initSpareScore);
    }
    private static void initSpareScore(Shot shot) {
        spareScoreMap.put(shot.toInt(), shot);
    }

    private final int result;
    private final String display;

    SpareShotResult(int result, String display) {
        this.result = result;
        this.display = display;
    }

    public static Shot of(Shot previous, Shot score) {
        if (previous == null || score == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        if (previous.toInt() + score.toInt() == 10) {
            return spareScoreMap.get(score.toInt());
        }

        return score;
    }

    @Override
    public int toInt() {
        return result;
    }

    @Override
    public String toString() {
        return display;
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
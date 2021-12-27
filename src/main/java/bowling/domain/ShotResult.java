package bowling.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import bowling.engine.Shot;

public enum ShotResult implements Shot {
    GUTTER(0, "-"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    STRIKE(10, "X");

    public static final int DOUBLE_STRIKE_COUNT = 2;
    public static final int TURKEY_STRIKE_COUNT = 3;

    private static final Map<Integer, ShotResult> frameScoreMap = new HashMap<>();
    static {
        Arrays.stream(values())
                .forEach(ShotResult::initFrameScore);
    }

    private static void initFrameScore(ShotResult shotResult) {
        frameScoreMap.put(shotResult.toInt(), shotResult);
    }

    private final int result;
    private final String display;

    ShotResult(int result, String display) {
        this.result = result;
        this.display = display;
    }

    public static ShotResult of(int score) {
        if (score < GUTTER.result || score > STRIKE.result) {
            throw new IllegalArgumentException("score must be 0 to 10");
        }

        return frameScoreMap.get(score);
    }

    @Override
    public int toInt() {
        return result;
    }

    @Override
    public String toString() {
        return display;
    }
}

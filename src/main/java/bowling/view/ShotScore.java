package bowling.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import bowling.engine.Shot;

public enum ShotScore {
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
    SPARE(10, "/"),
    STRIKE(10, "X");

    private static final Map<Integer, ShotScore> frameScoreMap = new HashMap<>();
    static {
        Arrays.stream(values())
                .forEach(ShotScore::initFrameScore);
    }
    private static void initFrameScore(ShotScore shot) {
        frameScoreMap.put(shot.result, shot);
    }

    private final int result;
    private final String display;

    ShotScore(int result, String display) {
        this.result = result;
        this.display = display;
    }

    public static ShotScore of(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("shot cannot be null");
        }

        if (shot.isSpare()) {
            return SPARE;
        }

        return frameScoreMap.get(shot.toInt());
    }

    @Override
    public String toString() {
        return display;
    }
}

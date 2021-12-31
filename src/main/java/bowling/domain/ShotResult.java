package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import bowling.engine.Shot;

public class ShotResult implements Shot {
    private static final Map<Integer, Shot> frameScoreMap = new HashMap<>();
    private static void initFrameScore(int score) {
        frameScoreMap.put(score, new ShotResult(score, false));
    }

    static {
        IntStream.rangeClosed(0, 10)
                .forEach(ShotResult::initFrameScore);
    }

    public static final Shot GUTTER = frameScoreMap.get(0);
    public static final Shot STRIKE = frameScoreMap.get(10);

    private final int result;
    private final boolean isSpare;

    protected ShotResult(int result, boolean isSpare) {
        this.result = result;
        this.isSpare = isSpare;
    }

    public static Shot of(int score) {
        if (score < GUTTER.toInt() || score > STRIKE.toInt()) {
            throw new IllegalArgumentException("score must be 0 to 10");
        }

        return frameScoreMap.get(score);
    }

    public static Shot of(Shot previous, Shot shot) {
        if (previous == null || shot == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        if (previous.toInt() + shot.toInt() == 10) {
            return new ShotResult(shot.toInt(), true);
        }

        return shot;
    }

    @Override
    public int toInt() {
        return result;
    }

    @Override
    public boolean isSpare() {
        return isSpare;
    }
}

package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.Shots;

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

        if (shot.isSpareWith(previous)) {
            return new ShotResult(shot.toInt(), true);
        }

        return shot;
    }

    @Override
    public Shot add(Shot other) {
        return of(result + other.toInt());
    }

    @Override
    public int toInt() {
        return result;
    }

    @Override
    public boolean isClear() {
        return isSpare || result == Shots.NUMBER_OF_PINS;
    }

    @Override
    public boolean isSpare() {
        return isSpare;
    }

    @Override
    public boolean isSpareWith(Shot previous) {
        return previous != STRIKE && !previous.isSpare() && add(previous).isClear();
    }

    @Override
    public Score score() {
        return BowlingScore.of(result);
    }

    @Override
    public BonusScores bonusScore() {
        if (isSpare) {
            return ClearBonusScores.bySpare();
        }

        if (isClear()) {
            return ClearBonusScores.byStrike();
        }

        return ClearBonusScores.byNone();
    }

    @Override
    public String toString() {
        return "ShotResult{" +
                "result=" + result +
                ", isSpare=" + isSpare +
                '}';
    }
}

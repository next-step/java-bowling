package bowling.domain;

import java.util.Arrays;

public enum ScoreRull {
    STRIKE("X", 10),
    SPARE("/", 10),
    MISS("", 9),
    GUTTER("-", 0);

    private final String symbol;
    private final int fellDownPinCount;

    ScoreRull(String symbol, int fellDownPinCount){
        this.symbol = symbol;
        this.fellDownPinCount = fellDownPinCount;
    }

    public static ScoreRull of(int fellDownPinCount, boolean atOnce) {
        return Arrays.stream(ScoreRull.values())
                .filter(scoreRull -> scoreRull.isSameFellDownPinCount(fellDownPinCount))
                .filter(scoreRull -> atOnce || !scoreRull.equals(STRIKE))
                .findFirst()
                .orElse(MISS);
    }

    private boolean isSameFellDownPinCount(int fellDownPinCount) {
        return this.fellDownPinCount == fellDownPinCount;
    }

    @Override
    public String toString() {
        return "ScoreRull{" +
                "symbol='" + symbol + '\'' +
                ", fellDownPinCount=" + fellDownPinCount +
                '}';
    }
}

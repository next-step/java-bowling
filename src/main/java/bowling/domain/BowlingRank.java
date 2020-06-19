package bowling.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum BowlingRank {
    STRIKE((firstPitch, secondPitch) -> firstPitch == 10);

    private BiFunction<Integer, Integer, Boolean> searchingFunction;

    private BowlingRank(BiFunction<Integer, Integer, Boolean> searchingFunction) {
        this.searchingFunction = searchingFunction;
    }

    public static BowlingRank valueOf(int firstPitch, int secondPitch) {
        return Arrays.stream(values())
                .filter(bowlingRank -> bowlingRank.isExisting(firstPitch, secondPitch))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    private boolean isExisting(int firstPitch, int secondPitch) {
        return searchingFunction.apply(firstPitch, secondPitch);
    }
}

package bowling.domain;

import java.util.Arrays;

public enum ScoreType {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    boolean isIn(ScoreType... scoreTypes) {
        return Arrays.asList(scoreTypes).contains(this);
    }
}

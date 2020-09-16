package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum BowlResult {

    STRIKE(frameBowl -> frameBowl.isStrike()),
    SPARE(frameBowl -> frameBowl.isSpare()),
    MISS(frameBowl -> frameBowl.isMiss()),
    GUTTER(frameBowl -> frameBowl.isGutter()),
    NONE(frameBowl -> true)
    ;

    BowlResult(Function<FrameBowl, Boolean> getTypeFunction) {
        this.getTypeFunction = getTypeFunction;
    }

    private Function<FrameBowl, Boolean> getTypeFunction;

    public static BowlResult getType(FrameBowl frameBowl) {
        return Arrays.stream(values())
                .filter(bowlResult -> bowlResult.getTypeFunction.apply(frameBowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isCompleted() {
        return !this.equals(NONE);
    }

}

package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum BowlResult {

    STRIKE(normalBowl -> normalBowl.isStrike()),
    SPARE(normalBowl -> normalBowl.isSpare()),
    MISS(normalBowl -> normalBowl.isMiss()),
    GUTTER(normalBowl -> normalBowl.isGutter()),
    NONE(normalBowl -> true)
    ;

    BowlResult(Function<NormalBowl, Boolean> getTypeFunction) {
        this.getTypeFunction = getTypeFunction;
    }

    private Function<NormalBowl, Boolean> getTypeFunction;

    public static BowlResult getType(NormalBowl normalBowl) {
        return Arrays.stream(values())
                .filter(bowlResult -> bowlResult.getTypeFunction.apply(normalBowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}

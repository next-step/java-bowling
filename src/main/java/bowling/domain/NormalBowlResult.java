package bowling.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;

public enum NormalBowlResult {

    NONE(normalBowl -> normalBowl.isNone(), normalBowl -> ""),
    STRIKE(normalBowl -> normalBowl.isStrike(), normalBowl -> "X"),
    SPARE(normalBowl -> normalBowl.isSpare(), normalBowl -> MessageFormat.format("{0}|/", normalBowl.getFirstNumberOfPins())),
    MISS(normalBowl -> normalBowl.isMiss(), normalBowl -> MessageFormat.format("{0}|{1}", normalBowl.getFirstNumberOfPins(), normalBowl.getSecondNumberOfPins())),
    GUTTER(normalBowl -> normalBowl.isGutter(), normalBowl -> "-|-"),
    DEFAULT(normalBowl -> true, normalBowl -> MessageFormat.format("{0}", normalBowl.getFirstNumberOfPins()))
    ;

    NormalBowlResult(Function<NormalBowl, Boolean> getTypeFunction, Function<NormalBowl, String> toStringFunction) {
        this.getTypeFunction = getTypeFunction;
        this.toStringFunction = toStringFunction;
    }

    private Function<NormalBowl, Boolean> getTypeFunction;
    private Function<NormalBowl, String> toStringFunction;

    public static NormalBowlResult getType(NormalBowl normalBowl) {
        return Arrays.stream(values())
                .filter(bowlResult -> bowlResult.getTypeFunction.apply(normalBowl))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String format(NormalBowl normalBowl) {
        return toStringFunction.apply(normalBowl);
    }

}

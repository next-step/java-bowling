package game.bowling.domain;

import java.util.function.BiFunction;

/**
 * Created by yusik on 2019/11/23.
 */
public enum FrameResult {

    STRIKE((first, second) -> "X"),
    SPARE((first, second) -> String.format("%d|/", first)),
    MISS((first, second) -> String.format("%d|%d", first, second)),
    GUTTER((first, second) -> "-"),
    THROWING((first, second) -> String.valueOf(first)),
    NONE((first, second) -> ""),
    ;

    private final BiFunction<Integer, Integer, String> function;

    FrameResult(BiFunction<Integer, Integer, String> function) {
        this.function = function;
    }

    public String getFormat(int first, int second) {
        return function.apply(first, second);
    }
}

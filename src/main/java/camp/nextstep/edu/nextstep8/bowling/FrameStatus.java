package camp.nextstep.edu.nextstep8.bowling;

import java.util.function.BiFunction;

public enum FrameStatus {
    STRIKE((score, spare) -> "X"),
    SPARE((score, spare) -> score + "|/"),
    MISS((score, spare) -> score + "|" + spare),
    GUTTER((score, spare) -> "-");

    private BiFunction<Integer, Integer, String> expression;
    FrameStatus(BiFunction<Integer, Integer, String> expression) {
        this.expression = expression;
    }

    public String makeSymbol(int score, int spare){
        return expression.apply(score, spare);
    }
}

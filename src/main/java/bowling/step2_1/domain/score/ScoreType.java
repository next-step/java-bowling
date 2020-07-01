package bowling.step2_1.domain.score;

import java.util.function.Function;

public enum ScoreType {
    STRIKE(10,2, point -> "X"),
    SPARE(10,1, point -> "/"),
    GUTTER(0,0, point -> "-"),
    DEFAULT(0,0, point -> point.toString());

    private final int point;
    private final int bonusCount;
    private final Function<Integer, String> display;

    ScoreType(int point, int bonusCount, Function<Integer, String> display) {
        this.point = point;
        this.bonusCount = bonusCount;
        this.display = display;
    }

    public String convertToDisplay(int pitch){
        return display.apply(pitch);
    }

    public boolean findType(int pitch) {
        return point == pitch;
    }
}

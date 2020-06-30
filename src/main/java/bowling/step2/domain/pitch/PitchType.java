package bowling.step2.domain.pitch;

import java.util.function.Function;

public enum PitchType {
    STRIKE(pitch -> "X"),
    SPARE(pitch -> "/"),
    GUTTER(pitch -> "-"),
    DEFAULT(pitch -> pitch.toString());

    private Function<Integer, String> display;

    PitchType(Function<Integer, String> display) {
        this.display = display;
    }

    public String convertToDisplay(int pitch){
        return display.apply(pitch);
    }
}

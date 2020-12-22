package bowling.domain;

import java.util.List;

public class Gutter {

    private static final String SYMBOL = "-";
    public static final int GUTTER_CONDITION = 0;

    private final Pitch pitch;

    private Gutter(Pitch pitch) {
        this.pitch = pitch;
    }

    public static Gutter from(Pitch pitch) {
        return new Gutter(pitch);
    }

    public boolean isGutter() {
        return pitch.getScore() == GUTTER_CONDITION;
    }

    @Override
    public String toString() {
        return SYMBOL;
    }
}

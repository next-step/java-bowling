package bowling.domain;

public class Strike {

    private static final String SYMBOL = "X";

    private final Pitch pitch;

    private Strike(Pitch pitch) {
        this.pitch = pitch;
    }

    public static Strike from(Pitch pitch) {
        return new Strike(pitch);
    }

    public boolean isStrike() {
        return pitch.isStrike();
    }

    @Override
    public String toString() {
        return SYMBOL;
    }
}

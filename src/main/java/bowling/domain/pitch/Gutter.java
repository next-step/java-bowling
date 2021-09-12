package bowling.domain.pitch;

public class Gutter extends Pitch {
    public Gutter() {
        super(Pitch.MINIMUM_COUNT_OF_PINS);
    }

    @Override
    public String value() {
        return "-";
    }
}

package bowling.domain.pitch;

public class Strike extends Pitch {
    public Strike() {
        super(Pitch.MAXIMUM_COUNT_OF_PINS);
    }
    @Override
    public String value() {
        return "X";
    }
}

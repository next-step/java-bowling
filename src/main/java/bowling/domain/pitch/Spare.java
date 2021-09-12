package bowling.domain.pitch;

public class Spare extends Pitch {
    public Spare(int countOfPins) {
        super(countOfPins);
    }
    @Override
    public String value() {
        return "/";
    }
}

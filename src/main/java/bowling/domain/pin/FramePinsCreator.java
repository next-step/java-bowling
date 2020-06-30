package bowling.domain.pin;

public class FramePinsCreator {
    public static FramePins next(Pins pins) {
        if (pins.isStrike()) {
            return Strike.of(pins);
        }

        return FirstFramePins.of(pins);
    }
}

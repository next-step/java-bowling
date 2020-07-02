package bowling.domain.pin;

public class FramePinsCreator {
    public static FramePins next(FramePins framePins, Pins pins) {
        if (framePins != null && !framePins.isEnd()) {
            return framePins.createBy(pins);
        }

        return returnStrikeOrFirstPins(pins);
    }

    private static FramePins returnStrikeOrFirstPins(Pins pins) {
        if (pins.isStrike()) {
            return Strike.of(pins);
        }

        return FirstFramePins.of(pins);
    }
}

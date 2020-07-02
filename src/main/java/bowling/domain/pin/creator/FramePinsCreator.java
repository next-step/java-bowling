package bowling.domain.pin.creator;

import bowling.domain.pin.FirstFramePins;
import bowling.domain.pin.FramePins;
import bowling.domain.pin.Pins;
import bowling.domain.pin.Strike;

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

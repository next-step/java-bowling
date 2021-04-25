package bowling.domain.engine.roll;

import bowling.domain.concrete.roll.GutterResult;
import bowling.domain.concrete.roll.NormalResult;

public class Roll {

    private Roll() {}

    public static RollResult result(int numberOfPins) {
        if (numberOfPins == 0) {
            return new GutterResult();
        }
        return new NormalResult(numberOfPins);
    }

}

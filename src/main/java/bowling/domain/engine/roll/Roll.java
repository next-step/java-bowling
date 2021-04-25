package bowling.domain.engine.roll;

import bowling.domain.concrete.roll.GutterResult;
import bowling.domain.concrete.roll.NormalResult;

public class Roll {

    private static final RollResult[] CACHE = new RollResult[11];

    private Roll() {}

    public static RollResult result(int numberOfPins) {
        return getCache(numberOfPins);
    }

    private static RollResult getCache(int numberOfPins) {
        if (CACHE[numberOfPins] == null) {
            CACHE[numberOfPins] = create(numberOfPins);
        }

        return CACHE[numberOfPins];
    }

    private static RollResult create(int numberOfPins) {
        if (numberOfPins == 0) {
            return new GutterResult();
        }
        return new NormalResult(numberOfPins);
    }

}

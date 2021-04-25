package bowling.domain.engine.roll;

public class Roll {

    private Roll() {}

    public static RollResult result(int numberOfPins) {
        if (numberOfPins == 0) {
            return new GutterResult();
        }
        return new NormalResult(numberOfPins);
    }

}

package bowling.domain;

public class LastFrame extends BaseFrame {

    LastFrame(int number, int pinCount, boolean next) {
        super(number, pinCount, next);
    }

    public static Frame from() {
        return new LastFrame(Frames.END_NUMBER, Frames.PIN_COUNT, false);
    }
}

package bowling.domain;

public class NormalFrame extends BaseFrame {

    NormalFrame(int number, int pinCount, boolean next) {
        super(number, pinCount, next);
    }

    public static Frame from() {
        return new NormalFrame(Frames.BEGIN_NUMBER, Frames.PIN_COUNT, true);
    }
}

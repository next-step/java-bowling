package bowling.domain;

public enum FrameState {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private static final int MAX_PIN_NUMBER = 10;

    private final String frameState;

    FrameState(String frameState) {
        this.frameState = frameState;
    }

    public String frameState() {
        return frameState;
    }

    public static String eachState(PinNumber pinNumber) {
        if (pinNumber.isStrike()) {
            return STRIKE.frameState();
        }
        if (pinNumber.isGutter()) {
            return GUTTER.frameState();
        }
        return Integer.toString(pinNumber.pinNumber());
    }

    public static String pairState(PinNumber first, PinNumber second) {
        if (first.pinNumber() + second.pinNumber() == MAX_PIN_NUMBER) {
            return SPARE.frameState();
        }
        if (second.isGutter()) {
            return GUTTER.frameState();
        }
        return Integer.toString(second.pinNumber());
    }
}

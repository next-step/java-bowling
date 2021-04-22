package bowling.domain;

public enum FrameResult {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private static final int MAX_PIN_NUMBER = 10;

    private final String frameResult;

    FrameResult(String frameResult) {
        this.frameResult = frameResult;
    }

    public String frameResult() {
        return frameResult;
    }

    public static String eachResult(PinNumber pinNumber) {
        if (pinNumber.isStrike()) {
            return STRIKE.frameResult();
        }
        if (pinNumber.isGutter()) {
            return GUTTER.frameResult();
        }
        return Integer.toString(pinNumber.pinNumber());
    }

    public static String pairResult(PinNumber first, PinNumber second) {
        if (first.pinNumber() + second.pinNumber() == MAX_PIN_NUMBER) {
            return SPARE.frameResult();
        }
        if (second.isGutter()) {
            return GUTTER.frameResult();
        }
        return Integer.toString(second.pinNumber());
    }
}

package bowling;

public enum FrameState {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private final String frameState;

    FrameState(String frameState) {
        this.frameState = frameState;
    }

    public String getFrameState() {
        return frameState;
    }

    public static String eachState(PinNumber pinNumber) {
        if (pinNumber.isStrike()) {
            return STRIKE.getFrameState();
        }
        if (pinNumber.isGutter()) {
            return GUTTER.getFrameState();
        }
        return Integer.toString(pinNumber.getPinNumber());
    }

    public static String pairState(PinNumber first, PinNumber second) {
        if (first.getPinNumber() + second.getPinNumber() == 10) {
            return SPARE.getFrameState();
        }
        if (second.isGutter()) {
            return GUTTER.getFrameState();
        }
        return Integer.toString(second.getPinNumber());
    }
}

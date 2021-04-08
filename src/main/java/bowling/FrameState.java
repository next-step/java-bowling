package bowling;

public enum FrameState {

    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private final String frameState;

    FrameState(String frameState) {
        this.frameState = frameState;
    }

    public String getFrameState() {
        return frameState;
    }

    public static FrameState eachState(PinNumber pinNumber) {
        if (pinNumber.getPinNumber() == 10) {
            return STRIKE;
        }
        if (pinNumber.getPinNumber() == 0) {
            return GUTTER;
        }
        return MISS;
    }

    public static FrameState finishState(PinNumber first, PinNumber second) {
        if (first.getPinNumber() + second.getPinNumber() == 10) {
            return SPARE;
        }
        if (second.getPinNumber() == 0) {
            return GUTTER;
        }
        return MISS;
    }
}

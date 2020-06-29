package bowling.domain;

public abstract class Frame {

    protected static final int BOWLING_MAX_PINS = 10;
    protected Pin firstPin;
    protected Pin secondPin;
    protected State state = State.MISS;

    public static Frame of(Pin firstPin, Pin secondPin) {
        return new NormalFrame(firstPin, secondPin);
    }

    public static Frame of(Pin firstPin) {
        return new FinalFrame(firstPin);
    }

    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (state == State.STRIKE) {
            return state.state;
        }
        if (state == State.SPARE) {
            return state.state;
        }
        return normalResult(stringBuilder);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", firstPin.isGutter() ? "-" : firstPin.toString()));
        stringBuilder.append("/");
        stringBuilder.append(String.format("%2s", secondPin.isGutter() ? "-" : secondPin.toString()));
        return stringBuilder.toString();
    }

}

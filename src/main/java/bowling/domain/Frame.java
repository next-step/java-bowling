package bowling.domain;

public abstract class Frame {

    protected static final int BOWLING_MAX_PINS = 10;

    protected Pin firstPin;
    protected Pin secondPin;
    protected Pin thirdPin;
    protected State state = State.MISS;
    protected State thirdState = State.MISS;

    public static Frame of(Pins pins) {
        return new NormalFrame(pins);
    }

    public static Frame of(Frame frame) {
        return new FinalFrame(frame);
    }

    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (state == State.STRIKE) {
            return state.state;
        }
        if (state == State.SPARE) {
            stringBuilder.append(firstPin.falledPins());
            stringBuilder.append(":");
            stringBuilder.append(state.state);
            return stringBuilder.toString();
        }
        return normalResult(stringBuilder);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", firstPin.isGutter() ? State.GURTER : firstPin.toString()));
        stringBuilder.append(":");
        stringBuilder.append(String.format("%2s", secondPin.isGutter() ? State.GURTER : secondPin.toString()));
        return stringBuilder.toString();
    }

    public void setSecondPin(Pin pin) {
        this.secondPin = pin;
    }

    public void setThirdPin(Pin pin) {
        this.thirdPin = pin;
    }

    public Pin getFirstPin() {
        return firstPin;
    }

    public Pin getSecondPin() {
        return secondPin;
    }

    public Pin getThirdPin() {
        return thirdPin;
    }

}

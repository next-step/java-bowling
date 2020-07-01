package bowling.domain;

public abstract class Frame {

    protected static final int BOWLING_MAX_PINS = 10;
    protected Pins pins;
    protected State state = State.MISS;

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
            stringBuilder.append(pins.getFirstPin().falledPins());
            stringBuilder.append(":");
            stringBuilder.append(state.state);
            return stringBuilder.toString();
        }
        return normalResult(stringBuilder);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", pins.getFirstPin().isGutter() ? State.GURTER : pins.getFirstPin().toString()));
        stringBuilder.append(":");
        stringBuilder.append(String.format("%2s", pins.getSecondPin().isGutter() ? State.GURTER : pins.getSecondPin().toString()));
        return stringBuilder.toString();
    }


}

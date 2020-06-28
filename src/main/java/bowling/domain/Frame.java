package bowling.domain;

public abstract class Frame {

    protected int frameIndex;
    protected Pin firstPin;
    protected Pin secondPin;
    protected State state = State.MISS;
    protected String result;
    protected Score score;

    public static Frame of(Pin firstPin, Pin secondPin) {
        return new NormalFrame(firstPin, secondPin);
    }

    public static Frame of(Pin firstPin) {
        return new FinalFrame(firstPin);
    }

    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isStrike(stringBuilder)) return stringBuilder.toString();
        if (isSpare(stringBuilder)) return stringBuilder.toString();
        return normalResult(stringBuilder);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", firstPin.isGutter() ? "-" : firstPin.toString()));
        stringBuilder.append("/");
        stringBuilder.append(String.format("%2s", secondPin.isGutter() ? "-" : secondPin.toString()));
        return stringBuilder.toString();
    }

    private boolean isSpare(StringBuilder stringBuilder) {
        if ((state == State.SPARE)) {
            stringBuilder.append(String.format("%2s", firstPin.toString()));
            stringBuilder.append(":");
            stringBuilder.append(String.format("%2s", "/"));
            return true;
        }
        return false;
    }

    private boolean isStrike(StringBuilder stringBuilder) {
        if ((state == State.STRIKE)) {
            stringBuilder.append(String.format("%5s", "X"));
            return true;
        }
        return false;
    }

}

package bowling.domain;

public class FinalFrame extends Frame {

    private State thirdState = State.MISS;

    private boolean hasThirdDraw;

    public FinalFrame(Frame frame) {
        this.pins = frame.pins;
        this.hasThirdDraw = true;
        //state
        if (pins.getThirdPin().isAllClear()) {
            this.thirdState = State.STRIKE;
        }
        if (pins.getThirdPin().isGutter()) {
            this.thirdState = State.GURTER;
        }
    }

    @Override
    public String showResult() {

        StringBuilder stringBuilder = new StringBuilder();
        // 1 pin
        firstPinResult(stringBuilder);
        stringBuilder.append(":");
        secondPinResult(stringBuilder);
        thirdPinResult(stringBuilder);
        return stringBuilder.toString();
    }

    public boolean isHasThirdDraw() {
        return hasThirdDraw;
    }

    public State getThirdState() {
        return thirdState;
    }

    public Pin getThirdPin() {
        return pins.getThirdPin();
    }

    private void firstPinResult(StringBuilder stringBuilder) {
        if (pins.getFirstPin().isAllClear()) {
            stringBuilder.append(State.STRIKE);
            return;
        }
        if (pins.getFirstPin().isGutter()) {
            stringBuilder.append(State.GURTER);
            return;
        }
        stringBuilder.append(pins.getFirstPin());
    }

    private void secondPinResult(StringBuilder stringBuilder) {
        if (!pins.getFirstPin().isAllClear() && pins.getSecondPin().isAllClear()) {
            stringBuilder.append(State.SPARE);
            return;
        }
        if (pins.getFirstPin().isAllClear() && pins.getSecondPin().isAllClear()) {
            stringBuilder.append(State.STRIKE);
            return;
        }
        if (pins.getSecondPin().isGutter()) {
            stringBuilder.append(State.GURTER);
        }
        stringBuilder.append(pins.getSecondPin());
    }

    private void thirdPinResult(StringBuilder stringBuilder) {
        stringBuilder.append(":");
        if (thirdState != State.MISS) {
            stringBuilder.append(thirdState);
            return;
        }
        stringBuilder.append(pins.getThirdPin());
    }

}

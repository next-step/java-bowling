package bowling.domain;

public class FinalFrame extends Frame {

    Pin thirdPin;
    State thirdState = State.MISS;

    public FinalFrame(Pin thirdPin, Frame frame) {
        this.firstPin = frame.firstPin;
        this.secondPin = frame.secondPin;
        this.thirdPin = thirdPin;
        this.hasThirdDraw = true;
        //state
        if (thirdPin.isAllClear()) {
            this.thirdState = State.STRIKE;
        }
        if (thirdPin.isGutter()) {
            this.thirdState = State.GURTER;
        }
    }

    public boolean hasThirdPin() {
        return hasThirdDraw;
    }

    @Override
    public String showResult() {

        StringBuilder stringBuilder = new StringBuilder();
        // 1 pin
        firstPinResult(stringBuilder);
        stringBuilder.append(":");
        secondPinResult(stringBuilder);
        if (hasThirdPin()) {
            thirdPinResult(stringBuilder);
        }
        return stringBuilder.toString();
    }

    private void firstPinResult(StringBuilder stringBuilder) {
        if (firstPin.isAllClear()) {
            stringBuilder.append(State.STRIKE);
            return;
        }
        if (firstPin.isGutter()) {
            stringBuilder.append(State.GURTER);
            return;
        }
        stringBuilder.append(firstPin);
    }

    private void secondPinResult(StringBuilder stringBuilder) {
        if (!firstPin.isAllClear() && secondPin.isAllClear()) {
            stringBuilder.append(State.SPARE);
            return;
        }
        if (firstPin.isAllClear() && secondPin.isAllClear()) {
            stringBuilder.append(State.STRIKE);
            return;
        }
        if (secondPin.isGutter()) {
            stringBuilder.append(State.GURTER);
        }
        stringBuilder.append(secondPin);
    }

    private void thirdPinResult(StringBuilder stringBuilder) {
        stringBuilder.append(":");
        if (thirdState != State.MISS) {
            stringBuilder.append(thirdState);
            return;
        }
        stringBuilder.append(thirdPin.toString());
    }

}

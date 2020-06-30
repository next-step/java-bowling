package bowling.domain;

public class FinalFrame extends Frame {

    private Pin thirdPin;
    private State thirdState = State.MISS;

    private boolean hasThirdDraw;

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
        return thirdPin;
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

    @Override
    public Frame calculateAdditionalScore(Score nextScore) {
        this.currentScore.addBonusNumberLastFrame(this);
        return this;
    }

}

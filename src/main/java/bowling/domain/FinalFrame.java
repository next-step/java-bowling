package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame(Frame frame) {
        this.firstPin = frame.getFirstPin();
        this.secondPin = frame.getSecondPin();
        this.thirdPin = frame.getThirdPin();
        if (thirdPin.isAllClear()) {
            thirdState = State.STRIKE;
        }
        if (thirdPin.isGutter()) {
            thirdState = State.GURTER;
        }
    }

    @Override
    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        firstPinResult(stringBuilder);
        stringBuilder.append(":");
        secondPinResult(stringBuilder);
        thirdPinResult(stringBuilder);
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
        stringBuilder.append(thirdPin);
    }

}

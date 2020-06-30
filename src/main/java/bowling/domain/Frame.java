package bowling.domain;

public abstract class Frame {

    protected static final int BOWLING_MAX_PINS = 10;
    protected Pin firstPin;
    protected Pin secondPin;
    protected State state = State.MISS;
    protected Score currentScore;
    protected Score nextScore;


    public static Frame of(Pin firstPin, Pin secondPin) {
        return new NormalFrame(firstPin, secondPin);
    }

    public static Frame of(Pin thirdPin, Frame frame) {
        return new FinalFrame(thirdPin, frame);
    }

    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (state == State.STRIKE) {
            return state.state;
        }
        if (state == State.SPARE) {
            stringBuilder.append(firstPin);
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

    public void createScore() {
        if (state == State.SPARE) {
            this.currentScore = new Score(firstPin.falledPins(), secondPin.falledPins());
        }
        if (state == State.STRIKE) {
            this.currentScore = new Score(firstPin.falledPins(), secondPin.falledPins());
        }
        this.currentScore = new Score(firstPin.falledPins(), secondPin.falledPins());
    }

    public void setNextScore(Frame nextFrame) {
        this.nextScore = nextFrame.currentScore;
    }

    public String getCurrentScore() {
        return Integer.toString(currentScore.getScore());
    }

    public String getCurrentSumScore() {
        return Integer.toString(this.currentScore.getSumScore());
    }

    public abstract Frame calculateAdditionalScore(Score nextScore);

    public void reset() {
        currentScore.resetSumScore();
    }
}

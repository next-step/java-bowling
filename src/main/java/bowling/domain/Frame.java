package bowling.domain;

import bowling.exceptions.CannotCalculteException;

public abstract class Frame {

    protected static final int BOWLING_MAX_PINS = 10;
    protected Pin firstPin;
    protected Pin secondPin;
    protected State state = State.MISS;
    protected Frame next;


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

    public Score createScore() {
        if (state == State.SPARE) {
            return new Score(firstPin.falledPins() + secondPin.falledPins(), 1);
        }
        if (state == State.STRIKE) {
            return new Score(firstPin.falledPins() + secondPin.falledPins(), 2);
        }
        return new Score(firstPin.falledPins() + secondPin.falledPins(), 0);
    }

    public int getScore() throws CannotCalculteException {
        Score score = createScore();
        if (score.canCalculateScore()) {
            return score.getScore();
        }
        return next.calculateAdditionalScore(score);
    }

    private int calculateAdditionalScore(Score beforeScore) {
        // before 스코어와 합해서 구하기



        return 0;
    }



}

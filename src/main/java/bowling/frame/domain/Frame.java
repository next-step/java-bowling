package bowling.frame.domain;

import bowling.pin.domain.Pin;

public interface Frame {

    static Frame ofNormal() {
        return new NormalFrame();
    }
    static Frame ofFinal() {
        return new FinalFrame();
    }

    void roll(Pin felled);

    boolean isEnd();

    FrameScore getScore();



/*

    protected static final int BOWLING_MAX_PINS = 10;
    protected static final int NUMBER_ZERO = 0;
    protected static final int NUMBER_ONE = 1;

    protected Pins pins = new Pins();
    protected State state = State.MISS;


    public void drawBowl(BowlingStrategy bowlingStrategy, int index) {
        int frameIndex = index;
        setFirstPin(bowlingStrategy, frameIndex);
        state = checkState();
        setSecondPin(bowlingStrategy, frameIndex);
        state = checkState();
        setThirdPin(bowlingStrategy, frameIndex);
        state = checkState();
    }

    private void setFirstPin(BowlingStrategy bowlingStrategy, int index) {
        pins.setFirstPin(bowlingStrategy.drawBowl(new Pin(BOWLING_MAX_PINS, NUMBER_ZERO), index));
    }

    protected abstract State checkState();

    public boolean isState(State state) {
        return this.state == state;
    }

    public Pins getPins() {
        return pins;
    }

    protected abstract void setSecondPin(BowlingStrategy bowlingStrategy, int index);

    protected abstract void setThirdPin(BowlingStrategy bowlingStrategy, int index);

    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (state == State.STRIKE) {
            stringBuilder.append(state.toString());
            return stringBuilder.toString();
        }
        if (state == State.SPARE) {
            stringBuilder.append(pins.getFirstFallenPins());
            stringBuilder.append(":");
            stringBuilder.append(state.toString());
            return stringBuilder.toString();
        }
        return normalResult(stringBuilder);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", zeroToGutter(pins.getFirstFallenPins())));
        stringBuilder.append(":");
        stringBuilder.append(String.format("%2s", zeroToGutter(pins.getSecondFallenPins())));
        return stringBuilder.toString();
    }

    protected String zeroToGutter(int fallenPins) {
        return String.valueOf(fallenPins == 0 ? State.GUTTER : fallenPins);
    }*/

}

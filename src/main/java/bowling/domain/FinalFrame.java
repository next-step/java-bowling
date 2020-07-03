package bowling.domain;

import bowling.strategy.BowlingStrategy;

public class FinalFrame extends Frame {

    @Override
    protected void setSecondPin(BowlingStrategy bowlingStrategy, int index) {
        // first pin strike
        if (pins.isFirstClean()) {
            pins.setSecondPin(bowlingStrategy.drawBowl(new Pin(BOWLING_MAX_PINS, NUMBER_ZERO), index));
            return;
        }
        pins.setSecondPin(bowlingStrategy.drawBowl(new Pin(pins.getFirstLeftPins(), NUMBER_ZERO), index));
    }

    @Override
    protected void setThirdPin(BowlingStrategy bowlingStrategy, int index) {
        // double
        if (pins.isFirstClean() && pins.isSecondClean()) {
            pins.setThirdPin(bowlingStrategy.drawBowl(new Pin(BOWLING_MAX_PINS, NUMBER_ZERO), index));
            return;
        }
        // spare
        if (pins.isSecondClean()) {
            pins.setThirdPin(bowlingStrategy.drawBowl(new Pin(BOWLING_MAX_PINS, NUMBER_ZERO), index));
            return;
        }
        // first strike
        if (pins.isFirstClean() && !pins.isSecondClean()) {
            pins.setThirdPin(bowlingStrategy.drawBowl(new Pin(pins.secondLeftPins(), NUMBER_ZERO), index));
        }
    }

    @Override
    protected State checkState() {
        return calcState();
    }

    @Override
    public String showResult() {
        StringBuilder stringBuilder = new StringBuilder();
        if (pins.isFirstClean() && pins.isSecondClean() && pins.isThirdClean()) {
            stringBuilder.append(State.FINAL_TURKEY);
            return stringBuilder.toString();
        }
        if (state == State.FINAL_DOUBLE) {
            stringBuilder.append(state);
            stringBuilder.append(":");
            stringBuilder.append(zeroToGutter(pins.getThirdFallenPins()));
            return stringBuilder.toString();
        }
        if (state == State.SPARE) {
            stringBuilder.append(zeroToGutter(pins.getFirstFallenPins()));
            stringBuilder.append(":");
            stringBuilder.append(state);
            stringBuilder.append(":");
            stringBuilder.append(finalDrawDisplay(pins.getThirdFallenPins()));
            return stringBuilder.toString();
        }
        normalResult(stringBuilder);
        return stringBuilder.toString();
    }

    private String finalDrawDisplay(int thirdFallenPins) {
        if (thirdFallenPins == BOWLING_MAX_PINS) {
            return State.STRIKE.toString();
        }
        return zeroToGutter(thirdFallenPins);
    }

    private String normalResult(StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%2s", zeroToGutter(pins.getFirstFallenPins())));
        stringBuilder.append(":");
        stringBuilder.append(String.format("%2s", zeroToGutter(pins.getSecondFallenPins())));
        return stringBuilder.toString();
    }


    public State calcState() {
        if (pins.isTurkey()) {
            return State.FINAL_TURKEY;
        }
        if (pins.isDouble()) {
            return State.FINAL_DOUBLE;
        }
        if (pins.isGutter()) {
            return State.GUTTER;
        }
        if (pins.isSpare()) {
            return State.SPARE;
        }
        if (pins.isMiss()) {
            return State.MISS;
        }
        if (pins.isStrike()) {
            return State.STRIKE;
        }
        throw new IllegalStateException();
    }

}

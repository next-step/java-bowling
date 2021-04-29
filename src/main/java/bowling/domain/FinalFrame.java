package bowling.domain;

public class FinalFrame extends Frame{
    private final static int FULL_FRAME_COUNT = 3;
    private final static int BEFORE_INDEX = 1;

    @Override
    public void pitching(int pinCount) {
        downPins.add(pinCount);
        states.add(State.of(getPinCount(pinCount), !beforeStateOther()));
    }

    @Override
    public boolean isContinue() {
        return states.size() < FULL_FRAME_COUNT;
    }

    @Override
    public boolean isEndFrame() {
        return downPins.size() == FULL_FRAME_COUNT;
    }

    private int getPinCount(int pinCount) {
        if (beforeStateOther()) {
            return downPins().get(BEFORE_INDEX).count() + pinCount;
        }
        return pinCount;
    }

    private boolean beforeStateOther() {
        if (downPins().isFirst()) {
            return false;
        }
        return isOther();
    }

    private boolean isOther() {
        return State.OTHER.equals(states.get(states.size() - BEFORE_INDEX));
    }
}

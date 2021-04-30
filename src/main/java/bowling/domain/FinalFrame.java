package bowling.domain;

public class FinalFrame extends Frame{
    private final static int FULL_FRAME_COUNT = 3;
    private final static int BEFORE_INDEX = 1;
    private final static int READY_COUNT = 0;

    @Override
    public void pitching(int pinCount) {
        downPins.add(pinCount);
        int p = getPinCount(pinCount);
        boolean isFirst = beforeStateStrikeOrSpare();
        states.add(State.of(p, isFirst));
    }

    @Override
    public boolean isContinue() {
        return downPins().isFirst()|| isStrikeOrSpare();
    }

    @Override
    public boolean isEndFrame() {
        if (downPins.size() == READY_COUNT) {
            return false;
        }
        return downPins.size() == FULL_FRAME_COUNT || !isContinue();
    }

    private int getPinCount(int pinCount) {
        if (beforeStateOther()) {
            return downPins().previousCount() + pinCount;
        }
        return pinCount;
    }

    private boolean beforeStateOther() {
        if (downPins().isFirst()) {
            return false;
        }
        return isOther();
    }

    private boolean beforeStateStrikeOrSpare() {
        if (downPins().isFirst()) {
            return true;
        }
        return isStrikeOrSpare();
    }

    private boolean isStrikeOrSpare() {
        State beforeState = states.get(states.size() - BEFORE_INDEX);
        return State.STRIKE.equals(beforeState) || State.SPARE.equals(beforeState);
    }

    private boolean isOther() {
        return State.OTHER.equals(states.get(states.size() - BEFORE_INDEX));
    }
}

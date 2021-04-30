package bowling.domain;

public class FinalFrame extends Frame{
    private final static int FULL_FRAME_COUNT = 3;
    private final static int BEFORE_INDEX = 1;
    private final static int READY_COUNT = 0;

    @Override
    public void pitching(int pinCount) {
        downPins.add(pinCount);
        pinCount = getPinCount(pinCount);
        boolean isFirst = beforeStateStrikeOrSpare();
        states.add(State.of(pinCount, isFirst));
    }

    @Override
    public boolean isContinue() {
        return downPins.size() < FULL_FRAME_COUNT && isStrikeOrSpare();
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
        return State.OTHER.equals(states.get(states.size() - BEFORE_INDEX));
    }

    private boolean beforeStateStrikeOrSpare() {
        return downPins().isFirst() || isStrikeOrSpare();
    }

    private boolean isStrikeOrSpare() {
        return states.stream()
                .anyMatch(state -> State.STRIKE.equals(state)
                        || State.SPARE.equals(state));
    }
}

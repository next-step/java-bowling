package bowling.domain;

public class NormalFrame extends Frame {
    private final static int FULL_FRAME_COUNT = 2;
    private final static int FIRST_INDEX = 0;

    public void pitching(int pinCount) {
        downPins.add(pinCount);
        states.add(State.of(downPins.sum(), downPins.isFirst()));
        if (isEndFrame()) {
            states().removeIf(State.OTHER::equals);
        }
    }

    public boolean isContinue() {
        return states.size() > 0 && State.OTHER.equals(states.get(FIRST_INDEX));
    }

    public boolean isEndFrame() {
        return !isContinue() || downPins.size() == FULL_FRAME_COUNT;
    }

}

package bowling.domain;

public class LastFrame implements Frame {
    private int number;
    private int pinCount;

    private LastFrame(int number, int pinCount) {
        this.number = number;
        this.pinCount = pinCount;
    }

    private static LastFrame from(int number) {
        return new LastFrame(number, Frames.PIN_COUNT);
    }

    @Override
    public boolean isEndFrame() {
        return true;
    }

    @Override
    public boolean isClear() {
        return pinCount == Frames.PIN_CLEAR_COUNT;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int hit(int count) {
        pinCount -= count;
        return pinCount;
    }
}

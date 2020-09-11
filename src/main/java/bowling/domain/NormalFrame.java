package bowling.domain;

public class NormalFrame implements Frame {
    private int number;
    private boolean next;
    private int pinCount;

    private NormalFrame(int number, int pinCount, boolean next) {
        this.number = number;
        this.pinCount = pinCount;
        this.next = next;
    }

    public static Frame from(int number) {
        return new NormalFrame(number, Frames.PIN_COUNT, number < Frames.END_NUMBER);
    }

    @Override
    public boolean isEndFrame() {
        return !next;
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

    @Override
    public String toString() {
        return "NormalFrame{" +
                "number=" + number +
                ", next=" + next +
                ", pinCount=" + pinCount +
                '}';
    }
}

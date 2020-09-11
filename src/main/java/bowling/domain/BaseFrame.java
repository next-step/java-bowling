package bowling.domain;

public class BaseFrame implements Frame {

    protected int number;
    protected boolean next;
    protected int pinCount;

    BaseFrame(int number, int pinCount, boolean next) {

        if (number < Frames.BEGIN_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정할 수 없습니다. [%s]", Frames.BEGIN_NUMBER, number));
        }

        if (number > Frames.END_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정할 수 없습니다. [%s]", Frames.END_NUMBER, number));
        }

        this.number = number;
        this.pinCount = pinCount;
        this.next = next;
    }

    public static Frame from(int number) {
        return new BaseFrame(number, Frames.PIN_COUNT, number < Frames.END_NUMBER);
    }

    @Override
    public boolean isLastFrame() {
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
    public Frame next() {
        return from(number + 1);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "number=" + number +
                ", next=" + next +
                ", pinCount=" + pinCount +
                '}';
    }
}

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

    private static NormalFrame from(int number) {
        if (number < Frames.BEGIN_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정할 수 없습니다. [%s]", Frames.BEGIN_NUMBER, number));
        }

        if (number > Frames.END_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정할 수 없습니다. [%s]", Frames.END_NUMBER, number));
        }

        return new NormalFrame(number, Frames.PIN_COUNT, number < Frames.END_NUMBER);
    }

    public static NormalFrame first() {
        return from(Frames.BEGIN_NUMBER);
    }

    @Override
    public Frame next() {
        if (!next) {
            throw new RuntimeException("남아있는 프레임이 없습니다.");
        }

        return from(number + 1);
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
}

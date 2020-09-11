package bowling.domain;

public class LastFrame implements Frame {
    private int number;
    private int pinCount;

    private LastFrame(int number, int pinCount) {
        this.number = number;
        this.pinCount = pinCount;
    }

    private static LastFrame from(int number) {
        if (number < Frames.BEGIN_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정할 수 없습니다. [%s]", Frames.BEGIN_NUMBER, number));
        }

        if (number > Frames.END_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정할 수 없습니다. [%s]", Frames.END_NUMBER, number));
        }

        return new LastFrame(number, Frames.PIN_COUNT);
    }

    @Override
    public Frame next() {
        throw new RuntimeException("남아있는 프레임이 없습니다.");
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

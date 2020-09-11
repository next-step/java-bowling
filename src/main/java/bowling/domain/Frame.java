package bowling.domain;

public class Frame {
    public static final int PIN_COUNT = 10;
    public static final int PIN_CLEAR_COUNT = 0;
    public static final int BEGIN_NUMBER = 1;
    public static final int END_NUMBER = 10;

    private int number;
    private boolean next;
    private int pinCount;

    private Frame(int number, int pinCount, boolean next) {
        this.number = number;
        this.pinCount = pinCount;
        this.next = next;
    }

    private static Frame from(int number) {
        if (number < BEGIN_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정할 수 없습니다. [%s]", BEGIN_NUMBER, number));
        }

        if (number > END_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정할 수 없습니다. [%s]", END_NUMBER, number));
        }

        return new Frame(number, PIN_COUNT, number < END_NUMBER);
    }

    public static Frame first() {
        return from(BEGIN_NUMBER);
    }

    public Frame next() {
        if (!next) {
            throw new RuntimeException("남아있는 프레임이 없습니다.");
        }

        return from(number + 1);
    }

    public boolean isEndFrame() {
        return !next;
    }

    public boolean isClear() {
        return pinCount == PIN_CLEAR_COUNT;
    }

    public int getNumber() {
        return number;
    }

    public int hit(int count) {
        pinCount -= count;
        return pinCount;
    }
}

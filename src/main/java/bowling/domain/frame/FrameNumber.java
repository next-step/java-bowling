package bowling.domain.frame;

public class FrameNumber {
    private static final int FIRST = 1;
    private static final int LAST = 10;
    private final int number;

    public FrameNumber(int number) {
        this.number = number;
    }

    public static FrameNumber first() {
        return of(FIRST);
    }

    public static FrameNumber of(int number) {
        validateNumber(number);
        return new FrameNumber(number);
    }

    private static void validateNumber(int number) {
        if (number < FIRST || number > LAST) {
            throw new InvalidFrameNumberException();
        }
    }

    public int getNumber() {
        return number;
    }

    public FrameNumber next() {
        return of(number + 1);
    }

    public boolean isLast() {
        return number == LAST;
    }

}

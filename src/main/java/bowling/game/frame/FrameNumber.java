package bowling.game.frame;

public class FrameNumber implements Comparable<FrameNumber> {
    public static final int FRAME_NUMBER_MIN = 1;
    public static final int FRAME_NUMBER_MAX = 10;

    private final int number;

    public FrameNumber(final int number) {
        validateFrameNumber(number);
        this.number = number;
    }

    private void validateFrameNumber(final int number) {
        if (number < FRAME_NUMBER_MIN || number > FRAME_NUMBER_MAX) {
            throw new IllegalArgumentException("프레임 번호는 1 ~ 10 사이의 값이어야 합니다. - " + number);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(final FrameNumber o) {
        return Integer.compare(number, o.number);
    }
}

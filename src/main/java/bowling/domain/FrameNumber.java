package bowling.domain;

public class FrameNumber implements Comparable<FrameNumber> {

    private static final int MIN = 1;

    private final int number;

    public FrameNumber(int number) {
        if (number < MIN ) {
            throw new IllegalArgumentException("프레임 번호는 1-10사이어야 합니다.");
        }
        this.number = number;
    }

    public static FrameNumber first() {
        return new FrameNumber(MIN);
    }


    public FrameNumber next() {
        return new FrameNumber(number + 1);
    }

    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FrameNumber that = (FrameNumber) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public int compareTo(FrameNumber o) {
        return Integer.compare(number,o.number);
    }
}

package domain;

public class FrameNumber {
    static final int MINIMUM_FRAME = 1;
    static final int MAXIMUM_FRAME = 10;

    private int number;

    public FrameNumber(int number) {
        if(number < MINIMUM_FRAME || number > MAXIMUM_FRAME) {
            throw new IllegalArgumentException();
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
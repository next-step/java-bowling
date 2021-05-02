package bowling;

public class HitNumber {
    private static final int ROLL_LOWER_BOUND = 0;
    private static final int ROLL_UPPER_BOUND = 10;
    private static final String INVALID_ROLL_NUMBER = "투구횟수는 0~10 사이어야합니다.";

    private final int rollNumber;

    public HitNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public static HitNumber of(int rollNumber) {
        valid(rollNumber);
        return new HitNumber(rollNumber);
    }

    private static void valid(int rollNumber) {
        if (rollNumber < ROLL_LOWER_BOUND || rollNumber > ROLL_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_ROLL_NUMBER);
        }
    }

    public int hit(int pinNum) {
        return pinNum - rollNumber > ROLL_LOWER_BOUND ? pinNum - rollNumber : ROLL_LOWER_BOUND;
    }
}

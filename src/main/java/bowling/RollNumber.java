package bowling;

public class RollNumber {
    private static final int ROLL_LOWER_BOUND = 0;
    private static final int ROLL_UPPER_BOUND = 10;
    private static final String INVALID_ROLL_NUMBER = "투구횟수는 0~10 사이어야합니다.";

    private final int rollNumber;

    public RollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public static RollNumber of(int rollNumber) {
        valid(rollNumber);
        return new RollNumber(rollNumber);
    }

    private static void valid(int rollNumber) {
        if (rollNumber < ROLL_LOWER_BOUND || rollNumber > ROLL_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_ROLL_NUMBER);
        }
    }

    public int hit(int pinNum) {
        return pinNum - rollNumber > 0 ? pinNum - rollNumber : 0;
    }
}

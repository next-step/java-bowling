package bowling.domain;

public class HitNumber {
    private static final int ROLL_LOWER_BOUND = 0;
    private static final int ROLL_UPPER_BOUND = 10;
    private static final String INVALID_ROLL_NUMBER = "투구횟수는 0~10 사이어야합니다.";
    private static final String INVALID_HIT_TOTAL = "남아있는 핀의 갯수보다 더 많이 쳤습니다.";

    private final int rollNumber;

    private HitNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public static HitNumber of(int rollNumber) {
        validHit(rollNumber);
        return new HitNumber(rollNumber);
    }

    private static void validHit(int rollNumber) {
        if (rollNumber < ROLL_LOWER_BOUND || rollNumber > ROLL_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_ROLL_NUMBER);
        }
    }

    private void validTotalHit(int pinNum) {
        if(pinNum - rollNumber < ROLL_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_HIT_TOTAL);
        }
    }

    public int hit(int pinNum) {
        validTotalHit(pinNum);
        return pinNum - rollNumber;
    }
}

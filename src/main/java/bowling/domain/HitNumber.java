package bowling.domain;

import bowling.domain.frame.Frame;

public class HitNumber {
    private static final int ROLL_LOWER_BOUND = 0;
    private static final int ROLL_UPPER_BOUND = 10;
    private static final String INVALID_ROLL_NUMBER = "투구횟수는 0~10 사이어야합니다.";
    private static final String INVALID_HIT_TOTAL = "남아있는 핀의 갯수보다 더 많이 쳤습니다.";

    private final int hitNumber;

    private HitNumber(int hitNumber) {
        this.hitNumber = hitNumber;
    }

    public static HitNumber of(int hitNumber) {
        validHit(hitNumber);
        return new HitNumber(hitNumber);
    }

    public int hit(int pinNum) {
        validTotalHit(pinNum);
        return pinNum - hitNumber;
    }

    public Frame addScore(Frame frame) {
        return frame.accumulate(hitNumber);
    }

    private static void validHit(int hitNumber) {
        if (hitNumber < ROLL_LOWER_BOUND || hitNumber > ROLL_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_ROLL_NUMBER);
        }
    }

    private void validTotalHit(int pinNum) {
        if(pinNum - hitNumber < ROLL_LOWER_BOUND) {
            throw new IllegalArgumentException(INVALID_HIT_TOTAL);
        }
    }
}

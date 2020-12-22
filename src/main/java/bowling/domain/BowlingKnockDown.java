package bowling.domain;

import bowling.exception.BowlingMaxCountException;

public class BowlingKnockDown {
    public static final int MAX_NUMBER = 10;

    private Integer currentOfKnockDown;

    private Integer nextOfKnockDown;

    private Integer bonusKnockDown;

    public BowlingKnockDown(Integer currentOfKnockDown) {
        validate(currentOfKnockDown);
        this.currentOfKnockDown = currentOfKnockDown;
    }

    public BowlingKnockDown(Integer currentOfKnockDown, Integer nextOfKnockDown) {
        validate(nextOfKnockDown);
        this.currentOfKnockDown = currentOfKnockDown;
        this.nextOfKnockDown = nextOfKnockDown;
    }

    public BowlingKnockDown(Integer currentOfKnockDown, Integer nextOfKnockDown, Integer bonusKnockDown) {
        validate(bonusKnockDown);
        this.currentOfKnockDown = currentOfKnockDown;
        this.nextOfKnockDown = nextOfKnockDown;
        this.bonusKnockDown = bonusKnockDown;
    }

    public Integer getCurrentOfKnockDown() {
        return currentOfKnockDown;
    }

    public Integer getNextOfKnockDown() {
        return nextOfKnockDown;
    }

    public Integer getBonusKnockDown() {
        return bonusKnockDown;
    }

    private static void validate(int count) {
        if (count > MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }
}

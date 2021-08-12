package bowling.domain.pin;

import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

public class Pins {

    public static final int MIN_COUNT_HIT_PINS = 0;
    public static final int MAX_COUNT_HIT_PINS = 10;

    private final int countHitPins;

    private Pins(int countHitPins) {
        validate(countHitPins);
        this.countHitPins = countHitPins;
    }

    private void validate(int countFallenPins) {
        if (countFallenPins < MIN_COUNT_HIT_PINS || countFallenPins > MAX_COUNT_HIT_PINS) {
            throw new IllegalArgumentException(
                    "핀의 개수는 " + MIN_COUNT_HIT_PINS + " ~ " + MAX_COUNT_HIT_PINS + " 사이입니다."
            );
        }
    }

    public static Pins of(int pinCount) {
        return new Pins(pinCount);
    }

    public Pins add(Pins pins) {
        return new Pins(countHitPins + pins.countHitPins);
    }

    public int getCountHitPins() {
        return countHitPins;
    }

    public boolean isAllHit() {
        return countHitPins == MAX_COUNT_HIT_PINS;
    }

    public Score score() {
        return CommonScore.of(countHitPins);
    }

    public Score spareScore() {
        return CommonScore.of(MAX_COUNT_HIT_PINS - countHitPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pins pins = (Pins) o;

        return countHitPins == pins.countHitPins;
    }

    @Override
    public int hashCode() {
        return countHitPins;
    }
}

package bowling.domain;

import bowling.domain.status.*;

public class Pitch {
    private static final int MIN_PIN_COUNT = 0;
    private static final int MAX_PIN_COUNT = 10;

    private final int fallenPins;
    private final Status status;

    public Pitch(int fallenPins) {
        this(fallenPins, new Ready());
    }

    public Pitch(int fallenPins, Status status) {
        validateFallenPins(fallenPins);
        this.fallenPins = fallenPins;
        this.status = status;
    }

    public Pitch pitch2(int fallenPins, int pitchIndex) {
        if ((fallenPins == MAX_PIN_COUNT && pitchIndex == 0)
                || (this.fallenPins + fallenPins == 20)
                || (fallenPins == MAX_PIN_COUNT && pitchIndex == 2)) {
            return new Pitch(fallenPins, new Strike());
        }
        if (this.fallenPins + fallenPins == MAX_PIN_COUNT) {
            return new Pitch(fallenPins, new Spare());
        }
        if (this.fallenPins + fallenPins < MAX_PIN_COUNT && pitchIndex == 1) {
            return new Pitch(fallenPins, new Open());
        }
        return new Pitch(fallenPins, new Default());
    }

    public Pitch pitch(int fallenPins, int pitchIndex) {
        return new Pitch(fallenPins, status.roll(fallenPins));
    }

    public boolean isStrike() {
        return status.isStrike();
    }

    public boolean isSpare() {
        return status.isSpare();
    }

    public boolean isOpen() {
        return status.isOpen();
    }

    public int intValue() {
        return fallenPins;
    }

    public Status status() {
        return status;
    }

    public void validateFallenPins(int fallenPins) {
        if (fallenPins < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("%d개 이하의 핀을 쓰러트릴 수는 없습니다.", MIN_PIN_COUNT));
        }
        if (fallenPins > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("%d개 이상의 핀을 쓰러트릴 수는 없습니다.", MAX_PIN_COUNT));
        }
    }

    public boolean isStrike2() {
        return fallenPins == MAX_PIN_COUNT;
    }

    public boolean isSpare2(int fallenPins) {
        return this.fallenPins + fallenPins == MAX_PIN_COUNT;
    }

    public boolean isEnd() {
        return status.isEnd();
    }

    public boolean hasBonusPitch() {
        return status.hasBonusPitch();
    }
}

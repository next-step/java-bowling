package bowling.domain;

import bowling.domain.status.Ready;
import bowling.domain.status.Status;

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

    public Pitch pitch(int fallenPins) {
        return new Pitch(fallenPins, status.roll(fallenPins));
    }

    public boolean isStrike() {
        return fallenPins == MAX_PIN_COUNT;
    }

    public boolean isSpare(int fallenPins) {
        return this.fallenPins + fallenPins == MAX_PIN_COUNT;
    }

    public boolean isEnd() {
        return status.isEnd();
    }

    public boolean hasBonusPitch() {
        return status.hasBonusPitch();
    }

    public int intValue() {
        return fallenPins;
    }

    public String display() {
        return status.display();
    }

    public AddedPitch bonusPitch() {
        return new AddedPitch(status);
    }

    public void validateFallenPins(int fallenPins) {
        if (fallenPins < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("%d개 이하의 핀을 쓰러트릴 수는 없습니다.", MIN_PIN_COUNT));
        }
        if (fallenPins > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("%d개 이상의 핀을 쓰러트릴 수는 없습니다.", MAX_PIN_COUNT));
        }
    }
}

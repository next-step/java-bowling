package bowling.domain;

public class Pins {
    public static final int MIN_HIT_COUNT = 0;
    public static final int MAX_HIT_COUNT = 10;

    private final int hitPins;

    public Pins(int hitPins) {
        validatePins(hitPins);
        this.hitPins = hitPins;
    }

    private void validatePins(int hitPins) {
        if (hitPins > MAX_HIT_COUNT || hitPins < MIN_HIT_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀이 0 ~ 10개 범위를 벗어날 수 없습니다. 전달 받은 쓰러뜨린 핀 갯수 : %d", hitPins));
        }
    }

    public static Pins create(String hitPins) {
        return Pins.create(Integer.parseInt(hitPins));
    }

    public static Pins create(int hitPins) {
        return new Pins(hitPins);
    }

    public int hitPins() {
        return this.hitPins;
    }

    public boolean isStrike() {
        return this.hitPins == MAX_HIT_COUNT;
    }

    public boolean isSpare(Pins nextHitPins) {
        return this.hitPins + nextHitPins.hitPins == MAX_HIT_COUNT;
    }

    public boolean isGutter() {
        return this.hitPins == MIN_HIT_COUNT;
    }

    public boolean isMiss(Pins nextHitPins) {
        return this.hitPins + nextHitPins.hitPins < MAX_HIT_COUNT;
    }

    public boolean isOverMaxHitPins(Pins hitPins) {
        return this.hitPins + hitPins.hitPins > MAX_HIT_COUNT;
    }

    public int addPins(Pins nextHitPins) {
        return this.hitPins + nextHitPins.hitPins;
    }

    public int toComplementScore() {
        return Pins.MAX_HIT_COUNT - this.hitPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pins pins = (Pins) o;

        return hitPins == pins.hitPins;
    }

    @Override
    public int hashCode() {
        return hitPins;
    }

    @Override
    public String toString() {
        return "Pins{" +
                "hitPins=" + hitPins +
                '}';
    }
}
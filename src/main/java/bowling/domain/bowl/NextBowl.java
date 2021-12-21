package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;

public class NextBowl extends ProceedingBowl {

    private static final BowlType BOWL_TYPE = BowlType.NEXT;

    private final Pin previousPin;

    public NextBowl(int previousHitCount) {
        this(Pin.from(previousHitCount));
    }

    public NextBowl(Pin previousPin) {
        checkNotStrike(previousPin);
        this.previousPin = previousPin;
    }

    private void checkNotStrike(Pin previousPin) {
        if (previousPin.isAllHit()) {
            throw new BowlCreationException("이전 핀이 스트라이크일 수 없습니다.");
        }
    }

    @Override
    public Bowl pitch(Pin pin) {
        Pin sumOfPin = previousPin.plus(pin);
        if (sumOfPin.isAllHit()) {
            return new SpareBowl(previousPin, pin);
        }
        if (sumOfPin.isNoneHit()) {
            return GutterBowl.bowl();
        }
        return new MissBowl(previousPin, pin);
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public List<Pin> pins() {
        return singletonList(previousPin);
    }

    @Override
    public String toString() {
        return "NextBowl{" +
                "previousPin=" + previousPin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextBowl nextBowl = (NextBowl) o;
        return Objects.equals(previousPin, nextBowl.previousPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousPin);
    }
}

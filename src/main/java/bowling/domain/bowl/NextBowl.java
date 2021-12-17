package bowling.domain.bowl;

import bowling.domain.pin.Pin;

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
}

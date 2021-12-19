package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MissBowl extends FinishedBowl {

    public static final String NOT_MISS_MESSAGE = "미스가 아닙니다.";
    private static final BowlType BOWL_TYPE = BowlType.MISS;

    private final Pin firstPin;
    private final Pin secondPin;

    public MissBowl(int firstHitCount, int secondHitCount) {
        this(Pin.from(firstHitCount), Pin.from(secondHitCount));
    }

    public MissBowl(Pin firstPin, Pin secondPin) {
        checkMiss(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void checkMiss(Pin firstPin, Pin secondPin) {
        Pin sumOfPin = firstPin.plus(secondPin);
        if (sumOfPin.isAllHit() || sumOfPin.isNoneHit()) {
            throw new CanNotPitchException(NOT_MISS_MESSAGE);
        }
    }

    @Override
    public Score score() {
        Pin sumOfPin = firstPin.plus(secondPin);
        return sumOfPin.toScore();
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public List<Pin> pins() {
        List<Pin> pins = new ArrayList<>();
        pins.add(firstPin);
        pins.add(secondPin);
        return Collections.unmodifiableList(pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissBowl missBowl = (MissBowl) o;
        return Objects.equals(firstPin, missBowl.firstPin) && Objects.equals(secondPin, missBowl.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}

package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.state.HitState;

import java.util.Objects;

public class Hit {

    private final int value;
    private final Hit previousHit;

    public Hit(int value) {
        this(value, null);
    }

    public Hit(int value, Hit previousHit) {
        validateHitIsNegative(value);
        validateHitIsUnderRemainedPins(value, previousHit);
        this.value = value;
        this.previousHit = previousHit;
    }

    private void validateHitIsNegative(int hit) {
        if (hit < Frame.MIN_NUMBER_OF_BOWLING_PINS) {
            throw new IllegalArgumentException(String.format("투구는 %d 보다 작을 수 없습니다.", Frame.MIN_NUMBER_OF_BOWLING_PINS));
        }
    }

    private void validateHitIsUnderRemainedPins(int hit, Hit previousHit) {
        if (previousHit == null) {
            return;
        }

        int remainedPins = getRemainedPins(previousHit);
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", remainedPins));
        }
    }

    private int getRemainedPins(Hit previousHit) {
        HitState state = previousHit.getState();
        if (HitState.STRIKE.equals(state) || HitState.SPARE.equals(state)) {
            return Frame.MAX_NUMBER_OF_BOWLING_PINS;
        }

        return Frame.MAX_NUMBER_OF_BOWLING_PINS - previousHit.value;
    }

    public int getValue() {
        return value;
    }

    public HitState getState() {
        return HitState.from(value, previousHit == null ? null : previousHit.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return value == hit.value && Objects.equals(previousHit, hit.previousHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, previousHit);
    }

    @Override
    public String toString() {
        return "Hit{" +
                "value=" + value +
                ", previousHit=" + previousHit +
                '}';
    }

}

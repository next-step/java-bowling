package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Ready;

import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame extends AbstractFrame {

    public static final int MAX_BOWLCOUNT = 3;
    public static final int FRAMENUMBER = 10;
    public static final String MESSAGE_DELIMITER = "|";

    private int bowlCount = 0;

    FinalFrame(int maxBowlCount, int frameNumber) {
        super(maxBowlCount, frameNumber);
    }

    public static Frame init() {
        return new FinalFrame(MAX_BOWLCOUNT, FRAMENUMBER);
    }

    @Override
    public void bowl(Pin pin) {
        assertFinished();

        if (currentStatus().isFinished()) {
            statuses.add(new Ready());
        }

        super.bowl(pin);
        bowlCount++;
    }

    @Override
    public boolean isFinished() {
        if (bowlCount == MAX_BOWLCOUNT) {
            return true;
        }
        if (bowlCount == MAX_BOWLCOUNT - 1) {
            return statuses.stream()
                    .anyMatch(status -> status instanceof Miss);
        }
        return false;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다. 다음 프레임을 생성할 수 없습니다.");
    }

    @Override
    public String toString() {
        return statuses.stream()
                .map(Object::toString)
                .collect(Collectors.joining(MESSAGE_DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FinalFrame that = (FinalFrame) o;
        return bowlCount == that.bowlCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bowlCount);
    }
}

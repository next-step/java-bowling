package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    protected final List<Status> statuses;
    protected final int frameNumber;

    AbstractFrame(int maxBowlCount, int frameNumber) {
        statuses = new ArrayList<>(maxBowlCount);
        statuses.add(new Ready());

        this.frameNumber = frameNumber;
    }

    @Override
    public void bowl(Pin pin) {
        Status currentStatus = currentStatus();
        statuses.remove(currentStatusIndex());

        statuses.add(currentStatus.bowl(pin));
    }

    @Override
    public int frameNumber() {
        return frameNumber;
    }

    protected void assertFinished() {
        if (isFinished()) {
            throw new IllegalStateException("현재 프레임에서는 더 이상 게임을 진행할 수 없습니다.");
        }
    }

    protected Status currentStatus() {
        return statuses.get(currentStatusIndex());
    }

    protected int currentStatusIndex() {
        return statuses.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFrame that = (AbstractFrame) o;
        return frameNumber == that.frameNumber && Objects.equals(statuses, that.statuses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statuses, frameNumber);
    }
}

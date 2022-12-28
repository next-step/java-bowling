package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    public static final int MAX_BOWLCOUNT = 3;
    public static final String FINALFRAME_MESSAGE_DELIMITER = "|";

    private final List<Status> statuses;
    private final int frameNumber = 10;

    private int bowlCount = 0;

    public FinalFrame() {
        this(initStatuses());
    }

    public FinalFrame(List<Status> statuses) {
        this.statuses = statuses;
    }

    private static List<Status> initStatuses() {
        List<Status> statuses = new ArrayList<>(MAX_BOWLCOUNT);
        statuses.add(new Ready());
        return statuses;
    }

    @Override
    public void bowl(Pin pin) {
        assertFinished();
        if (currentStatus().isFinished()) {
            statuses.add(new Ready());
        }

        Status currentStatus = currentStatus();
        statuses.remove(currentStatusIndex());

        statuses.add(currentStatus.bowl(pin));
        bowlCount++;
    }

    private Status currentStatus() {
        return statuses.get(currentStatusIndex());
    }

    private int currentStatusIndex() {
        return statuses.size() - 1;
    }

    private void assertFinished() {
        if (isFinished()) {
            throw new IllegalStateException("현재 프레임에서는 더 이상 게임을 진행할 수 없습니다.");
        }
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
    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        return statuses.stream()
                .map(Object::toString)
                .collect(Collectors.joining(FINALFRAME_MESSAGE_DELIMITER));
    }
}

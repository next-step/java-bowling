package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.status.Ready;
import bowling.domain.status.Spare;
import bowling.domain.status.Status;
import bowling.domain.status.Strike;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame extends Frame {

    private final List<Status> statuses;

    public FinalFrame(List<Status> statuses) {
        this.statuses = statuses;
    }

    public static FinalFrame init() {
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Ready());
        return new FinalFrame(statuses);
    }

    @Override
    public Frame bowl(Pin pin) {
        int curIdx = statuses.size() - 1;
        status = statuses.get(curIdx).bowl(pin);
        statuses.set(curIdx, status);

        if (status instanceof Strike || status instanceof Spare) {
            statuses.add(new Ready());
        }
        return new FinalFrame(statuses);
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public Boolean isFinalFrame() {
        return true;
    }

    @Override
    public Frame nextFrame() {
        throw new RuntimeException("마지막 프레임입니다.");
    }

    @Override
    public Boolean isFinished() {
        return statuses.get(statuses.size() - 1).isFinished();
    }
}

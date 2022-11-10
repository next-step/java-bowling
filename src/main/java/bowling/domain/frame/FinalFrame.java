package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.status.*;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame extends Frame {

    private final List<Status> statuses;

    private FinalFrame(List<Status> statuses) {
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
        return this;
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
    public Score getScore() {
        return null;
    }

    @Override
    public Score addScore(Score preScore) {
        return null;
    }

    @Override
    public Boolean isFinished() {
        if (statuses.size() == 1 && statuses.get(0) instanceof Miss) return true;
        if (statuses.size() == 2 && statuses.get(0) instanceof Spare && statuses.get(1) instanceof FirstBowl) return true;
        if (statuses.size() == 2 && statuses.get(0) instanceof Strike && statuses.get(1) instanceof Spare) return true;
        if (statuses.size() == 2 && statuses.get(0) instanceof Strike && statuses.get(1) instanceof Miss) return true;
        return false;
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}

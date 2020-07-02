package bowling.domain.frame;

import bowling.domain.status.Status;
import bowling.domain.status.running.Ready;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int BONUS_TRY_COUNT = 3;

    private int trying;
    private LinkedList<Status> status = new LinkedList<>();

    public FinalFrame() {
        this.trying = 0;
        status.add(new Ready());
    }

    public static Frame init() {
        return new FinalFrame();
    }

    public Status bowl(int downPin) {
        Status currentStatus = this.status.getLast();
        Status status = currentStatus.bowl(downPin);
        updateStatus(status);
        trying++;

        if (status.isClearAllPins()) {
            settingBonus();
        }

        return status;
    }

    @Override
    public Frame nextFrame(int index) {
        throw new IllegalStateException("볼링이 종료 되었습니다.");
    }

    @Override
    public String printFrameResult() {
        return status.stream()
                .map(status -> status.printResult())
                .collect(Collectors.joining("|"));
    }

    @Override
    public boolean canPlayMore() {
        return status.getLast().canPlayMore() && trying < BONUS_TRY_COUNT;
    }

    private void updateStatus(Status status) {
        this.status.removeLast();
        this.status.add(status);
    }

    private void settingBonus() {
        if (trying < BONUS_TRY_COUNT) {
            this.status.add(new Ready());
        }
    }

}

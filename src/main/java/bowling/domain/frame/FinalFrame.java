package bowling.domain.frame;

import bowling.domain.status.Miss;
import bowling.domain.status.Spare;
import bowling.domain.status.Status;
import bowling.domain.status.Strike;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int FIRST_TRY = 0;
    private static final int MAX_TRY_COUNT = 2;
    private static final int BONUS_TRY_COUNT = 3;

    private Pins pins;
    private int trying;
    private List<Status> statuses = new LinkedList<>();

    public FinalFrame(Pins pins, int trying) {
        this.pins = pins;
        this.trying = trying;
    }

    public static Frame init() {
        return new FinalFrame(Pins.init(), 0);
    }

    public void bowl(int downPin) {
        Status status = bowling(downPin);
        trying++;

        bonusPinSetting(status);
        statuses.add(status);
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("볼링이 종료 되었습니다.");
    }

    @Override
    public boolean isLastTryAtFrame() {
        if (haveBonus()) {
            return trying == BONUS_TRY_COUNT;
        }
        return trying == MAX_TRY_COUNT;
    }

    @Override
    public String printFrameResult() {
        return statuses.stream()
                .map(status -> status.printResult())
                .collect(Collectors.joining("|"));
    }

    private Status bowling(int downPin) {
        if (trying == FIRST_TRY || haveBonus()) {
            return pins.firstBowl(downPin);
        }
        return pins.bowl(downPin);
    }

    private void bonusPinSetting(Status status) {
        if (isBonusCondition(status)) {
            pins = Pins.init();
        }
        if (isStatusRemoveCondition(status)) {
            statuses.remove(0);
        }
    }

    private boolean isStatusRemoveCondition(Status status) {
        return status instanceof Spare || !haveBonus() && isLastTryAtFrame();
    }

    private boolean haveBonus() {
        return statuses.stream().anyMatch(status -> isBonusCondition(status));
    }

    private boolean isBonusCondition(Status status) {
        return status instanceof Strike || status instanceof Spare;
    }


    @Override
    public String toString() {
        return "FinalFrame{" +
                "pins=" + pins.getDownPin() +
                ", status=" + printFrameResult() +
                ", trying=" + trying +
                '}';
    }
}

package bowling.domain.frame;

import bowling.domain.status.Pending;
import bowling.domain.status.Status;

public class NormalFrame implements Frame {
    public static final int PLUS = 1;
    private static final int FIRST_TRY = 0;
    private static final int MAX_TRY_COUNT = 2;
    Frame next;
    private Pins pins;
    private Status status;
    private int trying;

    public NormalFrame(Pins pins, Status status, int trying) {
        this.pins = pins;
        this.status = status;
        this.trying = trying;
    }

    public NormalFrame(Pins pins, int trying) {
        this.pins = pins;
        this.trying = trying;
    }

    public static Frame init() {
        return new NormalFrame(Pins.init(), 0);
    }


    public void bowl(int downPin) {
        status = bowling(downPin);
        trying = addTrying();
    }

    @Override
    public Frame next(int index) {
        if (index == 9) {
            next = FinalFrame.init();
            return next;
        }
        next = new NormalFrame(Pins.init(), new Pending(), 0);
        return next;
    }

    @Override
    public boolean isLastTryAtFrame() {
        return trying == MAX_TRY_COUNT;
    }

    public String printFrameResult() {
        return status.printResult();
    }

    private Status bowling(int downPin) {
        if (trying == FIRST_TRY) {
            return pins.firstBowl(downPin);
        }
        return pins.bowl(downPin, this.status);
    }

    private int addTrying() {
        if (!status.canPlayMore()) {
            return MAX_TRY_COUNT;
        }
        return trying + PLUS;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "next=" + next +
                ", pins=" + pins.getDownPin() +
                ", status=" + status.printResult() +
                ", trying=" + trying +
                '}';
    }
}

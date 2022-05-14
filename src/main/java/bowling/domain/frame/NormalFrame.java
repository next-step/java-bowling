package bowling.domain.frame;

import bowling.domain.pin.PinNumbers;
import bowling.domain.util.PinNoPrinter;

import static bowling.domain.frame.NormalFrameNo.MIN_NORMAL_FRAME_NO;

public class NormalFrame implements Frame {

    private final NormalFrameNo frameNo;

    private final PinNumbers pinNumbers;

    NormalFrame(int frameNo, int pinNo) {
        this.frameNo = NormalFrameNo.of(frameNo);
        this.pinNumbers = new PinNumbers(pinNo);
    }

    public static NormalFrame init(int pinNo) {
        return new NormalFrame(MIN_NORMAL_FRAME_NO, pinNo);
    }

    @Override
    public boolean isFull() {
        return pinNumbers.isFull();
    }

    @Override
    public void addPin(int pinNo) {
        pinNumbers.addPin(pinNo);
    }

    @Override
    public Frame nextFrame(int pinNo) {
        if (frameNo.isMax()) {
            return new FinalFrame(pinNo);
        }
        return new NormalFrame(frameNo.next(), pinNo);
    }

    @Override
    public String toExpression() {
        return PinNoPrinter.print(pinNumbers.getFirstNo(), pinNumbers.getSecondNo());
    }
}

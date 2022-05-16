package bowling.domain.frame;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.pin.NormalPinNumbers;

import static bowling.domain.frame.NormalFrameNo.MIN_NORMAL_FRAME_NO;

public class NormalFrame implements Frame {

    private final NormalFrameNo frameNo;

    private final NormalPinNumbers pinNumbers;

    private Frame nextFrame;

    NormalFrame(int frameNo, int pinNo) {
        this.frameNo = NormalFrameNo.of(frameNo);
        this.pinNumbers = new NormalPinNumbers(pinNo);
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
    public Frame getNextFrame(int pinNo) {
        if (frameNo.isMax()) {
            this.nextFrame = new FinalFrame(pinNo);
            return nextFrame;
        }
        this.nextFrame =  new NormalFrame(frameNo.next(), pinNo);
        return nextFrame;
    }

    @Override
    public String toExpression() {
        return pinNumbers.toExpression();
    }

    @Override
    public boolean canGetScore() {
        if (!isFull()) {
            return false;
        }

        FrameResult result = pinNumbers.getResult();
        if (result instanceof Miss) {
            return true;
        }
        if (result instanceof Spare) {
            return nextFrame != null;
        }
        return nextFrame != null && nextFrame.isFull();
    }

    @Override
    public int getScore() {
        FrameResult result = pinNumbers.getResult();
        if (result instanceof Miss) {
            return result.getScoreWithBonus(0);
        }
        if (result instanceof Spare) {
            return result.getScoreWithBonus(nextFrame.spareBonusForPreviousFrame());
        }
        return result.getScoreWithBonus(nextFrame.strikeBonusForPreviousFrame());
    }

    @Override
    public int spareBonusForPreviousFrame() {
        return pinNumbers.spareBonus();
    }

    @Override
    public int strikeBonusForPreviousFrame() {
        return pinNumbers.strikeBonus();
    }
}

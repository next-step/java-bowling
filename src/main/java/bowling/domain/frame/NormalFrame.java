package bowling.domain.frame;

import bowling.domain.frameresult.Strike;
import bowling.domain.pin.NormalPinNumbers;

import java.util.Optional;

import static bowling.domain.frame.NormalFrameNo.MIN_NORMAL_FRAME_NO;
import static bowling.domain.pin.PinNo.MAX_PIN_NO;

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
    public void addPin(int pinNo) {
        pinNumbers.addPin(pinNo);
    }

    @Override
    public Frame nextFrame(int pinNo) {
        if (frameNo.isMax()) {
            this.nextFrame = new FinalFrame(pinNo);
            return nextFrame;
        }
        this.nextFrame =  new NormalFrame(frameNo.next(), pinNo);
        return nextFrame;
    }

    @Override
    public boolean canGetScore() {
        return pinNumbers.isFull();
    }

    @Override
    public Optional<Integer> score() {
        if (!canGetScore()) {
            return Optional.empty();
        }
        return pinNumbers.result().score(nextFrame);
    }

    @Override
    public int spareBonusForPreviousFrame() {
        return pinNumbers.spareBonus();
    }

    @Override
    public Optional<Integer> strikeBonusForPreviousFrame() {
        if (!pinNumbers.isFull())  {
            return Optional.empty();
        }
        if (!isStrike()) {
            return Optional.of(pinNumbers.strikeBonus());
        }
        if (nextFrame == null) {
            return Optional.empty();
        }
        return Optional.of(MAX_PIN_NO + nextFrame.spareBonusForPreviousFrame());
    }

    private boolean isStrike() {
        return pinNumbers.result() instanceof Strike;
    }

    @Override
    public String expression() {
        return pinNumbers.expression();
    }
}

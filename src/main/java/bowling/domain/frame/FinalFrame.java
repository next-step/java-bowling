package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.Status;
import bowling.domain.frame.info.FinalFrameInfo;
import bowling.domain.frame.info.FrameInfo;

public class FinalFrame implements Frame {

    private final FinalFrameInfo frameInfo;
    private final Pins pins;

    public FinalFrame(Pins pins, FinalFrameInfo frameInfo) {
        this.frameInfo = frameInfo;
        this.pins = pins;
    }

    public static Frame create() {
        return new FinalFrame(Pins.create(), FinalFrameInfo.create());
    }

    @Override
    public Frame roll(int downPins) {
        validate();

        Pins downedPins = pins.roll(downPins);

        if (downedPins.isStrike() || downedPins.isSpare() || frameInfo.hasBonusRound()) {
            return new FinalFrame(Pins.create(), frameInfo.nextRoundWithBonusRound());
        }

        return new FinalFrame(Pins.from(pins), frameInfo.nextRound());
    }

    private void validate() {
        if (frameInfo.isLastRound() && !frameInfo.hasBonusRound()) {
            throw new IllegalArgumentException("스페어처리를 못하여서 3라운드를 진행 할 수 없습니다.");
        }
    }

    @Override
    public boolean hasNextRound() {
        return frameInfo.hasNextRound();
    }

    @Override
    public Status pinStatus() {
        return pins.status();
    }

    @Override
    public int numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public FrameInfo frameInfo() {
        return frameInfo;
    }

}

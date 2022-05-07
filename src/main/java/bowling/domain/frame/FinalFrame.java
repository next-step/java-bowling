package bowling.domain.frame;

import bowling.domain.frame.info.FinalFrameInfo;
import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;
import bowling.domain.pins.Status;
import bowling.domain.score.Score;
import java.util.Optional;

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

    public static Frame of(Pins pins, FinalFrameInfo frameInfo) {
        return new FinalFrame(pins, frameInfo);
    }

    @Override
    public void roll(int downPins) {
        pins.roll(Score.from(downPins), frameInfo);
    }

    @Override
    public Optional<Frame> nextRound() {
        if (!frameInfo.isLastRound() && isCurrentRoundEnd() || pins.isSpare()) {
            return Optional.of(new FinalFrame(Pins.create(), frameInfo.nextRoundWithBonusRound()));
        }

        if (!frameInfo.isLastRound()) {
            return Optional.of(new FinalFrame(Pins.of(pins, Status.READY), frameInfo.nextRound()));
        }

        return Optional.empty();
    }

    private boolean isCurrentRoundEnd() {
        return pins.isAllDown() || frameInfo.hasBonusRound();
    }

    @Override
    public boolean hasNextRound() {
        return frameInfo.hasNextRound() || pins.isReady();
    }

    @Override
    public boolean isFrameEnd(int currentFrame) {
        return !hasNextRound() || frameInfo.isAfterFrame(currentFrame);
    }

    @Override
    public Optional<Score> calcScore(Frames playerFrames) {
        if (!hasNextRound()) {
            return Optional.of(Score.of(numberOfDownedPins().score(), true));
        }

        return Optional.of(numberOfDownedPins());
    }

    @Override
    public Pins pins() {
        return pins;
    }

    @Override
    public Score numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public FrameInfo frameInfo() {
        return frameInfo;
    }

}

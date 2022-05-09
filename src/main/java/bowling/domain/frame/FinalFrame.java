package bowling.domain.frame;

import bowling.domain.frame.info.FinalFrameInfo;
import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;
import bowling.domain.pins.Status;
import bowling.domain.rule.FinalRule;
import bowling.domain.rule.Rule;
import bowling.domain.score.Score;
import java.util.Optional;

public class FinalFrame extends Frame {

    protected FinalFrame(Rule rule, FrameInfo frameInfo, Pins pins) {
        super(rule, frameInfo, pins);
    }

    public static Frame create() {
        return of(Pins.create(), FinalFrameInfo.create());
    }

    public static Frame of(Pins pins, FrameInfo frameInfo) {
        return new FinalFrame(new FinalRule(frameInfo, pins), frameInfo, pins);
    }

    @Override
    public void roll(int downPins) {
        pins.roll(Score.from(downPins), frameInfo);
    }

    @Override
    public Optional<Frame> nextRound() {
        if (rule.isEnd()) {
            return Optional.empty();
        }

        return next();
    }

    private Optional<Frame> next() {
        if (rule.isNewPins()) {
            return Optional.of(of(Pins.create(), FinalFrameInfo.ofBonusFinalFrame(frameInfo)));
        }

        return Optional.of(of(Pins.of(pins, Status.READY), frameInfo.nextRound()));
    }

    @Override
    public boolean canRoll() {
        return !rule.isEnd();
    }

    @Override
    public boolean isFrameEnd(int currentFrame) {
        return rule.isFrameEnd(currentFrame);
    }

    @Override
    public Optional<Score> calcScore(Frames playerFrames) {
        if (!canRoll()) {
            return Optional.of(Score.of(numberOfDownedPins().score(), true));
        }

        return Optional.of(numberOfDownedPins());
    }


}

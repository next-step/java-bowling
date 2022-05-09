package bowling.domain.frame;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.frame.info.NormalFrameInfo;
import bowling.domain.pins.Pins;
import bowling.domain.pins.Status;
import bowling.domain.rule.NormalRule;
import bowling.domain.rule.Rule;
import bowling.domain.score.Score;
import java.util.Optional;

public class NormalFrame implements Frame {

    private final Rule rule;
    private final FrameInfo frameInfo;
    private final Pins pins;

    private NormalFrame(Rule rule, FrameInfo normalFrameInfo, Pins pins) {
        this.rule = rule;
        this.frameInfo = normalFrameInfo;
        this.pins = pins;
    }

    public static NormalFrame create() {
        return of(NormalFrameInfo.create(), Pins.create());
    }

    public static NormalFrame of(FrameInfo normalFrameInfo, Pins pins) {
        return new NormalFrame(new NormalRule(normalFrameInfo, pins), normalFrameInfo, pins);
    }

    @Override
    public void roll(int downPins) {
        pins.roll(Score.from(downPins), frameInfo);
    }

    @Override
    public Optional<Frame> nextRound() {
        if (rule.isEnd()) {
            return Optional.of(FinalFrame.create());
        }

        return next();
    }

    private Optional<Frame> next() {
        if (rule.isNewPins()) {
            return Optional.of(of(frameInfo.nextFrame(), Pins.create()));
        }

        return Optional.of(of(frameInfo.nextRound(), Pins.of(pins, Status.READY)));
    }

    @Override
    public boolean canRoll() {
        return rule.hasNextRound();
    }

    @Override
    public boolean isFrameEnd(int givenFrame) {
        return rule.isFrameEnd(givenFrame);
    }

    @Override
    public Optional<Score> calcScore(Frames playerFrames) {
        if (pins.isStrike()) {
            return Optional.of(playerFrames.calcStrike(this));
        }

        if (pins.isSpare()) {
            return Optional.of(playerFrames.calcSpare(this));
        }

        if (!canRoll()) {
            return Optional.of(playerFrames.calcMiss(this));
        }

        return Optional.empty();
    }

    @Override
    public Score numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public FrameInfo frameInfo() {
        return frameInfo;
    }

    @Override
    public Pins pins() {
        return pins;
    }

}

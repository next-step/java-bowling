package bowling.domain.frame;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;
import bowling.domain.rule.Rule;
import bowling.domain.score.Score;
import java.util.Optional;

public abstract class Frame {

    protected final Rule rule;
    protected final FrameInfo frameInfo;
    protected final Pins pins;

    protected Frame(Rule rule, FrameInfo frameInfo, Pins pins) {
        this.rule = rule;
        this.frameInfo = frameInfo;
        this.pins = pins;
    }

    public abstract void roll(int downPins);

    public abstract Optional<Frame> nextRound();

    public abstract boolean canRoll();

    public abstract boolean isFrameEnd(int currentFrame);

    public abstract Optional<Score> calcScore(Frames playerFrames);

    public int compareTo(Frame other) {
        return frameInfo.compareTo(other.frameInfo);
    }

    public boolean isRolled() {
        return !pins.isReady();
    }

    public Score numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    public FrameInfo frameInfo() {
        return frameInfo;
    }

    public Pins pins() {
        return pins;
    }

}

package bowling.domain;

import bowling.domain.state.State;

import java.util.Arrays;
import java.util.List;

public abstract class Frame {
    public static final int FIRST_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    protected static final List<Shot> BONUS_SHOT = Arrays.asList(Shot.STRIKE, Shot.SPARE);
    protected int frameNo;
    protected Pitch pitch;
    protected Frame nextFrame;

    public Frame(int frameNo) {
        this.frameNo = frameNo;
        this.pitch = Pitch.of();
    }

    public static Frame first() {
        return new NormalFrame(FIRST_FRAME);
    }

    public static Frame middle(int frameNo) {
        return new NormalFrame(frameNo);
    }

    public static Frame last() {
        return new FinalFrame();
    }

    public Frame next() {
        if (frameNo == FINAL_FRAME) {
            throw new IllegalArgumentException("10 Frame is last frame");
        }
        if (frameNo + 1 == FINAL_FRAME) {
            nextFrame = Frame.last();
            return nextFrame;
        }
        nextFrame = Frame.middle(frameNo + 1);
        return nextFrame;
    }

    public abstract State bowling(Pin pin);

    public abstract ShotHistory getShotHistory();

    public abstract Score calculateScore();

    public abstract Score calculateBonusScore(Shot shot);

    public abstract boolean isGameEnd();

    public Pitch getPitch() {
        return pitch;
    }
}

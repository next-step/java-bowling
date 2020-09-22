package bowling;

import java.util.Objects;

public class Frame {

    private final Pitchings pitchings;
    private Frame nextFrame;

    private Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
    }

    public static Frame of() {
        return new Frame(NormalPitchings.ofReady());
    }

    public Pitchings getPitchings() {
        return this.pitchings;
    }

    public Frame getNextFrame() {
        return this.nextFrame;
    }

    public boolean isLastFrame() {
        return Objects.isNull(nextFrame);
    }

    public void bowl(Pin pin) {
        pitchings.bowl(pin);
    }

    public Pin getFirstPin() {
        return pitchings.getFirstPin();
    }

    public Pin getSecondPin() {
        return pitchings.getSecondPin();
    }

    public Pin getBonusPin() {
        return pitchings.getBonusPin();
    }

    public boolean isFirstDone() {
        return pitchings.isFirstDone();
    }

    public boolean isSecondDone() {
        return pitchings.isSecondDone();
    }

    public boolean isBonusDone() {
        return pitchings.isBonusDone();
    }

    public boolean isDone() {
        return pitchings.isDone();
    }

    public Frame next() {
        this.nextFrame = of();
        return this.nextFrame;
    }

    public void last() {
        this.nextFrame = new Frame(LastPitchings.ofReady());
    }

    public boolean isStrike() {
        return pitchings.isStrike();
    }

    public boolean isSpare() {
        return pitchings.isSpare();
    }

    public boolean canCalculateScore() {
        if (isLastFrame()) {
            return isDone();
        }
        FrameStatus frameStatus = FrameStatus.of(this);
        return frameStatus.canCalculateScore(this);
    }

    public int calculateScore() {
        if (!canCalculateScore()) {
            throw new IllegalStateException("점수를 계산할 수 없습니다.");
        }
        if (isLastFrame()) {
            return pitchings.calculateScore();
        }
        FrameStatus frameStatus = FrameStatus.of(this);
        return pitchings.calculateScore() + frameStatus.getBonusScore(this);
    }
}

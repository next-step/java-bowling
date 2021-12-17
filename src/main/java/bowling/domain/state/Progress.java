package bowling.domain.state;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.FrameInfo;
import bowling.domain.Pins;

import java.util.Objects;

public class Progress implements State {
    private final boolean retry;

    public Progress() {
        this(false);
    }

    public Progress(boolean retry) {
        this.retry = retry;
    }

    @Override
    public void pitch(Pins existPins, Pins fallDownPins, Frame frame) {
        if (existPins.isStrike(fallDownPins)) {
            checkFrame(frame);
            return;
        }
        checkRetry(frame);
    }

    @Override
    public boolean progressing() {
        return true;
    }

    private void checkFrame(Frame frame) {
        if (frame instanceof FinalFrame) {
            checkPitchNo(frame);
            return;
        }
        frame.changeState(new End());
    }

    private void checkRetry(Frame frame) {
        Progress progress = (Progress) frame.state();
        if (progress.retry) {
            checkPitchNo(frame);
            return;
        }
        frame.changeState(new End());
    }

    private void checkPitchNo(Frame frame) {
        FrameInfo frameInfo = frame.info();
        if (frameInfo.isSecondPitch()) {
            frame.changeState(new Progress());
            return;
        }
        frame.changeState(new End());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return retry == progress.retry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(retry);
    }

    @Override
    public String toString() {
        return "Progress{" +
                "retry=" + retry +
                '}';
    }
}

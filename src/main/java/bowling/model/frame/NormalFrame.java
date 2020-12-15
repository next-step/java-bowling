package bowling.model.frame;

public class NormalFrame extends Frame {

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static NormalFrame createFirstFrame() {
        return new NormalFrame(FrameNumber.from(1));
    }

    private NormalFrame next() {
        return new NormalFrame(frameNumber.next());
    }

    @Override
    public Frame bowling(int fallenPins) {
        states.bowling(fallenPins);

        if (frameNumber.beforeLast() && states.isFinished()) {
            return new FinalFrame();
        }

        if (states.isFinished()) {
            return next();
        }

        return this;
    }

    @Override
    public String toString() {
        return states.toString();
    }
}

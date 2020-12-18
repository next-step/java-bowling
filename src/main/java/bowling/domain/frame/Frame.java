package bowling.domain.frame;

public class Frame {

    private static final int FIRST_FRAME_NUMBER = 0;
    private static final int FINAL_FRAME_NUMBER = 9;

    private final FrameNumber frameNumber;
    private final Pins pins;

    private Frame nextFrame;

    private Frame(FrameNumber frameNumber, Pins pins) {
        this.frameNumber = frameNumber;
        this.pins = pins;
    }

    public static Frame first() {
        return new Frame(FrameNumber.create(FIRST_FRAME_NUMBER), new NormalPins());
    }

    public Frame last() {
        this.nextFrame = new Frame(FrameNumber.create(FINAL_FRAME_NUMBER), FinalFramePins.create());
        return this.nextFrame;
    }

    public Frame next() {
        this.nextFrame = new Frame(FrameNumber.create(this.frameNumber.getValue() + 1), new NormalPins());
        return this.nextFrame;
    }

    public void roll(int numberOfDownPin) {
        this.pins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.pins.hasTurn();
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.pins.getDownPins(), this.pins.getScoreType());
    }
}

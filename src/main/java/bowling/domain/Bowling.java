package bowling.domain;

public class Bowling {
    private static final FrameFatory normalFrameFactory = new NormalFrameFactory();
    private static final FrameFatory finalFrameFactory = new FinalFrameFactory();

    private final BowlingResult bowlingResult;
    private Frame currentFrame;

    public Bowling(Frame frame) {
        bowlingResult = new BowlingResult();
        currentFrame = frame;
    }

    public Bowling() {
        bowlingResult = new BowlingResult();
        currentFrame = new NormalFrame();
    }

    public void roll(Pinfall pinfall) {
        FrameFatory frameFatory = normalFrameFactory;
        if (currentFrame.frameNumber().equals(new FrameNumber(9))) {
            frameFatory = finalFrameFactory;
        }

        Frame newFrame = currentFrame.roll(pinfall, frameFatory);
        bowlingResult.add(currentFrame.frameNumber(), currentFrame.result());
        if (!currentFrame.equals(newFrame)) {
            currentFrame = newFrame;
        }
    }

    public BowlingResult result() {
        return bowlingResult;
    }

    public FrameNumber frameNumber() {
        return currentFrame.frameNumber();
    }

    public boolean isDone() {
        return isLastFrame() && currentFrame.isDone();
    }

    private boolean isLastFrame() {
        return currentFrame.frameNumber().equals(new FrameNumber(10));
    }
}

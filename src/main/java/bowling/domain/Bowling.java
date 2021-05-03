package bowling.domain;

public class Bowling {
    private static final FrameFatory normalFrameFactory = new NormalFrameFactory();
    private static final FrameFatory finalFrameFactory = new FinalFrameFactory();

    private final BowlingResult bowlingResult;
    private final Frame firstFrame;
    private Frame currentFrame;

    public Bowling(Frame frame) {
        bowlingResult = new BowlingResult();
        firstFrame = frame;
        currentFrame = frame;
    }

    public Bowling() {
        bowlingResult = new BowlingResult();
        firstFrame = new NormalFrame();
        currentFrame = firstFrame;
    }

    public void roll(Pinfall pinfall) {
        FrameFatory frameFatory = normalFrameFactory;
        if (currentFrame.frameNumber().equals(new FrameNumber(9))) {
            frameFatory = finalFrameFactory;
        }

        currentFrame = currentFrame.roll(pinfall, frameFatory);
    }

    public BowlingResult result() {
        Frame indexFrame = firstFrame;
        bowlingResult.add(indexFrame.frameNumber(), indexFrame.pointSymbols(), indexFrame.score());

        while (indexFrame.hasNext()) {
            indexFrame = indexFrame.next();
            bowlingResult.add(indexFrame.frameNumber(), indexFrame.pointSymbols(), indexFrame.score());
        }

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

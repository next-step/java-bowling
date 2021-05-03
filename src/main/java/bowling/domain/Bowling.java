package bowling.domain;

public class Bowling {
    private final Frame firstFrame;
    private Frame currentFrame;

    public Bowling(Frame frame) {
        firstFrame = frame;
        currentFrame = frame;
    }

    public Bowling() {
        firstFrame = new NormalFrame();
        currentFrame = firstFrame;
    }

    public void roll(Pinfall pinfall) {
        FrameFatory frameFatory = new NormalFrameFactory();
        if (currentFrame.frameNumber().equals(new FrameNumber(9))) {
            frameFatory = new FinalFrameFactory();
        }

        currentFrame = currentFrame.roll(pinfall, frameFatory);
    }

    public BowlingResult result() {
        BowlingResult bowlingResult = new BowlingResult();
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

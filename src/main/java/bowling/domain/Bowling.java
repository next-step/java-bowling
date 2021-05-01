package bowling.domain;

public class Bowling {
    private final BowlingResult bowlingResult;
    private Frame currentFrame;

    public Bowling() {
        bowlingResult = new BowlingResult();
        currentFrame = new NormalFrame();
    }

    public void roll(Pinfall pinfall) {
        Frame newFrame = currentFrame.roll(pinfall);
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
}

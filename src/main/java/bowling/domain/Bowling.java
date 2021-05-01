package bowling.domain;

public class Bowling {
    private final BowlingResult bowlingResult;
    private NormalFrame currentFrame;

    public Bowling() {
        bowlingResult = new BowlingResult();
        currentFrame = new NormalFrame();
    }

    public void roll(Pinfall pinfall) {
        NormalFrame newFrame = currentFrame.roll(pinfall);
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

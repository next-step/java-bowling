package bowling.domain;

import java.util.Objects;

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
        return result(Player.noname());
    }

    public BowlingResult result(Player player) {
        BowlingResult bowlingResult = new BowlingResult(player);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowling bowling = (Bowling) o;
        return Objects.equals(firstFrame, bowling.firstFrame) && Objects.equals(currentFrame, bowling.currentFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFrame, currentFrame);
    }


}

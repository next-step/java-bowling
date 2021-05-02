package bowling.domain;

public class BowlingGame {
    private Frame frame;
    private int currentFrameNumber;

    public BowlingGame(int startFrameNumber) {
        frame = new NormalFrame();
        currentFrameNumber = startFrameNumber;
    }

    public boolean isAvailable() {
        return frame.isAvailable();
    }

    public int bowling(String inputScore) {
        return frame.addScore(Integer.parseInt(inputScore));
    }

    public int nextFrameIfAvailable() {
        if (!frame.isAvailable() && !(frame instanceof FinalFrame)) {
            startNewFrame();
        }
        return currentFrameNumber;
    }

    private void startNewFrame() {
        frame = frame.createFrame(++currentFrameNumber);
    }
}

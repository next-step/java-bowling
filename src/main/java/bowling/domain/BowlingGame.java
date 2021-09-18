package bowling.domain;

public class BowlingGame {

    private final Frames frames;
    private static final int FINAL_GAME_INDEX = 9;

    public BowlingGame() {
        frames = new Frames();
    }

    public void start() {
        frames.addFrame(NormalFrame.first());
    }

    public Frame currentFrame() {
        return frames.currentFrame();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public void next() {
        if (isFinished()) {
            return;
        }

        if (currentFrame().isFinished()
                && currentFrame().findCurrentIndex() == FINAL_GAME_INDEX) {
            frames.addFrame(new FinalFrame());
            return;
        }

        if (currentFrame().isFinished()) {
            frames.addFrame(currentFrame().next());
        }
    }

    public Frames getFrames() {
        return frames;
    }
}

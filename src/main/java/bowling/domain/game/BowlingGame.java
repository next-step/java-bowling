package bowling.domain.game;

import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames;
import java.util.List;

public class BowlingGame {

    private final Frames frames;

    private BowlingGame(Frames frames) {
        this.frames = frames;
    }

    public static BowlingGame start() {
        Frames frames = Frames.create();
        return new BowlingGame(frames);
    }

    public void roll(int numberOfDownPin) {
        this.frames.roll(numberOfDownPin);
    }

    public int getFrameNumber() {
        return this.frames.getCurrentFrameNumber();
    }

    public List<FrameResult> getResult() {
        return this.frames.getFrameResults();
    }

    public boolean isFinished() {
        return this.frames.isFinished();
    }
}

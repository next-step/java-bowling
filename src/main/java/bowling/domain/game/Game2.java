package bowling.domain.game;

import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames2;

import java.util.List;

public class Game2 {
    private final Frames2 frames;

    public Game2(Frames2 frames) {
        this.frames = frames;
    }

    public static Game2 start() {
        Frames2 frame = Frames2.generate();
        return new Game2(frame);
    }

    public void roll(int pin) {
        this.frames.roll(pin);
    }

    public int getFrameIndex() {
        return this.frames.getCurrentIndex();
    }

    public List<FrameResult> getResults() {
        return this.frames.getFrameResults();
    }

    public boolean isGameOver() {
        return this.frames.isDone();
    }
}

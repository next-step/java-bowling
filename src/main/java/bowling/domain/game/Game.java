package bowling.domain.game;

import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames;

import java.util.List;

public class Game {
    private final Frames frames;

    public Game(Frames frames) {
        this.frames = frames;
    }

    public static Game start() {
        Frames frame = Frames.generate();
        return new Game(frame);
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

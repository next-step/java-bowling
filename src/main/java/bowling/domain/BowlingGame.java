package bowling.domain;

import bowling.domain.dto.FrameResult;
import bowling.domain.frame.Frames;
import bowling.domain.pin.PinCount;

import java.util.List;

public class BowlingGame {

    private final Frames frames;

    private BowlingGame() {
        frames = Frames.of();
    }

    public static BowlingGame of() {
        return new BowlingGame();
    }

    public void play(final int hitCount) {
        frames.bowl(PinCount.of(hitCount));
    }

    public boolean isGameOver() {
        return this.frames.isGameOver();
    }

    public int getFrameNumber() {
        return this.frames.getFrameNumber();
    }

    public List<FrameResult> getFrameResults() {
        return frames.getFrameResult();
    }
}

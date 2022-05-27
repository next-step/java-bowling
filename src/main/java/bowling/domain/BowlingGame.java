package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {

    private final Frames frames;

    private final Player player;

    private BowlingGame(Frames frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public static BowlingGame of(Frames frames, Player player) {
        return new BowlingGame(frames, player);
    }

    public void pitch(Pins pins) {
        frames.pitch(pins);
    }

    public boolean isRunning() {
        return frames.isRunning();
    }

    public boolean isFinish() {
        return !isRunning();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public Player player() {
        return player;
    }

    public List<Integer> sumScores() {
        return frames.sumScores();
    }

    public void nextRound() {
        frames.nextIndex();
    }

    public boolean isCurrentFrameEnd() {
        return frames.isCurrentFrameEnd();
    }

}

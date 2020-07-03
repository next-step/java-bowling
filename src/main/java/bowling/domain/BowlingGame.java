package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.result.Scores;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;
    private Scores scores;

    private BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
        this.scores = new Scores();
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player, Frames.init());
    }

    public void run(int downPin) {
        frames.bowl(downPin);
    }

    public void next() {
        frames.next();
    }

    public int getCurrentIndex() {
        return frames.getIndex();
    }

    public boolean isLastFrame() {
        return frames.isLastFrame();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String whoseTurn() {
        return player.getName();
    }

    public List<Integer> getScores() {
        return scores.getScores();
    }
}

package bowling.domain;

import java.util.List;

public class BowlingGameResult {

    private final Player player;
    private final Frames frames;

    private BowlingGameResult(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGameResult of(Player player, Frames frames) {
        return new BowlingGameResult(player, frames);
    }

    public BowlingGameResult initFrame(Frame frame) {
        frames.initNextFrame(frame);
        return this;
    }

    public Player getPlayer() {
        return player;
    }



    public BowlType printBowlType(int index) {
        return frames.getFrames().get(index).getScores().printScores();
    }

    public int getRound() {
        return getFrames().size();
    }

    private List<Frame> getFrames() {
        return frames.getFrames();
    }
}

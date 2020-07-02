package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {
    public static final int LAST_FRAME_INDEX = 10;

    private final Player player;
    private final Frames frames;

    private BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
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

    public boolean isLastFrame() {
        return frames.getIndex() == LAST_FRAME_INDEX;
    }

    public int getCurrentIndex() {
        return frames.getIndex();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String whoseTurn() {
        return player.getName();
    }
}

package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {
    private static final int LAST_GAME_INDEX = 10;

    private final Player player;
    private final Frames frames;
    private int currentIndex;

    private BowlingGame(Player player, Frames frames, int currentIndex) {
        this.player = player;
        this.frames = frames;
        this.currentIndex = currentIndex;
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player, Frames.init(), 1);
    }

    public void run(int downPin) {
        frames.bowl(downPin);
        if (frames.isLastTryAtFrame()) {
            currentIndex++;
        }
    }

    public boolean isLastFrame() {
        return currentIndex - 1 == LAST_GAME_INDEX;
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getFrameSize() {
        return frames.size();
    }

    public String whoseTurn() {
        return player.getName();
    }
}

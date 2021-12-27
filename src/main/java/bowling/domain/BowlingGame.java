package bowling.domain;

import bowling.annotations.ForUI;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public void prepareFrame() {
        frames.prepareFrame();
    }

    public boolean isCurrentFrameEnd() {
        return frames.isCurrentFrameEnd();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public void bowl(int knockedOutCount) {
        frames.bowl(knockedOutCount);
    }

    @ForUI
    public Player player() {
        return player;
    }

    @ForUI
    public List<Frame> frames() {
        return frames.values();
    }
}

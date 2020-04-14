package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.PinCount;

import java.util.List;

public class Game {
    private static final int ONE = 1;
    private static final int DEFAULT_FRAME_SIZE = 10;

    private Player player;
    private Frames frames;
    private Frame currentFrame;

    protected Game(String playerName, int frameSize) {
        this.player = new Player(playerName);
        this.frames = new Frames(frameSize);
        this.currentFrame = frames.getFirstFrame();
    }

    public Game(String playerName) {
        this(playerName, DEFAULT_FRAME_SIZE);
    }

    public boolean isFinished() {
        return !isAddable();
    }

    public Game addPin(int count) {
        if (!isAddable()) {
            return this;
        }

        currentFrame.addPinCount(count);
        if (currentFrame.isDone() && !currentFrame.isLast()) {
            currentFrame = currentFrame.getNext();
        }

        return this;
    }

    public List<PinCount> getFramePinCounts(int frameIndex) {
        return frames.getFramePinCounts(frameIndex);
    }

    public int getCurrentFrame() {
        return frames.getCurrentFrameIndex(currentFrame) + ONE;
    }

    public int getFrameTotal() {
        return frames.size();
    }

    public String getPlayerName() {
        return player.getName();
    }

    private boolean isAddable() {
        return !currentFrame.isLast() || !currentFrame.isDone();
    }
}

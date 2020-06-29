package bowling.domain.bowlinggame;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.utils.ElementFindUtils;

public class BowlingGame {
    private static final int MAX_FRAME = 10;
    private static final int ZERO = 0;

    private Frames frames;
    private final Player player;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public void addNextFrame() {
        if (frames.getFrameSize() != ZERO && isCurrentFramePlayable()) {
            throw new IllegalArgumentException("currentFrame is not finish");
        }

        if (frames.getFrameSize() == MAX_FRAME) {
            throw new IllegalArgumentException("frame is maxFrame");
        }
        frames.createNextFrame();
    }

    public void writePoint(int point) {
        Frame frame = findCurrentFrame();
        frame.addPoint(point);
    }

    public boolean isGameOver() {
        if (frames.getFrameSize() == MAX_FRAME) {
            Frame lastFrame = ElementFindUtils.findLastElement(frames.getFrames());
            return !lastFrame.isAvailablePlay();
        }
        return false;
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame findCurrentFrame() {
        return frames.findCurrentFrame();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCurrentFramePlayable() {
        Frame frame = findCurrentFrame();
        return frame.isAvailablePlay();
    }
}

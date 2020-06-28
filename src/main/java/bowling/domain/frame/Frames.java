package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.utils.ElementFindUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frames {
    private static final int MAX_FRAME = 10;
    private static final int ONE = 1;

    private final List<Frame> frames;
    private final Player player;

    public Frames(Player player) {
        this.frames = new ArrayList<>();
        this.player = player;
    }

    public void createNextFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            frames.add(NormalFrame.createFirstFrame());
            return;
        }

        if (frames.size() == MAX_FRAME) {
            throw new IllegalArgumentException("frame is maxFrame");
        }

        NormalFrame normalFrame = (NormalFrame) ElementFindUtils.findLastElement(frames);
        int nextFrameIndex = frames.size();
        if (isLast()) {
            frames.add(normalFrame.createLastFrame(nextFrameIndex));
            return;
        }

        frames.add(normalFrame.createNextFrame(nextFrameIndex));
        return;
    }

    private boolean isLast() {
        return MAX_FRAME - ONE == frames.size();
    }

    public boolean isGameOver() {
        if (frames.size() == MAX_FRAME) {
            Frame lastFrame = frames.get(MAX_FRAME - ONE);
            return !lastFrame.availablePlay();
        }
        return false;
    }

    public int getFrameSize() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Player getPlayer() {
        return player;
    }

    public Frame findCurrentFrame() {
        return ElementFindUtils.findLastElement(frames);
    }
}

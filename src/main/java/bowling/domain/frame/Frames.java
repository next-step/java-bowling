package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.utils.ElementFindUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int MAX_FRAME = 10;
    private static final int ONE = 1;

    private final List<Frame> frames;
    private final Player player;

    public Frames(Player player) {
        this.frames = new ArrayList<>();
        this.player = player;
    }

    public void addPoint(int point) {
        Frame currentFrame = findFrame();
        currentFrame.addPoint(point);
        if (frames.size() > MAX_FRAME) {
            throw new IllegalArgumentException(String.format("limit frame is %d", MAX_FRAME));
        }
    }

    private Frame findFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            return generateNextFrame();
        }

        Frame currentFrame = ElementFindUtils.findLastElement(frames);
        if (!currentFrame.availablePlay()) {
            return generateNextFrame();
        }

        return currentFrame;
    }

    private Frame generateNextFrame() {
        Frame frame = null;
        if (isLast()) {
            frame = new FinalFrame();
            frames.add(frame);
            return frame;
        }

        frame = new NormalFrame();
        frames.add(frame);
        return frame;
    }

    private boolean isLast() {
        return MAX_FRAME - ONE == frames.size();
    }

    public boolean gameOver() {
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

}

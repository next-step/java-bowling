package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_SIZE = 10;
    private final List<Frame> frames;
    private PlayerName playerName;
    private Frame currentFrame;

    private Frames() {
        frames = new ArrayList<>();
        initFrames();
        currentFrame = frames.get(0);
    }

    private Frames(PlayerName playerName) {
        frames = new ArrayList<>();
        this.playerName = playerName;
        initFrames();
        currentFrame = frames.get(0);
    }

    public static Frames init() {
        return new Frames();
    }

    public static Frames init(PlayerName playerName) {
        return new Frames(playerName);
    }

    public void initFrames() {
        Frame frame = NormalFrame.getFirstFrame();
        frames.add(frame);

        while (size() < MAX_FRAME_SIZE) {
            frame = frame.initNextFrame();
            frames.add(frame);
        }
    }

    public int size() {
        return frames.size();
    }

    public boolean isEnd() {
        return frames.get(MAX_FRAME_SIZE - 1).isEnd();
    }

    public void setKnockDownPins(int knockDownPins) {
        currentFrame.setKnockDownPins(knockDownPins);

        if (currentFrame.isEnd() && isNotLastFrame()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    private boolean isNotLastFrame() {
        return currentFrame.getIndex() != MAX_FRAME_SIZE;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public int getCurrentFrameIndex() {
        return currentFrame.getIndex();
    }
}

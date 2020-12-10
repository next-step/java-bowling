package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    public static final int MAX_FRAME_SIZE = 10;
    private final List<Frame> frames;
    private String playerName;
    private Frame currentFrame;

    private BowlingGame() {
        frames = new ArrayList<>();
        initFrames();
        currentFrame = frames.get(0);
    }

    private BowlingGame(String playerName) {
        frames = new ArrayList<>();
        this.playerName = playerName;
        initFrames();
        currentFrame = frames.get(0);
    }

    public static BowlingGame init() {
        return new BowlingGame();
    }

    public static BowlingGame init(String playerName) {
        return new BowlingGame(playerName);
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

        if (currentFrame.isEnd()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCurrentFrameIndex() {
        return currentFrame.getIndex();
    }
}

package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame;

    public Player(String playerName) {
        this.playerName = new PlayerName(playerName);
    }

    public String getName() {
        return playerName.getValue();
    }

    public int getFrameIndex() {
        return frames.size();
    }

    public Frame getFrame(int frameIndex) {
        return frames.get(frameIndex);
    }

    public Frame createEmptyFrame() {
        Frame frame = new Frame();
        frames.add(frame);
        return frame;
    }

    public Frame firstBall(int hitNumberOfPin) {
        currentFrame = frames.get(frames.size() - 1);
        Frame nextFrame = currentFrame.nextFrame(hitNumberOfPin);
        frames.add(nextFrame);
        return nextFrame;
    }

    public FinalFrame finalFrame(int hitNumberOfPin) {
        currentFrame = frames.get(frames.size() - 1);
        FinalFrame finalFrame = currentFrame.finalFrame(hitNumberOfPin);
        frames.add(finalFrame);
        return finalFrame;
    }
}

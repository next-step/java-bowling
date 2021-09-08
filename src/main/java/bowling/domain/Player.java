package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private List<Frame> frames = new ArrayList<>();

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

    public void firstBall(int firstBall) {
        frames.add(new Frame(firstBall));
    }

    public void secondBall(int secondBall) {
        Frame currentFrame = frames.get(frames.size() - 1);
        currentFrame.secondBall(secondBall);
    }
}

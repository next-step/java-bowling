package bowling;


import bowling.domain.Frame;
import bowling.domain.IllegalBallThrownException;
import bowling.domain.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final List<Frame> frames = new ArrayList<>();
    private final String playerName;

    public GameService(String playerName) {
        if (playerName.length() > 3) {
            throw new IllegalPlayerNameException();
        }
        this.playerName = playerName;
    }

    public boolean isFinish() {
        return getCurrentFrame().isFinish();
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getNumber();
    }

    public GameStatus throwBall(int fallingPins) {
        Frame currentFrame = getCurrentFrame();
        if (currentFrame.isFinish()) {
            throw new IllegalBallThrownException();
        }
        Frame frame = currentFrame.throwBall(fallingPins);
        updateFrames(frame);
        return new GameStatus(this);
    }

    public GameStatus start() {
        frames.add(new NormalFrame());
        return new GameStatus(this);
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private void updateFrames(Frame frame) {
        if (getCurrentFrame() != frame) {
            frames.add(frame);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public Frame getFirstFrame() {
        return getCurrentFrame();
    }
}

package bowling;


import bowling.domain.Frame;
import bowling.domain.IllegalBallThrownException;
import bowling.domain.NormalFrame;
import bowling.view.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frames {
    private final List<Frame> frames = new ArrayList<>();
    private final String playerName;

    public Frames(String playerName) {
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

    public Optional<Frame> getFrame(int i) {
        if (frames.size() < i) {
            return Optional.empty();
        }
        return Optional.of(frames.get(i - 1));
    }
}

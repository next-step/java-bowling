package domain.game;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.frame.NormalFrame;
import domain.pin.Pin;
import domain.player.PlayerName;

import java.util.List;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class BowlingGame {
    private final PlayerName playerName;
    private Frames frames = new Frames();

    public BowlingGame(PlayerName playerName) {
        this.playerName = playerName;
    }

    public BowlingGame(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    public int getFramesSize() {
        return frames.size();
    }

    public Frame getFrame(int i) {
        return frames.get(i);
    }

    public Frame getRecentFrame() {
        return (frames.isEmpty()) ? null : frames.get(frames.size() - 1);
    }

    public int getNextFrameNumber() {
        return frames.getNextFrameNumber();
    }

    public void play(int pin) {
        Frame currentFrame = null;

        if (frames.isEmpty()) {
            currentFrame = new NormalFrame(START_FRAME, Pin.of(pin), null);
        }

        if (!frames.isEmpty()) {
            currentFrame = frames.getRecentFrame().bowl(Pin.of(pin));
        }

        if (!frames.contains(currentFrame)) {
            frames.add(currentFrame);
        }
    }

    public boolean isContinuable() {
        if (frames.isEmpty()) {
            return true;
        }

        return frames.getNextFrameNumber() <= LAST_FRAME || !frames.getRecentFrame().isFinished();
    }

    public List<String> getStatusStrings() {
        return frames.getStatusStrings();
    }

    public List<String> getScoreStrings() {
        return frames.getScoreStrings();
    }
}
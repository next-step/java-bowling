package bowling.domain;

import bowling.domain.frame.Pitch;

import java.util.List;
import java.util.Optional;

public class Game {


    private Player player;
    private Frames frames;

    public Game(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public void addPin(int count) {
        if (isFinished()) {
            return;
        }
        frames.addPinCount(count);
        return;
    }

    public List<Pitch> getFramePinCounts(int frameIndex) {
        return frames.getFramePinCounts(frameIndex);
    }

    public int getCurrentFrame() {
        return frames.size();
    }

    public Optional<Integer> getFrameScore(int frameIndex) {
        return frames.getFrameScore(frameIndex);
    }

    public int getFrameTotal() {
        return frames.size();
    }

    public String getPlayerName() {
        return player.getName();
    }
}

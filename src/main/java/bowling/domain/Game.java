package bowling.domain;

import bowling.domain.dto.GameStatus;

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
    }

    public int getCurrentFrame() {
        return frames.size();
    }

    public GameStatus getGameStatus() {
        return new GameStatus(frames, player.getName());
    }
}

package bowling.domain;

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

    public String getPlayerName() {
        return player.getName();
    }

    public Frames getFrames() {
        return new Frames(frames);
    }
}

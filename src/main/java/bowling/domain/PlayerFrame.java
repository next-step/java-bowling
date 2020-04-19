package bowling.domain;

public class PlayerFrame {
    private Player player;
    private Frames frames;

    public PlayerFrame(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public boolean addPinCount(int count) {
        return frames.addPinCount(count);
    }

    public boolean isCurrentFrameDone() {
        return frames.isLastFrameDone();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Frames getFrames() {
        return new Frames(frames);
    }

    public boolean isFinished() {
        return frames.isFinished();
    }
}

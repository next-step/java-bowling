package bowling.domain;

public class Player {
    private final Name playerName;
    private Frames frames;

    public Player(final String playerName) {
        this.playerName = new Name(playerName);
        frames = new Frames();
    }

    public boolean isFinish() {
        return frames.isEndSize();
    }

    public String getName() {
        return playerName.getName();
    }
}

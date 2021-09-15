package bowling.domain;

public class Player {

    private final PlayerName playerName;
    private final Frames frames;

    public Player(String playerName) {
        this.playerName = new PlayerName(playerName);
        this.frames = new Frames();
    }

    public void bowl(FrameNumber frameNumber, PinCount fallenPinCount) {
        frames.bowl(frameNumber, fallenPinCount);
    }

    public Renderer toRenderer() {
        return new PlayerRenderer(playerName, frames);
    }
}

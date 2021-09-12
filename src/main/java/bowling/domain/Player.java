package bowling.domain;

import bowling.PlayerRenderer;

public class Player {

    private final PlayerName playerName;
    private final Frames frames;

    public Player(String playerName) {
        this.playerName = new PlayerName(playerName);
        this.frames = new Frames();
    }

    public FrameNumber bowl(FrameNumber frameNumber, PinCount fallenPinCount) {
        return frames.bowl(frameNumber, fallenPinCount);
    }

    public PlayerRenderer toRenderer() {
        return new PlayerRenderer(playerName, frames);
    }
}

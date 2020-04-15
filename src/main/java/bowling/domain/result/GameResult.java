package bowling.domain.result;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frames;

import java.util.List;

public class GameResult {
    private final PlayerName playerName;
    private final Frames frames;

    public GameResult(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Frames getFrames() {
        return frames;
    }
}
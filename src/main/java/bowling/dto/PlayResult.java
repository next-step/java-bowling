package bowling.dto;

import bowling.domain.Frames;
import bowling.domain.PlayerName;

public class PlayResult {

    private String playerName;

    private FramesResult framesResult;

    public PlayResult(PlayerName playerName, Frames frames) {
        this.playerName = playerName.name();
        this.framesResult = frames.result();
    }

    public String playerName() {
        return playerName;
    }

    public FramesResult framesResult() {
        return framesResult;
    }

}

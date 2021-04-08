package bowling.dto;

import bowling.domain.frame.Frames;
import bowling.domain.PlayerName;

public class PlayResult {

    private String playerName;

    private FrameResults frameResults;

    public PlayResult(PlayerName playerName, Frames frames) {
        this.playerName = playerName.name();
        this.frameResults = frames.result();
    }

    public String playerName() {
        return playerName;
    }

    public FrameResults framesResult() {
        return frameResults;
    }

}

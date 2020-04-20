package bowling.domain.result;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class GameResult {
    private static final int OFFSET = -1;

    private final PlayerName playerName;
    private final Frames frames;

    public GameResult(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static GameResult of(PlayerName playerName, Frames frames) {
        return new GameResult(playerName, frames);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public String getName() {
        return playerName.getName();
    }

    public Frame getFrameByFrameId(int frameId) {
        return frames.getFrames().get(frameId + OFFSET);
    }

    public int getScoreUntilFrame(int frameId) {
        return frames.getTotalPointUntil(frameId);
    }
}
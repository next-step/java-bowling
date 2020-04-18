package bowling.domain.result;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames;

import static bowling.domain.frame.FrameResult.SPARE;
import static bowling.domain.frame.FrameResult.STRIKE;

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

    public Frames getFrames() {
        return frames;
    }

    public Frame getFrameByFrameId(int frameId) {
        return frames.getFrames().get(frameId + OFFSET);
    }

    public boolean isPrevFrameStrike(int frameId){
        if(frameId == 1){
            return false;
        }

        return frames.getPreviousFrame(getFrameByFrameId(frameId))
                .isResult(STRIKE);
    }

    public boolean isPrevFrameSpare(int frameId){
        if(frameId == 1){
            return false;
        }

        return frames.getPreviousFrame(getFrameByFrameId(frameId))
                .isResult(SPARE);
    }

    public int getScoreUntilFrame(int frameId){
        return frames.getTotalPointUntil(frames.getFrames().get(frameId - 1));
    }
}
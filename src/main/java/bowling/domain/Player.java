package bowling.domain;

import bowling.domain.engine.frame.FrameNumber;
import bowling.domain.engine.frame.Frames;
import bowling.dto.PlayerDto;

public class Player {

    private final PlayerName playerName;
    private final Frames frames;

    private Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static Player initialize(String name) {
        return new Player(PlayerName.wrap(name), Frames.init());
    }

    public void roll(RollResult rollResult) {
        frames.roll(rollResult);
    }

    public boolean isPlayedFrameOf(FrameNumber frameNumber) {
        return frames.isFinishedFrame(frameNumber);
    }

    public PlayerDto export() {
        return PlayerDto.of(playerName, frames);
    }

    public String exportPlayerName() {
        return playerName.export();
    }
}

package bowling.domain;

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

    public PlayerDto throwBall(int numberOfPins) {
        frames.roll(RollResult.of(numberOfPins));

        return exportFrameState();
    }

    public boolean checkPlaying() {
        return !frames.isAllFrameEnded();
    }

    public int getNextFrameNumber() {
        return frames.getNextFrameNumber();
    }

    public PlayerDto exportFrameState() {
        return PlayerDto.of(playerName, frames);
    }
}

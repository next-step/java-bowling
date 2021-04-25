package bowling.domain;

import bowling.domain.engine.frame.Frames;
import bowling.domain.engine.roll.Roll;
import bowling.dto.PlayerDto;

public class Player {

    private final PlayerName playerName;
    private final Frames frames;

    private Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static Player initialize(String name) {
        return new Player(PlayerName.wrap(name), new Frames());
    }

    public PlayerDto throwBall(int numberOfPins) {
        frames.roll(Roll.result(numberOfPins));

        return PlayerDto.of(playerName, frames);
    }

    public boolean checkPlaying() {
        return !frames.isAllFrameEnded();
    }

    public int getNextFrameNumber() {
        return frames.getNextFrameNumber();
    }
}

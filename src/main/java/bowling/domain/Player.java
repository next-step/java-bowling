package bowling.domain;

import bowling.domain.engine.Bowling;
import bowling.domain.engine.PitchResult;
import bowling.dto.PlayerDto;

public class Player {

    private final PlayerName playerName;
    private final Bowling bowling;

    private Player(PlayerName playerName, Bowling bowling) {
        this.playerName = playerName;
        this.bowling = bowling;
    }

    public static Player initialize(String name) {
        return new Player(PlayerName.wrap(name), new Bowling());
    }

    public PlayerDto throwBall(int numberOfPins) {
        bowling.throwBall(PitchResult.wrap(numberOfPins));

        return PlayerDto.of(playerName, bowling);
    }

    public boolean checkPlaying() {
        return !bowling.isEnded();
    }

    public int getNextFrameNumber() {
        return bowling.getNextFrameNumber();
    }
}

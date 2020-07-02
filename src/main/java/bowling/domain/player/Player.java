package bowling.domain.player;

import bowling.domain.FallenPinNumber;
import bowling.view.BowlingView;

import java.util.Objects;

public class Player {

    private PlayerName playerName;

    private Player(PlayerName playerName) {
        this.playerName = playerName;
    }

    public static Player of(PlayerName playerName) {
        return new Player(playerName);
    }

    public FallenPinNumber pitchBowlingBall() {
        int fallenPins = BowlingView.inputFallenPinNumber();
        return FallenPinNumber.of(fallenPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return playerName.toString();
    }
}

package bowling.dto;

import bowling.domain.pin.FallenPins;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

public class BowlingRequest {
    private final int fallenPin;
    private final String playerName;

    public BowlingRequest(final int fallenPin, final String playerName) {
        this.fallenPin = fallenPin;
        this.playerName = playerName;
    }

    public Pins toPins() {
        return new FallenPins(fallenPin);
    }

    public Player toPlayer() {
        return new Player(playerName);
    }
}

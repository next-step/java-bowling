package bowling.domain.scoreboard;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerGameIndex;

import java.util.Collection;

public class RotationPlayerOrderStrategy implements PlayerOrderStrategy {
    private int playerCounter;

    public RotationPlayerOrderStrategy() {
        playerCounter = 0;
    }

    @Override
    public Player currentPlayer(Collection<Player> players) {
        PlayerGameIndex playerIndex = currentPlayerIndex(players.size());

        //noinspection OptionalGetWithoutIsPresent
        return players.stream()
                .filter(iPlayer -> iPlayer.matchesOrder(
                        playerIndex
                ))
                .findFirst().get();
    }

    @Override
    public void checkout() {
        ++playerCounter;
    }

    private PlayerGameIndex currentPlayerIndex(int playerSize) {
        return new PlayerGameIndex(playerCounter % playerSize);
    }
}

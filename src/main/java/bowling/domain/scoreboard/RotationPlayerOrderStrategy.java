package bowling.domain.scoreboard;

import bowling.domain.player.Player;

import java.util.Collection;

public class RotationPlayerOrderStrategy implements PlayerOrderStrategy {
    private int playerCounter;

    public RotationPlayerOrderStrategy() {
        playerCounter = 0;
    }

    @Override
    public Player currentPlayer(Collection<Player> players) {
        int playerIndex = currentPlayerIndex(players.size());

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

    private int currentPlayerIndex(int playerSize) {
        return playerCounter % playerSize;
    }
}

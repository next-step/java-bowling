package bowling.domain.scoreboard;

import bowling.domain.player.Player;

import java.util.Collection;

public interface PlayerOrderStrategy {
    Player currentPlayer(Collection<Player> players);

    void checkout();
}

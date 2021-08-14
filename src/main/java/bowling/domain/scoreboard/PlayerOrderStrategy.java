package bowling.domain.scoreboard;

import bowling.domain.player.Player;

public interface PlayerOrderStrategy {
    Player currentPlayer();

    void checkout();
}

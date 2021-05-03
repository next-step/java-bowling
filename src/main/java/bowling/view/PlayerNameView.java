package bowling.view;

import bowling.domain.player.Player;
import bowling.view.ui.Cell;

public final class PlayerNameView {

    private final String playerName;

    private PlayerNameView(Player player) {
        this(player.playerName());
    }

    public PlayerNameView(String playerName) {
        this.playerName = playerName;
    }

    public static PlayerNameView from(Player player) {
        return new PlayerNameView(player);
    }

    public String playerName() {
        return playerName;
    }

    public Cell cell() {
        return Cell.right(playerName);
    }
}

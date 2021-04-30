package bowling.view;

import bowling.domain.player.Player;

public final class PlayerNameView {

    private final String playerName;

    public PlayerNameView(Player player) {
        this(player.playerName());
    }

    public PlayerNameView(String playerName) {
        this.playerName = playerName;
    }

    public String playerName() {
        return playerName;
    }
}

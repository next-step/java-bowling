package bowling.dto;

import bowling.domain.Player;

public class PlayerDto {

    private final String playerName;
    private final boolean isCurrent;

    private PlayerDto(String playerName) {
        this.playerName = playerName;
        this.isCurrent = false;
    }

    private PlayerDto(Player player, Player currentPlayer) {
        this.playerName = player.toString();
        this.isCurrent = player.equals(currentPlayer);
    }

    public static PlayerDto of(Player player) {
        return new PlayerDto(player.name());
    }

    public static PlayerDto of(Player player, Player currentPlayer) {
        return new PlayerDto(player, currentPlayer);
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCurrentPlayer() {
        return isCurrent;
    }
}

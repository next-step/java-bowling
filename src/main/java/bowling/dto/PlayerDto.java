package bowling.dto;

import bowling.domain.Player;

public class PlayerDto {

    private final String PlayerName;

    public PlayerDto(String playerName) {
        PlayerName = playerName;
    }

    public static PlayerDto of(Player player) {
        return new PlayerDto(player.name());
    }

    public String getPlayerName() {
        return PlayerName;
    }
}

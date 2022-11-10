package bowling.dto;

import bowling.domain.Player;

public class PlayerDto {

    private final String name;

    public PlayerDto(String name) {
        this.name = name;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName());
    }

    public String getName() {
        return name;
    }

}

package bowling.dto;

import bowling.domain.Player;

public class PlayerDto {

    private final String name;
    private final BowlingGameDto game;

    public PlayerDto(String name, BowlingGameDto game) {
        this.name = name;
        this.game = game;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), BowlingGameDto.from(player.getGame()));
    }

    public String getName() {
        return name;
    }

    public BowlingGameDto getGame() {
        return game;
    }

}
